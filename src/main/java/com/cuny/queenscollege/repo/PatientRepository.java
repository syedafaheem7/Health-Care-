package com.cuny.queenscollege.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cuny.queenscollege.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

	Optional<Patient> findByEmail(String email);

}
