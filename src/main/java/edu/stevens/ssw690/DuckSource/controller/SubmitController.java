package edu.stevens.ssw690.DuckSource.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.stevens.ssw690.DuckSource.model.DuckUser;
import edu.stevens.ssw690.DuckSource.model.MailDistribution;
import edu.stevens.ssw690.DuckSource.model.MailMessage;
import edu.stevens.ssw690.DuckSource.model.Mailbox;
import edu.stevens.ssw690.DuckSource.model.Opportunity;
import edu.stevens.ssw690.DuckSource.model.OpportunitySubmitted;
import edu.stevens.ssw690.DuckSource.model.OpportunityTime;
import edu.stevens.ssw690.DuckSource.model.WorkInterval;
import edu.stevens.ssw690.DuckSource.service.DuckUserManager;
import edu.stevens.ssw690.DuckSource.service.MailDistributionManager;
import edu.stevens.ssw690.DuckSource.service.MailMessageManager;
import edu.stevens.ssw690.DuckSource.service.MailboxManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityRegisteredManager;
import edu.stevens.ssw690.DuckSource.service.OpportunitySubmittedManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityTimeManager;

/**
 * @author susan
 *
 */
@Controller
@SessionAttributes("submit")
public class SubmitController extends MultiActionController {

	@Autowired
	OpportunityManager opportunityService;
	
	@Autowired
	OpportunityRegisteredManager opportunityRegisteredService; 
	
	@Autowired
	OpportunitySubmittedManager opportunitySubmittedService; 
	
	@Autowired
	DuckUserManager duckUserService;
	
	@Autowired
	OpportunityTimeManager opportunityTimeService; 
	
	@Autowired
	MailboxManager mailboxService; 
	
	@Autowired
	MailMessageManager mailMessageService; 
	
	@Autowired
	MailDistributionManager mailDistributionService; 
    
	private static final int BUFFER_SIZE = 4096;
	
