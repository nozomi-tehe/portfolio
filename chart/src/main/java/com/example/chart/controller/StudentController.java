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

import com.example.chart.constant.ButtonConst;
import com.example.chart.entity.Student;
import com.example.chart.form.SignupForm;
import com.example.chart.form.StudentForm;
import com.example.chart.form.StudentSrchForm;
import com.example.chart.service.SignupService;
import com.example.chart.service.StudentService;

/**
 * 生徒DB操作Controller
 */
@Controller
@RequestMapping("/admin/student")
public class StudentController {
	
	private final StudentService studentService;
	private final SignupService signupService;
	
	public StudentController(StudentService studentService, SignupService signupService) {
		this.studentService = studentService;
		this.signupService = signupService;
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
		studentForm.setLoginId(String.format("%03d",student.getStId()));
		model.addAttribute("studentForm",studentForm);
		return "updateForm";
	}
	
	@GetMapping("/validate-insert-input")
	public String validateInsertForm(Model model) {
		StudentForm studentForm = new StudentForm();
		model.addAttribute("studentForm",studentForm);
		model.addAttribute("buttoned",ButtonConst.BUTTON_NEW);
		return "insertForm";
	}
	
	@PostMapping(value = "/validate-update-input" ,params = ButtonConst.BUTTON_NEW)
	public String validateInsertForm(@Validated StudentForm studentForm, BindingResult bindResult,Model model) {
		if (bindResult.hasErrors()) {
			return "insertForm";
		}
		model.addAttribute("buttoned",ButtonConst.BUTTON_NEW);
		return "updateConfirmation";
	}
	
	@PostMapping(value = "/validate-update-input" ,params = ButtonConst.BUTTON_UPDATE)
	public String validateUpdateForm(@Validated StudentForm studentForm, BindingResult bindResult,Model model) {
		if (bindResult.hasErrors()) {
			return "updateForm";
		}
		model.addAttribute("buttoned",ButtonConst.BUTTON_UPDATE);
		return "updateConfirmation";
	}
	
	@PostMapping(value = "/validate-update-input" ,params = ButtonConst.BUTTON_DELETE)
	public String validateDeleteForm(@Validated StudentForm studentForm, BindingResult bindResult,Model model) {
		if (bindResult.hasErrors()) {
			return "updateForm";
		}
		model.addAttribute("buttoned",ButtonConst.BUTTON_DELETE);
		return "updateConfirmation";
	}
	
	
	@PostMapping(value = "/update" , params = ButtonConst.BUTTON_UPDATE)
	public String doUpdate(@Validated StudentForm studentForm,Model model) {
		studentService.updateStudent(studentForm.toEntity());
		model.addAttribute("buttoned",ButtonConst.BUTTON_UPDATE);
		return "updateCompletion";
	}
	
	@PostMapping(value = "/update" , params = ButtonConst.BUTTON_DELETE)
	public String doDelete(@Validated StudentForm studentForm,Model model) {
		Student student = studentForm.toEntity();
		studentService.deleteStudentById(student.getStId());
		model.addAttribute("buttoned",ButtonConst.BUTTON_DELETE);
		return "updateCompletion";
	}
	
	@PostMapping(value = "/update" , params = ButtonConst.BUTTON_NEW)
	public String doInsert(@Validated StudentForm studentForm,Model model) {
		Student student = studentForm.toEntity();
		studentService.insertStudent(student);
		model.addAttribute("buttoned",ButtonConst.BUTTON_NEW);
		return "updateCompletion";
	}
	
	@PostMapping(value = "/update" , params = ButtonConst.BUTTON_BACK)
	public String back(@Validated StudentForm studentForm) {
		return "updateForm";
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
	
	
	@GetMapping("/auth-set/{loginId}")
	public String authInsert(@PathVariable String loginId, Model model) {
		
		SignupForm signupForm = new SignupForm(); 
		signupForm.setLoginId(loginId);
		signupForm.setPassword(loginId);
		signupForm.setUserId(Integer.parseInt(loginId));
		
		var userInfoOpt = signupService.resistUserInfo(signupForm);
		if (userInfoOpt.isPresent()) {
			model.addAttribute("signupInfo", userInfoOpt);
		}
		return "signupCompletion";
	}
	
	
	
	

}
