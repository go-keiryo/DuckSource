package edu.stevens.ssw690.DuckSource.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.runner.notification.Failure;

import edu.stevens.ssw690.DuckSource.controller.test.IndexControllerTest;
import edu.stevens.ssw690.DuckSource.controller.test.OpportunityControllerTest;
import edu.stevens.ssw690.DuckSource.controller.test.RegisterReviewControllerTest;
import edu.stevens.ssw690.DuckSource.controller.test.SubmitControllerTest;
import edu.stevens.ssw690.DuckSource.dao.test.DuckUserDaoTest;
import edu.stevens.ssw690.DuckSource.dao.test.OpportunityDaoTest;
import edu.stevens.ssw690.DuckSource.dao.test.OpportunityRegisteredDaoTest;
import edu.stevens.ssw690.DuckSource.dao.test.OpportunityReviewIssueDaoTest;
import edu.stevens.ssw690.DuckSource.dao.test.OpportunitySubmittedDaoTest;
import edu.stevens.ssw690.DuckSource.dao.test.OpportunityTimeDaoTest;
import edu.stevens.ssw690.DuckSource.dao.test.ReviewIssueDaoTest;

@RunWith(Suite.class)
@SuiteClasses({ IndexControllerTest.class, OpportunityControllerTest.class, RegisterReviewControllerTest.class,
		SubmitControllerTest.class, DuckUserDaoTest.class, OpportunityDaoTest.class, OpportunityRegisteredDaoTest.class,
		OpportunityReviewIssueDaoTest.class, OpportunitySubmittedDaoTest.class, OpportunityTimeDaoTest.class,
		ReviewIssueDaoTest.class })
public class AllDuckSourceTests {

	public static void main(String[] args) {
	    Result result = JUnitCore.runClasses(AllDuckSourceTests.class);
	    
	    System.out.println("Duck Source JUnit Test Report");
	    System.out.println();
	    System.out.println("Total: " + result.getRunCount());
	    System.out.println("Succeeded: " + (result.getRunCount() - result.getFailureCount()));
	    System.out.println("Failed: " + result.getFailureCount());
	    
	    System.out.println("Failures: ");
	    for (Failure failure : result.getFailures()) {
	      System.out.println(failure.toString());
	      System.out.println();
	    }
	  }
}
