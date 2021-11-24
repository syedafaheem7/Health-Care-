package com.cuny.queenscollege.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuny.queenscollege.constants.UserRoles;
import com.cuny.queenscollege.entity.Patient;
import com.cuny.queenscollege.entity.User;
import com.cuny.queenscollege.exception.PatientNotFoundException;
import com.cuny.queenscollege.repo.PatientRepository;
import com.cuny.queenscollege.service.IPatientService;
import com.cuny.queenscollege.service.IUserService;
import com.cuny.queenscollege.util.MyMailUtil;
import com.cuny.queenscollege.util.UserUtil;

import org.springframework.transaction.annotation.Transactional;
@Service
public class PatientServiceImpl implements IPatientService {

	@Autowired
	private PatientRepository repo;
	
	@Autowired
	private IUserService userService;
	@Autowired
	private UserUtil util;

	@Autowired
	private MyMailUtil mailUtil ;
	@Override
	@Transactional
	public Long savePatient(Patient patient) {
		Long id = repo.save(patient).getId();
		if(id!=null) {
			String pwd = util.genPwd();
			User user = new User();
			user.setDisplayName(patient.getFirstName()+" "+patient.getLastName());
			user.setUserName(patient.getEmail());
			user.setPassword(pwd);
			user.setRole(UserRoles.PATIENT.name());
			Long genId  = userService.saveUser(user);
			if(genId!=null)
				new Thread(new Runnable() {
					public void run() {
						String text = "Your name is " + patient.getEmail() +", password is "+ pwd;
						mailUtil.send(patient.getEmail(), "PATIENT ADDED", text);
					}
				}).start();
		}
		return id;
	}

	@Override
	@Transactional
	public void updatePatient(Patient patient) {
		repo.save(patient);
	}

	@Override
	@Transactional
	public void deletePatient(Long id) {
		repo.deleteById(id);
	}

	@Override
	@Transactional(
			readOnly = true
			)
	public Patient getOnePatient(Long id) {
		return repo.findById(id).get();
	}

	@Override
	@Transactional(
			readOnly = true
			)
	public List<Patient> getAllPatients() {
		return repo.findAll();
	}
	
	@Override
	public Patient getOneByEmail(String email) {
		return repo.findByEmail(email).get();
	}
	
	@Override
	public Long getPatientCount() {
		return repo.count();
	}
}
