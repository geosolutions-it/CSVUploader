package it.geosolutions.csvimporter.controller;

import java.io.File;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/monitoring")
public class MonitoringRestController {

	@Autowired
	private Environment env;

	@GetMapping
	public ResponseEntity<String> monitoring() {
			File ftpDir = new File(env.getProperty("upload.dir"));
			StringBuilder sb = new StringBuilder("Currently there are ");
			if (ftpDir.list()!=null) {
				sb.append(Arrays.asList(ftpDir.list()).stream().filter(f -> f.endsWith(".zip")).count());
			} else sb.append("0");
			sb.append(" file in the directory " + ftpDir.getName());
			return ResponseEntity.ok(sb.toString());
	}

}
