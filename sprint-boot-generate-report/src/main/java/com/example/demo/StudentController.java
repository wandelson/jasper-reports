package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pojo.Student;
import com.example.pojo.Subject;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.query.JsonQLQueryExecuterFactory;

@RestController
@RequestMapping("/api/student")
public class StudentController {

	@GetMapping("/report")
	public ResponseEntity<byte[]> getReport() {

		try {
			String filePath = ResourceUtils.getFile("classpath:Blank_A4.jrxml")
					.getAbsolutePath();
				
			File filePathJson = ResourceUtils.getFile("classpath:teste.json");
						
			JsonDataSource chartDataSource = new JsonDataSource(filePathJson);
			
			JasperReport report = JasperCompileManager.compileReport(filePath);
			
			Map<String, Object> parameters = new HashMap<String, Object>();
			
			parameters.put(JsonQLQueryExecuterFactory.JSON_INPUT_STREAM, new FileInputStream(filePathJson));
			
			JasperPrint print = 
					JasperFillManager.fillReport(report,parameters);
			
			byte[] byteArray = JasperExportManager.exportReportToPdf(print);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_PDF);
			headers.setContentDispositionFormData("filename", "student.pdf");
			
			return new ResponseEntity<byte[]>(byteArray, headers, HttpStatus.OK);
			
		} catch(Exception e) {
			System.out.println("Exception while creating report");
			return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}


	
	@GetMapping("/report2")
	public ResponseEntity getReport2() {

		
			
			return new ResponseEntity( HttpStatus.OK);
			
		
		
	}

	
}
