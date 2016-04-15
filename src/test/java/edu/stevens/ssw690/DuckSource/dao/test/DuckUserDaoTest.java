package edu.stevens.ssw690.DuckSource.dao.test;


import org.junit.Assert;
import static org.junit.Assert.fail;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import edu.stevens.ssw690.DuckSource.dao.DuckUserDao;
import edu.stevens.ssw690.DuckSource.model.DuckUser;
import edu.stevens.ssw690.DuckSource.service.DuckUserManager;
import edu.stevens.ssw690.DuckSource.test.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@WebAppConfiguration
public class DuckUserDaoTest {


	@Autowired
    DuckUserManager duckUserService;
     
    @Test
    @Transactional
    @Rollback(true)
	public final void testPersist() {
		DuckUser duckUser = new DuckUser();
		duckUser.setUserName("duck");
		duckUser.setFirstName("daffy");
		duckUser.setLastName("duck");
		duckUser.setPassword("password");
		duckUser.setEmailAddress("duck@stevens.edu");
		duckUser.setRegistrationDate(Date.from(LocalDateTime.now().toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		duckUserService.persist(duckUser);
		
		List<DuckUser> duckUsers = duckUserService.getAll();
		int last = duckUsers.size() - 1;
		Assert.assertEquals(duckUser.getUserName(), duckUsers.get(last).getUserName());
		
	}
    
    @Test
    @Transactional
    @Rollback(true)
	public final void testMerge() {
		DuckUser duckUser = new DuckUser();
		duckUser.setUserName("duck");
		duckUser.setFirstName("daffy");
		duckUser.setLastName("duck");
		duckUser.setPassword("password");
		duckUser.setEmailAddress("duck@stevens.edu");
		duckUser.setRegistrationDate(Date.from(LocalDateTime.now().toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		duckUserService.merge(duckUser);
		
		List<DuckUser> duckUsers = duckUserService.getAll();
		int last = duckUsers.size() - 1;
		Assert.assertEquals(duckUser.getUserName(), duckUsers.get(last).getUserName());
		
	}

	@Test
	public final void testGetById() {
		DuckUser duckUser = duckUserService.getById(1);
		Assert.assertEquals("daffy",duckUser.getUserName());
		
	}

	@Test
	public final void testFindById() {
		DuckUser duckUser = duckUserService.findById(1);
		Assert.assertEquals("daffy",duckUser.getUserName());
	}

	@Test
	public final void testGetDuckUser() {
		
		DuckUser duckUser = duckUserService.getDuckUser("daffy","duck");
		Assert.assertEquals("daffy",duckUser.getUserName());
	}

	@Test
	public final void testGetUsernameExists() {
		
		Assert.assertTrue(duckUserService.getUsernameExists("daffy"));
	}
	

}
