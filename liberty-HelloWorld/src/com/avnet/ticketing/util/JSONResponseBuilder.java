package com.avnet.ticketing.util;

import java.io.Serializable;

public class JSONResponseBuilder implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 5132814463016474966L;
private String response;
private String message;
public String getResponse() {
	return response;
}
public void setResponse(String response) {
	this.response = response;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
@Override
public String toString() {
	return "JSONResponseBuilder [response=" + response + ", message=" + message
			+ "]";
}


}
