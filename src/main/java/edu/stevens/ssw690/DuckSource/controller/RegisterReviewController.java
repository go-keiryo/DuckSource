package edu.stevens.ssw690.DuckSource.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import edu.stevens.ssw690.DuckSource.model.DuckUser;
import edu.stevens.ssw690.DuckSource.model.Opportunity;
import edu.stevens.ssw690.DuckSource.model.OpportunityRegistered;
import edu.stevens.ssw690.DuckSource.model.OpportunityReviewIssue;
import edu.stevens.ssw690.DuckSource.model.OpportunitySubmitted;
import edu.stevens.ssw690.DuckSource.model.ReviewIssue;
import edu.stevens.ssw690.DuckSource.service.DuckUserManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityRegisteredManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityReviewIssueManager;
import edu.stevens.ssw690.DuckSource.service.OpportunitySubmittedManager;
import edu.stevens.ssw690.DuckSource.service.ReviewIssueManager;

@Controller
@SessionAttributes("registerreview")
public class RegisterReviewController extends MultiActionController {

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
    	
    	OpportunityRegistered opportunityRegistered = opportunityRegisteredSvc.getByRegisteredOpportunity(userId, oppId);
    	opportunityRegistered = opportunityRegisteredSvc.findById(opportunityRegistered.getId());
    	opportunityRegisteredSvc.remove(opportunityRegistered);
		
    	DuckUser user =  userSvc.findById(userId);
    	model.addAttribute("user", user);
        model.addAttribute("opportunities", opportunitySvc.getByCreator(userId));
        model.addAttribute("opportunities_registered", opportunitySvc.getByRegistered(userId));
        model.addAttribute("opportunities_submitted", opportunitySubmittedSvc.getBySubmitted(userId));
        model.addAttribute("userId", userId);
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
    	 model.addAttribute("reviewIssues", opportunityReviewIssueSvc.getByOpportunity(oppId)); 
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
    	 model.addAttribute("reviewIssues", opportunityReviewIssueSvc.getByOpportunity(oppId));
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
   		 BindingResult reviewResult, @ModelAttribute("reviewIssue") ReviewIssue reviewIssue,
   		 BindingResult issueResult, HttpServletResponse response, SessionStatus status, Model model)
    { 
    	   
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
	   	model.addAttribute("loc",loc);
    	 
    	if (formname.equalsIgnoreCase("issue")){
    		boolean issueError = false;
    		if(issueResult.hasErrors()) {
	            return "addissue";
	        }
    		if (reviewIssue.getIssueTitle().isEmpty()) {
    			 issueResult.rejectValue("issueTitle", "error.issuetitle");
    			 issueError = true;
    		}
    		if (reviewIssue.getDescription().isEmpty()) {
   			 issueResult.rejectValue("description", "error.issuedescription");
   			 issueError = true;
    		}
    		if(issueError) {
        		return "addissue";
            }
    		issueSvc.persist(reviewIssue);
    		model.addAttribute("issueList", issueSvc.getAll());
    		return "addissue";
    	}
    	
    	boolean reviewError = false;
		if(reviewResult.hasErrors()) {
            return "addissue";
        }
		
		if (opportunityReviewIssue.getIssueId() == null ) {
			reviewResult.rejectValue("issueId", "error.reviewissueid");
			 reviewError = true;
		}
		
		if (reviewError) {
   		return "addissue";
       }
        	
    	opportunityReviewIssue.setCreationDate(Date.from(LocalDateTime.now().toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        opportunityReviewIssue.setOpportunitySubmitted(opportunitySubmitted);
        opportunityReviewIssueSvc.persist(opportunityReviewIssue);
    	
   	 	model.addAttribute("submissions", opportunitySubmittedSvc.findById(subId));
   	    model.addAttribute("reviewIssues", opportunityReviewIssueSvc.getByOpportunitySubmittedExtended(subId));
       
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
   		 BindingResult reviewResult, @ModelAttribute("reviewIssue") ReviewIssue reviewIssue, 
   		 BindingResult issueResult, HttpServletResponse response, SessionStatus status, Model model)
    
    { 
    	
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
    	 
    	if (formname.equalsIgnoreCase("issue")){
    		boolean issueError = false;
    		if(issueResult.hasErrors()) {
	            return "editissue";
	        }
    		if (reviewIssue.getIssueTitle().isEmpty()) {
    			 issueResult.rejectValue("issueTitle", "error.issuetitle");
    			 issueError = true;
    		}
    		if (reviewIssue.getDescription().isEmpty()) {
   			 issueResult.rejectValue("description", "error.issuedescription");
   			 issueError = true;
    		}
    		if(issueError) {
        		return "editissue";
            }
    		issueSvc.persist(reviewIssue);
    		model.addAttribute("issueList", issueSvc.getAll());
    		return "editissue";
    	} 
    	
    	boolean reviewError = false;
		if(reviewResult.hasErrors()) {
            return "editissue";
        }
		
		if (opportunityReviewIssue.getIssueId() == null ) {
			reviewResult.rejectValue("issueId", "error.reviewissueid");
			 reviewError = true;
		}
		
		if (reviewError) {
			return "editissue";
       }
        	
    	OpportunityReviewIssue curropportunityReviewIssue = opportunityReviewIssueSvc.findById(reviewId);
    	curropportunityReviewIssue.setComment(opportunityReviewIssue.getComment());
    	curropportunityReviewIssue.setIssueId(opportunityReviewIssue.getIssueId());
    	curropportunityReviewIssue.setResolutionDate(opportunityReviewIssue.getResolutionDate());
    	opportunityReviewIssueSvc.merge(curropportunityReviewIssue);
        
    
    	 model.addAttribute("submission", opportunitySubmittedSvc.findById(subId));
    	 model.addAttribute("reviewIssues", opportunityReviewIssueSvc.getByOpportunitySubmittedExtended(subId));
         
         return "redirect:reviewoppissues";

    }
    
    @RequestMapping(value="/deleteissue")
    public String deleteissue(@RequestParam("userId") Integer userId, 
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
        	 model.addAttribute("reviewIssues", opportunityReviewIssueSvc.getByOpportunitySubmittedExtended(subId));
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
