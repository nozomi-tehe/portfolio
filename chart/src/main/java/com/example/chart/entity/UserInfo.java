package com.example.chart.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UserInfo {
	
	@Id
	private String loginId;
	private String password;
	private Integer userId;
	private LocalDate createdAt;
	private LocalDate updatedAt;
	

}
