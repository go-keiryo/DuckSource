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
import edu.stevens.ssw690.DuckSource.service.DuckUserManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityManager;
import edu.stevens.ssw690.DuckSource.test.TestConfig;
import edu.stevens.ssw690.DuckSource.utilities.DuckUtilities;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@WebAppConfiguration
public class OpportunityDaoTest {
	
	@Autowired
	OpportunityManager opportunityService;

	@Test
    @Transactional
    @Rollback(true)
	public final void testPersist() {
		Opportunity opportunity = new Opportunity();
		opportunity.setCreatorId(1);
		opportunity.setOpportunityTitle("Test");
		opportunity.setDescription("Testing");
		opportunity.setDuckbills(DuckUtilities.getBigDecimalFromString("10.25"));
		opportunity.setOpportunityType("Design");
		opportunity.setRegisterDate(DuckUtilities.getDateFromString("6/30/2016"));
		opportunity.setSubmitDate(DuckUtilities.getDateFromString("12/31/2016"));
		opportunityService.persist(opportunity);
		
		List<Opportunity> opportunities = opportunityService.getAllOpportunities();
		int last = opportunities.size() - 1;
		Assert.assertEquals(opportunity.getOpportunityTitle(), opportunities.get(last).getOpportunityTitle());
		
	}

	@Test
    @Transactional
    @Rollback(true)
	public final void testMerge() {
		Opportunity opportunity = new Opportunity();
		opportunity.setCreatorId(1);
		opportunity.setOpportunityTitle("Test");
		opportunity.setDescription("Testing");
		opportunity.setDuckbills(DuckUtilities.getBigDecimalFromString("10.25"));
		opportunity.setOpportunityType("Design");
		opportunity.setRegisterDate(DuckUtilities.getDateFromString("6/30/2016"));
		opportunity.setSubmitDate(DuckUtilities.getDateFromString("12/31/2016"));
		opportunityService.merge(opportunity);
		
		List<Opportunity> opportunities = opportunityService.getAllOpportunities();
		int last = opportunities.size() - 1;
		Assert.assertEquals(opportunity.getOpportunityTitle(), opportunities.get(last).getOpportunityTitle());
	}

	@Test
	public final void testFindById() {
		Opportunity opportunity = opportunityService.getById(1);
		Assert.assertEquals("Daffy Opportunity",opportunity.getOpportunityTitle());
	}

	@Test
	public final void testGetById() {
		Opportunity opportunity = opportunityService.getById(1);
		Assert.assertEquals("Daffy Opportunity",opportunity.getOpportunityTitle());
	}

	@Test
	public final void testGetAllOpportunities() {
		List<Opportunity> opportunities = opportunityService.getAllOpportunities();
		Assert.assertNotNull(opportunities);
	}

	@Test
	public final void testGetByCreator() {
		List<Opportunity> opportunities = opportunityService.getByCreator(1);
		Assert.assertNotNull(opportunities);
	}

	@Test
	public final void testGetByRegistered() {
		List<Opportunity> opportunities = opportunityService.getByRegistered(1);
		Assert.assertNotNull(opportunities);
	}

	@Test
	public final void testGetBySubmitted() {
		List<Opportunity> opportunities = opportunityService.getBySubmitted(1);
		Assert.assertNotNull(opportunities);
	}

	@Test
	public final void testGetBySubmittedOpportunity() {
		List<Opportunity> opportunities = opportunityService.getBySubmitted(1);
		Assert.assertNotNull(opportunities);
	}

	@Test
	public final void testGetByOtherThanCreator() {
		List<Opportunity> opportunities = opportunityService.getByOtherThanCreator(1);
		Assert.assertNotNull(opportunities);
	}

	@Test
	public final void testGetByType() {
		List<Opportunity> opportunities = opportunityService.getByType("Design");
		Assert.assertNotNull(opportunities);
	}

	@Test
	public final void testGetByOtherThanCreatorByType() {
		List<Opportunity> opportunities = opportunityService.getByOtherThanCreatorByType(1, "Design");
		Assert.assertNotNull(opportunities);
	}

}
