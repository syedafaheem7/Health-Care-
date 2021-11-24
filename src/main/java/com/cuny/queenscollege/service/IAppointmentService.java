package com.cuny.queenscollege.service;

import java.util.List;


import com.cuny.queenscollege.entity.Appointment;
import com.cuny.queenscollege.entity.SlotRequest;

public interface IAppointmentService {
	Long saveAppointment(Appointment appointment);
	void updateAppointment(Appointment appointment);
	void deleteAppointment(Long id);
	public Appointment getOneAppointment(Long id);
	public List<Appointment> getAllAppointments();
	//booking slot
	public List<Object[]> getAppoinmentsByDoctor(Long docId);
	public List<Object[]> getAppoinmentsByDoctorEmail(String userName);
	public Long  getAppointmentCount();
	public void updateSlotCountForAppoinment(Long id, int count);
	
	SlotRequest getOneSlotRequest(Long id);
	
}
