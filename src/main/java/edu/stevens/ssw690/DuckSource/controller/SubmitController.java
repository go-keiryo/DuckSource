package edu.stevens.ssw690.DuckSource.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.stevens.ssw690.DuckSource.model.DuckUser;
import edu.stevens.ssw690.DuckSource.model.Opportunity;
import edu.stevens.ssw690.DuckSource.model.OpportunitySubmitted;
import edu.stevens.ssw690.DuckSource.model.OpportunityTime;
import edu.stevens.ssw690.DuckSource.service.DuckUserManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityRegisteredManager;
import edu.stevens.ssw690.DuckSource.service.OpportunitySubmittedManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityTimeManager;

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
	
	@Autowired
	OpportunityTimeManager opportunityTimeSvc; 
    
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
	    
	    @RequestMapping(value="/profileimg", method = RequestMethod.GET)
	    public String getProfileImage(@RequestParam("userId") Integer userId, Model model) 
	    {
			model.addAttribute("userId", userId);
			DuckUser user = new DuckUser();
	   	 	model.addAttribute("user", user);
			return "profileimg";
		}
	    
	    @RequestMapping(value="/profileimg", method = RequestMethod.POST)
	    public String onImageSubmit(HttpServletRequest request,
	    		 @RequestParam("file") MultipartFile file, @RequestParam("userId") Integer userId, 
	    		 SessionStatus status, Model model) 
	    {
	    	 DuckUser user =  userSvc.findById(userId);
	    	
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
			userSvc.merge(user);
			
	        status.setComplete();
	        
	        model.addAttribute("userId", userId);
	        model.addAttribute("message", message);
			model.addAttribute("messageClass", messageClass);
	        
			return "redirect:account";
	        
		}
	    
	    @RequestMapping(value="/timesheet", method = RequestMethod.GET)
	    public String getTimesheet(@RequestParam("userId") Integer userId, @RequestParam("oppId") Integer oppId, 
	    		@RequestParam("message") String message,  @RequestParam("messageClass") String messageClass, Model model) 
	    {
	    	Opportunity opportunity = opportunitySvc.findById(oppId);
	    	
	    	Calendar cal = Calendar.getInstance();
	    	String title = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH) + " " + cal.get(Calendar.YEAR);
	    	int month = cal.get(Calendar.MONTH);
	    	int year = cal.get(Calendar.YEAR);
	    	
	    	List<String> workDays = getTimeforDisplay(userId, oppId, month, year);
	    	
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
	  
	    	DuckUser user =  userSvc.findById(userId);
	    	Opportunity opportunity = opportunitySvc.findById(oppId);
	    	String message = "";
	    	String messageClass = "";
    		 
    		if (save == 1 && dirty == 1) {
    			 
    			 YearMonth yearMonth = YearMonth.of(year, month+1);
    	    	 int daysInMonth = yearMonth.lengthOfMonth();
    	    	 LocalDate localDate = LocalDate.of(year, month+1, 1);
        		 Date startDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        		 localDate = LocalDate.of(year, month+1, daysInMonth);
        		 Date endDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    			 
		    	 opportunityTimeSvc.clearTime(userId, oppId, startDate, endDate);
		    	 
		    	 String[] days = timeData.split(",");
		    	
		    	 for (int i=0; i < days.length; i++) {
		    		 if (days[i] != " ") {
		    			 localDate = LocalDate.of(year, month+1, i+1);
			    		 Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		    			 String[] times = days[i].split("\\|");
		    			 for (int j=0; j < times.length; j++) {
		    				 String[] hours = times[j].split("-");
		    				 if (hours.length == 2) {
			    				 Time startTime = Time.valueOf(hours[0] + ":00");
			    				 Time endTime = Time.valueOf(hours[1] + ":00");
			    				 OpportunityTime oppTime = new OpportunityTime();
			    				 oppTime.setUser(user);
			    				 oppTime.setOpportunity(opportunity);
			    				 oppTime.setWorkDate(date);
			    				 oppTime.setStartTime(startTime);
			    				 oppTime.setEndTime(endTime);
			    				 opportunityTimeSvc.merge(oppTime);
		    				 }
		    			 }
		    		 }
		    	 }
		    	 
		    	 message = "Time Sheet updated";
    		}
	    	 
	    	month = newMonth;
	    	year = newYear;
	    	List<String> workDays = getTimeforDisplay(userId, oppId, month, year);
	    	
	    	LocalDate localDate = LocalDate.of(year, month+1, 1);
   		    Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	    	Calendar cal = Calendar.getInstance();
	    	cal.setTime(date);
	    	String title = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH) + " " + cal.get(Calendar.YEAR);
	        
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
			model.addAttribute("message", message);
			model.addAttribute("messageClass", messageClass);
			model.addAttribute("title", title);
			model.addAttribute("displayMonth", cal.get(Calendar.MONTH));
			model.addAttribute("displayYear", cal.get(Calendar.YEAR));
			model.addAttribute("timeData", jsonTimeData);
			
			return "timesheet";
	        
		}
	    
	    private List<String> getTimeforDisplay(int userId, int oppId, int month, int year) {
	    	 List<String> workDays = new ArrayList<String>();
	    	 YearMonth yearMonth = YearMonth.of(year, month+1);
		     int daysInMonth = yearMonth.lengthOfMonth();
	    	 for (int i=0; i <daysInMonth ; i++) {
	    		 	LocalDate localDate = LocalDate.of(year, month+1, i+1);
		    		 Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		    		 List<OpportunityTime> oppTimeList = opportunityTimeSvc.getByDate(userId, oppId, date, date);
		    		 Integer[] workHours =  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		    		 if (oppTimeList.isEmpty()) {
		    			 workDays.add(i,Arrays.toString(workHours));
		    		 } else {
		    			 for (OpportunityTime oppTime : oppTimeList) {
		    					Calendar timeCal = Calendar.getInstance();
		    					timeCal.setTime(oppTime.getStartTime());
		    					int start = timeCal.get(Calendar.HOUR_OF_DAY);
		    					timeCal.setTime(oppTime.getEndTime());
		    					int end = timeCal.get(Calendar.HOUR_OF_DAY);
		    					if (end == 0) {
		    						end = 24;
		    					}
		    					for (int j=start; j < end ; j++) {
		    						workHours[j] = 1;
		    					}
		    			 }
		    			 workDays.add(i,Arrays.toString(workHours));
		    		 }
	    	 }
			return workDays;
		}
}
