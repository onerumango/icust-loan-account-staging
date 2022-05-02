package com.rumango.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rumango.model.IcustCardDocumentsModel;

@Service
public interface IcustCardDocumentsService {

	ResponseEntity<?> uploadCardDocs(MultipartFile file, IcustCardDocumentsModel model);

	ResponseEntity<?> fetchCardDocuments(Long cardId, Integer screenType);

	Resource loadAsResource(String fileName);
}
