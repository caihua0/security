package com.imooc.web.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.net.ssl.SSLEngineResult.Status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.jayway.jsonpath.JsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	private final MediaType contentType=MediaType.APPLICATION_JSON_UTF8;
	@Before
	public void setup(){
		mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void whenQuerySucess() throws Exception{
		 String contentAsString = mockMvc.perform(MockMvcRequestBuilders.get("/user")
				 		.param("username", "admin")
				 		.param("age", "18")
				 		.param("ageTO", "60")
				 		.param("xxx", "yyy")
				 		.param("page", "3")
				 		.param("size", "15")
				 		.param("sort", "age,desc")
				 		.contentType(MediaType.APPLICATION_JSON_UTF8))
		 		 .andExpect(MockMvcResultMatchers.status().isOk())
		 		 .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
		 		 .andReturn().getResponse().getContentAsString();
			System.out.println(contentAsString);
	}
	@Test
	public void whenGetInfoSuccess() throws Exception{
		String contentAsString = mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
				.contentType(contentType))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.username").value("tom"))
			.andReturn().getResponse().getContentAsString();
		System.out.println(contentAsString);
	}
	@Test
	public void whenGetInfoFail() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/user/t")
				.contentType(contentType))
			.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
	
	@Test
	public void whenCreateSuccess() throws Exception{
		String content="{\"username\":\"tom\",\"password\":null,\"birthday\":"+new Date().getTime()+"}";
		String contentAsString = mockMvc.perform(MockMvcRequestBuilders.post("/user").contentType(contentType).content(content))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
		.andReturn().getResponse().getContentAsString();
		System.out.println(contentAsString);
	}
	
	@Test
	public void whenUpdateSuccess() throws Exception{
		
		Date date =new Date(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
		System.out.println(date.getTime());
		String content="{\"id\":\"1\",\"username\":\"tom\",\"password\":null,\"birthday\":"+date.getTime()+"}";
		String contentAsString = mockMvc.perform(MockMvcRequestBuilders.put("/user/1").contentType(contentType).content(content))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
		.andReturn().getResponse().getContentAsString();
		System.out.println(contentAsString);
	}
	
	@Test
	public void whenDeleteSuccess() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.delete("/user/1").contentType(contentType))
		.andExpect(MockMvcResultMatchers.status().isOk());	
	}
	
	
	@Test
	public void whenUploadSuccess() throws Exception{
		String result =mockMvc.perform(MockMvcRequestBuilders.fileUpload("/file")
				.file(new MockMultipartFile("file","test.txt","multipart/form-data","hello upload".getBytes())))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
	
}
