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
import edu.stevens.ssw690.DuckSource.service.DuckUserManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityRegisteredManager;
import edu.stevens.ssw690.DuckSource.test.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@WebAppConfiguration
public class OpportunityRegisteredDaoTest {

	@Autowired
	OpportunityRegisteredManager opportunityRegisteredService;
	
	@Autowired
	DuckUserManager duckUserService;
	
	@Autowired
	OpportunityManager opportunityService;
	
	@Test
    @Transactional
    @Rollback(true)
	public final void testPersist() {
		OpportunityRegistered opportunityRegistered = new OpportunityRegistered();
		DuckUser duckUser = duckUserService.getById(1);
		Opportunity opportunity = opportunityService.getById(1);
		opportunityRegistered.setUser(duckUser);
		opportunityRegistered.setOpportunity(opportunity);
		opportunityRegistered.setRegisteredDate(Date.from(LocalDateTime.now().toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		opportunityRegisteredService.persist(opportunityRegistered);
	
		List<OpportunityRegistered> opportunitiesRegistered = opportunityRegisteredService.getByRegistered(1);
		int last = opportunitiesRegistered.size() - 1;
		Assert.assertEquals(opportunityRegistered.getUserId(), opportunitiesRegistered.get(last).getUserId());
	
	}
	

	@Test
	@Transactional
	@Rollback(true)
	public final void testMerge() {
		OpportunityRegistered opportunityRegistered = new OpportunityRegistered();
		DuckUser duckUser = duckUserService.getById(1);
		Opportunity opportunity = opportunityService.getById(1);
		opportunityRegistered.setUser(duckUser);
		opportunityRegistered.setOpportunity(opportunity);
		opportunityRegistered.setRegisteredDate(Date.from(LocalDateTime.now().toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		opportunityRegisteredService.persist(opportunityRegistered);
		
		List<OpportunityRegistered> opportunitiesRegistered = opportunityRegisteredService.getByRegistered(1);
		int last = opportunitiesRegistered.size() - 1;
		Assert.assertEquals(opportunityRegistered.getUserId(), opportunitiesRegistered.get(last).getUserId());
	}

	@Test
	@Transactional
	@Rollback(true)
	public final void testRemove() {
		OpportunityRegistered opportunityRegistered = opportunityRegisteredService.findById(2);
		opportunityRegisteredService.remove(opportunityRegistered);
		
		opportunityRegistered = opportunityRegisteredService.findById(2);
		Assert.assertNull(opportunityRegistered);
		
	}

	@Test
	public final void testFindById() {
		OpportunityRegistered opportunityRegistered = opportunityRegisteredService.findById(2);
		Assert.assertNotNull(opportunityRegistered);
	}

	@Test
	public final void testGetByRegistered() {
		List<OpportunityRegistered> opportunitiesRegistered = opportunityRegisteredService.getByRegistered(1);
		Assert.assertNotNull(opportunitiesRegistered);
	}

	@Test
	public final void testGetByRegisteredOpportunity() {
		OpportunityRegistered opportunityRegistered = opportunityRegisteredService.getByRegisteredOpportunity(1,1);
		Assert.assertNull(opportunityRegistered);
	}

}
