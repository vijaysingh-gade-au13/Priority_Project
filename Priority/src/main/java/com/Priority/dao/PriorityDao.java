package com.tatsam.dao;

import java.util.List;

import com.tatsam.model.Priority;

public interface PriorityDao {

	public Boolean isPriorityDataExist(Priority priority);
	
	public List<String> getAllPriorityAreaData();
	
	public void savePriorityData(Priority priority);
	
}
