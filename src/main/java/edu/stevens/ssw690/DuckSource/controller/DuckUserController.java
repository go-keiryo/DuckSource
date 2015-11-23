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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import edu.stevens.ssw690.DuckSource.model.DuckUser;
import edu.stevens.ssw690.DuckSource.model.Opportunity;
import edu.stevens.ssw690.DuckSource.service.DuckUserManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityManager;
import edu.stevens.ssw690.DuckSource.utilities.DuckUtilities;
import edu.stevens.ssw690.DuckSource.utilities.EmailValidator;


@Controller
@SessionAttributes("user")
public class DuckUserController extends MultiActionController {
	
	@Autowired
	DuckUserManager userSvc;
	
	@Autowired
	OpportunityManager opportunitySvc;
	
	private static EmailValidator emailValidator;
 
	@ModelAttribute("allDuckUsers")
	public List<DuckUser> populateDuckUsers()
    {
       List<DuckUser> duckosers = userSvc.getAll();
       return duckosers;
    }
	
	@RequestMapping(value="/signup", method = RequestMethod.GET)
	 public String getSignup(HttpServletRequest request, Model model) {
		DuckUser userForm = new DuckUser();
        model.addAttribute("userForm", userForm);
        return "signup";
    }
	
	@RequestMapping(value="/signup", method = RequestMethod.POST)
	public String postSignup(@ModelAttribute("userForm") DuckUser user,
            BindingResult result, SessionStatus status, Model model) {
		
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
       
        userSvc.persist(user);
        Integer creator = user.getId();
		model.addAttribute("user", user.getFirstName() + " " + user.getLastName());
		model.addAttribute("opportunties", opportunitySvc.getByCreator(creator));
		model.addAttribute("userId", creator);
		
		return "main";
    	
    }
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	 public String getIndex(HttpServletRequest request, Model model) {
		model.addAttribute("opportunties", opportunitySvc.getAllOpportunities());
       return "../index";
   }

	@RequestMapping(value="/indexupdate")
	 public String iIndexUpdarw(HttpServletRequest request, Model model) {
		String selectType = request.getParameter("select");
		if (selectType.isEmpty() || selectType.equalsIgnoreCase("All Types")) {
			model.addAttribute("opportunties", opportunitySvc.getAllOpportunities());
		} else {
			model.addAttribute("opportunties", opportunitySvc.getByType(selectType));
		}
		
      return "../index";
   }
	
	@RequestMapping(value="/main", method = RequestMethod.GET)
	 public String getMain(HttpServletRequest request, Model model) {
      return "main";
  }
	
	@RequestMapping(value="/main", method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model) {
      
        String username = request.getParameter("username");
        String password = request.getParameter("password");
 
        // Redirect to error
        if (username != null && password !=null) {
        	DuckUser user =userSvc.getDuckUser(username, password);
        	if (user == null) {
        		model.addAttribute("message", "Invalid Username or Password");
        		return "../index";
        	} else {
        		Integer creator = user.getId();
        		model.addAttribute("user", user.getFirstName() + " " + user.getLastName());
        		model.addAttribute("opportunties", opportunitySvc.getByCreator(creator));
        		model.addAttribute("userId", creator);
        		return "main";
        	}
        } else {
        	return "../index";
        }
    }
	
}
