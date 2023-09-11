package com.example.chart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.chart.entity.Student;
import com.example.chart.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{
	
	private final StudentRepository studentRepository;
	
	public StudentServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	//登録更新系
	@Override
	public void insertStudent(Student student) {
		studentRepository.insert(student);
	}

	@Override
	public void updateStudent(Student student) {
		studentRepository.update(student);
		
	}

	@Override
	public void deleteStudentById(Integer StId) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	
	//検索系
	@Override
	public List<Student> findAll() {
		return studentRepository.selectAll();
	}

	@Override
	public List<Student> findById(Integer StId) {
		return studentRepository.selectById(StId);
	}

	@Override
	public List<Student> findByName(String StName) {
		return studentRepository.selectByName(StName);
	}

	@Override
	public Student findOneById(Integer stId) {
		return studentRepository.selectOneById(stId);
	}
	
	

}
