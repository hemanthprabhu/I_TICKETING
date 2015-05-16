package com.avnet.ticketing.DataBeans;

public class TweetsBean {
private String body;
private String link;
private String preferredUsername;
private String image;
public String getBody() {
	return body;
}
public void setBody(String body) {
	this.body = body;
}
public String getLink() {
	return link;
}
public void setLink(String link) {
	this.link = link;
}
public String getPreferredUsername() {
	return preferredUsername;
}
public void setPreferredUsername(String preferredUsername) {
	this.preferredUsername = preferredUsername;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
@Override
public String toString() {
	return "TweetsBean [body=" + body + ", link=" + link
			+ ", preferredUsername=" + preferredUsername + ", image=" + image
			+ "]";
}


}
