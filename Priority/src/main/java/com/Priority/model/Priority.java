package com.priority.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;




@Entity
@Table(name="priority")

public class Priority {
    
	private static final long serialVersionUID = 1L;
	
	private Long priorityId;
	private String areaName;
	private Integer rating;
	private String priorityLevel;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="priority_id")
	public Long getPriorityId() {
		return priorityId;
	}
	public void setPriorityId(Long priorityId) {
		this.priorityId = priorityId;
	}
	
	@Column(name="area_name")
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	@Min(1)
	@Max(5)
	@Column(name="rating")
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
	@Column(name="priority_level")
	public String getPriorityLevel() {
		return priorityLevel;
	}
	public void setPriorityLevel(String priorityLevel) {
		this.priorityLevel = priorityLevel;
	}
	

}
