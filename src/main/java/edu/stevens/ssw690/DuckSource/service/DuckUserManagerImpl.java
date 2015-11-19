package edu.stevens.ssw690.DuckSource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.stevens.ssw690.DuckSource.dao.DuckUserDao;
import edu.stevens.ssw690.DuckSource.model.DuckUser;

@Service
public class DuckUserManagerImpl implements DuckUserManager{

	@Autowired
	DuckUserDao dao;
	
	
	public List<DuckUser> getAll() {
		return dao.getAll();
	}

	public DuckUser findById(Integer id) {
		return dao.findById(id);
	}

	public void persist(DuckUser user) {
		dao.persist(user);
		
	}

	public DuckUser getDuckUser(String username, String password) {
		return dao.getDuckUser(username, password);
	}
   
}
