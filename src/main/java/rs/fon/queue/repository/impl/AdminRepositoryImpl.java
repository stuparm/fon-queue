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
	public Admin findByUsernameAndPassword(String username, String password) {
		TypedQuery<Admin> query = getEntityManager().createNamedQuery(Admin.FIND_BY_NAME_AND_PASS, Admin.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
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
	
	
	@Override
	public List<Admin> getAdmins() {
		TypedQuery<Admin> query = getEntityManager().createQuery("SELECT a from Admin a", Admin.class);
		List<Admin> results = query.getResultList();
		return results;
	}
	
	@Override
	public List<User> getUsers() {
		TypedQuery<User> query = getEntityManager().createQuery("SELECT u from User u", User.class);
		List<User> results = query.getResultList();
		return results;
	}
	
	@Override
	@Transactional
	public boolean delete(User user) {
		try {
			getEntityManager().remove(getEntityManager().contains(user) ? user : getEntityManager().merge(user));
		} catch (IllegalArgumentException e) {
			return false;
		}
		return true;
	}
	
	@Override
	@Transactional
	public void insertAdmin(Admin admin) {
		getEntityManager().persist(admin);
		
	}
	
	@Override
	@Transactional
	public boolean delete(Admin admin) {
		try {
			getEntityManager().remove(getEntityManager().contains(admin) ? admin : getEntityManager().merge(admin));
		} catch (IllegalArgumentException e) {
			return false;
		}
		return true;
	}
	
	@Override
	@Transactional
	public boolean update(User user) {
		try {
			getEntityManager().merge(user);
		} catch (IllegalArgumentException e) {
			return false;
		}
		return true;
	}
	
	@Override
	@Transactional
	public boolean update(Admin admin) {
		try {
			getEntityManager().merge(admin);
		} catch (IllegalArgumentException e) {
			return false;
		}
		return true;
	}
	
	
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}




	
}
