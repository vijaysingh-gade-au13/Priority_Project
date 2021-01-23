package com.priority.service;
import java.util.List;

import com.priority.model.Priority;

public interface PriorityService {
	
	public Boolean isPriorityDataExist(Priority priority);
	
	public List<String> getAllPriorityAreaData();
	
	public void savePriorityData(Priority priority);
	
}
