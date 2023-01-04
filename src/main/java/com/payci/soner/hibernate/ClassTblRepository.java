package com.payci.soner.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Transaction;

import com.payci.soner.entities.reflection.CommandTbl;
import com.payci.soner.hibernate.base.BaseRepository;

public class ClassTblRepository extends BaseRepository<CommandTbl> {
	@Transactional
	public List<CommandTbl> getAll() {
		return getAll(CommandTbl.class);
	}

	@Transactional
	public CommandTbl get(long id) {
		CommandTbl clazz = get(CommandTbl.class, id);
		clazz.getMethods();
		return clazz;
	}
	
	@Transactional
	public CommandTbl getByName(String name) {
		Transaction transaction = null;
		List<CommandTbl> entityRecords = new ArrayList<>();
        try /*(/*Session session = sessionFactory.openSession())*/ {
            // start a transaction
            transaction = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
		    CriteriaQuery<CommandTbl> criteria = builder.createQuery(CommandTbl.class);
		    
		    Root<CommandTbl> root = criteria.from(CommandTbl.class);
		    criteria.where(builder.equal(root.get("name"), name));
		    entityRecords = session.createQuery(criteria).getResultList();
            // commit transaction
            transaction.commit();
            
            if (!entityRecords.isEmpty()) {
            	CommandTbl entity =  entityRecords.get(0);
            	entity.getMethods();
            	return entity;
            }
            
            return null;
            
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
	}

	public void deleteById(long id) {
		super.deleteById(CommandTbl.class, id);
	}
}
