package com.tech;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Subject_Service {
	@Autowired
	private Subject_dao Subject_dao;

	public float total(int sub1,int sub2,int sub3,int sub4,int sub5) {
		float Total1=Subject_dao.total(sub1, sub2, sub3, sub4, sub5);
		return Total1;
	}
}
