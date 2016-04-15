package edu.stevens.ssw690.DuckSource.dao.test;

import static org.junit.Assert.*;

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

import edu.stevens.ssw690.DuckSource.model.OpportunitySubmitted;
import edu.stevens.ssw690.DuckSource.model.ReviewIssue;
import edu.stevens.ssw690.DuckSource.service.OpportunityReviewIssueManager;
import edu.stevens.ssw690.DuckSource.service.OpportunitySubmittedManager;
import edu.stevens.ssw690.DuckSource.service.ReviewIssueManager;
import edu.stevens.ssw690.DuckSource.test.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@WebAppConfiguration
public class ReviewIssueDaoTest {

	@Autowired
	ReviewIssueManager reviewIssueService;
	

	@Test
    @Transactional
    @Rollback(true)
	public final void testPersist() {
		ReviewIssue reviewIssue = new ReviewIssue();
		reviewIssue.setIssueTitle("title");
		reviewIssue.setDescription("description");
		reviewIssueService.persist(reviewIssue);
		
		List<ReviewIssue> reviewIssues = reviewIssueService.getAll();
		int last = reviewIssues.size() - 1;
		Assert.assertEquals(reviewIssue.getIssueTitle(), reviewIssues.get(last).getIssueTitle());
	}

	@Test
	@Transactional
    @Rollback(true)
	public final void testMerge() {
		ReviewIssue reviewIssue = new ReviewIssue();
		reviewIssue.setIssueTitle("title");
		reviewIssue.setDescription("description");
		reviewIssueService.merge(reviewIssue);
		
		List<ReviewIssue> reviewIssues = reviewIssueService.getAll();
		int last = reviewIssues.size() - 1;
		Assert.assertEquals(reviewIssue.getIssueTitle(), reviewIssues.get(last).getIssueTitle());
	}

	@Test
	public final void testGetAll() {
		List<ReviewIssue> reviewIssues = reviewIssueService.getAll();
		Assert.assertNotNull(reviewIssues);
	}

	@Test
	public final void testGetById() {
		ReviewIssue reviewIssue = reviewIssueService.getById(1);
		Assert.assertNotNull(reviewIssue);
	}

	@Test
	public final void testFindById() {
		ReviewIssue reviewIssue = reviewIssueService.findById(1);
		Assert.assertNotNull(reviewIssue);
	}

}
