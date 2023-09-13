package com.example.chart.form;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.chart.entity.Student;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
@SuppressWarnings("serial")
public class StudentForm {
	
	/** 生徒ID */
	private Integer stId;
	
	/** 生徒名 */
	@NotBlank
	@Length(max=12)
	private String stName;
	
	/** 生年月日 */
	@NotNull
	@Past
	@DateTimeFormat(iso =DateTimeFormat.ISO.DATE)
	private LocalDate stBirth;
	
	/** 学校名 */
	@Length(max=12)
	private String stSchool;
	
	/** グループ名 */
	@Max(3)
	private Integer stGroup;
	
	/** 年齢 */
	private int stAge;
	
	/** 学年 */
	private String stGrade;
	
	/** ログインID */
	private String loginId;
	
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
	public int getStAge() {
		return stAge;
	}
	public void setStAge(int stAge) {
		this.stAge = stAge;
	}
	public String getStGrade() {
		return stGrade;
	}
	public void setStGrade(String stGrade) {
		this.stGrade = stGrade;
	}

	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
	
	/**
	 * 生年月日から年齢と学年を計算し保持する
	 */
	public void setAgeGrade() {
		LocalDate localBirthdate = getStBirth();
		LocalDate nowDate = LocalDate.now();
		
		if (nowDate.compareTo(localBirthdate) >= 0) {
			// 年齢を計算する
			long age = ChronoUnit.YEARS.between(localBirthdate, nowDate);
			// 年齢
			setStAge((int)age);
		} else {
			setStAge(-1);
		}
		
		LocalDate first = LocalDate.of(localBirthdate.getYear(), 4, 1);
		if (first.isAfter(localBirthdate)) {
			//誕生年の4月1日が誕生日よりも後なら(早生まれ)、年度初日は1年前の4月1日
			first = first.minusYears(1);
		}
		
		LocalDate nowYear = LocalDate.of(nowDate.getYear(), 4, 1);
		if (nowYear.isAfter(nowDate)) {
			//現在が1.2.3月なら去年度で計算
			nowYear = nowYear.minusYears(1);
		}
		
		int grade = (nowYear.getYear())-(first.getYear());
		String schoolGrade = "未計算";
		
		if (grade <= 6) {
			schoolGrade = "未就学";
		}else if (grade <=12) {
			grade -= 6;
			schoolGrade = "小学"+ grade + "年";
		}else if (grade <=15) {
			grade -= 12;
			schoolGrade = "中学"+ grade + "年";
		}else if (grade <=18) {
			grade -= 15;
			schoolGrade = "高校"+ grade + "年";
		}else if (grade <=21) {
			grade -= 18;
			schoolGrade = "大学"+ grade + "年";
		}else {
			schoolGrade = "既卒";
		}
		
		setStGrade(schoolGrade);
		
	}

	/**
	 * formをentityに詰め替えて返す
	 *
	 * @return 生徒Entity
	 */
	public Student toEntity() {
		Student student = new Student();
		student.setStId(this.getStId());
		student.setStName(this.getStName());
		student.setStBirth(this.getStBirth());
		student.setStGroup(this.getStGroup());
		student.setStSchool(this.getStSchool());
		return student;
	}
	
	

}
