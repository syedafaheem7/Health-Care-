package com.cuny.queenscollege.service;

import java.util.List;

import com.cuny.queenscollege.entity.Patient;

public interface IPatientService {
	public Long savePatient(Patient patient);
	public List <Patient> getAllPatients();
	public void deletePatient(Long id);
	public Patient getOnePatient(Long id);
	public void updatePatient(Patient pat);
	public Long getPatientCount();
	public Patient getOneByEmail(String email);
	

}
