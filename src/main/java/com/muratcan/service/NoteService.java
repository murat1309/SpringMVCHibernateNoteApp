package com.muratcan.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.muratcan.dao.NoteDAO;
import com.muratcan.dto.UserLoginDTO;
import com.muratcan.entity.Note;
import com.muratcan.entity.User;
import com.muratcan.security.LoginFilter;

@Service
@Transactional
public class NoteService {

	@Autowired
	private NoteDAO noteDAO;
	
	@Autowired
	private UserService userService;
	
	public Long createNote(Note note, HttpServletRequest request) {
		//TODO: userId deðiþecek
		note.setUser_id(LoginFilter.user.getId());
		
		return noteDAO.insert(note);
	}
	public Long updateNote(Note note, HttpServletRequest request) {
		
		 noteDAO.update(note);
		 return 1L;
	}
	
	public Long deleteNote(Note note, HttpServletRequest request) {
		
		 noteDAO.delete(note);
		 return 1L;
	}
	
	
	public ArrayList<Note> getAll(){
		
		return noteDAO.getAll();
	}
	
	public ArrayList<Note> getAll(Long userId){
		
		return noteDAO.getAll(userId);
	}
	
	public Note getNoteFindById(Long id){
		
		return noteDAO.getNoteFindById(id);
	}
	
	public ArrayList<Note> getAll(UserLoginDTO login){
		
		User userm = new User();
		userm.setUsername(login.getUsername());
		userm.setPassword(login.getPassword());
		
		User user = userService.getNoteFindByUsernameAndPass(userm);
		
		return noteDAO.getAll(user.getId());
	}
}
