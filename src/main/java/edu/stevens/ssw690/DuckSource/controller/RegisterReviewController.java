package edu.stevens.ssw690.DuckSource.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
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
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import edu.stevens.ssw690.DuckSource.model.DuckUser;
import edu.stevens.ssw690.DuckSource.model.Opportunity;
import edu.stevens.ssw690.DuckSource.model.OpportunityRegistered;
import edu.stevens.ssw690.DuckSource.model.OpportunityReviewIssue;
import edu.stevens.ssw690.DuckSource.model.OpportunitySubmitted;
import edu.stevens.ssw690.DuckSource.model.ReviewIssue;
import edu.stevens.ssw690.DuckSource.service.DuckUserManager;
import edu.stevens.ssw690.DuckSource.service.MailboxManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityRegisteredManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityReviewIssueManager;
import edu.stevens.ssw690.DuckSource.service.OpportunitySubmittedManager;
import edu.stevens.ssw690.DuckSource.service.ReviewIssueManager;

@Controller
@SessionAttributes("registerreview")
@RequestMapping("/")

public class RegisterReviewController extends MultiActionController {

	@Autowired
	OpportunityManager opportunityService;

	@Autowired
	OpportunityRegisteredManager opportunityRegisteredService;

	@Autowired
	OpportunityReviewIssueManager opportunityReviewIssueService;

	@Autowired
	OpportunitySubmittedManager opportunitySubmittedService;

	@Autowired
	DuckUserManager duckUserService;

	@Autowired
	ReviewIssueManager reviewIssueService;

	@Autowired
	ServletContext context;
	
	@Autowired
	MailboxManager mailboxService; 

	/**
	 * Get the user's account page
	 * @param userId
	 * @param model
	 * @param request
	 * @return account.jsp
	 */
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String getAccount(@RequestParam("userId") Integer userId, Model model, HttpServletRequest request) {

		model.addAttribute("userId", userId);
		DuckUser user = duckUserService.findById(userId);
		model.addAttribute("user", user);
		int cnt= mailboxService.getUnreadCount(userId);
		model.addAttribute("unread", "(" + cnt + ")");
		

		String profileImg = user.getProfileImage();

		if (profileImg == null) {
			try {
				BufferedImage bImage = ImageIO.read(new File(context.getRealPath("/") + "resources/images/User1.jpg"));
				request.getSession().setAttribute("profileimage", bImage);
			} catch (IOException e1) {
				request.getSession().setAttribute("profileimage", null);
			}
			return "account";
		}

		String root = context.getRealPath("/");
		String profileImage = root + File.separator + "userdata" + File.separator + user.getProfileImage();

		try {
			BufferedImage bImage = ImageIO.read(new File(profileImage));
			request.getSession().setAttribute("profileimage", bImage);
		} catch (IOException e1) {
			request.getSession().setAttribute("profileimage", null);
		}

		return "account";
	}

	/**
	 * Gets the password change page
	 * @param userId
	 * @param model
	 * @return password.jsp
	 */
	@RequestMapping(value = "/password", method = RequestMethod.GET)
	public String getPassword(@RequestParam("userId") Integer userId, Model model) {
		model.addAttribute("userId", userId);
		DuckUser userForm = new DuckUser();
		userForm.setPassword("");
		model.addAttribute("userForm", userForm);
		return "password";
	}

	/**
	 * Validates and saves the new password
	 * @param request
	 * @param user
	 * @param result
	 * @param status
	 * @param model
	 * @return pasword.jsp if error otherwise account.jsp
	 */
	@RequestMapping(value = "/password", method = RequestMethod.POST)
	public String submitPasswordForm(HttpServletRequest request, @ModelAttribute("userForm") DuckUser user,
			BindingResult result, SessionStatus status, Model model) {
		if (result.hasErrors()) {
			return "password";
		}

		Integer userId = Integer.parseInt(request.getParameter("userId"));
		boolean error = false;

		DuckUser currentUser = duckUserService.findById(userId);
		if (user.getPassword().isEmpty()) {
			result.rejectValue("password", "error.password");
			error = true;
		} else {
			if (!user.getPassword().equalsIgnoreCase(currentUser.getPassword())) {
				result.rejectValue("password", "error.password3");
				error = true;
			}
		}

		if (user.getNewPassword().isEmpty()) {
			result.rejectValue("newPassword", "error.newpassword");
			error = true;
		}

		if (user.getConfirmPassword().isEmpty()) {
			result.rejectValue("confirmPassword", "error.confirmpassword");
			error = true;
		}

		if ((!user.getNewPassword().isEmpty()) && (!user.getConfirmPassword().isEmpty())
				&& (!user.getNewPassword().equalsIgnoreCase(user.getConfirmPassword()))) {
			result.rejectValue("confirmPassword", "error.password2");
			error = true;
		}

		if (error) {
			return "password";
		}

		currentUser.setPassword(user.getNewPassword());
		duckUserService.merge(currentUser);

		status.setComplete();

		model.addAttribute("userId", userId);
		return "redirect:account";

	}

