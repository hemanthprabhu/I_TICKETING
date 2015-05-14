package com.avnet.ticketing.sendEmail;

/**
 * Class for SendGrid configuration parameters
 *
 */
public class ConfigParams {
	String _emailTo = "";
	String _emailToName = "";
	String _replyTo = "";
	String _replyToName = "";
	String _bccTo = "";
	String _subject = "";
	String _message = "";
	String _fileName = "";
	String _filePath = "";
	String _category = "";
	String _footerHtml = "";
	String _footerText = "";
	String _templateId = "";
	boolean _openTrack = false;	
	boolean _clickTrack = false;
	String _userName = "";
	String _password = "";

	/**
	 * Instantiate the object with the required parameters
	 * @param emailTo
	 * @param replyTo
	 * @param subject
	 * @param message
	 * @param userName
	 * @param password
	 */
	public ConfigParams(String emailTo, String replyTo, String subject,
			String message, String userName, String password) {
		_emailTo = emailTo;
		_replyTo = replyTo;
		_subject = subject;
		_message = message;
		_userName = userName;
		_password = password;
	}
	
	public String get_emailTo() {
		return _emailTo;
	}

	public void set_emailTo(String emailTo) {
		this._emailTo = emailTo;
	}

	public String get_emailToName() {
		return _emailToName;
	}

	public void set_emailToName(String emailToName) {
		this._emailToName = emailToName;
	}

	public String get_replyTo() {
		return _replyTo;
	}

	public void set_replyTo(String replyTo) {
		this._replyTo = replyTo;
	}

	public String get_replyToName() {
		return _replyToName;
	}

	public void set_replyToName(String replyToName) {
		this._replyToName = replyToName;
	}

	public String get_bccTo() {
		return _bccTo;
	}

	public void set_bccTo(String bccTo) {
		this._bccTo = bccTo;
	}

	public String get_subject() {
		return _subject;
	}

	public void set_subject(String subject) {
		this._subject = subject;
	}

	public String get_message() {
		return _message;
	}

	public void set_message(String message) {
		this._message = message;
	}

	public String get_fileName() {
		return _fileName;
	}

	public void set_fileName(String fileName) {
		this._fileName = fileName;
	}

	public String get_filePath() {
		return _filePath;
	}

	public void set_filePath(String filePath) {
		this._filePath = filePath;
	}

	public String get_category() {
		return _category;
	}

	public void set_category(String category) {
		this._category = category;
	}
	
	public String get_footerHtml() {
		return _footerHtml;
	}

	public void set_footerHtml(String footerHtml) {
		this._footerHtml = footerHtml;
	}

	public String get_footerText() {
		return _footerText;
	}

	public void set_footerText(String footerText) {
		this._footerText = footerText;
	}	

	public String get_templateId() {
		return _templateId;
	}

	public void set_templateId(String templateId) {
		this._templateId = templateId;
	}

	public boolean is_openTrack() {
		return _openTrack;
	}

	public void set_openTrack(boolean openTrack) {
		this._openTrack = openTrack;
	}
	
	public boolean is_clickTrack() {
		return _clickTrack;
	}

	public void set_clickTrack(boolean clickTrack) {
		this._clickTrack = clickTrack;
	}	

	public String get_userName() {
		return _userName;
	}

	public void set_userName(String userName) {
		this._userName = userName;
	}

	public String get_password() {
		return _password;
	}

	public void set_password(String password) {
		this._password = password;
	}	
}