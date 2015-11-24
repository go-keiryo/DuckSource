package edu.stevens.ssw690.DuckSource.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.stevens.ssw690.DuckSource.model.DuckUser;

@Repository
@Transactional
@Component
public class DuckUserDaoImpl implements DuckUserDao{

	@PersistenceContext 
	private EntityManager em;
	 
    public void persist(DuckUser user) {
        em.persist(user);
    }
    
	public List<DuckUser> getAll() {
	    List<DuckUser> result = em.createQuery("from DuckUser", DuckUser.class).getResultList();
	    return result;
	 }
	
	public DuckUser findById(Integer id) {
    	return em.find(DuckUser.class, id);
	}
    
    public DuckUser getDuckUser(String username, String password) {
    	DuckUser user = null;
    	Query query = em.createQuery("from DuckUser u WHERE u.userName=:username and u.password=:password)");
    	query.setParameter("username", username);
    	query.setParameter("password", password);
    	@SuppressWarnings("unchecked")
		List<DuckUser> list = (List<DuckUser>) query.getResultList();
    	int size = list.size();
    	if (size > 0)
    		user = list.get(0);
    	return user;
    }
    
    public boolean getUsernameExists(String username) {
        boolean exists = false;
    	Query query = em.createQuery("from DuckUser u WHERE u.userName=:username)");
    	query.setParameter("username", username);
    	@SuppressWarnings("unchecked")
		List<DuckUser> list = (List<DuckUser>) query.getResultList();
    	int size = list.size();
    	if (size > 0) {
    		exists = true;
    	}
    	return exists;
    }
}
