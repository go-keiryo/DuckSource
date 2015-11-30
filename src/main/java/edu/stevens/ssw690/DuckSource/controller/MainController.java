package edu.stevens.ssw690.DuckSource.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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

import edu.stevens.ssw690.DuckSource.model.DuckUser;
import edu.stevens.ssw690.DuckSource.model.Opportunity;
import edu.stevens.ssw690.DuckSource.model.OpportunityRegistered;
import edu.stevens.ssw690.DuckSource.model.OpportunityReviewIssue;
import edu.stevens.ssw690.DuckSource.model.OpportunitySubmitted;
import edu.stevens.ssw690.DuckSource.model.OpportunitytReviewIssueExtended;
import edu.stevens.ssw690.DuckSource.model.ReviewIssue;
import edu.stevens.ssw690.DuckSource.service.DuckUserManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityRegisteredManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityReviewIssueManager;
import edu.stevens.ssw690.DuckSource.service.OpportunitySubmittedManager;
import edu.stevens.ssw690.DuckSource.service.ReviewIssueManager;
import edu.stevens.ssw690.DuckSource.utilities.DuckUtilities;

@Controller
@SessionAttributes("main")
public class MainController extends MultiActionController {

	@Autowired
	OpportunityManager opportunitySvc;
	
	@Autowired
	OpportunityRegisteredManager opportunityRegisteredSvc; 
	
	@Autowired
	OpportunityReviewIssueManager opportunityReviewIssueSvc; 
	
	@Autowired
	OpportunitySubmittedManager opportunitySubmittedSvc; 
	
	@Autowired
	DuckUserManager userSvc;
	
	@Autowired
	ReviewIssueManager issueSvc;
	 
	@ModelAttribute("allOpportunities")
	public List<Opportunity> populateOpportunities()
    {
       List<Opportunity> opportunities = opportunitySvc.getAllOpportunities();
       return opportunities;
    }
	 
    
    @RequestMapping(value="/indexoppdetail", method = RequestMethod.GET)
    public String showDetailIndex(HttpServletRequest request, Model model)
    {
    	Integer oppId = Integer.parseInt(request.getParameter("oppId"));
         Opportunity opportunity = opportunitySvc.findById(oppId);
         model.addAttribute("opportunity", opportunity);
         
         return "indexoppdetail";
    }
    
    @RequestMapping(value="/oppdetail", method = RequestMethod.GET)
    public String showDetail(HttpServletRequest request, Model model)
    {
    	Integer oppId = Integer.parseInt(request.getParameter("oppId"));
    	String userId = request.getParameter("userId");
    	if (userId != null && (!userId.isEmpty())) {
    		 model.addAttribute("userId",userId);
    	} else {
    		 model.addAttribute("userId","");
    	}
         Opportunity opportunity = opportunitySvc.findById(oppId);
         model.addAttribute("opportunity", opportunity);
         
         return "oppdetail";
    }
    
    @RequestMapping(value="/findupdate", method = RequestMethod.GET)
	 public String getFindUpdate(HttpServletRequest request, Model model) {
    	Integer userId = Integer.parseInt( request.getParameter("userId"));
		String selectType = request.getParameter("select");
		if (selectType.isEmpty() || selectType.equalsIgnoreCase("All Types")) {
			model.addAttribute("userId",userId);
			model.addAttribute("opportunities", opportunitySvc.getByOtherThanCreator(userId));
		} else {
			model.addAttribute("userId",userId);
			model.addAttribute("opportunities", opportunitySvc.getByOtherThanCreatorByType(userId, selectType));
		}
		
     return "findopp";
  }
    
    @RequestMapping(value="/findopp", method = RequestMethod.GET)
    public String setupFindForm(@RequestParam("userId") Integer userId, Model model) 
    {
		model.addAttribute("opportunities", opportunitySvc.getByOtherThanCreator(userId));
		model.addAttribute("userId", userId);
		return "findopp";
	}
    
