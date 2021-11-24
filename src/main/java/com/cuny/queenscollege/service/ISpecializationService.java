package com.cuny.queenscollege.service;



import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.cuny.queenscollege.entity.Specialization;

public interface ISpecializationService {
	
	public Long saveSpecialization(Specialization spec);
	public List <Specialization> getAllSpecializations();
	public void removeSpecialization(Long Id);
	public Specialization getOneSpecialization(Long Id);
	public void updateSpecialization(Specialization spec);
	
	public boolean isSpecCodeExist(String specCode);
	public boolean isSpecCodeExistForEdit(String specCode,Long Id);
	
	Map<Long,String> getSpecIdAndName();
	//for slot controller
	public Long getSpecializationCount();
	
	public Page<Specialization> getAllSpecializations(Pageable pageable);
	
}
