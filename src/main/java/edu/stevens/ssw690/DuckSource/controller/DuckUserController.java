package edu.stevens.ssw690.DuckSource.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import edu.stevens.ssw690.DuckSource.model.DuckUser;
import edu.stevens.ssw690.DuckSource.service.DuckUserManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityManager;
import edu.stevens.ssw690.DuckSource.utilities.DuckUtilities;


@Controller
@SessionAttributes("user")
public class DuckUserController extends MultiActionController {
	
	@Autowired
	DuckUserManager userSvc;
	
	@Autowired
	OpportunityManager opportunitySvc;
 
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
	public ModelAndView postSignup(HttpServletRequest request) {
		
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        DuckUser user = new DuckUser();
        user.setLastName(lastname);
        user.setFirstName(firstname);
        user.setEmailAddress(email);
        user.setUserName(username);
        user.setPassword(password);
       
        if ((DuckUtilities.isStringPopulated(firstname) && (DuckUtilities.isStringPopulated(lastname)) && (DuckUtilities.isStringPopulated(email)) && (DuckUtilities.isStringPopulated(username)) && (DuckUtilities.isStringPopulated(password)))) {
            userSvc.persist(user);
            Integer creator = user.getId();
            ModelAndView modelview = new ModelAndView("main");
    		modelview.addObject("user", firstname + " " + lastname);
    		modelview.addObject("opportunties", opportunitySvc.getByCreator(creator));
    		modelview.addObject("userId", creator);
    		return modelview;
        } else {
        	ModelAndView modelview = new ModelAndView("signup");
        	return modelview;
        }
    }
	
	@RequestMapping(value="/login")
	public ModelAndView login(HttpServletRequest request) {
      
        String username = request.getParameter("username");
        String password = request.getParameter("password");
 
        // Redirect to error
        if (username != null && password !=null) {
        	DuckUser user =userSvc.getDuckUser(username, password);
        	if (user == null) {
        		return new ModelAndView("loginerror", "DuckUserDao", userSvc );
        	} else {
        		Integer creator = user.getId();
        		ModelAndView modelview = new ModelAndView("main", "opportunityDao", opportunitySvc);
        		modelview.addObject("user", user.getFirstName() + " " + user.getLastName());
        		modelview.addObject("opportunties", opportunitySvc.getByCreator(creator));
        		modelview.addObject("userId", creator);
        		return modelview;
        	}
        } else {
        	return new ModelAndView("login", "duckUserDao", userSvc);
        }
    }
	
}
