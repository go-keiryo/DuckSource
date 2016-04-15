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

import edu.stevens.ssw690.DuckSource.model.DuckUser;
import edu.stevens.ssw690.DuckSource.model.Opportunity;
import edu.stevens.ssw690.DuckSource.model.OpportunityRegistered;
import edu.stevens.ssw690.DuckSource.model.OpportunitySubmitted;
import edu.stevens.ssw690.DuckSource.service.DuckUserManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityReviewIssueManager;
import edu.stevens.ssw690.DuckSource.service.OpportunitySubmittedManager;
import edu.stevens.ssw690.DuckSource.test.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@WebAppConfiguration
public class OpportunitySubmittedDaoTest {

	@Autowired
	OpportunitySubmittedManager opportunitySubmittedService;

	@Autowired
	OpportunityManager opportunityService;
	
	@Autowired
	DuckUserManager duckUserService;

	@Test
    @Transactional
    @Rollback(true)
	public final void testPersist() {
		OpportunitySubmitted opportunitySubmitted = new OpportunitySubmitted();
		DuckUser duckUser = duckUserService.getById(1);
		opportunitySubmitted.setUser(duckUser);
		Opportunity opportunity = opportunityService.getById(1);
		opportunitySubmitted.setOpportunity(opportunity);
		opportunitySubmitted.setSubmissionDate(Date.from(LocalDateTime.now().toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		opportunitySubmitted.setFilePath("test");
		opportunitySubmittedService.persist(opportunitySubmitted);
		
		List<OpportunitySubmitted> opportunitiesSubmitted = opportunitySubmittedService.getBySubmitted(1);
		int last = opportunitiesSubmitted.size() - 1;
		Assert.assertEquals(opportunitySubmitted.getUserId(), opportunitiesSubmitted.get(last).getUserId());
	}
	

	@Test
    @Transactional
    @Rollback(true)
	public final void testMerge() {
		OpportunitySubmitted opportunitySubmitted = new OpportunitySubmitted();
		DuckUser duckUser = duckUserService.getById(1);
		opportunitySubmitted.setUser(duckUser);
		Opportunity opportunity = opportunityService.getById(1);
		opportunitySubmitted.setOpportunity(opportunity);
		opportunitySubmitted.setSubmissionDate(Date.from(LocalDateTime.now().toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		opportunitySubmitted.setFilePath("test");
		opportunitySubmittedService.merge(opportunitySubmitted);
		
		List<OpportunitySubmitted> opportunitiesSubmitted = opportunitySubmittedService.getBySubmitted(1);
		int last = opportunitiesSubmitted.size() - 1;
		Assert.assertEquals(opportunitySubmitted.getUserId(), opportunitiesSubmitted.get(last).getUserId());
	}

	@Test
	public final void testFindById() {
		OpportunitySubmitted opportunitySubmitted = opportunitySubmittedService.findById(1);
		Assert.assertNotNull(opportunitySubmitted);
	}

	@Test
	public final void testGetByOpportunity() {
		List<OpportunitySubmitted> opportunitiesSubmitted = opportunitySubmittedService.getByOpportunity(1);
		Assert.assertNotNull(opportunitiesSubmitted);
	}

	@Test
	public final void testGetBySubmitted() {
		List<OpportunitySubmitted> opportunitiesSubmitted = opportunitySubmittedService.getBySubmitted(1);
		Assert.assertNotNull(opportunitiesSubmitted);
	}

	@Test
	public final void testGetBySubmittedOpportunity() {
		List<OpportunitySubmitted> opportunitiesSubmitted = opportunitySubmittedService.getBySubmitted(1);
		Assert.assertNotNull(opportunitiesSubmitted);
	}

}
