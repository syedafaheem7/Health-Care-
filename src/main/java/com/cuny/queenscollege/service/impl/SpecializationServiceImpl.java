package com.cuny.queenscollege.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import com.cuny.queenscollege.entity.Specialization;
import com.cuny.queenscollege.exception.SpecializationNotFoundException;
import com.cuny.queenscollege.repo.SpecializationRepository;
import com.cuny.queenscollege.service.ISpecializationService;
import com.cuny.queenscollege.util.MyCollectionsUtil;

@Service
public class SpecializationServiceImpl implements ISpecializationService{

	@Autowired
	private SpecializationRepository repo;
	
	
	public Long saveSpecialization(Specialization spec) {
		return repo.save(spec).getId();
	}

	public List<Specialization> getAllSpecializations() {
		return repo.findAll();
	}

	public void removeSpecialization(Long Id) {
		//repo.deleteById(id);
		repo.delete(getOneSpecialization(Id));
	}

	public Specialization getOneSpecialization(Long Id) {
		/*
		 * Optional <Specialization> optional = repo.findById(id);
		 * if(optional.isPresent()) { return optional.get(); }else { return null; }
		 */
		//using funtional interface and lamda functions
		return repo.findById(Id).orElseThrow(
		()-> new SpecializationNotFoundException(Id+ " Not Found")
		);
	}
	
	public void updateSpecialization(Specialization spec) {
		repo.save(spec);
	}

	@Override
	public boolean isSpecCodeExist(String specCode) {
		/*Integer count = repo.getSpecCodeCount(specCode);
		boolean exist = count>0 ? true : false;
		return exist;*/
		return repo.getSpecCodeCount(specCode)>0;
		
	}

	@Override
	public boolean isSpecCodeExistForEdit(String specCode, Long Id) {
		return repo.getSpecCodeCountForEdit(specCode,Id)>0;
		
	}
     // get id and name from query store in list
	  // convert list to map
	@Override
	public Map<Long, String> getSpecIdAndName() {
		// TODO Auto-generated method stub
		List<Object[]> list = repo.getSpecIdAndName();
		Map<Long, String> map= MyCollectionsUtil.convertToMap(list);
		return map;
	}

	@Override
	public Long getSpecializationCount() {
		return repo.count();
	}

	@Override
	public Page<Specialization> getAllSpecializations(Pageable pageable) {
		return repo.findAll(pageable);
	}

	
}
