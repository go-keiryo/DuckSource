package edu.stevens.ssw690.DuckSource.dao;

import java.util.List;

import edu.stevens.ssw690.DuckSource.model.DuckUser;

public interface  DuckUserDao {
	
	public List<DuckUser> getAll();
    public DuckUser findById(Integer id);
    public void persist(DuckUser user);
    DuckUser getDuckUser(String username, String password);

}
