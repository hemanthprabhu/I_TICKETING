package com.avnet.ticketing.sendEmail;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGrid.Email;
import com.sendgrid.SendGridException;

/**
 * SendGrid Java example
 *
 */
public class SendGridService {
	
	/**
	 * Send email
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws SendGridException 
	 *
	 */
	public static void send(ConfigParams params) throws FileNotFoundException, IOException, SendGridException {		

		// Create an email message
		Email email = new Email();
		email.addTo(params.get_emailTo());
		email.addToName(params.get_emailToName());
		email.setFrom(params.get_replyTo());
		email.setFromName(params.get_replyToName());
		email.setSubject(params.get_subject());
		email.setText(params.get_message());

		// Add a Bcc recipient
		if (params.get_bccTo().length() > 0) {
			// Enable the filter
			email.addFilter("bcc", "enable", "1");
			// Add email address destination for the bcc message
			email.addFilter("bcc", "email", params.get_bccTo());
		}		
		
		// Tag it with category
		if (params.get_category().length() > 0) {
			email.addCategory(params.get_category());
		}
		
		if (params.get_templateId().length() == 0) {						
			// Insert email footer
			email.addFilter("footer", "enable", "1");			
			email.addFilter("footer", "text/html", params.get_footerHtml());
			email.addFilter("footer", "text/plain", params.get_footerText());
			
			// Add an attachment
			if (params.get_filePath().length() > 0) {
				final String name = params.get_fileName();		
				final String file = params.get_filePath();
				email.addAttachment(name, new File(file));			
			}
		} else {
			// Demo how to use a template created with the Template Engine when sending an email
			// Create a HTML content
			email.setHtml("Hello -name-,<br>");
			
			// Specify substitution value for the '-name-' tag defined in the HTML body 
			String[] val = { "SendGridUser" };
			email.addSubstitution("-name-", val);

			// Enable template
			email.addFilter("templates", "enable", "1");
			email.addFilter("templates", "template_id", params.get_templateId());
		}		
		
		// Enable open track
		if (params.is_openTrack()) {
			email.addFilter("opentrack", "enable", "1");
		}
		
		// Enable click track
		if (params.is_clickTrack()) {
			email.addFilter("clicktrack", "enable", "1");
		}		
		
		// Send the email
		try {
			SendGrid sendgrid = new SendGrid(params.get_userName(), params.get_password());
			sendgrid.send(email);
			System.out.println("Message is sent to: " + params.get_emailTo());
		} catch (SendGridException e) {
			throw e;
		}
	}
}