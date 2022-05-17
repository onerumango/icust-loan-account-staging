package com.rumango.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.rumango.entity.IcustCardDocuments;
import com.rumango.exception.FileNotFoundException;
import com.rumango.exception.InternalServerException;
import com.rumango.exception.StorageException;
import com.rumango.model.IcustCardDocumentsModel;
import com.rumango.model.IcustCardPreferencesModel;
import com.rumango.repository.IcustCardDocumentsRepo;
import com.rumango.service.IcustCardDocumentsService;

@Service
public class IcustCardDocumentsServiceImpl implements IcustCardDocumentsService {

	private static final Logger logger = Logger.getLogger(IcustCardDocumentsServiceImpl.class);

	private String uploadLocation = "./fileUploads";
	
	@Autowired
	IcustCardDocumentsRepo icustCardDocumentsRepo;
	@Autowired
	ModelMapper mapper;
	
	@Override
	public ResponseEntity<?> uploadCardDocs(MultipartFile file, IcustCardDocumentsModel data) {
		String docDownloadPath = null;
		IcustCardDocumentsModel documentModel = null;
		try {
			if (!file.isEmpty()) {
				String fileName = data.getCardId() + "_"
						+ String.valueOf(
								new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH).format(new java.util.Date()))
						+ "_" + StringUtils.cleanPath(file.getOriginalFilename());

				docDownloadPath = ServletUriComponentsBuilder.fromCurrentContextPath()
						.path("/card/upload/downloadDocs/").queryParam("fileName", fileName).toUriString();
				storeCardDocs(file, fileName);

				IcustCardDocuments cardDocs = new Gson().fromJson(new Gson().toJson(data),
						IcustCardDocuments.class);
				cardDocs.setFileUrl(docDownloadPath);
					Optional<IcustCardDocuments> docs = icustCardDocumentsRepo
							.findByCardIdAndScreenType(cardDocs.getCardId(), cardDocs.getScreenType());
					if (docs.isPresent())
						icustCardDocumentsRepo.deleteById(docs.get().getId());
				IcustCardDocuments cardDocumentInfo = icustCardDocumentsRepo.save(cardDocs);
				documentModel = new Gson().fromJson(new Gson().toJson(cardDocumentInfo), IcustCardDocumentsModel.class);
			
			} else
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is mandatory");
		} catch (Exception e) {
			logger.error("Execption occoured while executing uploadCardDocs", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(documentModel);
	}
	
	private String storeCardDocs(MultipartFile file, String fileName) throws InternalServerException{
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
	public ResponseEntity<?> fetchCardDocuments(Long cardId, String screenType) {
		List<IcustCardDocuments> documentList = new ArrayList<>();
		List<IcustCardDocuments> cardDocuments = null;
		try {
			
			cardDocuments = icustCardDocumentsRepo.findByCardIdAndScreenType(cardId, Integer.valueOf(screenType));

			if (!CollectionUtils.isEmpty(cardDocuments)) {
				for (IcustCardDocuments corporateDocs : cardDocuments) {
					if (Strings.isNullOrEmpty(corporateDocs.getFileUrl())) {
						corporateDocs.setFileUrl("not_available");
						documentList.add(corporateDocs);
					} else {
						documentList.add(corporateDocs);
					}
				}
				List<IcustCardDocumentsModel> docInfo = mapper.map(documentList, new TypeToken<List<IcustCardDocumentsModel>>() {}.getType());
				return ResponseEntity.status(HttpStatus.OK).body(docInfo);
			} else {
				logger.error("No record exists for given search");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No record exists for given serach");
			}
		} catch (Exception e) {
			logger.error(MessageFormat.format("Exception occoured while fetchCardDocuments", e.getMessage()), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public Resource loadAsResource(String fileName) {
		try {
			Path file = load(fileName);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new FileNotFoundException("Could not read file: " + fileName);
			}
		} catch (MalformedURLException | FileNotFoundException e) {
			throw new FileNotFoundException("Could not read file: " + e);
		}
	}
	
	public Path load(String filename) {
		return Paths.get(uploadLocation).resolve(filename);
	}
	
}
