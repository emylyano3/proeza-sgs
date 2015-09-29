package com.proeza.sgs.business.entity.builder;

import java.text.Normalizer;

public class CodeBuilder {

    public CodeBuilder () {
        this.code = new StringBuilder();
    }

    public CodeBuilder (int capacity) {
        this.code = new StringBuilder(capacity);
    }

    private StringBuilder code;

    public CodeBuilder append (String token) {
        this.code.append(normalize(token));
        return this;
    }

    public CodeBuilder append (String token, int length, char paddChar) {
        this.code.append(formatToken(token, length, paddChar));
        return this;
    }

    public CodeBuilder append (Number token, int length, char paddChar) {
        this.code.append(formatToken("" + token, length, paddChar));
        return this;
    }

    public String build () {
        return this.code.toString();
    }

    private String formatToken (String token, int length, char paddChar) {
        token = normalize(token);
        int paddLength = length - token.length();
        if (paddLength <= 0) {
            return token.substring(0, length).toUpperCase();
        } else {
            String padd = "";
            for (int i = 0; i < paddLength; i++) {
                padd += paddChar;
            }
            return padd + token.toUpperCase();
        }
    }

    private String normalize(String value) {
        value = Normalizer.normalize(value, Normalizer.Form.NFD);
        StringBuilder builder = new StringBuilder(value.length());
        for (int i = 0; i < value.length(); i++) {
            if (Character.isLetterOrDigit(value.charAt(i))) {
                builder.append(value.charAt(i));
            }
        }
        return builder.toString();
    }
}