package com.cuny.queenscollege.service;

import java.util.List;

import com.cuny.queenscollege.entity.Document;

public interface IDocumentService {
	void saveDocument(Document doc);
	List<Object[]> getDocumentIdAndName();
	void deleteDocumentById(Long id);
	Document getDocumentById(Long id);

}
