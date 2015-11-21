package edu.stevens.ssw690.DuckSource.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import edu.stevens.ssw690.DuckSource.model.DuckUser;
import edu.stevens.ssw690.DuckSource.model.Opportunity;
import edu.stevens.ssw690.DuckSource.service.DuckUserManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityManager;
import edu.stevens.ssw690.DuckSource.utilities.DuckUtilities;

@Controller
@RequestMapping("/createopp")
@SessionAttributes("opportunity")
public class OpportunityController extends MultiActionController {

	@Autowired
	OpportunityManager opportunitySvc;
	
	@Autowired
	DuckUserManager userSvc;
    
	@ModelAttribute("allOpportunities")
	public List<Opportunity> populateOpportunities()
    {
       List<Opportunity> opportunities = opportunitySvc.getAllOpportunities();
       return opportunities;
    }
	 
    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(@RequestParam("creatorId") Integer creatorId, Model model)
    {
         Opportunity opportunityForm = new Opportunity();
         opportunityForm.setCreatorId(creatorId);
         model.addAttribute("opportunityForm", opportunityForm);
         
         return "createopp";
    }
     
    @RequestMapping(method = RequestMethod.POST)
    public  ModelAndView submitForm(@ModelAttribute("opportunityForm") Opportunity opportunity,
                           BindingResult result, SessionStatus status)
    {
    	//Validation code start
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
        }
        
        if(opportunity.getDescription().isEmpty()){
            result.rejectValue("description", "error.description");
            error = true;
        }
         
        if(error) {
        	ModelAndView modelview = new ModelAndView("createopp", "opportunitySvc", opportunitySvc);
    		return modelview;
        }
        
        Integer creatorId = opportunity.getCreatorId();
    	String opportunitytype = opportunity.getOpportunityType();
		String opportunitytitle = opportunity.getOpportunityTitle();
        BigDecimal duckbills = opportunity.getDuckbills();
        Date registerdate = opportunity.getRegisterDate();
        Date submitdate = opportunity.getSubmitDate();
        String description = opportunity.getDescription();
		
        if (DuckUtilities.isStringPopulated(opportunitytype) && (DuckUtilities.isStringPopulated(opportunitytitle) && (duckbills != null) && (registerdate != null) && (submitdate != null) && (DuckUtilities.isStringPopulated(description)))) {
        	opportunitySvc.persist(new Opportunity(opportunitytype, opportunitytitle, duckbills, registerdate, submitdate, description, creatorId));
        
        }
       
        status.setComplete();
        
        DuckUser user = userSvc.findById(creatorId);
        Integer creator = user.getId();
        ModelAndView modelview = new ModelAndView("main", "opportunitySvc", opportunitySvc);
        modelview.addObject("user", user.getFirstName() + " " + user.getLastName());
        modelview.addObject("opportunties", opportunitySvc.getByCreator(creator));
        modelview.addObject("userId", creator);
		return modelview;
       
    }
    
}
