package edu.stevens.ssw690.DuckSource.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import edu.stevens.ssw690.DuckSource.model.OpportunitySubmitted;
import edu.stevens.ssw690.DuckSource.service.DuckUserManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityRegisteredManager;
import edu.stevens.ssw690.DuckSource.service.OpportunitySubmittedManager;

@Controller
@SessionAttributes("file")
public class FileController extends MultiActionController {

	@Autowired
	OpportunityManager opportunitySvc;
	
	@Autowired
	OpportunityRegisteredManager opportunityRegisteredSvc; 
	
	@Autowired
	OpportunitySubmittedManager opportunitySubmittedSvc; 
	
	@Autowired
	DuckUserManager userSvc;
    
	private static final int BUFFER_SIZE = 4096;
	
	 @RequestMapping(value="/download", method = RequestMethod.GET)
	    public void getDownload(HttpServletRequest request, HttpServletResponse response, 
	    		@RequestParam("userId") Integer userId, @RequestParam("oppId") Integer oppId, 
	    		@RequestParam("subId") Integer oppSubmitId, SessionStatus status, Model model) 
	    {
	    	
	    	OpportunitySubmitted opportunitySubmitted = opportunitySubmittedSvc.findById(oppSubmitId);
	    	
	    	@SuppressWarnings("unused")
			boolean error = false;
	    	
	        String fullPath = opportunitySubmitted.getFilePath();       
		    File downloadFile = new File(fullPath);
		    FileInputStream inputStream = null;
		     
			try {
				inputStream = new FileInputStream(downloadFile);
			} catch (FileNotFoundException e) {
				error = true;
			}
		      
		    ServletContext context = request.getServletContext();
		    String mimeType = context.getMimeType(fullPath);
		    if (mimeType == null) {
		        mimeType = "application/octet-stream";
		    }
		         
		    response.setContentType(mimeType);
		    response.setContentLength((int) downloadFile.length());
		  
		    String headerKey = "Content-Disposition";
		    String headerValue = String.format("attachment; filename=\"%s\"",
		    downloadFile.getName());
		    response.setHeader(headerKey, headerValue);
	  
	        OutputStream outStream = null;
			try {
				outStream = response.getOutputStream();
			} catch (IOException e) {
				error = true;
			}
	  
	        byte[] buffer = new byte[BUFFER_SIZE];
	        int bytesRead = -1;
	  
	        try {
				while ((bytesRead = inputStream.read(buffer)) != -1) {
				     outStream.write(buffer, 0, bytesRead);
				 }
				inputStream.close();
				outStream.close();
	        } catch (IOException e) {
				error = true;
			}
	        
		}
	 
}
