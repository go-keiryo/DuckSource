package edu.stevens.ssw690.DuckSource.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import edu.stevens.ssw690.DuckSource.model.DuckUser;
import edu.stevens.ssw690.DuckSource.service.DuckUserManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityManager;
import edu.stevens.ssw690.DuckSource.service.OpportunitySubmittedManager;
import edu.stevens.ssw690.DuckSource.utilities.EmailValidator;


@Controller
@SessionAttributes("index")
public class IndexController extends MultiActionController {
	
	@Autowired
	DuckUserManager duckUserService ;
	
	@Autowired
	OpportunityManager opportunityService;
	
	@Autowired
	OpportunitySubmittedManager opportunitySubmittedService;
	
	private static EmailValidator emailValidator;
 
	/**
	 * Gets a list of all Users
	 * @return list of DuckUser objects
	 */
	@ModelAttribute("allDuckUsers")
	public List<DuckUser> populateDuckUsers()
    {
       List<DuckUser> duckusers = duckUserService.getAll();
       return duckusers;
    }
	
	/**
	 * Gets the sign up page
	 * @param request
	 * @param model
	 * @return signup.jsp
	 */
	@RequestMapping(value="/signup", method = RequestMethod.GET)
	 public String getSignup(HttpServletRequest request, Model model) {
		DuckUser userForm = new DuckUser();
        model.addAttribute("userForm", userForm);
        return "signup";
    }
	
