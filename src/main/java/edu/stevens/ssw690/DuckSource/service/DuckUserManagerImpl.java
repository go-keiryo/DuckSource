package edu.stevens.ssw690.DuckSource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import edu.stevens.ssw690.DuckSource.dao.DuckUserDao;
import edu.stevens.ssw690.DuckSource.model.DuckUser;

/**
 * 
 * @author susan
 * @see interface (DuckUserManager)
 * 
 */

@Service
public class DuckUserManagerImpl implements DuckUserManager{

	@Autowired
	DuckUserDao duckUserDao;
	
	
	public List<DuckUser> getAll() {
		return duckUserDao.getAll();
	}

	public DuckUser findById(Integer id) {
		return duckUserDao.findById(id);
	}

	public void persist(DuckUser user) {
		duckUserDao.persist(user);
		
	}

	public DuckUser getDuckUser(String username, String password) {
		return duckUserDao.getDuckUser(username, password);
	}

	public DuckUser getDuckUser(String username) {
		return duckUserDao.getDuckUser(username);
	}
	
	public boolean getUsernameExists(String username) {
		return duckUserDao.getUsernameExists(username);
	}

	
	public void merge(DuckUser user) {
		duckUserDao.merge(user);
		
	}

	public DuckUser getById(Integer id) {
		return duckUserDao.getById(id);
	}
	
	public List<String> getAllUserNames() {
		return duckUserDao.getAllUserNames();
	}
	
}
