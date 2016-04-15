package edu.stevens.ssw690.DuckSource.controller.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.context.WebApplicationContext;

import edu.stevens.ssw690.DuckSource.test.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@WebAppConfiguration
public class RegisterReviewControllerTest {

	@Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mockMvc = builder.build();
    }
    
	@Test
	public final void testGetAccount() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/account");
        ResultActions result = this.mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public final void testGetPassword() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/password");
        ResultActions result = this.mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public final void testSubmitPasswordForm() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/password");
        ResultActions result = this.mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public final void testRegisterForOpp() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/register");
        ResultActions result = this.mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public final void testDeregisterForOpp() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/deregister");
        ResultActions result = this.mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public final void testGetReviewOpp() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/reviewopp");
        ResultActions result = this.mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public final void testGetEditReviewOpp() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/editreview");
        ResultActions result = this.mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public final void testGetSaveReviewOpp() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/savereview");
        ResultActions result = this.mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public final void testGetReviewIssue() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/addissue");
        ResultActions result = this.mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public final void testSaveReviewIssue() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/addissue");
        ResultActions result = this.mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public final void testGetReviewIssues() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/reviewoppissues");
        ResultActions result = this.mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.status().isOk());
	}
	@Test
	public final void testGetEditReviewIssue() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/editissue");
        ResultActions result = this.mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public final void testSaveEditReviewIssue() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/editissue");
        ResultActions result = this.mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public final void testDeleteissue() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/deleteissue");
        ResultActions result = this.mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
