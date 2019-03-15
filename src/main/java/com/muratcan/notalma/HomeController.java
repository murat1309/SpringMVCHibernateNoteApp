package com.muratcan.notalma;


import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.muratcan.entity.Note;
import com.muratcan.security.LoginFilter;
import com.muratcan.service.MailService;
import com.muratcan.service.NoteService;


@Controller
public class HomeController {
	
	public static String url="http://localhost:8090";
	
	
	@Autowired
	private NoteService noteService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home(Model model,HttpServletRequest req) {
		
		return "redirect:/index";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homes(Model model,HttpServletRequest req) {
		
		
		return "redirect:/index";
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model,HttpServletRequest req) {
		
		model.addAttribute("user", req.getSession().getAttribute("user"));
		
		model.addAttribute("baslik", "Not Alma");
		model.addAttribute("serverTime", "/");
		model.addAttribute("baslik", "Not Alma");
		model.addAttribute("notlar", noteService.getAll(1L));
		
		return "index";
	}
	
	@RequestMapping(value ="/getNotes", method=RequestMethod.POST)
	public ResponseEntity<ArrayList<Note>> getNotes(HttpServletRequest request ){
					
		return new ResponseEntity<ArrayList<Note>>(noteService.getAll(LoginFilter.user.getId()),HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/detay/{id}", method = RequestMethod.GET)
	public String home(@PathVariable("id") Long id ,Model model) {		
		
		model.addAttribute("id", id);					
		
		return "detail";
	}
	

	@RequestMapping(value ="/getNote", method=RequestMethod.POST)
	public ResponseEntity<Note> getNote(@RequestBody String id, HttpServletRequest request ){
					
		Note note = noteService.getNoteFindById(Long.parseLong(id));
		
		if(note.getUser_id().equals(LoginFilter.user.getId())) {
			return new ResponseEntity<Note>(noteService.getNoteFindById(Long.parseLong(id)) ,HttpStatus.OK);
		}
		
		return null;
	}
	
	@RequestMapping(value ="/updateNote", method=RequestMethod.POST)
	public ResponseEntity<String> updateNote(@RequestBody Note note, HttpServletRequest request ){
		
		Note oldNote = noteService.getNoteFindById(note.getId());
		oldNote.setTitle(note.getTitle());
		oldNote.setContent(note.getContent());
		
		noteService.updateNote(oldNote,request);
		
		return new ResponseEntity<String>("OK",HttpStatus.OK);
	}
	
	@RequestMapping(value ="/deleteNote", method=RequestMethod.POST)
	public ResponseEntity<String> deleteNote(@RequestBody Note note, HttpServletRequest request ){
		
		Note oldNote = noteService.getNoteFindById(note.getId());
		
		noteService.deleteNote(oldNote,request);
		
		return new ResponseEntity<String>("OK",HttpStatus.OK);
	}
	
	
	@RequestMapping(value ="/addNote", method=RequestMethod.POST)
	public ResponseEntity<String> addNote(@RequestBody Note note, HttpServletRequest request ){
		
		System.out.println(note.toString());
		
		noteService.createNote(note,request);
		return new ResponseEntity<String>("OK",HttpStatus.OK);
	}
	
	@RequestMapping(value = "ekle", method = RequestMethod.GET)
	public String ekle(Model model) {
		
		return "addNote";
	}
	
	@RequestMapping(value = "/error_404", method = RequestMethod.GET)
	public String error(Model model) {
		
		return "error404";
	}
	
}
