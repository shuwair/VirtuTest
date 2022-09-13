package com.thevirtugroup.postitnote.response;

import java.util.List;

import com.thevirtugroup.postitnote.entity.Note;

public class NotesResponse {
	
    private List<Note> notes;
    
    public NotesResponse(List<Note> notes) {
		this.notes = notes;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
}