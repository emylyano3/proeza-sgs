package com.proeza.core.service;

import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailService {

	@Autowired
	private JavaMailSender	mailSender;

	@Autowired
	private TemplateEngine	templateEngine;

	@Transactional(readOnly = true)
	public void sendContactEmail (final String recipientName, final String recipientEmail, final String imageResourceName, final byte[] imageBytes, final String imageContentType, final Locale locale) throws MessagingException {
		// Prepare the evaluation context
		final Context ctx = new Context(locale);
		ctx.setVariable("nombre", recipientName);
		ctx.setVariable("subscriptionDate", new Date());
		ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));
		ctx.setVariable("imageResourceName", imageResourceName); // so that we can reference it from HTML

		// Prepare message using a Spring helper
		final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
		message.setSubject("Example HTML email with inline image");
		message.setFrom("thymeleaf@example.com");
		message.setTo(recipientEmail);

		// Create the HTML body using Thymeleaf
		final String htmlContent = this.templateEngine.process("contact", ctx);
		message.setText(htmlContent, true); // true = isHtml

		// Add the inline image, referenced from the HTML code as "cid:${imageResourceName}"
		// final InputStreamSource imageSource = new ByteArrayResource(imageBytes);
		// message.addInline(imageResourceName, imageSource, imageContentType);

		// Send mail
		this.mailSender.send(mimeMessage);
	}
}