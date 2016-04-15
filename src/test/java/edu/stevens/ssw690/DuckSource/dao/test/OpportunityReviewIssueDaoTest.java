package edu.stevens.ssw690.DuckSource.dao.test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import edu.stevens.ssw690.DuckSource.model.OpportunityRegistered;
import edu.stevens.ssw690.DuckSource.model.OpportunityReviewIssue;
import edu.stevens.ssw690.DuckSource.model.OpportunitySubmitted;
import edu.stevens.ssw690.DuckSource.model.OpportunitytReviewIssueExtended;
import edu.stevens.ssw690.DuckSource.service.DuckUserManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityRegisteredManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityReviewIssueManager;
import edu.stevens.ssw690.DuckSource.service.OpportunitySubmittedManager;
import edu.stevens.ssw690.DuckSource.test.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@WebAppConfiguration
public class OpportunityReviewIssueDaoTest {

	@Autowired
	OpportunityReviewIssueManager opportunityReviewIssueService;
	
	@Autowired
	OpportunitySubmittedManager opportunitySubmittedService;
	
	@Test
    @Transactional
    @Rollback(true)
	public final void testPersist() {
		OpportunityReviewIssue opportunityReviewIssue = new OpportunityReviewIssue();
		OpportunitySubmitted opportunitySubmitted = opportunitySubmittedService.findById(1);
		opportunityReviewIssue.setOpportunitySubmitted(opportunitySubmitted);
		opportunityReviewIssue.setCreationDate(Date.from(LocalDateTime.now().toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		opportunityReviewIssueService.persist(opportunityReviewIssue);
		
		List<OpportunityReviewIssue> opportunityReviewIssues = opportunityReviewIssueService.getByOpportunitySubmitted(1);
		int last = opportunityReviewIssues.size() - 1;
		Assert.assertEquals(opportunityReviewIssue.getOpportunitySubmittedId(), opportunityReviewIssues.get(last).getOpportunitySubmittedId());
	}

	@Test
	@Transactional
    @Rollback(true)
	public final void testMerge() {
		OpportunityReviewIssue opportunityReviewIssue = new OpportunityReviewIssue();
		OpportunitySubmitted opportunitySubmitted = opportunitySubmittedService.findById(1);
		opportunityReviewIssue.setOpportunitySubmitted(opportunitySubmitted);
		opportunityReviewIssue.setCreationDate(Date.from(LocalDateTime.now().toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		opportunityReviewIssueService.merge(opportunityReviewIssue);
		
		List<OpportunityReviewIssue> opportunityReviewIssues = opportunityReviewIssueService.getByOpportunitySubmitted(1);
		int last = opportunityReviewIssues.size() - 1;
		Assert.assertEquals(opportunityReviewIssue.getOpportunitySubmittedId(), opportunityReviewIssues.get(last).getOpportunitySubmittedId());
	}

	@Test
	@Transactional
    @Rollback(true)
	public final void testRemove() {
		OpportunityReviewIssue opportunityReviewIssue = opportunityReviewIssueService.findById(2);
		opportunityReviewIssueService.remove(opportunityReviewIssue);
		
		opportunityReviewIssue = opportunityReviewIssueService.findById(2);
		Assert.assertNull(opportunityReviewIssue);
	}

	@Test
	public final void testFindById() {
		OpportunityReviewIssue opportunityReviewIssue = opportunityReviewIssueService.findById(2);
		Assert.assertNotNull(opportunityReviewIssue);
		
	}

	@Test
	public final void testGetByOpportunity() {
		List<OpportunityReviewIssue> opportunityReviewIssues = opportunityReviewIssueService.getByOpportunity(1);
		Assert.assertNotNull(opportunityReviewIssues);
	}

	@Test
	public final void testGetByOpportunitySubmitted() {
		List<OpportunityReviewIssue> opportunityReviewIssues = opportunityReviewIssueService.getByOpportunitySubmitted(1);
		Assert.assertNotNull(opportunityReviewIssues);
	}

	@Test
	public final void testGetByOpportunitySubmittedExtended() {
		List<OpportunitytReviewIssueExtended> opportunityReviewIssues = opportunityReviewIssueService.getByOpportunitySubmittedExtended(1);
		Assert.assertNotNull(opportunityReviewIssues);
	}

}
