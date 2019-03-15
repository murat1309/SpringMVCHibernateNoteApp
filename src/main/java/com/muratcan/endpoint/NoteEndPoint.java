package com.muratcan.endpoint;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.muratcan.dto.UserLoginDTO;
import com.muratcan.entity.Note;
import com.muratcan.service.NoteService;

@RestController
@RequestMapping(value="/rest")
public class NoteEndPoint {

	@Autowired
	private NoteService noteService;
	
	@RequestMapping(value="/getAll" , method=RequestMethod.POST)
	public ResponseEntity< ArrayList<Note>> getNotes(@RequestBody UserLoginDTO login){		
		
		return new ResponseEntity<ArrayList<Note>>(noteService.getAll(login),HttpStatus.OK);
	}
}
