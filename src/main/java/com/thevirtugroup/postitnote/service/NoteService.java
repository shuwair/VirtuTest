package com.thevirtugroup.postitnote.service;

import java.util.List;
import com.thevirtugroup.postitnote.entity.Note;


public interface NoteService {
	
	public Note saveNote(Note note); 
	
	public Note getNote(Long id);
	
	public void deleteNote(Note note);
	
	public List<Note> getAll(String userName);
}

