package com.proeza.core.service;

import java.awt.image.BufferedImage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.proeza.core.resources.image.ImageManager;

import static com.proeza.core.resources.image.Anchor.*;

@Component
public class ImageService implements IImageService {

    @Autowired
    private ImageManager manager;

    @Override
    public byte[] getThumbnail (byte[] image, int size, MediaType mediaType) {
        BufferedImage bi = this.manager.build(image);
        float ratio = this.manager.getScaleRatio(bi, size);
        bi = this.manager.scale(bi, ratio);
        bi = this.manager.chop(bi, MIDDLE_CENTER, size, size);
        return this.manager.toBytes(bi, mediaType.getSubtype());
    }
}