package com.example.chart.repository;

import java.util.List;

import com.example.chart.entity.Student;


public interface StudentRepository {
	
	void insert(Student student);
	boolean update(Student student);
	boolean delete(Integer stId);
	
    Student selectById(Integer StId);
    List<Student> selectByName(String StName);
    List<Student> selectAll();
	

}
