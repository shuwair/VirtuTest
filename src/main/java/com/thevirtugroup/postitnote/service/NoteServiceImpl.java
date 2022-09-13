package com.thevirtugroup.postitnote.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thevirtugroup.postitnote.entity.Note;
import com.thevirtugroup.postitnote.repository.NoteRepository;


@Service
public class NoteServiceImpl implements NoteService {
	
	@Autowired
	private NoteRepository noteRepository;
	
	public Note saveNote(Note note) {
		note.setCreateDate(new Timestamp(System.currentTimeMillis()));
		return noteRepository.save(note);
	}
	
	public Note getNote(Long id) {
		return noteRepository.findById(id).get();
	}
	
	
	public void deleteNote(Note note) {
		 noteRepository.delete(note);
	}
	
	public List<Note> getAll(String userName){
		return noteRepository.findAllByUserNameOrderByCreateDate(userName);
	}
}

