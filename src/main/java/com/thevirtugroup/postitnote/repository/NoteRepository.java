package com.thevirtugroup.postitnote.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thevirtugroup.postitnote.entity.Note;

@Repository
public interface NoteRepository extends CrudRepository<Note,Long> {
	
	List<Note> findAllByUserNameOrderByCreateDate(String userName);

}
