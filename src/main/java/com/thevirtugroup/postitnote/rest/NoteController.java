package com.thevirtugroup.postitnote.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thevirtugroup.postitnote.entity.Note;
import com.thevirtugroup.postitnote.response.NoteResponse;
import com.thevirtugroup.postitnote.response.NotesResponse;
import com.thevirtugroup.postitnote.security.SecurityContext;
import com.thevirtugroup.postitnote.security.SecurityUserWrapper;
import com.thevirtugroup.postitnote.service.NoteService;

@RestController
@RequestMapping("/note")
public class NoteController 
{
	@Autowired
	private NoteService noteService;
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    public NoteResponse save(@RequestBody Note note) {
		final SecurityUserWrapper loggedInUser = SecurityContext.getLoggedInUser();
		note.setUserName(loggedInUser.getUsername());
		NoteResponse noteResponse = new NoteResponse(noteService.saveNote(note));
		return noteResponse;
       
    }
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
    public NoteResponse update(@RequestBody Note note) {
		final SecurityUserWrapper loggedInUser = SecurityContext.getLoggedInUser();
		note.setUserName(loggedInUser.getUsername());
		NoteResponse noteResponse = new NoteResponse(noteService.saveNote(note));
		return noteResponse;
       
    }
	
	@RequestMapping(value = "/getNote", method = RequestMethod.POST)
    public NoteResponse getNote(@RequestBody Note note) {
		NoteResponse noteResponse = new NoteResponse(noteService.getNote(note.getId()));
		return noteResponse;
       
    }
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteNote(@RequestBody Note note) {
		noteService.getNote(note.getId());
       
    }
	
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public NotesResponse getAll(@RequestParam(required = true, value="userName") String userName) {
		NotesResponse notesResponse = new NotesResponse(noteService.getAll(userName));
		return notesResponse;
       
    }
}
