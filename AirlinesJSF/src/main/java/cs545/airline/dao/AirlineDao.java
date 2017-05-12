package cs545.airline.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import cs545.airline.model.Airline;
import cs545.airline.nonmanaged.JpaUtil;

public class AirlineDao {
	private static Logger logger = Logger.getLogger(AirlineDao.class);
	
	// This is an anti-pattern. To fix this use JTA to manage transactions
	
	public void create(Airline airline) {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			tx = entityManager.getTransaction();
			tx.begin();
			
			entityManager.persist(airline);

			tx.commit();
			
		} catch (PersistenceException e) {
			if (tx != null && tx.isActive()) {
				logger.error("Rolling back", e);
				tx.rollback();
			}
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}		
	}

	public Airline update(Airline airline) {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		Airline updated= null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			tx = entityManager.getTransaction();
			tx.begin();
			
			updated = entityManager.merge(airline);

			tx.commit();
			
		} catch (PersistenceException e) {
			if (tx != null && tx.isActive()) {
				logger.error("Rolling back", e);
				tx.rollback();
			}
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
		return updated;
	}

	public void delete(Airline airline) {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			tx = entityManager.getTransaction();
			tx.begin();
			
			entityManager.remove(airline);

			tx.commit();
			
		} catch (PersistenceException e) {
			if (tx != null && tx.isActive()) {
				logger.error("Rolling back", e);
				tx.rollback();
			}
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
	}

	public Airline findOne(long id) {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		Airline airline= null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			tx = entityManager.getTransaction();
			tx.begin();
			
			airline = entityManager.find(Airline.class, id);

			tx.commit();
			
		} catch (PersistenceException e) {
			if (tx != null && tx.isActive()) {
				logger.error("Rolling back", e);
				tx.rollback();
			}
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
		return airline;
	}

	public Airline findOneByName(String name) {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		Airline airline= null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			Query query = entityManager.createQuery("select a from Airline a where a.name=':name'", Airline.class);
			query.setParameter("name", name);

			tx = entityManager.getTransaction();
			tx.begin();

			airline = (Airline) query.getSingleResult();

			tx.commit();
			
		} catch (PersistenceException e) {
			if (tx != null && tx.isActive()) {
				logger.error("Rolling back", e);
				tx.rollback();
			}
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
		return airline;
	}


	@SuppressWarnings("unchecked")
	public List<Airline> findByFlight(long flightId) {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		List<Airline> airlines= null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			Query query = entityManager.createQuery("select distinct a from Airline a join a.flights f where f.id=:flightId", Airline.class);
			query.setParameter("flightId", flightId);

			tx = entityManager.getTransaction();
			tx.begin();

			airlines = query.getResultList();

			tx.commit();
			
		} catch (PersistenceException e) {
			if (tx != null && tx.isActive()) {
				logger.error("Rolling back", e);
				tx.rollback();
			}
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
		return airlines;
	}

	
	public List<Airline> findAll() {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		List<Airline> airlines= null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			tx = entityManager.getTransaction();
			tx.begin();
			
			airlines = entityManager.createQuery("from Airline", Airline.class).getResultList();

			tx.commit();
			
		} catch (PersistenceException e) {
			if (tx != null && tx.isActive()) {
				logger.error("Rolling back", e);
				tx.rollback();
			}
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
		return airlines;
	}

}
