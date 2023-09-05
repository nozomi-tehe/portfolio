package com.example.chart.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "students")
public class Student {
	
	@Id
	private Integer stId;
	private String stName;
	private LocalDate stBirth;
	private String stSchool;
	private Integer stGroup;
	
	private String buttonId;
	
	public Student() {}
	
	public Student(Integer stId, String stName, LocalDate stBirth, String stSchool, Integer stGroup) {
		this.stId = stId;
		this.stName = stName;
		this.stBirth = stBirth;
		this.stSchool = stSchool;
		this.stGroup = stGroup;
	}
	public Integer getStId() {
		return stId;
	}
	public void setStId(Integer stId) {
		this.stId = stId;
	}
	public String getStName() {
		return stName;
	}
	public void setStName(String stName) {
		this.stName = stName;
	}
	public LocalDate getStBirth() {
		return stBirth;
	}
	public void setStBirth(LocalDate stBirth) {
		this.stBirth = stBirth;
	}
	public String getStSchool() {
		return stSchool;
	}
	public void setStSchool(String stSchool) {
		this.stSchool = stSchool;
	}
	public Integer getStGroup() {
		return stGroup;
	}
	public void setStGroup(Integer stGroup) {
		this.stGroup = stGroup;
	}
	
	public String getButtonId() {
		return buttonId;
	}

	public void setButtonId(String buttonId) {
		this.buttonId = buttonId;
	}


}
