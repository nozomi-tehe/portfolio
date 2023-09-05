package com.example.chart.repository;

import java.util.List;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.chart.entity.Student;

@Repository
public class JdbcStudentRepository implements StudentRepository {
	
	private final JdbcTemplate jdbcTemplate;

    public JdbcStudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    //更新系
    @Override
	public void insert(Student student) {
		jdbcTemplate.update("INSERT INTO students (st_name,st_birth,st_group,st_school) VALUES (?, ?, ?, ?)",

                student.getStName(),
                student.getStBirth(),
                student.getStSchool(),
                student.getStGroup()
                );

	}

	@Override
	public boolean update(Student student) {
		int count = jdbcTemplate.update("UPDATE students SET st_name=?,st_birth=?,st_school=?,st_group=? WHERE st_id=?",
				
				student.getStName(),
				student.getStBirth(),
				student.getStSchool(),
				student.getStGroup(),
				student.getStId()
				);
		if (count != 0) {
    		return true;
    	}else {
    		return false;
    	}
		
	}

	@Override
	public boolean delete(Integer stId) {
		int count = jdbcTemplate.update("DELETE FROM students WHERE stId=?,stName=?",
				stId
				);
		if (count != 0) {
    		return true;
    	}else {
    		return false;
    	}
		
	}
	
	//検索系
	@Override
	public Student selectById(Integer stId) {
		return jdbcTemplate.queryForObject("SELECT * FROM students WHERE st_id=?", new DataClassRowMapper<>(Student.class), stId);
	}
	
	@Override
	public List<Student> selectByName(String stName) {
		return jdbcTemplate.query("SELECT * FROM students WHERE st_name LIKE  '%'||?||'%'", new DataClassRowMapper<>(Student.class),stName);
	}

	@Override
	public List<Student> selectAll() {
		return jdbcTemplate.query("SELECT * FROM students ORDER BY st_id", new DataClassRowMapper<>(Student.class));
	}
	
	



	

}
