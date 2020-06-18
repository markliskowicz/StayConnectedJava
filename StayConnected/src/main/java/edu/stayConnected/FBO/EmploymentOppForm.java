package edu.stayConnected.FBO;

import java.sql.Date;

public class EmploymentOppForm {
	private String company;
	private String location;
	private String jobTitle;
	private String field;
	private String type;
	private String wage;
	private Date startDate;
	private Date endDate;
	
	public void setCompany(String company) {
		this.company = company;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setWage(String wage) {
		this.wage = wage;
	}
	
	public String getCompany() {
		return company;
	}
	public String getLocation() {
		return location;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public String getType() {
		return type;
	}
	public String getWage() {
		return wage;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}	
}
