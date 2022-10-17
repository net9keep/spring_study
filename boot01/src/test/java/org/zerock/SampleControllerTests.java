package org.zerock;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.MockMvc.MvcResult'

import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
// 객체 테스트용 import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.zerock.controller.SampleController;

@WebMvcTest(SampleController.class) // @SprintBootTest X
public class SampleControllerTests {
	
	@Autowired
	MockMvc mock;
	
	@Test
	public void testHello() throws Exception{
		//mock.perform(get("/Hello")).andExpect(content().string("Hello, World"));
		MvcResult result = mock.perform(get("/Hello"))
							.andExpect(status().isOk())
							.andExpect(content().string("Hello, World?")).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
}