    @RequestMapping(value="/submit", method = RequestMethod.GET)
    public String getSubmit(@RequestParam("userId") Integer userId, @RequestParam("oppId") Integer oppId, Model model) 
    {
    	Opportunity opportunity = opportunitySvc.findById(oppId);
		model.addAttribute("opportunity", opportunity);
		model.addAttribute("userId", userId);
		model.addAttribute("message", "");
		model.addAttribute("messageClass", "");
		return "submit";
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
  
                 filePath = serverFileLocation;
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
  
                 filePath = serverFileLocation;
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
    
   
    
    @RequestMapping(value="/account", method = RequestMethod.GET)
    public String getAccount(@RequestParam("userId") Integer userId, Model model) 
    {
		model.addAttribute("userId", userId);
		DuckUser user = userSvc.findById(userId);
		model.addAttribute("user", user);
		return "account";
	}
    
    @RequestMapping(value="/password", method = RequestMethod.GET)
    public String getPassword(@RequestParam("userId") Integer userId, Model model) 
    {
		model.addAttribute("userId", userId);
		DuckUser userForm = new DuckUser();
		userForm.setPassword("");
   	 	model.addAttribute("userForm", userForm);
		return "password";
	}
    
    @RequestMapping(value="/password", method = RequestMethod.POST)
    public  String submitPasswordForm(HttpServletRequest request, @ModelAttribute("userForm") DuckUser user,
                           BindingResult result, SessionStatus status, Model model)
    {
    	if(result.hasErrors()) {
            return "password";
        }
    	
    	Integer userId = Integer.parseInt(request.getParameter("userId"));
        boolean error = false;
        
        DuckUser currentUser = userSvc.findById(userId);
		if(user.getPassword().isEmpty()){
            result.rejectValue("password", "error.password");
            error = true;
        } else {
        	if (!user.getPassword().equalsIgnoreCase(currentUser.getPassword())) {
        		result.rejectValue("password", "error.password3");
        		error = true;
        	}
        }
        
		if(user.getNewPassword().isEmpty()){
            result.rejectValue("newPassword", "error.newpassword");
            error = true;
        }
		
		if(user.getConfirmPassword().isEmpty()){
            result.rejectValue("confirmPassword", "error.confirmpassword");
            error = true;
		}
		
		if ((!user.getNewPassword().isEmpty()) && (!user.getConfirmPassword().isEmpty()) && (!user.getNewPassword().equalsIgnoreCase(user.getConfirmPassword()))) {
			result.rejectValue("confirmPassword", "error.password2");
            error = true;
		}
	
		if(error) {
    		return "password";
        }
		
		currentUser.setPassword(user.getNewPassword());
		userSvc.merge(currentUser);
		
        status.setComplete();
        
        model.addAttribute("userId", userId);
		return "redirect:account";
       
    }
    
    @RequestMapping(value="/register")
    public  String registerForOpp(HttpServletRequest request, SessionStatus status, Model model)
    {
    	Integer userId = Integer.parseInt(request.getParameter("userId"));
    	Integer oppId = Integer.parseInt(request.getParameter("oppId"));
    	
    	OpportunityRegistered opportunityRegistered = new OpportunityRegistered();
    	Opportunity opportunity = opportunitySvc.findById(oppId);
    	DuckUser user =  userSvc.findById(userId);
    	opportunityRegistered.setRegisteredDate(Date.from(LocalDateTime.now().toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
    	opportunityRegistered.setOpportunity(opportunity);
    	opportunityRegistered.setUser(user);
    	opportunityRegisteredSvc.persist(opportunityRegistered);
		
        model.addAttribute("user", user);
        model.addAttribute("opportunities", opportunitySvc.getByCreator(userId));
        model.addAttribute("opportunities_registered", opportunitySvc.getByRegistered(userId));
        model.addAttribute("opportunities_submitted", opportunitySubmittedSvc.getBySubmitted(userId));
        model.addAttribute("userId", userId);
 		return "redirect:main";
    }
    
    @RequestMapping(value="/deregister")
    public String deregisterForOpp(HttpServletRequest request, SessionStatus status, Model model)
    {
    	Integer userId = Integer.parseInt(request.getParameter("userId"));
    	Integer oppId = Integer.parseInt(request.getParameter("oppId"));
    	
    	DuckUser user =  userSvc.findById(userId);
    	Integer opportunityRegisteredId = opportunityRegisteredSvc.getByRegisteredOpportunity(userId, oppId).getId();
    	OpportunityRegistered opportunityRegistered = opportunityRegisteredSvc.findById(opportunityRegisteredId);
    	opportunityRegisteredSvc.remove(opportunityRegistered);
		
        model.addAttribute("user", user);
        model.addAttribute("opportunities", opportunitySvc.getByCreator(userId));
        model.addAttribute("opportunities_registered", opportunitySvc.getByRegistered(userId));
        model.addAttribute("opportunities_submitted", opportunitySubmittedSvc.getBySubmitted(userId));
        model.addAttribute("userId", userId);
 		return "redirect:main";
    }
    
    @RequestMapping(value="/createopp", method = RequestMethod.GET)
    public String getCreateOpp(HttpServletRequest request, Model model)
    {
    	 Integer userId = Integer.parseInt(request.getParameter("userId"));
    	 Opportunity opportunityForm = new Opportunity();
    	 model.addAttribute("opportunityForm", opportunityForm);
         opportunityForm.setCreatorId(userId);
         model.addAttribute("userId",userId);
         
         return "createopp";
    }
    
    @RequestMapping(value="/createopp", method = RequestMethod.POST)
    public  String setCreateOpp(@Valid @ModelAttribute("opportunityForm") Opportunity opportunity,
                           BindingResult result, SessionStatus status, HttpServletRequest request, Model model)
    {
    		
    	if(result.hasErrors()) {
            return "createopp";
        }
         
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        boolean error = false;
        
        if(opportunity.getOpportunityType().isEmpty()){
            result.rejectValue("opportunityType", "error.opportunitytype");
            error = true;
        }
         
        if(opportunity.getOpportunityTitle().isEmpty()){
            result.rejectValue("opportunityTitle", "error.opportunitytitle");
            error = true;
        }
         
        if(opportunity.getDuckbills() == null) {
            result.rejectValue("duckbills", "error.duckbills");
            error = true;
        }
        
        if(opportunity.getRegisterDate() == null) {
            result.rejectValue("registerDate", "error.registerdate");
            error = true;
        }
        
        if(opportunity.getSubmitDate() == null) {
            result.rejectValue("submitDate", "error.submitdate");
            error = true;
        } else {
        	
        }
        
        if(opportunity.getDescription().isEmpty()){
            result.rejectValue("description", "error.description");
            error = true;
        } 
         
        if(error) {
    		return "createopp";
        }
        
    	String opportunitytype = opportunity.getOpportunityType();
		String opportunitytitle = opportunity.getOpportunityTitle();
        BigDecimal duckbills = opportunity.getDuckbills();
        Date registerdate = opportunity.getRegisterDate();
        Date submitdate = opportunity.getSubmitDate();
        String description = opportunity.getDescription().replaceAll("(\r\n|\n)", "<br />");
		
        if (DuckUtilities.isStringPopulated(opportunitytype) && (DuckUtilities.isStringPopulated(opportunitytitle) && (duckbills != null) && (registerdate != null) && (submitdate != null) && (DuckUtilities.isStringPopulated(description)))) {
        	opportunitySvc.persist(new Opportunity(opportunitytype, opportunitytitle, duckbills, registerdate, submitdate, description, userId));
        
        }
       
        status.setComplete();
        
        DuckUser user = userSvc.findById(userId);
        model.addAttribute("user", user);
        model.addAttribute("opportunities", opportunitySvc.getByCreator(userId));
        model.addAttribute("opportunities_registered", opportunitySvc.getByRegistered(userId));
        model.addAttribute("opportunities_submitted", opportunitySubmittedSvc.getBySubmitted(userId));
        model.addAttribute("userId", userId);
		return "redirect:main";
       
    }
    
    @RequestMapping(value="/editopp", method = RequestMethod.GET)
    public String getEditOpp(HttpServletRequest request, Model model)
    {
    	 Integer userId = Integer.parseInt(request.getParameter("userId"));
    	 Integer oppId = Integer.parseInt(request.getParameter("oppId"));
    	 
    	 Opportunity opportunityForm = opportunitySvc.findById(oppId);
    	 model.addAttribute("opportunityForm", opportunityForm);
         model.addAttribute("userId",userId);
         
         return "editopp";
    }
    
    @RequestMapping(value="/editopp", method = RequestMethod.POST)
    public  String setEditOpp(@Valid @ModelAttribute("opportunityForm") Opportunity opportunity,
                           BindingResult result, SessionStatus status, HttpServletRequest request, Model model)
    {
    		
    	if(result.hasErrors()) {
            return "editopp";
        }
         
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        Integer oppId = Integer.parseInt(request.getParameter("userId"));
        Opportunity currentOpportunity = opportunitySvc.findById(oppId);
        
        boolean error = false;
        
        if(opportunity.getOpportunityType().isEmpty()){
            result.rejectValue("opportunityType", "error.opportunitytype");
            error = true;
        }
         
        if(opportunity.getOpportunityTitle().isEmpty()){
            result.rejectValue("opportunityTitle", "error.opportunitytitle");
            error = true;
        }
         
        if(opportunity.getDuckbills() == null) {
            result.rejectValue("duckbills", "error.duckbills");
            error = true;
        }
        
        if(opportunity.getRegisterDate() == null) {
            result.rejectValue("registerDate", "error.registerdate");
            error = true;
        }
        
        if(opportunity.getSubmitDate() == null) {
            result.rejectValue("submitDate", "error.submitdate");
            error = true;
        } else {
        	
        }
        
        if(opportunity.getDescription().isEmpty()){
            result.rejectValue("description", "error.description");
            error = true;
        } 
         
        if(error) {
    		return "editopp";
        }
        
        currentOpportunity.setOpportunityType(opportunity.getOpportunityType());
        currentOpportunity.setOpportunityTitle(opportunity.getOpportunityTitle());
        currentOpportunity.setDuckbills(opportunity.getDuckbills());
        currentOpportunity.setRegisterDate(opportunity.getRegisterDate());
        currentOpportunity.setSubmitDate(opportunity.getSubmitDate());
        currentOpportunity.setDescription(opportunity.getDescription().replaceAll("(\r\n|\n)", "<br />"));
        
        opportunitySvc.merge(currentOpportunity);
        
        DuckUser user = userSvc.findById(userId);
        model.addAttribute("user", user);
        model.addAttribute("opportunities", opportunitySvc.getByCreator(userId));
        model.addAttribute("opportunities_registered", opportunitySvc.getByRegistered(userId));
        model.addAttribute("opportunities_submitted", opportunitySubmittedSvc.getBySubmitted(userId));
        model.addAttribute("userId", userId);
        
        status.setComplete();
        
		return "redirect:main";
       
    }
    
    @RequestMapping(value="/reviewopp", method = RequestMethod.GET)
    public String getReviewOpp(HttpServletRequest request, Model model)
    {
    	 Integer userId = Integer.parseInt(request.getParameter("userId"));
    	 Integer oppId = Integer.parseInt(request.getParameter("oppId"));
    	 
    	 Opportunity opportunity = opportunitySvc.findById(oppId);
    	 model.addAttribute("opportunity", opportunity);
    	 model.addAttribute("submissions", opportunitySubmittedSvc.getByOpportunity(oppId));
    	 model.addAttribute("reviewIssues", opportunityReviewIssueSvc.getByOpportunitySubmitted(oppId));
         model.addAttribute("userId",userId);
         model.addAttribute("oppId",oppId);
         
         return "reviewopp";
    }
    
    @RequestMapping(value="/editreview", method = RequestMethod.GET)
    public String getEditReviewOpp(HttpServletRequest request, Model model)
    {
    	 Integer userId = Integer.parseInt(request.getParameter("userId"));
    	 Integer oppId = Integer.parseInt(request.getParameter("oppId"));
    	 Integer subId = Integer.parseInt(request.getParameter("subId"));
    	 String currStatus = request.getParameter("currStatus");
    	 
    	 Opportunity opportunity = opportunitySvc.findById(oppId);
    	 model.addAttribute("opportunity", opportunity);
    	 model.addAttribute("submissions", opportunitySubmittedSvc.getByOpportunity(oppId));
    	 model.addAttribute("subId",subId);
    	 model.addAttribute("oppId",oppId);
    	 model.addAttribute("userId",userId);
         model.addAttribute("targetId",subId);
         model.addAttribute("currStatus",currStatus);
         
         return "editreview";
    }
    
    @RequestMapping(value="/savereview", method = RequestMethod.POST)
    public String getSaveReviewOpp(@RequestParam("reviewStatus") String reviewStatus, 
   		 @RequestParam("userId") Integer userId, @RequestParam("oppId") Integer oppId, 
   		 @RequestParam("subId") Integer subId,HttpServletResponse response, 
		 SessionStatus status, Model model)
    {
    	 
    	 OpportunitySubmitted opportunitySubmitted = opportunitySubmittedSvc.findById(subId);
    	 opportunitySubmitted.setStatus(reviewStatus);
    	 opportunitySubmittedSvc.merge(opportunitySubmitted);
    	 
    	 Opportunity opportunity = opportunitySvc.findById(oppId);
    	 model.addAttribute("opportunity", opportunity);
    	 model.addAttribute("submissions", opportunitySubmittedSvc.getByOpportunity(oppId));
    	 model.addAttribute("subId",subId);
    	 model.addAttribute("oppId",oppId);
    	 model.addAttribute("userId",userId);
         
         return "reviewopp";
    }
    
    @RequestMapping(value="/addissue", method = RequestMethod.GET)
    public String getReviewIssue(HttpServletRequest request, Model model)
    {
    	 Integer userId = Integer.parseInt(request.getParameter("userId"));
    	 Integer oppId = Integer.parseInt(request.getParameter("oppId"));
    	 Integer subId = Integer.parseInt(request.getParameter("subId"));
    	 Integer location = Integer.parseInt(request.getParameter("loc"));
    	 
    	 OpportunityReviewIssue reviewIssueForm = new OpportunityReviewIssue();
    	 model.addAttribute("reviewIssueForm", reviewIssueForm);
    	 
    	 ReviewIssue issueForm = new ReviewIssue();
    	 model.addAttribute("issueForm", issueForm);
    	 
    	 Opportunity opportunity = opportunitySvc.findById(oppId);
    	 OpportunitySubmitted opportunitySubmitted = opportunitySubmittedSvc.findById(subId);
    	 
    	 model.addAttribute("opportunity", opportunity);
    	 model.addAttribute("opportunitySubmitted", opportunitySubmitted);
    	 model.addAttribute("issueList", issueSvc.getAll());
    	 model.addAttribute("oppId",oppId);
    	 model.addAttribute("subId",subId);
    	 model.addAttribute("userId",userId);
    	 model.addAttribute("loc",location);
         
         return "addissue";
    }
    
    @RequestMapping(value="/addissue", method = RequestMethod.POST)
    public String saveReviewIssue(@RequestParam("userId") Integer userId, 
    	 @RequestParam("oppId") Integer oppId, @RequestParam("subId") Integer subId,
    	 @RequestParam("formname") String formname,@RequestParam("loc") Integer loc,
   		 @ModelAttribute("reviewIssueForm") OpportunityReviewIssue opportunityReviewIssue,
   		 @ModelAttribute("reviewIssue") ReviewIssue reviewIssue,
   		 HttpServletResponse response, SessionStatus status, Model model)
    { 
    	
    	OpportunityReviewIssue reviewIssueForm = new OpportunityReviewIssue();
	   	model.addAttribute("reviewIssueForm", reviewIssueForm);
	   	 
	   	ReviewIssue issueForm = new ReviewIssue();
	   	model.addAttribute("issueForm", issueForm);
	   	 
	   	Opportunity opportunity = opportunitySvc.findById(oppId);
	   	OpportunitySubmitted opportunitySubmitted = opportunitySubmittedSvc.findById(subId);
	   	 
	   	model.addAttribute("opportunity", opportunity);
	   	model.addAttribute("opportunitySubmitted", opportunitySubmitted);
	   	model.addAttribute("oppId",oppId);
	   	model.addAttribute("subId",subId);
	   	model.addAttribute("userId",userId);
    	 
    	if (formname.equalsIgnoreCase("issue")){
    		issueSvc.persist(reviewIssue);
    		model.addAttribute("issueList", issueSvc.getAll());
    		return "addissue";
    	} 
        	
    	opportunityReviewIssue.setCreationDate(Date.from(LocalDateTime.now().toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        opportunityReviewIssue.setOpportunitySubmitted(opportunitySubmitted);
        opportunityReviewIssueSvc.persist(opportunityReviewIssue);
    	
   	 	model.addAttribute("submissions", opportunitySubmittedSvc.getByOpportunity(oppId));
   	    model.addAttribute("reviewIssues", opportunityReviewIssueSvc.getByOpportunitySubmitted(subId));
       
   	   if (loc > 0) {
   		return "redirect:reviewoppissues";
   	   }
   	   
       return "redirect:reviewopp";
    }
    
    @RequestMapping(value="/reviewoppissues", method = RequestMethod.GET)
    public String getReviewIssues(HttpServletRequest request, Model model)
    {
    	 Integer userId = Integer.parseInt(request.getParameter("userId"));
    	 Integer oppId = Integer.parseInt(request.getParameter("oppId"));
    	 Integer subId = Integer.parseInt(request.getParameter("subId"));
    	 
    	 Opportunity opportunity = opportunitySvc.findById(oppId);
    	 model.addAttribute("opportunity", opportunity);
    	 model.addAttribute("submission", opportunitySubmittedSvc.findById(subId));
    	 model.addAttribute("reviewIssues", opportunityReviewIssueSvc.getByOpportunitySubmittedExtended(subId));
    	 model.addAttribute("subId",subId);
    	 model.addAttribute("oppId",oppId);
    	 model.addAttribute("userId",userId);
         
         return "reviewoppissues";
    }
    
    @RequestMapping(value="/editissue", method = RequestMethod.GET)
    public String getEditReviewIssue(HttpServletRequest request, Model model)
    {
    	 Integer userId = Integer.parseInt(request.getParameter("userId"));
    	 Integer oppId = Integer.parseInt(request.getParameter("oppId"));
    	 Integer subId = Integer.parseInt(request.getParameter("subId"));
    	 Integer reviewId = Integer.parseInt(request.getParameter("reviewId"));
    	 
    	 OpportunityReviewIssue reviewIssueForm = opportunityReviewIssueSvc.findById(reviewId);
    	 model.addAttribute("reviewIssueForm", reviewIssueForm);
    	 
    	 ReviewIssue issueForm = new ReviewIssue();
    	 model.addAttribute("issueForm", issueForm);
    	 
    	 Opportunity opportunity = opportunitySvc.findById(oppId);
    	 OpportunitySubmitted opportunitySubmitted = opportunitySubmittedSvc.findById(subId);
    	 
    	 model.addAttribute("opportunity", opportunity);
    	 model.addAttribute("opportunitySubmitted", opportunitySubmitted);
    	 model.addAttribute("issueList", issueSvc.getAll());
    	 model.addAttribute("oppId",oppId);
    	 model.addAttribute("subId",subId);
    	 model.addAttribute("userId",userId);
         
         return "editissue";
    }
    
    @RequestMapping(value="/editissue", method = RequestMethod.POST)
    public String saveEditReviewIssue(@RequestParam("userId") Integer userId, 
    	 @RequestParam("oppId") Integer oppId, @RequestParam("subId") Integer subId,
    	 @RequestParam("formname") String formname, @RequestParam("reviewId")  Integer reviewId,
   		 @ModelAttribute("reviewIssueForm") OpportunityReviewIssue opportunityReviewIssue,
   		 @ModelAttribute("reviewIssue") ReviewIssue reviewIssue,
   		 HttpServletResponse response, SessionStatus status, Model model)
    { 
    	
	   	ReviewIssue issueForm = new ReviewIssue();
	   	model.addAttribute("issueForm", issueForm);
	   	 
	   	Opportunity opportunity = opportunitySvc.findById(oppId);
	   	OpportunitySubmitted opportunitySubmitted = opportunitySubmittedSvc.findById(subId);
	   	 
	   	model.addAttribute("opportunity", opportunity);
	   	model.addAttribute("opportunitySubmitted", opportunitySubmitted);
	   	model.addAttribute("oppId",oppId);
	   	model.addAttribute("subId",subId);
	   	model.addAttribute("userId",userId);
    	 
    	if (formname.equalsIgnoreCase("issue")){
    		issueSvc.persist(reviewIssue);
    		model.addAttribute("issueList", issueSvc.getAll());
    		return "editissue";
    	} 
        	
    	OpportunityReviewIssue curropportunityReviewIssue = opportunityReviewIssueSvc.findById(reviewId);
    	curropportunityReviewIssue.setComment(opportunityReviewIssue.getComment());
    	curropportunityReviewIssue.setIssueId(opportunityReviewIssue.getIssueId());
    	curropportunityReviewIssue.setResolutionDate(opportunityReviewIssue.getResolutionDate());
    	opportunityReviewIssueSvc.merge(curropportunityReviewIssue);
        
    
    	 model.addAttribute("submission", opportunitySubmittedSvc.findById(subId));
    	 model.addAttribute("reviewIssues", opportunityReviewIssueSvc.getByOpportunitySubmitted(subId));
         
         return "redirect:reviewoppissues";

    }
    
    @RequestMapping(value="/deleteissue")
    public String deregisterForOpp(@RequestParam("userId") Integer userId, 
       	 @RequestParam("oppId") Integer oppId, @RequestParam("subId") Integer subId,
       	 @RequestParam("reviewId")  Integer reviewId,
      	 @ModelAttribute("reviewIssueForm") OpportunityReviewIssue opportunityReviewIssue,
      	 @ModelAttribute("reviewIssue") ReviewIssue reviewIssue,
      	 HttpServletResponse response, SessionStatus status, Model model)
    {
    	
    	OpportunityReviewIssue curropportunityReviewIssue = opportunityReviewIssueSvc.findById(reviewId);
    	opportunityReviewIssueSvc.remove(curropportunityReviewIssue);
    	
    	Opportunity opportunity = opportunitySvc.findById(oppId);
    	
    	 List<OpportunityReviewIssue> list = opportunityReviewIssueSvc.getByOpportunitySubmitted(subId);
    	 if (list.size() > 0) {
    		 model.addAttribute("opportunity", opportunity);
    		 model.addAttribute("submission", opportunitySubmittedSvc.findById(subId));
        	 model.addAttribute("reviewIssues", opportunityReviewIssueSvc.getByOpportunitySubmitted(subId));
        	 model.addAttribute("subId",subId);
        	 model.addAttribute("oppId",oppId);
        	 model.addAttribute("userId",userId);
             
             return "reviewoppissues";
    	 } else {
        	 model.addAttribute("opportunity", opportunity);
        	 model.addAttribute("submissions", opportunitySubmittedSvc.getByOpportunity(oppId));
        	 model.addAttribute("subId",subId);
        	 model.addAttribute("oppId",oppId);
        	 model.addAttribute("userId",userId);
             
             return "redirect:reviewopp";
    	 }
    	 
    }
    
}
