package com.payci.soner.hibernate.base;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.payci.soner.entities.base.BaseEntity;


public abstract class BaseRepository<T extends BaseEntity> {

	private SessionFactory sessionFactory;

	protected Session session;
	
	public BaseRepository() {
		Configuration config = new Configuration();
		config.configure();
		this.sessionFactory = config.buildSessionFactory();
		
		// In general, we need different sessions for different requests for each request.
		// Since this is an example project, until any Repository which extends BaseRepository disposes, the session will remain.
		this.session = this.sessionFactory.openSession();
	}

	
	@Transactional
	public List<T> getAll(Class<T> entityClass) {
		Transaction transaction = null;
		List<T> entityRecords = new ArrayList<>();
		try /*(Session session = sessionFactory.openSession())*/ {
			// start a transaction
            transaction = session.beginTransaction();
            // get a list of desired entity based on db records
            CriteriaBuilder builder = session.getCriteriaBuilder();
		    CriteriaQuery<T> criteria = builder.createQuery(entityClass);
		    criteria.from(entityClass);
		    entityRecords = session.createQuery(criteria).getResultList();
            // commit transaction
            transaction.commit();
		    return entityRecords;
		} catch (Exception e) {
			if (transaction != null) {
                transaction.rollback();
            }
			e.printStackTrace();
            return entityRecords;
        }
	}

	@Transactional
	public T get(Class<T> entityClass, long id) {
        Transaction transaction = null;
        T entity = null;
        try /*(/*Session session = sessionFactory.openSession())*/ {
            // start a transaction
            transaction = session.beginTransaction();
            // get an entity object
            entity = session.get(entityClass, id);
            // commit transaction
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

	public void save(T entity) {
        Transaction transaction = null;
        try /*(Session session = sessionFactory.openSession())*/ {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(entity);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
	
	public void saveOrUpdate(T entity) {
        Transaction transaction = null;
        try /*(Session session = sessionFactory.openSession())*/ {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.saveOrUpdate(entity);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void update(T entity) {
        Transaction transaction = null;
        try /*(Session session = sessionFactory.openSession())*/ {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.update(entity);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(T entity) {
        Transaction transaction = null;
        try /*(Session session = sessionFactory.openSession())*/ {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete using entity
            if (entity != null) {
                session.delete(entity);
                System.out.println("entity is deleted");
            }
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public  void deleteById(Class<T> entityClass, long id) {
        Transaction transaction = null;
        try /*(Session session = sessionFactory.openSession())*/ {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete using entity class and id
            T entity = session.get(entityClass, id);
            if (entity != null) {
                session.delete(entity);
                System.out.println("entity is deleted");
            }

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
