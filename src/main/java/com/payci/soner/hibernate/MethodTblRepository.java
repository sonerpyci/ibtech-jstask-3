package com.payci.soner.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Transaction;

import com.payci.soner.entities.reflection.MethodTbl;
import com.payci.soner.hibernate.base.BaseRepository;

public class MethodTblRepository extends BaseRepository<MethodTbl> {
	@Transactional
	public List<MethodTbl> getAll() {
		return getAll(MethodTbl.class);
	}

	@Transactional
	public MethodTbl get(long id) {
		MethodTbl method = get(MethodTbl.class, id);
		method.getCommandTbl();
		method.getParameters();
		return method;
	}
	
	@Transactional
	public MethodTbl getByCommandName(String commandName) {
		Transaction transaction = null;
		List<MethodTbl> entityRecords = new ArrayList<>();
        try {
            // start a transaction
            transaction = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
		    CriteriaQuery<MethodTbl> criteria = builder.createQuery(MethodTbl.class);
		    
		    Root<MethodTbl> root = criteria.from(MethodTbl.class);
		    criteria.where(builder.equal(root.get("commandName"), commandName));
		    entityRecords = session.createQuery(criteria).getResultList();
            // commit transaction
            transaction.commit();
            
            if (!entityRecords.isEmpty()) {
            	MethodTbl entity = entityRecords.get(0);
            	entity.getCommandTbl();
            	entity.getParameters();
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
		super.deleteById(MethodTbl.class, id);
	}
}
