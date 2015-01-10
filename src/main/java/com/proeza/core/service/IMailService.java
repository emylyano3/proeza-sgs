package com.proeza.core.service;

import java.util.Locale;

import javax.mail.MessagingException;

public interface IMailService {

	void sendContactEmail (String recipientName, String recipientEmail, Locale locale) throws MessagingException;

	void sendPromoEmail (final String destinatario, final String emailDestinatario, final String imageResourceName, final byte[] imageBytes, final String imageContentType, final Locale locale) throws MessagingException;
}