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
public class OpportunityControllerTest {

	@Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mockMvc = builder.build();
    }
    
	@Test
	public final void testShowDetailIndex() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/indexoppdetail");
        ResultActions result = this.mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public final void testShowDetail() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/oppdetail");
        ResultActions result = this.mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public final void testGetFindUpdate() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/findupdate");
        ResultActions result = this.mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public final void testSetupFindForm() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/findopp");
        ResultActions result = this.mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public final void testGetCreateOpp() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/createopp");
        ResultActions result = this.mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public final void testSetCreateOpp() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/createopp");
        ResultActions result = this.mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public final void testGetEditOpp() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/editopp");
        ResultActions result = this.mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public final void testSetEditOpp() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/editopp");
        ResultActions result = this.mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
