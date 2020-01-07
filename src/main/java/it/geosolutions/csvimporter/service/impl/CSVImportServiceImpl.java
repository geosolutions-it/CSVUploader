package it.geosolutions.csvimporter.service.impl;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.geosolutions.csvimporter.service.CSVImportService;

@Service
public class CSVImportServiceImpl implements CSVImportService {

	@Autowired
	private Environment env;

	@Override
	public String saveCSV(MultipartFile file, String path) {
		Path fullPath = Paths.get(env.getProperty("upload.dir") + File.separator + path);
		try {
			InputStream is = file.getResource().getInputStream();
			Files.copy(is, fullPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return fullPath.toString();
	}

}
