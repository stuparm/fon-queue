package rs.fon.queue.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Controller;

import rs.fon.queue.domain.Coming;
import rs.fon.queue.repository.ComingRepository;

@Controller
public class CommingRepositoryImpl implements ComingRepository{

	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public void insertComing(Coming coming) {
		getEntityManager().persist(coming);
	}
	
	
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
