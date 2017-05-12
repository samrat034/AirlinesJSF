package cs545.airline.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.apache.log4j.Logger;

import cs545.airline.model.Flight;
import cs545.airline.nonmanaged.JpaUtil;

public class FlightDao {
	private static Logger logger = Logger.getLogger(FlightDao.class);
	
	// This is an anti-pattern. To fix this use JTA to manage transactions
	
	public void create(Flight flight) {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			tx = entityManager.getTransaction();
			tx.begin();
			
			entityManager.persist(flight);

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

	public Flight update(Flight flight) {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		Flight updated= null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			tx = entityManager.getTransaction();
			tx.begin();
			
			updated = entityManager.merge(flight);

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

	public void delete(Flight flight) {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			tx = entityManager.getTransaction();
			tx.begin();
			
			entityManager.remove(flight);

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

	public Flight findOne(long id) {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		Flight flight= null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			tx = entityManager.getTransaction();
			tx.begin();
			
			flight = entityManager.find(Flight.class, id);

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
		return flight;
	}

	@SuppressWarnings("unchecked")
	public List<Flight> findByFlightnr(String flightnr) {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		List<Flight> flights= null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			Query query = entityManager.createQuery("select f from Flight f where f.flightnr=':flightnr'", Flight.class);
			query.setParameter("flightnr", flightnr);

			tx = entityManager.getTransaction();
			tx.begin();
			flights = query.getResultList();

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
		return flights;
	}

	@SuppressWarnings("unchecked")
	public List<Flight> findByOrigin(long airportId) {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		List<Flight> flights= null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			Query query = entityManager.createQuery("select f from Flight f join f.origin o where o.id=':airportId'", Flight.class);
			query.setParameter("airportId", airportId);

			tx = entityManager.getTransaction();
			tx.begin();

			flights = query.getResultList();

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
		return flights;
	}

	@SuppressWarnings("unchecked")
	public List<Flight> findByDestination(long airportId) {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		List<Flight> flights= null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			Query query = entityManager.createQuery("select f from Flight f join f.destination d where d.id=':airportId'", Flight.class);
			query.setParameter("airportId", airportId);

			tx = entityManager.getTransaction();
			tx.begin();

			flights = query.getResultList();

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
		return flights;
	}
	
	@SuppressWarnings("unchecked")
	public List<Flight> findByAirplane(long airplaneId) {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		List<Flight> flights= null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			Query query = entityManager.createQuery("select f from Flight f join f.airplane a where a.id=':airplaneId'", Flight.class);
			query.setParameter("airplaneId", airplaneId);

			tx = entityManager.getTransaction();
			tx.begin();

			flights = query.getResultList();

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
		return flights;
	}

	@SuppressWarnings("unchecked")
	public List<Flight> findByAirline(long airlineId) {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		List<Flight> flights= null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			Query query = entityManager.createQuery("select f from Flight f join f.airline a where a.id=':airlineId'", Flight.class);
			query.setParameter("airlineId", airlineId);

			tx = entityManager.getTransaction();
			tx.begin();

			flights = query.getResultList();

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
		return flights;
	}
	
	@SuppressWarnings("unchecked")
	public List<Flight> findByDeparture(Date date, Date time) {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		List<Flight> flights= null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			
			Query query = entityManager.createQuery("select distinct a from Flight a where a.departureDate=:departureDate and departureTime=:departureTime", Flight.class);
			query.setParameter("departureDate", date, TemporalType.DATE);
			query.setParameter("departureTime", time, TemporalType.TIME);

			tx = entityManager.getTransaction();
			tx.begin();
			
			flights = query.getResultList();

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
		return flights;
	}	

	@SuppressWarnings("unchecked")
	public List<Flight> findByDepartureBetween(Date dateFrom, Date dateTo, Date timeFrom, Date timeTo) {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		List<Flight> flights= null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			
			Query query = entityManager.createQuery("select distinct a from Flight a where a.arrivalDate between :DateFrom and :DateTo and arrivalTime beteween :TimeFrom and :TimeTo", Flight.class);
			query.setParameter("DateFrom", dateFrom, TemporalType.DATE);
			query.setParameter("TimeFrom", dateFrom, TemporalType.TIME);
			query.setParameter("DateTo", dateTo, TemporalType.DATE);
			query.setParameter("TimeTo", dateTo, TemporalType.TIME);

			tx = entityManager.getTransaction();
			tx.begin();
			
			flights = query.getResultList();

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
		return flights;
	}	


	@SuppressWarnings("unchecked")
	public List<Flight> findByArrival(Date date, Date time) {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		List<Flight> flights= null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			
			Query query = entityManager.createQuery("select distinct a from Flight a where a.arrivalDate=:arrivalDate and arrivalTime=:arrivalTime", Flight.class);
			query.setParameter("arrivalDate", date, TemporalType.DATE);
			query.setParameter("arrivalTime", time, TemporalType.TIME);

			tx = entityManager.getTransaction();
			tx.begin();
			
			flights = query.getResultList();

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
		return flights;
	}	

	@SuppressWarnings("unchecked")
	public List<Flight> findByArrivalBetween(Date dateFrom, Date dateTo, Date timeFrom, Date timeTo) {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		List<Flight> flights= null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			
			Query query = entityManager.createQuery("select distinct a from Flight a where a.arrivalDate between :arrivalDateFrom and :arrivalDateTo and arrivalTime beteween :arrivalTimeFrom and :arrivalTimeTo", Flight.class);
			query.setParameter("arrivalDateFrom", dateFrom, TemporalType.DATE);
			query.setParameter("arrivalTimeFrom", dateFrom, TemporalType.TIME);
			query.setParameter("arrivalDateTo", dateTo, TemporalType.DATE);
			query.setParameter("arrivalTimeTo", dateTo, TemporalType.TIME);

			tx = entityManager.getTransaction();
			tx.begin();
			
			flights = query.getResultList();

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
		return flights;
	}	

	
	
	public List<Flight> findAll() {
		EntityManager entityManager=null;
		EntityTransaction tx=null;
		List<Flight> flights= null;
		try {

			entityManager = JpaUtil.getEntityManager();
			
			tx = entityManager.getTransaction();
			tx.begin();
			
			flights = entityManager.createQuery("from Flight", Flight.class).getResultList();

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
		return flights;
	}

}
