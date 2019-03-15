package com.muratcan.service;

import java.util.ArrayList;
import java.util.UUID;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.muratcan.dao.UserDAO;
import com.muratcan.entity.Note;
import com.muratcan.entity.User;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserDAO userDao;  
	
	@Autowired
	private MailService mailService;
	
	public Long insert(User user) {
		
		String uuid = UUID.randomUUID().toString();
		user.setKeyreg(uuid);
		
		if( userDao.insert(user) > 0) {
			mailService.registerMail(user.getEmail(), user.getKeyreg());
		}
		return 1L;
		
	}
	
	public void update(User user) {
		
		userDao.update(user);
	}
	
	//READ
	
	public User getNoteFindByUsernameAndPass(User user ){
		
		return userDao.getNoteFindByUsernameAndPass(user.getUsername(), user.getPassword());
	}
	
	
	public User getNoteFindByUsername(String username){
					
		return userDao.getNoteFindByUsername(username);
	}
	
	public boolean getNoteFindByKey(String key){
		
		User user = userDao.getNoteFindByKey(key);
		
		if(user != null) {
			user.setActive(true);
			update(user);
			return true;
			
		}else 
			return false;
	}

}
