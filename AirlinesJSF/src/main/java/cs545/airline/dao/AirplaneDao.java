package cs545.airline.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import cs545.airline.model.Airplane;
import cs545.airline.nonmanaged.JpaUtil;

public class AirplaneDao {
	private static Logger logger = Logger.getLogger(AirplaneDao.class);
	
	// This is an anti-pattern. To fix this use JTA to manage transactions
	
	public void create(Airplane airplane) {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			tx = entityManager.getTransaction();
			tx.begin();
			
			entityManager.persist(airplane);

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

	public Airplane update(Airplane airplane) {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		Airplane updated= null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			tx = entityManager.getTransaction();
			tx.begin();
			
			updated = entityManager.merge(airplane);

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

	public void delete(Airplane airplane) {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			tx = entityManager.getTransaction();
			tx.begin();
			
			entityManager.remove(airplane);

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

	public Airplane findOne(long id) {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		Airplane airplane= null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			tx = entityManager.getTransaction();
			tx.begin();
			
			airplane = entityManager.find(Airplane.class, id);

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
		return airplane;
	}

	public Airplane findOneBySerialnr(String sno) {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		Airplane airplane= null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			Query query = entityManager.createQuery("select a from Airplane a where a.serialnr=':serial'", Airplane.class);
			query.setParameter("serial", sno);

			tx = entityManager.getTransaction();
			tx.begin();

			airplane = (Airplane) query.getSingleResult();

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
		return airplane;
	}


	@SuppressWarnings("unchecked")
	public List<Airplane> findByModel(String model) {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		List<Airplane> airplanes= null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			Query query = entityManager.createQuery("select a from Airplane a where a.model=':model'", Airplane.class);
			query.setParameter("model", model);

			tx = entityManager.getTransaction();
			tx.begin();

			airplanes = query.getResultList();

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
		return airplanes;
	}

	@SuppressWarnings("unchecked")
	public List<Airplane> findByFlight(long flightId) {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		List<Airplane> airplanes= null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			Query query = entityManager.createQuery("select distinct a from Airplane a join a.flights f where f.id=:flightId", Airplane.class);
			query.setParameter("flightId", flightId);

			tx = entityManager.getTransaction();
			tx.begin();

			airplanes = query.getResultList();

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
		return airplanes;
	}

	
	public List<Airplane> findAll() {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		List<Airplane> airplanes= null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			tx = entityManager.getTransaction();
			tx.begin();
			
			airplanes = entityManager.createQuery("from airplanes", Airplane.class).getResultList();

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
		return airplanes;
	}

}
