package com.proeza.core.resources.image;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import static java.lang.Math.*;

public class ImageManager {

    public BufferedImage build (byte[] data) {
        InputStream is = new ByteArrayInputStream(data);
        try {
            return ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public float getScaleRatio (BufferedImage image, int width, int height) {
        final float horizontalRatio = (float) height / (float) image.getWidth();
        final float verticalRatio = (float) width / (float) image.getHeight();
        return Math.min(verticalRatio, horizontalRatio);
    }

    public float getScaleRatio (BufferedImage image, int size) {
        final float horizontalRatio = (float) size / (float) image.getWidth();
        final float verticalRatio = (float) size / (float) image.getHeight();
        return Math.max(verticalRatio, horizontalRatio);
    }

    public BufferedImage scale (BufferedImage image, float ratio) {
        final Float width = image.getWidth() * ratio;
        final Float height = image.getHeight() * ratio;
        final Image scaled = image.getScaledInstance(width.intValue(), height.intValue(), Image.SCALE_SMOOTH);
        final BufferedImage newImage = new BufferedImage(width.intValue(), height.intValue(), BufferedImage.TYPE_INT_RGB);
        final Graphics2D g = newImage.createGraphics();
        g.drawImage(scaled, 0, 0, null);
        g.dispose();
        return newImage;
    }

    public BufferedImage chop (BufferedImage image, Anchor anchor, int vpWidth, int vpHeight) {
        int iWidth = image.getWidth();
        int iHeight = image.getHeight();
        if (vpWidth >= iWidth && vpHeight >= iHeight) {
            return image;
        }
        vpWidth = min(vpWidth, iWidth);
        vpHeight = min(vpHeight, iHeight);
        int vpX, vpY;
        switch (anchor) {
            case TOP_LEFT:
                vpX = 0;
                vpY = 0;
                break;
            case TOP_CENTER:
                vpX = (iWidth >> 1) - (vpWidth >> 1);
                vpY = 0;
                break;
            case TOP_RIGHT:
                vpX = iWidth - vpWidth;
                vpY = 0;
                break;
            case BOTTOM_LEFT:
                vpX = 0;
                vpY = iHeight - vpHeight;
                break;
            case BOTTOM_CENTER:
                vpX = (iWidth >> 1) - (vpWidth >> 1);
                vpY = iHeight - vpHeight;
                break;
            case BOTTOM_RIGHT:
                vpX = iWidth - vpWidth;
                vpY = iHeight - vpHeight;
                break;
            case MIDDLE_LEFT:
                vpX = 0;
                vpY = (iHeight >> 1) - (vpHeight >> 1);
                break;
            case MIDDLE_RIGHT:
                vpX = iWidth - vpWidth;
                vpY = (iHeight >> 1) - (vpHeight >> 1);
                break;
            default:
            case MIDDLE_CENTER:
                vpX = (iWidth >> 1) - (vpWidth >> 1);
                vpY = (iHeight >> 1) - (vpHeight >> 1);
                break;
        }
        return image.getSubimage(vpX, vpY, vpWidth, vpHeight);
    }

    public byte[] toBytes (BufferedImage image, String type) {
        try {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, type, baos);
            baos.flush();
            return baos.toByteArray();
        } catch (final Exception e) {
            return null;
        }
    }
}