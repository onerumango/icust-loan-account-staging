package com.rumango.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.rumango.entity.IcustLoanDocuments;
import com.rumango.exception.InternalServerException;
import com.rumango.exception.StorageException;
import com.rumango.model.IcustLoanDocumentsModel;
import com.rumango.repository.IcustLoanDocumentsRepo;
import com.rumango.service.LoanDocumentUploadService;

@Service
public class LoanDocumentUploadServiceImpl implements LoanDocumentUploadService {
	private static final Logger logger = Logger.getLogger(LoanDocumentUploadServiceImpl.class);

	private String uploadLocation = "./fileUploads";
	
	@Autowired
	IcustLoanDocumentsRepo icustLoanDocumentsRepo;
	
	
	@Override
	public ResponseEntity<?> uploadLoanDocs(MultipartFile file, IcustLoanDocumentsModel data) {
		String docDownloadPath = null;
		IcustLoanDocumentsModel documentModel = null;
		try {
			if (!file.isEmpty()) {
				String fileName = data.getLoanAccountId() + "_" + data.getDocumentName() + "_"
						+ String.valueOf(
								new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH).format(new java.util.Date()))
						+ "_" + StringUtils.cleanPath(file.getOriginalFilename());

				docDownloadPath = ServletUriComponentsBuilder.fromCurrentContextPath()
						.path("/rest/upload/downloadDocs/").queryParam("fileName", fileName).toUriString();
				storeLoanDocs(file, fileName);

				IcustLoanDocuments loanDocs = new Gson().fromJson(new Gson().toJson(data),
						IcustLoanDocuments.class);
				loanDocs.setFileUrl(docDownloadPath);
//				if (loanDocs.getDocumentType() == DataConstants.PROFILEIMAGE) {
					Optional<IcustLoanDocuments> docs = icustLoanDocumentsRepo
							.findByLoanAccountIdAndScreenType(loanDocs.getLoanAccountId(), loanDocs.getScreenType());
					if (docs.isPresent())
						icustLoanDocumentsRepo.deleteById(docs.get().getId());
//				} else if (loanDocs.getDocumentType() == 3) {
//					Optional<IcustLoanDocuments> docs = icustLoanDocumentsRepo
//							.findByCorporateIdAndDocumentNameAndDocumentTypeAndDocumentSide(loanDocs.getLoanAccountId(),
//									loanDocs.getDocumentName(),loanDocs.getDocumentType(), loanDocs.getDocumentSide());
//					if (docs.isPresent()) {
//						icustLoanDocumentsRepo.deleteById(docs.get().getId());
//					}
//				}
				IcustLoanDocuments loanDocumentInfo = icustLoanDocumentsRepo.save(loanDocs);
				documentModel = new Gson().fromJson(new Gson().toJson(loanDocumentInfo), IcustLoanDocumentsModel.class);
			
			} else
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is mandatory");
		} catch (Exception e) {
			logger.error("Execption occoured while executing uploadLoanDocs", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(documentModel);
	}
	
	private String storeLoanDocs(MultipartFile file, String fileName) throws InternalServerException{
		if (Strings.isNullOrEmpty(fileName))
			fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			File f = new File(uploadLocation);
			if (f.mkdir()) {
				logger.info("Directory Created");
			} else {
				logger.info("Directory is not created");
			}
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file " + fileName);
			}
			if (fileName.contains("..")) {
				// This is a security check
				throw new StorageException(
						"Cannot store file with relative path outside current directory " + fileName);
			}
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, Paths.get(uploadLocation).resolve(fileName),
						StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (IOException e) {
			throw new InternalServerException(e.getMessage());
		}

		return fileName;
		
	}

	@Override
	public ResponseEntity<?> fetchLoanDocuments(Long loanAccountId, Integer screenType) {
		List<IcustLoanDocuments> documentList = new ArrayList<>();
		List<IcustLoanDocuments> loanDocuments = null;
		try {
			
			loanDocuments = icustLoanDocumentsRepo.findByLoanAccountIdAndScreenType(loanAccountId, screenType);

			if (!CollectionUtils.isEmpty(loanDocuments)) {
				for (IcustLoanDocuments corporateDocs : loanDocuments) {
					if (Strings.isNullOrEmpty(corporateDocs.getFileUrl())) {
						corporateDocs.setFileUrl("not_available");
						documentList.add(corporateDocs);
					} else {
						documentList.add(corporateDocs);
					}
				}

				return ResponseEntity.status(HttpStatus.OK).body(documentList);
			} else {
				logger.error("No record exists for given search");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No record exists for given serach");
			}
		} catch (Exception e) {
			logger.error(MessageFormat.format("Exception occoured while fetchLoanDocuments", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
