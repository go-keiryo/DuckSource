package edu.stevens.ssw690.DuckSource.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import edu.stevens.ssw690.DuckSource.model.DuckUser;
import edu.stevens.ssw690.DuckSource.model.Opportunity;
import edu.stevens.ssw690.DuckSource.model.OpportunitySubmitted;
import edu.stevens.ssw690.DuckSource.service.DuckUserManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityRegisteredManager;
import edu.stevens.ssw690.DuckSource.service.OpportunitySubmittedManager;

@Controller
@SessionAttributes("submit")
public class SubmitController extends MultiActionController {

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
	    	
	        
	        String root = getServletContext().getRealPath("/");
            String fullPath = root + File.separator + "userdata" +  File.separator +  opportunitySubmitted.getFilePath();
	        
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
	 
	 @RequestMapping(value="/submit", method = RequestMethod.POST)
	    public String onSubmit(HttpServletRequest request, @RequestParam("comments") String comments, 
	    		 @RequestParam("file") MultipartFile file, @RequestParam("userId") Integer userId, 
	    		 @RequestParam("oppId") Integer oppId,HttpServletResponse response, 
	    		 SessionStatus status, Model model) 
	    {
	    	 DuckUser user =  userSvc.findById(userId);
	    	 Opportunity opportunity = opportunitySvc.findById(oppId);
	    	
	    	 boolean error = false;
	    	 String message = "";
	    	 String messageClass = "";
	    	 String filePath = "";
	    	 String name = file.getOriginalFilename();
	    	
	    	 if (!file.isEmpty()) {
	             try {
	                 byte[] bytes = file.getBytes();
	                 
	                 String root = getServletContext().getRealPath("/"); 
	                 File dir = new File(root + File.separator + "userdata" +  File.separator + user.getUserName());
	                 if (!dir.exists()) {
	                     dir.mkdirs();
	                 }

	                 String serverFileLocation = dir.getAbsolutePath()
	                         + File.separator + name;
	                 File serverFile = new File(serverFileLocation);
	                 BufferedOutputStream stream = new BufferedOutputStream(
	                         new FileOutputStream(serverFile));
	                 stream.write(bytes);
	                 stream.close();
	  
	                 filePath = user.getUserName() + File.separator + name;
	                 message = name + " successfully uploaded";
	             } catch (Exception e) {
	            	 error = true;
	            	 messageClass = "error";
	                 message = "failed to upload " + name;
	             }
	         } else {
	        	 	error = true;
	        	 	messageClass = "error";
	        	 	message = name + " is empty";
	         }


	        if (error) {
	        	
	    		model.addAttribute("opportunity", opportunity);
	    		model.addAttribute("userId", userId);
	    		model.addAttribute("message", message);
	    		model.addAttribute("messageClass", messageClass);
	    		return "submit";
	        }
	        
	        OpportunitySubmitted opportunitySubmitted = new OpportunitySubmitted();
	        opportunitySubmitted.setSubmissionDate(Date.from(LocalDateTime.now().toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
	        opportunitySubmitted.setStatus("Pending");
	        opportunitySubmitted.setFilePath(filePath);
	        opportunitySubmitted.setComment(comments);
	        opportunitySubmitted.setOpportunity(opportunity);
	    	opportunitySubmitted.setUser(user);
	    	opportunitySubmittedSvc.persist(opportunitySubmitted);
	        
	        model.addAttribute("opportunity", opportunity);
			model.addAttribute("userId", userId);
			model.addAttribute("message", message);
			model.addAttribute("messageClass", messageClass);
			model.addAttribute("oppId", oppId);
			model.addAttribute("subId", opportunitySubmitted.getId());
			
			return "redirect:resubmit";
	        
	        
		}
	    
	    @RequestMapping(value="/resubmit", method = RequestMethod.GET)
	    public String getResubmit(@RequestParam("userId") Integer userId, @RequestParam("oppId") Integer oppId, 
	    		 @RequestParam("subId") Integer subId, @RequestParam("message") String message,  @RequestParam("messageClass") String messageClass, Model model) 
	    {
	    	Opportunity opportunity = opportunitySvc.findById(oppId);
	    	OpportunitySubmitted opportunitySubmitted = opportunitySubmittedSvc.findById(subId);
			model.addAttribute("opportunity", opportunity);
			model.addAttribute("opportunitySubmitted", opportunitySubmitted);
			model.addAttribute("userId", userId);
			model.addAttribute("message", message);
			model.addAttribute("messageClass", messageClass);
			return "resubmit";
		}
	    
	    @RequestMapping(value="/resubmit", method = RequestMethod.POST)
	    public String onResubmit(HttpServletRequest request, @RequestParam("comments") String comments, 
	    		 @RequestParam("file") MultipartFile file, @RequestParam("userId") Integer userId, 
	    		 @RequestParam("oppId") Integer oppId, @RequestParam("subId") Integer oppSubmitId,
	    		 SessionStatus status, Model model) 
	    {
	    	 DuckUser user =  userSvc.findById(userId);
	    	 Opportunity opportunity = opportunitySvc.findById(oppId);
	    	 OpportunitySubmitted opportunitySubmitted = opportunitySubmittedSvc.findById(oppSubmitId);
	    	
	    	 boolean error = false;
	    	 String message = "";
	    	 String messageClass = "";
	    	 String filePath = "";
	    	 String name = file.getOriginalFilename();
	    	
	    	 if (!file.isEmpty()) {
	             try {
	                 byte[] bytes = file.getBytes();
	                 
	                 String root = getServletContext().getRealPath("/");
	                 File dir = new File(root + File.separator + "userdata" +  File.separator + user.getUserName());
	                 if (!dir.exists()) {
	                     dir.mkdirs();
	                 }

	                 String serverFileLocation = dir.getAbsolutePath()
	                         + File.separator + name;
	                 File serverFile = new File(serverFileLocation);
	                 BufferedOutputStream stream = new BufferedOutputStream(
	                         new FileOutputStream(serverFile));
	                 stream.write(bytes);
	                 stream.close();
	  
	                 filePath = user.getUserName() + File.separator + name;
	                 message = name + " successfully uploaded";
	             } catch (Exception e) {
	            	 error = true;
	            	 messageClass = "error";
	                 message = "failed to upload " + name;
	             }
	         } else {
	        	 	error = true;
	        	 	messageClass = "error";
	        	 	message = name + " is empty";
	         }


	        if (error) {
	        	
	    		model.addAttribute("opportunity", opportunity);
	    		model.addAttribute("opportunitySubmitted", opportunitySubmitted);
	    		model.addAttribute("userId", userId);
	    		model.addAttribute("message", message);
	    		model.addAttribute("messageClass", messageClass);
	    		return "resubmit";
	        }
	        
	        opportunitySubmitted.setFilePath(filePath);
	        opportunitySubmitted.setComment(comments);
	        opportunitySubmitted.setSubmissionDate(Date.from(LocalDateTime.now().toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
	    	opportunitySubmittedSvc.merge(opportunitySubmitted);
	        
	        model.addAttribute("opportunity", opportunity);
	        model.addAttribute("opportunitySubmitted", opportunitySubmitted);
			model.addAttribute("userId", userId);
			model.addAttribute("message", message);
			model.addAttribute("messageClass", messageClass);
			return "resubmit";
	        
	        
		}
	    
	 
}
