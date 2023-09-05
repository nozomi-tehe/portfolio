package com.example.chart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.chart.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo,String>{

}
