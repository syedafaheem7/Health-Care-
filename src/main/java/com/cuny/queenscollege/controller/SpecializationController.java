package com.cuny.queenscollege.controller;


import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cuny.queenscollege.entity.Specialization;
import com.cuny.queenscollege.exception.SpecializationNotFoundException;
import com.cuny.queenscollege.service.ISpecializationService;
import com.cuny.queenscollege.view.SpecializationExcelView;
import com.cuny.queenscollege.view.SpecializationPdfView;

@Controller
@RequestMapping("/spec")
public class SpecializationController {

	@Autowired
	private ISpecializationService service;

	/***
	 * 1. show Register Page //
	 */
	@GetMapping("/register")
	public String displayRegister() {
		return "SpecializationRegister";

	}

	/***
	 * 2. Submit form data and save
	 */
	@PostMapping("/save")
	public String saveForm(@ModelAttribute Specialization specialization,
			Model model) {

		Long id = service.saveSpecialization(specialization);
		String message = "Record(" + id + ") is created";
		model.addAttribute("message", message);
		return "SpecializationRegister";

	}

	/**
	 * 3.display all Specialization
	 */

	@GetMapping("/all")
	public String viewAll(Model model, 
			@RequestParam(value = "message", required = false) 
	         String message) 
	{
		List<Specialization> list = service.getAllSpecializations();
		model.addAttribute("list", list);
		model.addAttribute("message", message);
		return "SpecializationData";
	}
	
	/*
	 * //... /all?page=3
	 * 
	 * @GetMapping("/all") public String viewAllPageable(
	 * 
	 * @PageableDefault(page = 0, size = 3) Pageable pageable, Model model,
	 * 
	 * @RequestParam(value = "message",required = false) String message ) {
	 * 
	 * Page<Specialization> page = service.getAllSpecializations(pageable);
	 * model.addAttribute("list",page.getContent());
	 * model.addAttribute("page",page); model.addAttribute("message", message);
	 * return "SpecializationData"; }
	 */


	/**
	 * 4. Delete by Id
	 */
	@GetMapping("/delete")
	public String deleteData(@RequestParam Long id,
			RedirectAttributes attributes) 
	{
		try {
			
		
		service.removeSpecialization(id);
		attributes.addAttribute("message", "Record (" + id + " ) is removed");
		}catch(SpecializationNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
		}
		return "redirect:all";

	}

	/**
	 * 
	 * 5.Fetch data into Edit Page
	 * enduser click on link, may enter ID manually
	 * If entered id is present in DB
	 *     >load row as object 
	 *     >send to edit page
	 *     >fill in Form
	 *  Else
	 *     >redirect to all (data page)
	 *     >show error message    
	 */
	@GetMapping("/edit")
	public String showEditPage(@RequestParam Long id, 
			Model model,
			RedirectAttributes attributes) 
	{
       String page = null;
       try {
		Specialization spec = service.getOneSpecialization(id);
		model.addAttribute("specialization", spec);
		 page=  "SpecializationEdit";
       }catch(SpecializationNotFoundException e){
    	   e.printStackTrace();
    	   attributes.addAttribute("message",e.getMessage());
    	   page="redirect:all";
    	   
       }
       return page;
	}

	/**
	 * 6.Update form data and redirect to all
	 * 
	 */
	@PostMapping("/update")
	public String updateDate(@ModelAttribute Specialization specialization,
			RedirectAttributes attributes) {

		service.updateSpecialization(specialization);
		attributes.addAttribute("message", "Record (" + specialization.getId() + ") is updated");

		return "redirect:all";

	}

	/**
	 * 7. Read code and check with service Return message back to UI
	 */
	@GetMapping("/checkCode")
	@ResponseBody
	public String validateSpecCode(@RequestParam String code, 
			@RequestParam Long id) 
	{
		String message = "";
		if (id == 0 && service.isSpecCodeExist(code)) { // register check
			message = code + ", already exist";
		} else if (id != 0 && service.isSpecCodeExistForEdit(code, id)) { // edit check
			message = code + ", already exist";
		}

		return message; // this is not viewName(it is message)
	}
	
	/**
	 * 8.export data to excel file
	 */
	@GetMapping("/excel")
	public ModelAndView exportToExcel() {
		ModelAndView m = new ModelAndView();
		m.setView(new SpecializationExcelView());
		
		//read data from db
		List<Specialization > list = service.getAllSpecializations();
		//send to excel impl class
		m.addObject("list",list);
		return m;
	}
	//export pdf file
	
	@GetMapping("/pdf")
	public ModelAndView exportToPdf() {
			ModelAndView m = new ModelAndView();
		m.setView(new SpecializationPdfView());
		
		//read data from DB
		List<Specialization> list = service.getAllSpecializations();
		//send to Excel Impl class
		m.addObject("list", list);

		return m;
	}
 
}
