package com.qa.notes.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qa.notes.dto.NoteDto;
import com.qa.notes.service.NoteService;

/**
 * Note controller. For retrieving and posting notes.
 *
 */

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/note")
public class NoteController {
	
	@Autowired
	private NoteService service;

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<NoteDto> getNotes(){
		return new ArrayList<NoteDto>(service.getNotes());
	}
	
	@PostMapping("/{notebookId}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public NoteDto createNote(@RequestBody NoteDto note, @PathVariable Long notebookId) throws NotFoundException{
		note.setId(null);
		return service.createNote(note, notebookId);
	}
	
	@PutMapping
	@ResponseStatus(code = HttpStatus.OK)
	public NoteDto updateNote(@RequestBody NoteDto note) throws NotFoundException{
		return service.updateNote(note);
	}
	
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public NoteDto deleteNote(@PathVariable Long id) throws NotFoundException{
		return service.deleteNote(id);
	}
}
