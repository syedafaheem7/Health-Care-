package com.cuny.queenscollege.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cuny.queenscollege.entity.Doctor;
import com.cuny.queenscollege.exception.DoctorNotFoundException;
import com.cuny.queenscollege.service.IDoctorService;
import com.cuny.queenscollege.service.ISpecializationService;
import com.cuny.queenscollege.util.MyMailUtil;



@Controller
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private MyMailUtil mailUtil;
	
	@Autowired
	private IDoctorService service;
	
	@Autowired
	private ISpecializationService specializationService;

	//create one common method for dynamic data from spec
	//use this method in register and edit page
	
	private void createDynamicUi(Model model) {
		model.addAttribute("specializations", specializationService.getSpecIdAndName());
	}
	/*
	 * 1.show Register page
	 */
	@GetMapping("/register")
	public String showReg(
			@RequestParam(value = "message", required = false) String message, 
			Model model) {
		
		model.addAttribute("message", message);
		createDynamicUi(model);
		return "DoctorRegister";
	}

	/*
	 * // 2.save data on submit
	 * 
	 * @PostMapping("/save") public String save(@ModelAttribute Doctor doctor,
	 * RedirectAttributes attributes) {
	 * 
	 * Long id= service.saveDoctor(doctor); attributes.addAttribute("message",
	 * "Doctor("+id+" is created "); return "redirect:register"; }
	 */
	@PostMapping("/save")
	public String saveForm(@ModelAttribute Doctor doctor,
			Model model) {

		Long id = service.saveDoctor(doctor);
		String message = "Doctor (" + id + ") is created";
		model.addAttribute("message", message);
		//use thread for mail creation using java 8
		if (id!= null) {
			new Thread(new Runnable() {

				public void run() {
					mailUtil.send(doctor.getEmail(), 
							"success", 
							message, 
							new ClassPathResource("/static/myres/sample.pdf"));
				}
			}).start();
		}
		return "DoctorRegister";
	}

	// 3.display all data
	@GetMapping("/all")
	public String Display(
			@RequestParam(value="message",required=false)String message, 
			Model model) {
		List<Doctor> list =service.getAllDoctors();
		model.addAttribute("list",list);
		model.addAttribute("message",message);
		return "DoctorData";
	}

	// 4.delete by id
	@GetMapping("/delete")
	public String delete(@RequestParam("id")Long id,
			RedirectAttributes attributes) {
		
		String message = null;
		try {
			service.removeDoctor(id);
			message="doctor removed";
			
			
		}catch(DoctorNotFoundException e){
			e.printStackTrace();
			message= e.getMessage();
		}
		attributes.addAttribute("message", message);
		return "redirect:all";
		
	}

//	5.show edit page
	@GetMapping("/edit")
	public String editpage(@RequestParam("id")Long id, 
			Model model,
			RedirectAttributes attributes)
	{
		
		String page= null;
		try {
			Doctor doc=service.getOneDoctor(id);
			model.addAttribute("doctor", doc);
			createDynamicUi(model);
			page="DoctorEdit";
			
		} catch (DoctorNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			page= "redirect:all";
			
		}
		return page;
		
	}
//	6.update data
	@PostMapping("/update")
	public String doUpdate(@ModelAttribute Doctor doctor,
			RedirectAttributes attributes) 
	{
		
		service.updateDoctor(doctor);
		attributes.addAttribute("message", doctor.getId()+", updated");
		return "redirect:all";
		
	}
//	7.email and mobile ajax validations
//	8.excel

}
