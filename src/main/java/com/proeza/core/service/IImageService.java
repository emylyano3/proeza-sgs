package com.proeza.core.service;

import org.springframework.http.MediaType;

public interface IImageService {

    byte[] getThumbnail (byte[] image, int size, MediaType mediaType);
}