	/**
	 * Registers a user for an opportunity
	 * @param request
	 * @param status
	 * @param model
	 * @return main.jsp
	 */
	@RequestMapping(value = "/register")
	public String registerForOpp(HttpServletRequest request, SessionStatus status, Model model) {
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		Integer oppId = Integer.parseInt(request.getParameter("oppId"));

		OpportunityRegistered opportunityRegistered = new OpportunityRegistered();
		Opportunity opportunity = opportunityService.findById(oppId);
		DuckUser user = duckUserService.findById(userId);
		opportunityRegistered.setRegisteredDate(
				Date.from(LocalDateTime.now().toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		opportunityRegistered.setOpportunity(opportunity);
		opportunityRegistered.setUser(user);
		opportunityRegisteredService.persist(opportunityRegistered);

		model.addAttribute("user", user);
		model.addAttribute("opportunities", opportunityService.getByCreator(userId));
		model.addAttribute("opportunities_registered", opportunityService.getByRegistered(userId));
		model.addAttribute("opportunities_submitted", opportunitySubmittedService.getBySubmitted(userId));
		model.addAttribute("userId", userId);
		return "redirect:main";
	}

	/**
	 * De-registers user from an opportunity
	 * @param request
	 * @param status
	 * @param model
	 * @return main.jsp
	 */
	@RequestMapping(value = "/deregister")
	public String deregisterForOpp(HttpServletRequest request, SessionStatus status, Model model) {
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		Integer oppId = Integer.parseInt(request.getParameter("oppId"));

		OpportunityRegistered opportunityRegistered = opportunityRegisteredService.getByRegisteredOpportunity(userId,
				oppId);
		opportunityRegistered = opportunityRegisteredService.findById(opportunityRegistered.getId());
		opportunityRegisteredService.remove(opportunityRegistered);

		DuckUser user = duckUserService.findById(userId);
		model.addAttribute("user", user);
		model.addAttribute("opportunities", opportunityService.getByCreator(userId));
		model.addAttribute("opportunities_registered", opportunityService.getByRegistered(userId));
		model.addAttribute("opportunities_submitted", opportunitySubmittedService.getBySubmitted(userId));
		model.addAttribute("userId", userId);
		return "redirect:main";
	}

	/**
	 * Gets the review opportunity page
	 * @param request
	 * @param model
	 * @return reviewopp.jsp
	 */
	@RequestMapping(value = "/reviewopp", method = RequestMethod.GET)
	public String getReviewOpp(HttpServletRequest request, Model model) {
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		Integer oppId = Integer.parseInt(request.getParameter("oppId"));

		Opportunity opportunity = opportunityService.findById(oppId);
		model.addAttribute("opportunity", opportunity);
		model.addAttribute("submissions", opportunitySubmittedService.getByOpportunity(oppId));
		model.addAttribute("reviewIssues", opportunityReviewIssueService.getByOpportunity(oppId));
		model.addAttribute("userId", userId);
		model.addAttribute("oppId", oppId);

		return "reviewopp";
	}

	/**
	 * Gets the edit review page
	 * @param request
	 * @param model
	 * @return editreview.jsp
	 */
	@RequestMapping(value = "/editreview", method = RequestMethod.GET)
	public String getEditReviewOpp(HttpServletRequest request, Model model) {
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		Integer oppId = Integer.parseInt(request.getParameter("oppId"));
		Integer subId = Integer.parseInt(request.getParameter("subId"));
		String currStatus = request.getParameter("currStatus");

		Opportunity opportunity = opportunityService.findById(oppId);
		model.addAttribute("opportunity", opportunity);
		model.addAttribute("submissions", opportunitySubmittedService.getByOpportunity(oppId));
		model.addAttribute("subId", subId);
		model.addAttribute("oppId", oppId);
		model.addAttribute("userId", userId);
		model.addAttribute("targetId", subId);
		model.addAttribute("currStatus", currStatus);

		return "editreview";
	}

	/**
	 * Save a review issue to an opportunity
	 * @param reviewStatus
	 * @param userId
	 * @param oppId
	 * @param subId
	 * @param response
	 * @param status
	 * @param model
	 * @return reviewopp.jsp
	 */
	@RequestMapping(value = "/savereview", method = RequestMethod.POST)
	public String getSaveReviewOpp(@RequestParam("reviewStatus") String reviewStatus,
			@RequestParam("userId") Integer userId, @RequestParam("oppId") Integer oppId,
			@RequestParam("subId") Integer subId, HttpServletResponse response, SessionStatus status, Model model) {

		OpportunitySubmitted opportunitySubmitted = opportunitySubmittedService.findById(subId);
		opportunitySubmitted.setStatus(reviewStatus);
		opportunitySubmittedService.merge(opportunitySubmitted);

		Opportunity opportunity = opportunityService.findById(oppId);
		model.addAttribute("opportunity", opportunity);
		model.addAttribute("submissions", opportunitySubmittedService.getByOpportunity(oppId));
		model.addAttribute("reviewIssues", opportunityReviewIssueService.getByOpportunity(oppId));
		model.addAttribute("subId", subId);
		model.addAttribute("oppId", oppId);
		model.addAttribute("userId", userId);

		return "reviewopp";
	}

	/**
	 * Gets the add review issue page
	 * @param request
	 * @param model
	 * @return addissue.jsp
	 */
	@RequestMapping(value = "/addissue", method = RequestMethod.GET)
	public String getReviewIssue(HttpServletRequest request, Model model) {
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		Integer oppId = Integer.parseInt(request.getParameter("oppId"));
		Integer subId = Integer.parseInt(request.getParameter("subId"));
		Integer location = Integer.parseInt(request.getParameter("loc"));

		OpportunityReviewIssue reviewIssueForm = new OpportunityReviewIssue();
		model.addAttribute("reviewIssueForm", reviewIssueForm);

		ReviewIssue issueForm = new ReviewIssue();
		model.addAttribute("issueForm", issueForm);

		Opportunity opportunity = opportunityService.findById(oppId);
		OpportunitySubmitted opportunitySubmitted = opportunitySubmittedService.findById(subId);

		model.addAttribute("opportunity", opportunity);
		model.addAttribute("opportunitySubmitted", opportunitySubmitted);
		model.addAttribute("issueList", reviewIssueService.getAll());
		model.addAttribute("oppId", oppId);
		model.addAttribute("subId", subId);
		model.addAttribute("userId", userId);
		model.addAttribute("loc", location);

		return "addissue";
	}

	/** Validate and save a new review issue
	 * Saves a new 
	 * @param userId
	 * @param oppId
	 * @param subId
	 * @param formname
	 * @param loc
	 * @param opportunityReviewIssue
	 * @param reviewResult
	 * @param reviewIssue
	 * @param issueResult
	 * @param response
	 * @param status
	 * @param model
	 * @return addissue.jsp
	 */
	@RequestMapping(value = "/addissue", method = RequestMethod.POST)
	public String saveReviewIssue(@RequestParam("userId") Integer userId, @RequestParam("oppId") Integer oppId,
			@RequestParam("subId") Integer subId, @RequestParam("formname") String formname,
			@RequestParam("loc") Integer loc,
			@ModelAttribute("reviewIssueForm") OpportunityReviewIssue opportunityReviewIssue,
			BindingResult reviewResult, @ModelAttribute("reviewIssue") ReviewIssue reviewIssue,
			BindingResult issueResult, HttpServletResponse response, SessionStatus status, Model model) {

		OpportunityReviewIssue reviewIssueForm = new OpportunityReviewIssue();
		model.addAttribute("reviewIssueForm", reviewIssueForm);

		ReviewIssue issueForm = new ReviewIssue();
		model.addAttribute("issueForm", issueForm);

		Opportunity opportunity = opportunityService.findById(oppId);
		OpportunitySubmitted opportunitySubmitted = opportunitySubmittedService.findById(subId);

		model.addAttribute("opportunity", opportunity);
		model.addAttribute("opportunitySubmitted", opportunitySubmitted);
		model.addAttribute("issueList", reviewIssueService.getAll());
		model.addAttribute("oppId", oppId);
		model.addAttribute("subId", subId);
		model.addAttribute("userId", userId);
		model.addAttribute("loc", loc);

		if (formname.equalsIgnoreCase("issue")) {
			boolean issueError = false;
			if (issueResult.hasErrors()) {
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
			if (issueError) {
				return "addissue";
			}
			reviewIssueService.persist(reviewIssue);
			model.addAttribute("issueList", reviewIssueService.getAll());
			return "addissue";
		}

		boolean reviewError = false;
		if (reviewResult.hasErrors()) {
			return "addissue";
		}

		if (opportunityReviewIssue.getIssueId() == null) {
			reviewResult.rejectValue("issueId", "error.reviewissueid");
			reviewError = true;
		}

		if (reviewError) {
			return "addissue";
		}

		opportunityReviewIssue.setCreationDate(
				Date.from(LocalDateTime.now().toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		opportunityReviewIssue.setOpportunitySubmitted(opportunitySubmitted);
		opportunityReviewIssueService.persist(opportunityReviewIssue);

		model.addAttribute("submissions", opportunitySubmittedService.findById(subId));
		model.addAttribute("reviewIssues", opportunityReviewIssueService.getByOpportunitySubmittedExtended(subId));

		if (loc > 0) {
			return "redirect:reviewoppissues";
		}

		return "redirect:reviewopp";
	}

	/**
	 * Gets the review issues for an opportunity page
	 * @param request
	 * @param model
	 * @return reviewoppissues.jsp
	 */
	@RequestMapping(value = "/reviewoppissues", method = RequestMethod.GET)
	public String getReviewIssues(HttpServletRequest request, Model model) {
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		Integer oppId = Integer.parseInt(request.getParameter("oppId"));
		Integer subId = Integer.parseInt(request.getParameter("subId"));

		Opportunity opportunity = opportunityService.findById(oppId);
		model.addAttribute("opportunity", opportunity);
		model.addAttribute("submission", opportunitySubmittedService.findById(subId));
		model.addAttribute("reviewIssues", opportunityReviewIssueService.getByOpportunitySubmittedExtended(subId));
		model.addAttribute("subId", subId);
		model.addAttribute("oppId", oppId);
		model.addAttribute("userId", userId);

		return "reviewoppissues";
	}

	/**
	 * Get the edit a review issue for an opportunity page
	 * @param request
	 * @param model
	 * @return editissue.jsp
	 */
	@RequestMapping(value = "/editissue", method = RequestMethod.GET)
	public String getEditReviewIssue(HttpServletRequest request, Model model) {
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		Integer oppId = Integer.parseInt(request.getParameter("oppId"));
		Integer subId = Integer.parseInt(request.getParameter("subId"));
		Integer reviewId = Integer.parseInt(request.getParameter("reviewId"));

		OpportunityReviewIssue reviewIssueForm = opportunityReviewIssueService.findById(reviewId);
		model.addAttribute("reviewIssueForm", reviewIssueForm);

		ReviewIssue issueForm = new ReviewIssue();
		model.addAttribute("issueForm", issueForm);

		Opportunity opportunity = opportunityService.findById(oppId);
		OpportunitySubmitted opportunitySubmitted = opportunitySubmittedService.findById(subId);

		model.addAttribute("opportunity", opportunity);
		model.addAttribute("opportunitySubmitted", opportunitySubmitted);
		model.addAttribute("issueList", reviewIssueService.getAll());
		model.addAttribute("oppId", oppId);
		model.addAttribute("subId", subId);
		model.addAttribute("userId", userId);

		return "editissue";
	}

	/**
	 * Validates and saves an updated review issue for an opportunity
	 * @param userId
	 * @param oppId
	 * @param subId
	 * @param formname
	 * @param reviewId
	 * @param opportunityReviewIssue
	 * @param reviewResult
	 * @param reviewIssue
	 * @param issueResult
	 * @param response
	 * @param status
	 * @param model
	 * @return editissue.jsp if error otherwise reviewoppissues.jsp
	 */
	@RequestMapping(value = "/editissue", method = RequestMethod.POST)
	public String saveEditReviewIssue(@RequestParam("userId") Integer userId, @RequestParam("oppId") Integer oppId,
			@RequestParam("subId") Integer subId, @RequestParam("formname") String formname,
			@RequestParam("reviewId") Integer reviewId,
			@ModelAttribute("reviewIssueForm") OpportunityReviewIssue opportunityReviewIssue,
			BindingResult reviewResult, @ModelAttribute("reviewIssue") ReviewIssue reviewIssue,
			BindingResult issueResult, HttpServletResponse response, SessionStatus status, Model model)

	{

		ReviewIssue issueForm = new ReviewIssue();
		model.addAttribute("issueForm", issueForm);

		Opportunity opportunity = opportunityService.findById(oppId);
		OpportunitySubmitted opportunitySubmitted = opportunitySubmittedService.findById(subId);

		model.addAttribute("opportunity", opportunity);
		model.addAttribute("opportunitySubmitted", opportunitySubmitted);
		model.addAttribute("issueList", reviewIssueService.getAll());
		model.addAttribute("oppId", oppId);
		model.addAttribute("subId", subId);
		model.addAttribute("userId", userId);

		if (formname.equalsIgnoreCase("issue")) {
			boolean issueError = false;
			if (issueResult.hasErrors()) {
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
			if (issueError) {
				return "editissue";
			}
			reviewIssueService.persist(reviewIssue);
			model.addAttribute("issueList", reviewIssueService.getAll());
			return "editissue";
		}

		boolean reviewError = false;
		if (reviewResult.hasErrors()) {
			return "editissue";
		}

		if (opportunityReviewIssue.getIssueId() == null) {
			reviewResult.rejectValue("issueId", "error.reviewissueid");
			reviewError = true;
		}

		if (reviewError) {
			return "editissue";
		}

		OpportunityReviewIssue curropportunityReviewIssue = opportunityReviewIssueService.findById(reviewId);
		curropportunityReviewIssue.setComment(opportunityReviewIssue.getComment());
		curropportunityReviewIssue.setIssueId(opportunityReviewIssue.getIssueId());
		curropportunityReviewIssue.setResolutionDate(opportunityReviewIssue.getResolutionDate());
		opportunityReviewIssueService.merge(curropportunityReviewIssue);

		model.addAttribute("submission", opportunitySubmittedService.findById(subId));
		model.addAttribute("reviewIssues", opportunityReviewIssueService.getByOpportunitySubmittedExtended(subId));

		return "redirect:reviewoppissues";

	}

	/**
	 * Deletes a review issue for an opportunity
	 * @param userId
	 * @param oppId
	 * @param subId
	 * @param reviewId
	 * @param opportunityReviewIssue
	 * @param reviewIssue
	 * @param response
	 * @param status
	 * @param model
	 * @return reviewoppissues.jsp
	 */
	@RequestMapping(value = "/deleteissue")
	public String deleteissue(@RequestParam("userId") Integer userId, @RequestParam("oppId") Integer oppId,
			@RequestParam("subId") Integer subId, @RequestParam("reviewId") Integer reviewId,
			@ModelAttribute("reviewIssueForm") OpportunityReviewIssue opportunityReviewIssue,
			@ModelAttribute("reviewIssue") ReviewIssue reviewIssue, HttpServletResponse response, SessionStatus status,
			Model model) {

		OpportunityReviewIssue curropportunityReviewIssue = opportunityReviewIssueService.findById(reviewId);
		opportunityReviewIssueService.remove(curropportunityReviewIssue);

		Opportunity opportunity = opportunityService.findById(oppId);

		List<OpportunityReviewIssue> list = opportunityReviewIssueService.getByOpportunitySubmitted(subId);
		if (list.size() > 0) {
			model.addAttribute("opportunity", opportunity);
			model.addAttribute("submission", opportunitySubmittedService.findById(subId));
			model.addAttribute("reviewIssues", opportunityReviewIssueService.getByOpportunitySubmittedExtended(subId));
			model.addAttribute("subId", subId);
			model.addAttribute("oppId", oppId);
			model.addAttribute("userId", userId);

			return "reviewoppissues";
		} else {
			model.addAttribute("opportunity", opportunity);
			model.addAttribute("submissions", opportunitySubmittedService.getByOpportunity(oppId));
			model.addAttribute("subId", subId);
			model.addAttribute("oppId", oppId);
			model.addAttribute("userId", userId);

			return "redirect:reviewopp";
		}

	}

}
