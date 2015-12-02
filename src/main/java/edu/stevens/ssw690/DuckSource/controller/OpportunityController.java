package edu.stevens.ssw690.DuckSource.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import edu.stevens.ssw690.DuckSource.model.DuckUser;
import edu.stevens.ssw690.DuckSource.model.Opportunity;
import edu.stevens.ssw690.DuckSource.service.DuckUserManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityRegisteredManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityReviewIssueManager;
import edu.stevens.ssw690.DuckSource.service.OpportunitySubmittedManager;
import edu.stevens.ssw690.DuckSource.service.ReviewIssueManager;
import edu.stevens.ssw690.DuckSource.utilities.DuckUtilities;

@Controller
@SessionAttributes("opportunity")
public class OpportunityController extends MultiActionController {
	
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
        String description = opportunity.getDescription().replaceAll("(\r\n|\n)", "<br>");
		
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
        currentOpportunity.setDescription(opportunity.getDescription().replaceAll("(\r\n|\n)", "<br>"));
        
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
	 

}