	 /**
	 * Get's the file related to the submission and downloads it
	 * if exists and can be opened.
	 * @param request
	 * @param response
	 * @param userId
	 * @param oppId
	 * @param oppSubmitId
	 * @param status
	 * @param model
	 */
	@RequestMapping(value="/download", method = RequestMethod.GET)
	    public void getDownload(HttpServletRequest request, HttpServletResponse response, 
	    		@RequestParam("userId") Integer userId, @RequestParam("oppId") Integer oppId, 
	    		@RequestParam("subId") Integer oppSubmitId, SessionStatus status, Model model) 
	    {
	    	
	    	OpportunitySubmitted opportunitySubmitted = opportunitySubmittedService.findById(oppSubmitId);
	    	
	    	@SuppressWarnings("unused")
	    	// for future use
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
	 
	@RequestMapping(value="/submit", method = RequestMethod.GET)
    public String getSubmit(@RequestParam("userId") Integer userId, @RequestParam("oppId") Integer oppId, Model model) 
    {
    	Opportunity opportunity = opportunityService.findById(oppId);
		model.addAttribute("opportunity", opportunity);
		model.addAttribute("userId", userId);
		model.addAttribute("message", "");
		model.addAttribute("messageClass", "");
		return "submit";
	}
	
	 /**
	 * Saves the file related to the submission and any comments
	 * @param request
	 * @param comments
	 * @param file
	 * @param userId
	 * @param oppId
	 * @param response
	 * @param status
	 * @param model
	 * @return submit.jsp (if error) or resubmit.jsp (if successful)
	 * model includes message (error or success) and class for styling message.
	 */
	@RequestMapping(value="/submit", method = RequestMethod.POST)
	    public String onSubmit(HttpServletRequest request, @RequestParam("comments") String comments, 
	    		 @RequestParam("file") MultipartFile file, @RequestParam("userId") Integer userId, 
	    		 @RequestParam("oppId") Integer oppId,HttpServletResponse response, 
	    		 SessionStatus status, Model model) 
	    {
	    	 DuckUser user =  duckUserService.findById(userId);
	    	 Opportunity opportunity = opportunityService.findById(oppId);
	    	
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
	    	opportunitySubmittedService.persist(opportunitySubmitted);
	        
	        model.addAttribute("opportunity", opportunity);
			model.addAttribute("userId", userId);
			model.addAttribute("message", message);
			model.addAttribute("messageClass", messageClass);
			model.addAttribute("oppId", oppId);
			model.addAttribute("subId", opportunitySubmitted.getId());
			
			return "redirect:resubmit";
	        
	        
		}
	    
	    /**
	     * Gets the page that allows the submission file for the opportunity to be resubmitted
	     * Sets up the parameters needed to resubmit, adds them to the model
	     * @param userId
	     * @param oppId
	     * @param subId
	     * @param message
	     * @param messageClass
	     * @param model
	     * @return resubmit.jsp
	     */
	    @RequestMapping(value="/resubmit", method = RequestMethod.GET)
	    public String getResubmit(@RequestParam("userId") Integer userId, @RequestParam("oppId") Integer oppId, 
	    		 @RequestParam("subId") Integer subId, @RequestParam("message") String message,  @RequestParam("messageClass") String messageClass, Model model) 
	    {
	    	Opportunity opportunity = opportunityService.findById(oppId);
	    	OpportunitySubmitted opportunitySubmitted = opportunitySubmittedService.findById(subId);
			model.addAttribute("opportunity", opportunity);
			model.addAttribute("opportunitySubmitted", opportunitySubmitted);
			model.addAttribute("userId", userId);
			model.addAttribute("message", message);
			model.addAttribute("messageClass", messageClass);
			return "resubmit";
		}
	    
	    /**
	     * Saves the file related to the re-submission and any comments
	     * @param request
	     * @param comments
	     * @param file
	     * @param userId
	     * @param oppId
	     * @param oppSubmitId
	     * @param status
	     * @param model
	     * @return resubmit.jsp, model includes message (error or success) and class for styling message.
	     */
	    @RequestMapping(value="/resubmit", method = RequestMethod.POST)
	    public String onResubmit(HttpServletRequest request, @RequestParam("comments") String comments, 
	    		 @RequestParam("file") MultipartFile file, @RequestParam("userId") Integer userId, 
	    		 @RequestParam("oppId") Integer oppId, @RequestParam("subId") Integer oppSubmitId,
	    		 SessionStatus status, Model model) 
	    {
	    	 DuckUser user =  duckUserService.findById(userId);
	    	 Opportunity opportunity = opportunityService.findById(oppId);
	    	 OpportunitySubmitted opportunitySubmitted = opportunitySubmittedService.findById(oppSubmitId);
	    	
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
	    	opportunitySubmittedService.merge(opportunitySubmitted);
	        
	        model.addAttribute("opportunity", opportunity);
	        model.addAttribute("opportunitySubmitted", opportunitySubmitted);
			model.addAttribute("userId", userId);
			model.addAttribute("message", message);
			model.addAttribute("messageClass", messageClass);
			return "resubmit";
	        
	        
		}
	    
	    /**
	     * Gets the page for the user's mailbox
	     * @param userId
	     * @param model
	     * @return mail.jsp
	     */
	    @RequestMapping(value="/mail", method = RequestMethod.GET)
	    public String getDuckMail(@RequestParam("userId") Integer userId, Model model) 
	    {
			model.addAttribute("userId", userId);
			List<String> userNames = duckUserService.getAllUserNames();
			model.addAttribute("users", userNames);
			return "mail";
		}
	    
	    @RequestMapping(value="/mailUser", method = RequestMethod.GET, produces="application/json")
	    public @ResponseBody DuckUser getDuckMailUser(@RequestParam("userId") Integer userId) throws JsonProcessingException 
	    {
			DuckUser user = duckUserService.findById(userId);
			return user;
	    }
	    
	    @RequestMapping(value="/inboxMailAngularJs", method = RequestMethod.GET, produces="application/json")
	    public @ResponseBody List<Mailbox> getInbox(@RequestParam("userId") Integer userId) throws JsonProcessingException 
	    {
	    	DuckUser user = duckUserService.findById(userId);
	    	List<Mailbox> inbox =  mailboxService.getUserInbox(user.getId());
	    	
	    	return inbox;
	    	
		}
	    
	    @RequestMapping(value="/sentMailAngularJs", method = RequestMethod.GET, produces="application/json")
	    public @ResponseBody List<Mailbox> getSent(@RequestParam("userId") Integer userId) throws JsonProcessingException 
	    {
	    	DuckUser user = duckUserService.findById(userId);
	    	List<Mailbox> sent =  mailboxService.getUserSent(user.getId());
	    	
	    	return sent;
			
		}
	    
	    @RequestMapping(value="/mailReadAngularJs", method = RequestMethod.POST) 
	    public @ResponseBody Mailbox onMesssageRead( @RequestBody Mailbox mailbox )  throws JsonProcessingException
	    { 
	
	    	mailbox.setRead(true);
	    	mailboxService.merge(mailbox);
	    	
	    	return mailbox;
	    	
	    }
	    
	    @RequestMapping(value="/mailAngularJs", method = RequestMethod.POST) 
	    public @ResponseBody Mailbox onMesssageSubmit( @RequestBody MailMessage mailMessage )  throws JsonProcessingException
	    { 
	    	DuckUser fromUser = duckUserService.findById(mailMessage.getUserId());
	    	DuckUser toUser = duckUserService.getDuckUser(mailMessage.getTo());
	    	
	    	boolean newMessage = false;
	    	MailMessage newMailMessage = null;
	    	mailMessage.setSent(Calendar.getInstance().getTime());
	    	if (mailMessage.getUser() == null) {
	    		mailMessage.setUser(fromUser);
	    		mailMessageService.persist(mailMessage);
	    	} else {
	            newMessage = true;
	    		newMailMessage = new MailMessage();
	    		newMailMessage.setUser(fromUser);
	    		newMailMessage.setTo(mailMessage.getTo());
	    		newMailMessage.setSubject(mailMessage.getSubject());
	    		newMailMessage.setBody(mailMessage.getBody());
	    		newMailMessage.setSent(mailMessage.getSent());
	    		mailMessageService.persist(newMailMessage);
	    	}
	    	
	    	MailDistribution mailToDistribution = new MailDistribution();
	    	if (newMessage) {
	    		mailToDistribution.setMailMessage(newMailMessage);
	    	} else {
	    		mailToDistribution.setMailMessage(mailMessage);
	    	}
	    	mailToDistribution.setUser(toUser);
	    	mailDistributionService.persist(mailToDistribution);
	    	
	    	MailDistribution mailFromDistribution = new MailDistribution();
	    	if (newMessage) {
	    		mailFromDistribution.setMailMessage(newMailMessage);
	    	} else {
	    		mailFromDistribution.setMailMessage(mailMessage);
	    	}
	    	mailFromDistribution.setUser(fromUser);
	    	mailDistributionService.persist(mailFromDistribution);
	    	
	    	Mailbox inboxMail = new Mailbox();
	    	inboxMail.setFolder("Inbox");
	    	if (newMessage) {
	    		inboxMail.setMailMessage(newMailMessage);
	    	} else {
	    		inboxMail.setMailMessage(mailMessage);
	    	}
	    	inboxMail.setUser(toUser);
	    	mailboxService.persist(inboxMail);
	    	
	    	Mailbox sentMail = new Mailbox();
	    	sentMail.setFolder("Sent");
	    	if (newMessage) {
	    		sentMail.setMailMessage(newMailMessage);
	    	} else {
	    		sentMail.setMailMessage(mailMessage);
	    	}
	    	sentMail.setUser(fromUser);
	    	mailboxService.persist(sentMail);
	    	
    		return sentMail;
	    }
        
	    /**
	     * Gets the page the allows the profile image to be changed
	     * @param userId
	     * @param model
	     * @return profileimg.jsp
	     */
	    @RequestMapping(value="/profileimg", method = RequestMethod.GET)
	    public String getProfileImage(@RequestParam("userId") Integer userId, Model model) 
	    {
			model.addAttribute("userId", userId);
			DuckUser user = new DuckUser();
	   	 	model.addAttribute("user", user);
			return "profileimg";
		}
	    
	    /**
	     * @param request
	     * @param file
	     * @param userId
	     * @param status
	     * @param model
	     * @return
	     */
	    @RequestMapping(value="/profileimg", method = RequestMethod.POST)
	    public String onImageSubmit(HttpServletRequest request,
	    		 @RequestParam("file") MultipartFile file, @RequestParam("userId") Integer userId, 
	    		 SessionStatus status, Model model) 
	    {
	    	 DuckUser user =  duckUserService.findById(userId);
	    	
	    	 boolean error = false;
	    	 String message = "";
	    	 String messageClass = "";
	    	 String profileImage = "";
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
	  
	                 profileImage = user.getUserName() + File.separator + name;
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
	    		model.addAttribute("userId", userId);
	    		model.addAttribute("message", message);
	    		model.addAttribute("messageClass", messageClass);
	    		return "profileimg";
	        }
	        
	        user.setProfileImage(profileImage);
			duckUserService.merge(user);
			
	        status.setComplete();
	        
	        model.addAttribute("userId", userId);
	        model.addAttribute("message", message);
			model.addAttribute("messageClass", messageClass);
	        
			return "redirect:account";
	        
		}
	    
	    
	    /**
	     * Gets the time sheet for the current month/year
	     * Time data formatted for display added to model in JSON format
	     * message holds any errors and message class is css style for display
	     * @param userId
	     * @param oppId
	     * @param message
	     * @param messageClass
	     * @param model
	     * @return timesheet.jsp
	     */
	    @RequestMapping(value="/timesheet", method = RequestMethod.GET)
	    public String getTimesheet(@RequestParam("userId") Integer userId, @RequestParam("oppId") Integer oppId, 
	    		@RequestParam("message") String message,  @RequestParam("messageClass") String messageClass, Model model) 
	    {
	    	Opportunity opportunity = opportunityService.findById(oppId);
	    	
	    	Calendar cal = Calendar.getInstance();
	    	String title = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH) + " " + cal.get(Calendar.YEAR);
	    	int month = cal.get(Calendar.MONTH);
	    	int year = cal.get(Calendar.YEAR);
	    	
