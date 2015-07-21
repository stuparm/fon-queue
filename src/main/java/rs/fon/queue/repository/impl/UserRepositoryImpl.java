package rs.fon.queue.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import rs.fon.queue.domain.User;
import rs.fon.queue.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public User findByUsername(String username) {
		TypedQuery<User> query = getEntityManager().createNamedQuery(User.FIND_BY_NAME, User.class);
		query.setParameter("username", username);
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
