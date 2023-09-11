package com.example.chart.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
	
	@Id
	private String loginId;
	private String password;
	private Integer userId;
	private LocalDate createdAt;
	private LocalDate updatedAt;
	

}