	    	// loads the time data from the database
	    	List<String> workDays = opportunityTimeService.getTimeforDisplay(userId, oppId, month, year);
	    	
	    	// convert to JSON
	    	ObjectMapper mapper = new ObjectMapper();
	    	String jsonTimeData = "";
			try {
				jsonTimeData = mapper.writeValueAsString(workDays);
			} catch (JsonProcessingException e) {
				message = e.getMessage();
				messageClass = "error";
			}
			
	    	model.addAttribute("opportunity", opportunity);
			model.addAttribute("userId", userId);
			model.addAttribute("title", title);
			model.addAttribute("message", message);
			model.addAttribute("messageClass", messageClass);
			model.addAttribute("displayMonth", month);
			model.addAttribute("displayYear", year);
			model.addAttribute("newMonth", month);
			model.addAttribute("newYear", year);
			model.addAttribute("timeData", jsonTimeData);
			model.addAttribute("save", 1);
			model.addAttribute("dirty", 0);
			return "timesheet";
		}
	    
	    /**
	     * Saves any changed time data to the database if it applies.
	     * Data not saved if unchanged or user elects not to save it.
	     * Time data formatted for display added to model in JSON format.
	     * message holds errors/success or "" if no data save required
	     * message class is css style for display
	     * @param request
	     * @param userId
	     * @param oppId
	     * @param month this is the current month
	     * @param year this is the current year
	     * @param newMonth this is the updated month (previous/next)
	     * @param newYear this is the updated year (previous/next)
	     * @param timeData data to potentially be saved
	     * @param save flag if user wants the data saved
	     * @param dirty flag if data has changed
	     * @param status
	     * @param model
	     * @return timesheet.jsp model includes time data for month/year requested
	     * 
	     */
	    @RequestMapping(value="/timesheet", method = RequestMethod.POST)
	    public String onTimesheet(HttpServletRequest request,
	    		 @RequestParam("userId") Integer userId, 
	    		 @RequestParam("oppId") Integer oppId,
	    		 @RequestParam("displayMonth") Integer month,
	    		 @RequestParam("displayYear") Integer year,
	    		 @RequestParam("newMonth") Integer newMonth,
	    		 @RequestParam("newYear") Integer newYear,
	    		 @RequestParam("timeData") String timeData,
	    		 @RequestParam("save") Integer save,
	    		 @RequestParam("dirty") Integer dirty,
	    		 SessionStatus status, Model model) 
	    {
	  
	    	DuckUser user =  duckUserService.findById(userId);
	    	Opportunity opportunity = opportunityService.findById(oppId);
	    	String message = "";
	    	String messageClass = "";
    		
	    	/*
	    	 *  don't save data if user elects not to
	    	 *  or the data hasn't changed
	    	 */
    		if (save == 1 && dirty == 1) {
    			 
    			 YearMonth yearMonth = YearMonth.of(year, month+1);
    	    	 int daysInMonth = yearMonth.lengthOfMonth();
    	    	 LocalDate localDate = LocalDate.of(year, month+1, 1);
        		 Date startDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        		 localDate = LocalDate.of(year, month+1, daysInMonth);
        		 Date endDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    			 
        		 // delete current time data in database for month
		    	 opportunityTimeService.clearTime(userId, oppId, startDate, endDate);
		    	 
		         // get the time data as WorkIntervals
		    	 List<WorkInterval> workIntervals = opportunityTimeService.getTimeforStorage(timeData, year, month);
		    	
		    	 // add them to the database
		 		for (WorkInterval workInterval : workIntervals) {
		 			 OpportunityTime oppTime = new OpportunityTime();
					 oppTime.setUser(user);
					 oppTime.setOpportunity(opportunity);
					 oppTime.setWorkDate(workInterval.getWorkDate());
					 oppTime.setStartTime(workInterval.getStartTime());
					 oppTime.setEndTime(workInterval.getEndTime());
					 // save
					 opportunityTimeService.merge(oppTime);
		 		}
		    	 
		    	message = "Time Sheet updated";
    		}
	    	
    		// switch to requested month/year
	    	month = newMonth;
	    	year = newYear;
	    	
	    	// loads the time data from the database
	    	List<String> workDays = opportunityTimeService.getTimeforDisplay(userId, oppId, month, year);
	    	
	    	// convert to JSON
	    	ObjectMapper mapper = new ObjectMapper();
	    	String jsonTimeData = "";
			try {
				jsonTimeData = mapper.writeValueAsString(workDays);
			} catch (JsonProcessingException e) {
				message = e.getMessage();
				messageClass = "error";
			}
			
			LocalDate localDate = LocalDate.of(year, month+1, 1);
   		    Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	    	Calendar cal = Calendar.getInstance();
	    	cal.setTime(date);
	    	String title = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH) + " " + cal.get(Calendar.YEAR);
	            	
	        model.addAttribute("opportunity", opportunity);
			model.addAttribute("userId", userId);
			model.addAttribute("message", message);
			model.addAttribute("messageClass", messageClass);
			model.addAttribute("title", title);
			model.addAttribute("displayMonth", cal.get(Calendar.MONTH));
			model.addAttribute("displayYear", cal.get(Calendar.YEAR));
			model.addAttribute("timeData", jsonTimeData);
			
			return "timesheet";
	        
		}
	 
	   
}
