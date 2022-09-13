package com.thevirtugroup.postitnote.test;

import java.util.UUID;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thevirtugroup.postitnote.Application;
import com.thevirtugroup.postitnote.entity.Note;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@WithMockUser("user")
public class NoteTest {
	

	 @Autowired
     private WebApplicationContext wac;

	 
    @Autowired
    private ObjectMapper mapper;
    
	private MockMvc mockMvc; 
    
    
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
	 
	 @Test
	 public void saveNote() throws Exception {
		 
		 Note note = new Note();
		 note.setNoteName("test");
		 note.setNoteText("test text");
		 note.setUserName("user");
		 
		  String json  = mapper.writeValueAsString(note);
		 
		this.mockMvc.perform(post("/note/save")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(json)
	        ).andExpect(status().isOk());
		 
	 }
	 
	 @Test
	 public void updateNote() throws Exception {
		 
		 Note note = new Note();
		 note.setId(Long.getLong("1"));
		 note.setNoteName("test");
		 note.setNoteText("test text");
		 note.setUserName("user");
		 
		  String json  = mapper.writeValueAsString(note);
		 
		this.mockMvc.perform(post("/note/update")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(json)
	        ).andExpect(status().isOk());
		 
	 }

}
