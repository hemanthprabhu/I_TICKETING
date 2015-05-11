package com.avnet.ticketing.DataBeans;

import java.io.Serializable;

public class TicketDataTableBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2377746073907871326L;
	private int ticketId;
	private String subject;
	private String agentname;
	private String priority;
	private String status;
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getAgentname() {
		return agentname;
	}
	public void setAgentname(String agentname) {
		this.agentname = agentname;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "TicketDataTableBean [ticketId=" + ticketId + ", subject="
				+ subject + ", agentname=" + agentname + ", priority="
				+ priority + ", status=" + status + "]";
	}
	
	

}
