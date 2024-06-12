package com.tech;

import org.springframework.stereotype.Repository;

@Repository
public class Subject_dao {
	
public float total(int sub1,int sub2,int sub3,int sub4,int sub5 )
	
	{
		float Totalmarks = sub1 + sub2 +sub3 + sub4 + sub5;
		float percentage=(Totalmarks*100)/500;
		System.out.println(percentage);
		return percentage;
	}
}
