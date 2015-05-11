package com.avnet.ticketing.DataBeans;

import java.io.Serializable;

public class UserDetails implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2963898627082644689L;
	private String emailId;
	private String userid;
	private String role;
	private String name;
	private boolean isValid;
	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "UserDetails [emailId=" + emailId + ", userid=" + userid
				+ ", role=" + role + ", name=" + name + ", isValid=" + isValid
				+ "]";
	}
	
	
	
		
	

}
