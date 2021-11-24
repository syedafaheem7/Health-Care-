package com.cuny.queenscollege.service;

import java.util.List;
import java.util.Map;

import com.cuny.queenscollege.entity.Doctor;


public interface IDoctorService {
	
	public Long saveDoctor(Doctor doc);
	public List <Doctor> getAllDoctors();
	public void removeDoctor(Long id);
	public Doctor getOneDoctor(Long id);
	public void updateDoctor(Doctor doc);
	public Map<Long, String> getDoctorIdAndNames();
	//view slot in appointment
	public List<Doctor> findDoctorBySpecName(Long specId);
	public Long getDoctorCount();
}
