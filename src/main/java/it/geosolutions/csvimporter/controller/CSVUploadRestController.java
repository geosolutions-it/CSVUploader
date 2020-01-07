package it.geosolutions.csvimporter.controller;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import it.geosolutions.csvimporter.service.CSVImportService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/upload")
public class CSVUploadRestController {
	
	@Autowired
	private CSVImportService importService;
	
	
	@PutMapping(value="/{objectKey}", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> importCSV (@RequestBody MultipartFile file, @PathVariable String objectKey) throws URISyntaxException{
		String path =importService.saveCSV(file ,objectKey);
		URI uri = (new File(path)).toURI();
		return ResponseEntity.created(uri).body(uri.toString());
	}

}
