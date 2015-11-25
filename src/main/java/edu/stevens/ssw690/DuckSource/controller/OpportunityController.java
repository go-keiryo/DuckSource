package edu.stevens.ssw690.DuckSource.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

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
import edu.stevens.ssw690.DuckSource.service.DuckUserManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityRegisteredManager;
import edu.stevens.ssw690.DuckSource.utilities.DuckUtilities;

@Controller
@SessionAttributes("opportunity")
public class OpportunityController extends MultiActionController {

	@Autowired
	OpportunityManager opportunitySvc;
	
	@Autowired
	OpportunityRegisteredManager opportunityRegisteredSvc; 
	
	@Autowired
	DuckUserManager userSvc;
    
	@ModelAttribute("allOpportunities")
	public List<Opportunity> populateOpportunities()
    {
       List<Opportunity> opportunities = opportunitySvc.getAllOpportunities();
       return opportunities;
    }
	 
    @RequestMapping(value="/createopp", method = RequestMethod.GET)
    public String setupForm(@RequestParam("creatorId") Integer creatorId, Model model)
    {
         Opportunity opportunityForm = new Opportunity();
         opportunityForm.setCreatorId(creatorId);
         model.addAttribute("userId",creatorId);
         model.addAttribute("opportunityForm", opportunityForm);
         
         return "createopp";
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
    public String setupFindForm(@RequestParam("creatorId") Integer creatorId, Model model) 
    {
		model.addAttribute("opportunities", opportunitySvc.getByOtherThanCreator(creatorId));
		model.addAttribute("userId", creatorId);
		return "findopp";
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
		
        model.addAttribute("user", user.getFirstName() + " " + user.getLastName());
        model.addAttribute("opportunties", opportunitySvc.getByCreator(userId));
        model.addAttribute("opportunities_registered", opportunitySvc.getByRegistered(userId));
        model.addAttribute("userId", userId);
 		return "main";
    }
    
     
    @RequestMapping(value="/createopp", method = RequestMethod.POST)
    public  String submitForm(@ModelAttribute("opportunityForm") Opportunity opportunity,
                           BindingResult result, SessionStatus status, Model model)
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
    		return "createopp";
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
        Integer userId = user.getId();
        model.addAttribute("user", user.getFirstName() + " " + user.getLastName());
        model.addAttribute("opportunities", opportunitySvc.getByCreator(userId));
        model.addAttribute("opportunities_registered", opportunitySvc.getByRegistered(userId));
        model.addAttribute("userId", userId);
		return "main";
       
    }
    
}
