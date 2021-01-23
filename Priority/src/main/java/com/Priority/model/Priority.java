package com.tatsam.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.sun.istack.NotNull;
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
	@NotNull
	@Column(name="priority_id")
	public Long getPriorityId() {
		return priorityId;
	}
	public void setAreaId(Long priorityId) {
		this.priorityId = priorityId;
	}
	
	@NotEmpty(message = "Please enter area name")
	@Column(name="area_name")
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	@NotEmpty(message = "Please enter rating")
	@Column(name="rating")
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
	@NotEmpty(message = "Please enter priority level")
	@Column(name="priority_level")
	public String getPriorityLevel() {
		return priorityLevel;
	}
	public void setPriorityType(String priorityLevel) {
		this.priorityLevel = priorityLevel;
	}
	

}
