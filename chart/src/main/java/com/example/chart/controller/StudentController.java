package com.example.chart.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.chart.entity.Student;
import com.example.chart.form.StudentForm;
import com.example.chart.form.StudentSrchForm;
import com.example.chart.service.StudentService;

/**
 * 
 * 
 * @author 3030627
 *
 */
@Controller
@RequestMapping("/admin/student")
public class StudentController {
	

	private final StudentService studentService ;
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@GetMapping("/display-list")
    public String displayList(Model model) {
		
		StudentSrchForm studentSrchForm = new StudentSrchForm();
		model.addAttribute("studentSrchForm", studentSrchForm);
        List<Student> students = studentService.findAll();
        List<StudentForm> studentForms = new ArrayList<>();
        
        
        for (Student student: students) {
        	StudentForm studentForm = new StudentForm();
        	studentForm.setStId(student.getStId());
    		studentForm.setStName(student.getStName());
    		studentForm.setStBirth(student.getStBirth());
    		studentForm.setStGroup(student.getStGroup());
    		studentForm.setStSchool(student.getStSchool());
    		studentForm.setAgeGrade();
    		studentForms.add(studentForm);
        }
        
        model.addAttribute("studentList", studentForms);
        return "studentList";
    }
	
	@GetMapping("/display-list/{stId}")
	public String displayUpdate(@PathVariable Integer stId, Model model) {
		Student student = studentService.findOneById(stId);
		StudentForm studentForm = new StudentForm();
		studentForm.setStId(student.getStId());
		studentForm.setStName(student.getStName());
		studentForm.setStBirth(student.getStBirth());
		studentForm.setStGroup(student.getStGroup());
		studentForm.setStSchool(student.getStSchool());
		studentForm.setLoginId(student.getStId());
		model.addAttribute("studentForm",studentForm);
		return "updateForm";
	}
	
	@PostMapping(value = "/validate-update-input" ,params = "update")
	public String validateUpdateForm(@Validated StudentForm studentForm, BindingResult bindResult) {
		if (bindResult.hasErrors()) {
			return "updateForm";
		}
		return "updateConfirmation";
	}
	
	@PostMapping(value = "/update" , params = "update")
	public String doUpdate(@Validated StudentForm studentForm) {
		studentService.updateStudent(studentForm.toEntity());
		return "updateCompletion";
	}
	
	@PostMapping(value = "/update" , params = "correct")
	public String correct(@Validated StudentForm studentForm) {
		return "updateForm";
	}
	
	@PostMapping(value = "/validate-update-input" ,params = "delete")
	public String validateDeleteForm(@Validated StudentForm studentForm) {
		return "deleteConfirmation";
	}
	
	@PostMapping("/search")
    public String getSearch(@Validated StudentSrchForm studentSrchForm,BindingResult bindingResult,Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("studentSrchForm", studentSrchForm);
			return "studentList";
		}
		
		List<Student> students = new ArrayList<>();
		if (studentSrchForm.getId() != null) {
			students = studentService.findById(studentSrchForm.getId());
		}else if (!(studentSrchForm.getName().equals("")) ) {
			students = studentService.findByName(studentSrchForm.getName());
		}else {
			return "redirect:/admin/student/display-list";
		}
		
        List<StudentForm> studentForms = new ArrayList<>();
        
        for (Student student: students) {
        	StudentForm studentForm = new StudentForm();
        	studentForm.setStId(student.getStId());
    		studentForm.setStName(student.getStName());
    		studentForm.setStBirth(student.getStBirth());
    		studentForm.setStGroup(student.getStGroup());
    		studentForm.setStSchool(student.getStSchool());
    		studentForm.setAgeGrade();
    		studentForms.add(studentForm);
        }
        
        model.addAttribute("studentList", studentForms);
        return "studentList";
    }
	
	

}