	/**
	 * Validates and saves new user sign up
	 * @param user
	 * @param result
	 * @param status
	 * @param model
	 * @return signup.jsp if error, otherwise main.jsp
	 */
	@RequestMapping(value="/signup", method = RequestMethod.POST)
	public String postSignup(@ModelAttribute("userForm") DuckUser user,
            BindingResult result, SessionStatus status, Model model) {
		
		if(result.hasErrors()) {
            return "signup";
        }
		
		boolean error = false;
		
		if (user.getFirstName().isEmpty()){
            result.rejectValue("firstName", "error.firstname");
            error = true;
        }
		if (user.getFirstName().isEmpty()){
            result.rejectValue("lastName", "error.lastname");
            error = true;
        }
		if (user.getEmailAddress().isEmpty()){
            result.rejectValue("emailAddress", "error.emailaddress");
            error = true;
        } else {
        	 emailValidator = new EmailValidator();
        	 if (!emailValidator.validateEmail(user.getEmailAddress())){
        		 result.rejectValue("emailAddress", "error.emailaddressinvalid");
            	 error = true;
             }
        }
		
		
		if(user.getUserName().isEmpty()){
            result.rejectValue("userName", "error.username");
            error = true;
        } else {
        	if (duckUserService.getUsernameExists(user.getUserName())) {
        		result.rejectValue("userName", "error.usernameinuse");
        		error = true;
        	}
        }
		
		if(user.getPassword().isEmpty()){
            result.rejectValue("password", "error.password");
            error = true;
        }
        
		if(user.getConfirmPassword().isEmpty()){
            result.rejectValue("confirmPassword", "error.confirmpassword");
            error = true;
		}
		
		if ((!user.getPassword().isEmpty()) && (!user.getConfirmPassword().isEmpty()) && (!user.getPassword().equalsIgnoreCase(user.getConfirmPassword()))) {
			result.rejectValue("confirmPassword", "error.password2");
            error = true;
		}
	
		if(error) {
    		return "signup";
        }
		
        user.setRegistrationDate(Date.from(LocalDateTime.now().toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
       
        duckUserService.persist(user);
        Integer userId = user.getId();
		model.addAttribute("user", user);
		model.addAttribute("opportunities", opportunityService.getByCreator(userId));
		model.addAttribute("opportunities_registered", opportunityService.getByRegistered(userId));
		model.addAttribute("opportunities_submitted", opportunitySubmittedService.getBySubmitted(userId));
		model.addAttribute("userId", userId);
		
		return "redirect:main";
    	
    }
	
	/**
	 * Gets index page with and all opportunities and users
	 * @param request
	 * @param model
	 * @return index.jsp
	 */
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String returntIndex(HttpServletRequest request, Model model) {
		model.addAttribute("opportunities", opportunityService.getAllOpportunities());
		model.addAttribute("users", duckUserService.getAll());
       return "../index";
   }

	/**
	 * Gets index page with and all opportunities and users
	 * @param request
	 * @param model
	 * @return index.jsp
	 */
	@RequestMapping(value="/", method = RequestMethod.GET)
	 public String getIndex(HttpServletRequest request, Model model) {
		model.addAttribute("opportunities", opportunityService.getAllOpportunities());
		model.addAttribute("users", duckUserService.getAll());
       return "../index";
   }

	/**
	 * Gets index page with selected type of opportunity and all users
	 * @param request
	 * @param model
	 * @return index.jsp
	 */
	@RequestMapping(value="/indexupdate")
	 public String getIndexUpdate(HttpServletRequest request, Model model) {
		String selectType = request.getParameter("select");
		if (selectType.isEmpty() || selectType.equalsIgnoreCase("All Types")) {
			model.addAttribute("opportunities", opportunityService.getAllOpportunities());
		} else {
			model.addAttribute("opportunities", opportunityService.getByType(selectType));
		}
		model.addAttribute("users", duckUserService.getAll());
		
      return "../index";
   }
	
	/**
	 * Gets the about page from the index page
	 * @param request
	 * @param model
	 * @return indexabout.jsp
	 */
	@RequestMapping(value="/indexabout", method = RequestMethod.GET)
	public String getIndexAbout(HttpServletRequest request, Model model) {
		return "indexabout";
    }
	
	/**
	 * Gets the reset password page
	 * @param request
	 * @param model
	 * @return resetpassword.jsp
	 */
	@RequestMapping(value="/resetpassword", method = RequestMethod.GET)
	public String getReset(HttpServletRequest request, Model model) {
		return "resetpassword";
	}
	
	/**
	 * Gets the about page with userId
	 * @param request
	 * @param model
	 * @return about.jsp
	 */
	@RequestMapping(value="/about", method = RequestMethod.GET)
	public String getAbout(HttpServletRequest request, Model model) {
		String userId = request.getParameter("userId");
		model.addAttribute("userId", userId);
		return "about";
    }
	
	/**
	 * Gets the main (home) page with user, and opportunities registered and submitted
	 * @param request
	 * @param model
	 * @return main.jsp
	 */
	@RequestMapping(value="/main", method = RequestMethod.GET)
	 public String getMain(HttpServletRequest request, Model model) {
		String userid = request.getParameter("userId");
		if (userid.isEmpty()) {
			return "redirect:../index";
		} else {
			Integer userId = Integer.parseInt(userid);
			DuckUser user =duckUserService.findById(userId);
			model.addAttribute("user", user);
    		model.addAttribute("opportunities", opportunityService.getByCreator(userId));
    		model.addAttribute("opportunities_registered", opportunityService.getByRegistered(userId));
    		model.addAttribute("opportunities_submitted", opportunitySubmittedService.getBySubmitted(userId));
    		model.addAttribute("userId", userId);
    		return "main";
		}
  }
	
	/**
	 * Validates user name and password on index page
	 * @param request
	 * @param model
	 * @return index.jsp if error otherwise main.jsp
	 */
	@RequestMapping(value="/main", method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model) {
      
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        String selectType = "All types";
        if (request.getParameter("select") != null){
        	selectType = request.getParameter("select");
        }
        	 		
        if (username != null && password !=null) {
        	DuckUser user = duckUserService.getDuckUser(username, password);
        	if (user == null) {
        		if (selectType.isEmpty() || selectType.equalsIgnoreCase("All Types")) {
        			model.addAttribute("opportunities", opportunityService.getAllOpportunities());
        		} else {
        			model.addAttribute("opportunities", opportunityService.getByType(selectType));
        		}
        		model.addAttribute("users", duckUserService.getAll());
        		model.addAttribute("message", "Invalid Username or Password");
        		return "../index";
        	} else {
        		Integer userId = user.getId();
        		model.addAttribute("user", user);
        		model.addAttribute("opportunities", opportunityService.getByCreator(userId));
        		model.addAttribute("opportunities_registered", opportunityService.getByRegistered(userId));
        		model.addAttribute("opportunities_submitted", opportunitySubmittedService.getBySubmitted(userId));
        		model.addAttribute("userId", userId);
        		return "main";
        	}
        } else {
    		if (selectType.isEmpty() || selectType.equalsIgnoreCase("All types")) {
    			model.addAttribute("opportunities", opportunityService.getAllOpportunities());
    		} else {
    			model.addAttribute("opportunities", opportunityService.getByType(selectType));
    		}
    		model.addAttribute("users", duckUserService.getAll());
        	return "../index";
        }
    }
	
}
