package cs545.airline.nonmanaged;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
	private EntityManagerFactory entityManagerFactory = null;
	private static final JpaUtil jpaUtil = new JpaUtil();

	private JpaUtil() {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("cs545");
		} catch (Throwable ex) {
			if ((entityManagerFactory!=null) && (entityManagerFactory.isOpen())) {
				entityManagerFactory.close();
			}
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	@Override
	protected void finalize() throws Throwable {
		if ((entityManagerFactory!=null) && (entityManagerFactory.isOpen())) {
			entityManagerFactory.close();
		}
		super.finalize();
	}

	static public EntityManager getEntityManager() {
		return jpaUtil.entityManagerFactory.createEntityManager();
	}
	
	static public void destroyJpaUtil() {
		if ((jpaUtil.entityManagerFactory!=null) && (jpaUtil.entityManagerFactory.isOpen())) {
			jpaUtil.entityManagerFactory.close();
		}
	}
}
