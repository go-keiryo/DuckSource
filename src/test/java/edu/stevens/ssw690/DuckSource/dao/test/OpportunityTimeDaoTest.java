package edu.stevens.ssw690.DuckSource.dao.test;

import static org.junit.Assert.*;

import java.sql.Time;
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
import edu.stevens.ssw690.DuckSource.model.OpportunitySubmitted;
import edu.stevens.ssw690.DuckSource.model.OpportunityTime;
import edu.stevens.ssw690.DuckSource.service.DuckUserManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityReviewIssueManager;
import edu.stevens.ssw690.DuckSource.service.OpportunityTimeManager;
import edu.stevens.ssw690.DuckSource.test.TestConfig;
import edu.stevens.ssw690.DuckSource.utilities.DuckUtilities;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@WebAppConfiguration
public class OpportunityTimeDaoTest {

	@Autowired
	OpportunityTimeManager opportunityTimeService;
	
	@Autowired
	OpportunityManager opportunityService;
	
	@Autowired
	DuckUserManager duckUserService;


	@Test
    @Transactional
    @Rollback(true)
	public final void testPersist() {
		OpportunityTime opportunityTime = new OpportunityTime();
		DuckUser duckUser = duckUserService.getById(1);
		opportunityTime.setUser(duckUser);
		Opportunity opportunity = opportunityService.getById(1);
		opportunityTime.setOpportunity(opportunity);
		opportunityTime.setWorkDate(DuckUtilities.getDateFromString("4/1/2016"));
		opportunityTime.setStartTime(Time.valueOf("03:00"));
		opportunityTime.setEndTime(Time.valueOf("05:00"));
		opportunityTimeService.persist(opportunityTime);
		
		List<OpportunityTime> opportunityTimes = opportunityTimeService.getByUser(1);
		int last = opportunityTimes.size() - 1;
		Assert.assertEquals(opportunityTime.getUserId(), opportunityTimes.get(last).getUserId());
		
	}	

	@Test
	@Transactional
    @Rollback(true)
	public final void testMerge() {
		OpportunityTime opportunityTime = new OpportunityTime();
		DuckUser duckUser = duckUserService.getById(1);
		opportunityTime.setUser(duckUser);
		Opportunity opportunity = opportunityService.getById(1);
		opportunityTime.setOpportunity(opportunity);
		opportunityTime.setWorkDate(DuckUtilities.getDateFromString("4/1/2016"));
		opportunityTime.setStartTime(Time.valueOf("03:00"));
		opportunityTime.setEndTime(Time.valueOf("05:00"));
		opportunityTimeService.persist(opportunityTime);
		
		List<OpportunityTime> opportunityTimes = opportunityTimeService.getByUser(1);
		int last = opportunityTimes.size() - 1;
		Assert.assertEquals(opportunityTime.getUserId(), opportunityTimes.get(last).getUserId());
	}

	@Test
	@Transactional
    @Rollback(true)
	public final void testRemove() {
		List<OpportunityTime> opportunityTimes = opportunityTimeService.getByUser(1);
		int last = opportunityTimes.size() - 1;
		OpportunityTime opportunityTime = opportunityTimes.get(last);
		opportunityTimeService.remove(opportunityTime);
		
		opportunityTime = opportunityTimes.get(last);
		Assert.assertNull(opportunityTime.getId());
		
	}

	@Test
	@Transactional
    @Rollback(true)
	public final void testClearTime() {
		Date tempDate = DuckUtilities.getDateFromString("4/30/2016");
		opportunityTimeService.clearTime(1, 1, tempDate, tempDate);
		List<OpportunityTime> opportunityTimes = opportunityTimeService.getByDate(1, 1, tempDate, tempDate);
		Assert.assertNull(opportunityTimes);
	}

	@Test
	public final void testFindById() {
		List<OpportunityTime> opportunityTimes = opportunityTimeService.getByUser(1);
		int last = opportunityTimes.size() - 1;
		OpportunityTime opportunityTime = opportunityTimes.get(last);
		
		Assert.assertNotNull(opportunityTime);
		
	}

	@Test
	public final void testGetByUser() {
		List<OpportunityTime> opportunityTimes = opportunityTimeService.getByUser(1);
		Assert.assertNotNull(opportunityTimes);
	}

	@Test
	public final void testGetByOpportunity() {
		List<OpportunityTime> opportunityTimes = opportunityTimeService.getByOpportunity(1, 1);
		Assert.assertNotNull(opportunityTimes);
	}

	@Test
	public final void testGetByDate() {
		Date tempDate = DuckUtilities.getDateFromString("4/1/2016");
		List<OpportunityTime> opportunityTimes = opportunityTimeService.getByDate(1, 1, tempDate, tempDate);
		Assert.assertNotNull(opportunityTimes);
	}

}
