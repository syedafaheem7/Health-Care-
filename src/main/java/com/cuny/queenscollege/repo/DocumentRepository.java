package com.cuny.queenscollege.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cuny.queenscollege.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

	
	@Query("SELECT docId,docName FROM Document")
	List<Object[]> getDocumentIdAndName();
}
