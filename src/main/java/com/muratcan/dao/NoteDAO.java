package com.muratcan.dao;

import java.util.ArrayList;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.muratcan.entity.Note;

@Repository
public class NoteDAO {

	@Autowired
	private SessionFactory sessionFactory;  
	
	public Long insert(Note note) {
		
		return (Long) sessionFactory.getCurrentSession().save(note);
		
	}
	
	public void update(Note note) {
		sessionFactory.getCurrentSession().update(note);
	}
	
	public void persist(Note note) {
		sessionFactory.getCurrentSession().persist(note); //veri varsa update eder yoksa insert eder.
	}
	

	public void delete(Note note) {
		sessionFactory.getCurrentSession().delete(note);
	}
	
	
	//READ
	
	public Note getNoteFindById(Long id){
		
		Query query = sessionFactory.getCurrentSession().createQuery("FROM Note WHERE id=:id") //tablo ismi deðil class ismi.
				.setLong("id", id);
		
		return (Note) query.getSingleResult();
	}
	
	public ArrayList<Note> getAll(){
		
		Query query = sessionFactory.getCurrentSession().createQuery("FROM Note"); //tablo ismi deðil class ismi.
		return (ArrayList<Note>) query.getResultList();
	}
	
	public ArrayList<Note> getAll(Long user_id){
			
		Query query = sessionFactory.getCurrentSession().createQuery("FROM Note WHERE user_id=:userId order by id desc")
			.setLong("userId", user_id);
		
		return (ArrayList<Note>) query.getResultList();
	}
}
