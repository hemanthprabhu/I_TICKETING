package com.avnet.ticketing.DataBeans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class Ticket implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7145881559569591977L;
	private int ticketId;
	private String customerName;
	private String subject;
	private String description;
	private String agenetName;
	private String priority;
	private String status;
	private Timestamp timestamp;
	private List<Comment> comments;
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAgenetName() {
		return agenetName;
	}
	public void setAgenetName(String agenetName) {
		this.agenetName = agenetName;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", customerName="
				+ customerName + ", subject=" + subject + ", description="
				+ description + ", agenetName=" + agenetName + ", priority="
				+ priority + ", status=" + status + ", timestamp=" + timestamp
				+ ", comments=" + comments + "]";
	}
	
	

}
