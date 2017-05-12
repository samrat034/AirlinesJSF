package cs545.airline.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import cs545.airline.model.Airport;
import cs545.airline.nonmanaged.JpaUtil;

public class AirportDao {
	private static Logger logger = Logger.getLogger(AirportDao.class);
	
	// This is an anti-pattern. To fix this use JTA to manage transactions
	
	public void create(Airport airport) {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			tx = entityManager.getTransaction();
			tx.begin();
			
			entityManager.persist(airport);

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

	public Airport update(Airport airport) {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		Airport updated= null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			tx = entityManager.getTransaction();
			tx.begin();
			
			updated = entityManager.merge(airport);

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

	public void delete(Airport airport) {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			tx = entityManager.getTransaction();
			tx.begin();
			
			entityManager.remove(airport);

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

	public Airport findOne(long id) {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		Airport airport= null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			tx = entityManager.getTransaction();
			tx.begin();
			
			airport = entityManager.find(Airport.class, id);

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
		return airport;
	}

	public Airport findOneByCode(String airportcode) {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		Airport airport= null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			Query query = entityManager.createQuery("select a from Airport a where a.airportcode=':airportcode'", Airport.class);
			query.setParameter("airportcode", airportcode);

			tx = entityManager.getTransaction();
			tx.begin();

			airport = (Airport) query.getSingleResult();

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
		return airport;
	}


	@SuppressWarnings("unchecked")
	public List<Airport> findByCity(String city) {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		List<Airport> airports= null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			Query query = entityManager.createQuery("select a from Airport a where a.city=':city'", Airport.class);
			query.setParameter("city", city);

			tx = entityManager.getTransaction();
			tx.begin();

			airports = query.getResultList();

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
		return airports;
	}

	@SuppressWarnings("unchecked")
	public List<Airport> findByName(String name) {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		List<Airport> airports= null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			Query query = entityManager.createQuery("select a from Airport a where a.name like ':name'", Airport.class);
			query.setParameter("name", name);

			tx = entityManager.getTransaction();
			tx.begin();

			airports = query.getResultList();

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
		return airports;
	}

	@SuppressWarnings("unchecked")
	public List<Airport> findByCountry(String country) {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		List<Airport> airports= null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			Query query = entityManager.createQuery("select a from Airport a where a.country=':country'", Airport.class);
			query.setParameter("country", country);

			tx = entityManager.getTransaction();
			tx.begin();

			airports = query.getResultList();

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
		return airports;
	}
	
	@SuppressWarnings("unchecked")
	public List<Airport> findByArrival(long flightId) {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		List<Airport> airports= null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			Query query = entityManager.createQuery("select distinct a from Airport a join a.arrivals ar where ar.id=:flightId", Airport.class);
			query.setParameter("flightId", flightId);

			tx = entityManager.getTransaction();
			tx.begin();

			airports = query.getResultList();

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
		return airports;
	}

	@SuppressWarnings("unchecked")
	public List<Airport> findByDeparture(long flightId) {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		List<Airport> airports= null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			Query query = entityManager.createQuery("select distinct a from Airport a join a.departures d where d.id=:flightId", Airport.class);
			query.setParameter("flightId", flightId);

			tx = entityManager.getTransaction();
			tx.begin();

			airports = query.getResultList();

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
		return airports;
	}	
	public List<Airport> findAll() {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		List<Airport> airports= null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			tx = entityManager.getTransaction();
			tx.begin();
			
			airports = entityManager.createQuery("from airports", Airport.class).getResultList();

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
		return airports;
	}

}
