package rs.fon.queue.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import rs.fon.queue.domain.User;
import rs.fon.queue.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public User findByUsername(String username) {
		TypedQuery<User> query = getEntityManager().createNamedQuery(User.FIND_BY_NAME, User.class);
		query.setParameter("username", username);
		List<User> results = query.getResultList();
		if (results == null || results.isEmpty())
			return null;
		return results.get(0);
	}
	
	
	@Override
	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	@Transactional
	public User findByUsernameandPassword(String username, String password) {
		
		TypedQuery<User> query = getEntityManager().createNamedQuery(User.FINF_BY_NAME_AND_PASSWORD, User.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		List<User> results = query.getResultList();
		if (results == null || results.isEmpty())
			return null;
		return results.get(0);
	}
	
	
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}




	
	
	
}
