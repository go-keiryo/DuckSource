package edu.stevens.ssw690.DuckSource.service;

import java.util.List;

import edu.stevens.ssw690.DuckSource.model.DuckUser;

public interface DuckUserManager {

	public List<DuckUser> getAll();
	public void merge(DuckUser user); 
    public DuckUser findById(Integer id);
    public DuckUser getById(Integer id);
    public void persist(DuckUser user);
    public DuckUser getDuckUser(String username, String password);
    public DuckUser getDuckUser(String username);
    public boolean getUsernameExists(String username);
}
