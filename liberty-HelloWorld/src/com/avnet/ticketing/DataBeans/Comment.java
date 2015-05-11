package com.avnet.ticketing.DataBeans;

import java.io.Serializable;
import java.sql.Timestamp;

public class Comment implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 8386882240844998775L;
private String commentText;
private Timestamp createdAt;
private String createdBy;
private String role;
public String getCommentText() {
	return commentText;
}
public void setCommentText(String commentText) {
	this.commentText = commentText;
}
public Timestamp getCreatedAt() {
	return createdAt;
}
public void setCreatedAt(Timestamp createdAt) {
	this.createdAt = createdAt;
}
public String getCreatedBy() {
	return createdBy;
}
public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
@Override
public String toString() {
	return "Comment [commentText=" + commentText + ", createdAt=" + createdAt
			+ ", createdBy=" + createdBy + ", role=" + role + "]";
}

}
