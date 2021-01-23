package com.tatsam.serviceImplementation;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tatsam.dao.PriorityDao;
import com.tatsam.model.Priority;
import com.tatsam.service.PriorityService;


@Service
public class PriorityServiceImplementation implements PriorityService{

	@Autowired
	private PriorityDao priorityDao;
	
	
	
	@Override
	public Boolean isPriorityDataExist(Priority priority) {
		return priorityDao.isPriorityDataExist(priority);
	}

	@Override
	public List<String> getAllPriorityAreaData() {
		return priorityDao.getAllPriorityAreaData();
	}

	@Override
	public void savePriorityData(Priority priority) {
		priorityDao.savePriorityData(priority);
	}

}
