package edu.stevens.ssw690.DuckSource.dao;

import java.util.List;

import edu.stevens.ssw690.DuckSource.model.DuckUser;

public interface  DuckUserDao {
	
	public List<DuckUser> getAll();
    public DuckUser findById(Integer id);
    public DuckUser getById(Integer id);
    public void persist(DuckUser user);
    public void merge(DuckUser user); 
    public DuckUser getDuckUser(String username, String password);
    public boolean getUsernameExists(String username);
}
