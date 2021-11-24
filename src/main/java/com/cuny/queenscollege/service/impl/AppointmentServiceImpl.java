package com.cuny.queenscollege.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cuny.queenscollege.entity.Appointment;
import com.cuny.queenscollege.entity.SlotRequest;
import com.cuny.queenscollege.repo.AppointmentRepository;
import com.cuny.queenscollege.service.IAppointmentService;
@Service
public class AppointmentServiceImpl implements IAppointmentService {

	@Autowired
	private AppointmentRepository repo;
	
	
	@Transactional
	public Long saveAppointment(Appointment appointment) 
	{
		return repo.save(appointment).getId();
	}

	@Transactional
	public void updateAppointment(Appointment appointment) 
	{
		repo.save(appointment);
	}

	@Transactional
	public void deleteAppointment(Long id) 
	{
		repo.deleteById(id);
	}

	@Transactional(readOnly= true)
	public Appointment getOneAppointment(Long id) {
		return repo.findById(id).get();
	}

	@Transactional(readOnly=true)
	public List<Appointment> getAllAppointments() {
		
		return repo.findAll();
	}

	@Override
	public List<Object[]> getAppoinmentsByDoctor(Long docId) {
		
		return repo.getAppoinmentsByDoctor(docId);
	}

	@Override
	public Long getAppointmentCount() {
		return repo.count();
	}

	@Transactional
	public void updateSlotCountForAppoinment(Long id, int count) {
		repo.updateSlotCountForAppoinment(id, count);
		
	}

	@Override
	public List<Object[]> getAppoinmentsByDoctorEmail(String userName) {
		return repo.getAppoinmentsByDoctorEmail(userName);
	}

	@Override
	public SlotRequest getOneSlotRequest(Long id) {
		
		return null;
	}

}
