package com.example.demo;

import org.springframework.stereotype.Repository;

import edu.stayConnected.FBO.EmploymentOppForm;

@Repository
public interface EmploymentOppDao {
	public void addOpportunity(EmploymentOppForm form, String userEmail);
}
