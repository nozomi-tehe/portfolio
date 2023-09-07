package com.example.chart.service;

import java.util.List;

import com.example.chart.entity.Student;

public interface StudentService {
	
	void insertStudent(Student student);
	void updateStudent(Student student);
	void deleteStudentById(Integer StId);
	
	List<Student> findAll();
	List<Student> findById(Integer StId);
    List<Student> findByName(String StId);
	Student findOneById(Integer stId);


}
