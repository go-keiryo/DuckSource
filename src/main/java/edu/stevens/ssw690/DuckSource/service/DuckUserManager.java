package edu.stevens.ssw690.DuckSource.service;

import java.util.List;

import edu.stevens.ssw690.DuckSource.model.DuckUser;

public interface DuckUserManager {

	public List<DuckUser> getAll();
    public DuckUser findById(Integer id);
    public void persist(DuckUser user);
    public DuckUser getDuckUser(String username, String password);
    public boolean getUsernameExists(String username);
}
