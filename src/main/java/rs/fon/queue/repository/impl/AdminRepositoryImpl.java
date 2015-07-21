package rs.fon.queue.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import rs.fon.queue.domain.Admin;
import rs.fon.queue.domain.User;
import rs.fon.queue.repository.AdminRepository;

@Repository
public class AdminRepositoryImpl implements AdminRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	@Transactional
	public Admin findByUsername(String username) {
		TypedQuery<Admin> query = getEntityManager().createNamedQuery(Admin.FIND_BY_NAME, Admin.class);
		query.setParameter("username", username);
		List<Admin> results = query.getResultList();
		if (results == null || results.isEmpty())
			return null;
		return results.get(0);
	}

	@Override
	@Transactional
	public void insertUser(User user) {
		getEntityManager().persist(user);;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	
}
