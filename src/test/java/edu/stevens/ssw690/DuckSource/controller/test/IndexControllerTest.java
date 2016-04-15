package edu.stevens.ssw690.DuckSource.controller.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import edu.stevens.ssw690.DuckSource.model.DuckUser;
import edu.stevens.ssw690.DuckSource.test.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@WebAppConfiguration
public class IndexControllerTest {

	@Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mockMvc = builder.build();
    }

	@Test
	public final void testGetSignup() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/signup");
        ResultActions result = this.mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/signup.jsp"));
	}

	/*
	@Test(expected=org.springframework.web.util.NestedServletException.class)
	public final void testPostSignup() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/signup");
		this.mockMvc.perform(request);
	}
	*/
	
	@Test
	@Transactional
    @Rollback(true)
	public final void testPostSignup() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/signup");
		request.contentType(MediaType.APPLICATION_FORM_URLENCODED);
		request.param("firstName", "duck");
		request.param("lastName", "ling");
		request.param("userName", "duckling");
		request.param("password", "pass");
		request.param("confirmPassword", "pass");
		request.param("emailAddress", "duckling@stevens.edu");
		request.sessionAttr("userForm", new DuckUser());
		
		ResultActions result = this.mockMvc.perform(request);
		result.andExpect(MockMvcResultMatchers.status().isOk());
		result.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/signup.jsp"));
		
	}
	

	@Test
	public final void testReturntIndex() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/index");
        ResultActions result = this.mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/../index.jsp"));
	}

	@Test
	public final void testGetIndex() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/");
        ResultActions result = this.mockMvc.perform(request);
        
        result.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public final void testGetIndexUpdate() throws Exception {
		 MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/indexupdate");
	     request.param("select", "All Types");
	     ResultActions result = this.mockMvc.perform(request);
	     
	     result.andExpect(MockMvcResultMatchers.status().isOk());
	     result.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/../index.jsp"));
	}

	@Test
	public final void testGetIndexAbout() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/indexabout");
	    ResultActions result = this.mockMvc.perform(request);
	        
	    result.andExpect(MockMvcResultMatchers.status().isOk());
	    result.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/indexabout.jsp"));
	}

	@Test
	public final void testGetReset() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/resetpassword");
        ResultActions result = this.mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/resetpassword.jsp"));
       
	}

	@Test
	public final void testGetAbout() throws Exception {		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/about");
		request.param("userId", "1");

		ResultActions result = this.mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/about.jsp"));
        result.andExpect(MockMvcResultMatchers.model().attribute("userId", "1"));
		
	}

	@Test
	public final void testGetMain() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/main");
		request.param("userId", "1");

		ResultActions result = this.mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/main.jsp"));
       
	}

	@Test
	public final void testLogin() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/main");
		request.param("userName", "daffy");
		request.param("password", "duck");

		ResultActions result = this.mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/../index.jsp"));
	}

}
