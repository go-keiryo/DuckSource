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
public class SubmitControllerTest {

	@Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mockMvc = builder.build();
    }
    
	@Test
	public final void testGetDownload() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/download");
        ResultActions result = this.mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public final void testGetSubmit() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/submit");
        ResultActions result = this.mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public final void testOnSubmit() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/submit");
        ResultActions result = this.mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public final void testGetResubmit() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/resubmit");
        ResultActions result = this.mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public final void testOnResubmit() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/resubmit");
        ResultActions result = this.mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public final void testGetProfileImage() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/profileimg");
        ResultActions result = this.mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public final void testOnImageSubmit() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/profileimg");
        ResultActions result = this.mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public final void testGetTimesheet() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/timesheet");
        ResultActions result = this.mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public final void testOnTimesheet() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/timesheet");
        ResultActions result = this.mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
