package com.priority.daoImplementation;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.priority.dao.PriorityDao;
import com.priority.model.Priority;


@Transactional
@Repository
public class PriorityDaoImplementation implements PriorityDao{

	public static final Logger logger = LoggerFactory.getLogger(PriorityDaoImplementation.class);
	
	
	@PersistenceContext		
	private EntityManager entityManager;
    
	
	//------------Get All Data from Table Priority---------------------------------------
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllPriorityAreaData() {
		Query query = null; 
		query = entityManager.createQuery("select p.areaName from Priority p");
		
		List<String> listOfPriorityAreaData =(List<String>) query.getResultList();
		return listOfPriorityAreaData;
	}

	
	//-------------Check if Data Exist in Database by ID---------------------------------
	@SuppressWarnings("unchecked")
	@Override
	public Boolean isPriorityDataExist(Priority priority) {
	    
		String priorityId =null;
		Query query = null;
		query  = entityManager.createQuery("select p.areaName from Priority p where p.priorityId=:id or p.areaName=:area");
		query.setParameter("id", priority.getPriorityId());
		query.setParameter("area", priority.getAreaName());
		priorityId = (String)query.getResultStream().findFirst().orElse(null);
	    if (priorityId != null ) {
		    return true;
	    }else {
	    	return false;
	    }
	}
	
    //-------------Save Priority Data to Database----------------------------------------
	@Override
	public void savePriorityData(Priority priority) {
		try {
            entityManager.persist(priority);
		}catch(Exception e) {
			logger.error("Exception while saving Area Data to Database : ",e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	

}
