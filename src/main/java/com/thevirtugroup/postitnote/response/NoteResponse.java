package com.thevirtugroup.postitnote.response;


import com.thevirtugroup.postitnote.entity.Note;

public class NoteResponse {
	

    private Note note;
    
    public NoteResponse(Note note) {
		this.note = note;
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}

	

}