package com.proeza.core.service;

import java.util.Locale;

import javax.mail.MessagingException;

public interface IMailService {

	public abstract void sendContactEmail (String recipientName, String recipientEmail, String imageResourceName, byte[] imageBytes, String imageContentType, Locale locale) throws MessagingException;

}