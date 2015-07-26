package proeza.test.unit.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.proeza.core.resources.image.ImageManager;

import static com.proeza.core.resources.image.Anchor.*;

public class ImageManagerTest {

    private ImageManager    manager                  = new ImageManager();

    private static byte[]   ORIGINAL_DATA;
    private static byte[]   SCALED_DATA;
    private static String   ORIGINAL_IMAGE_FILE_PATH = "/image/image_ORIGINAL.jpg";
    private static String   SCALED_IMAGE_FILE_PATH   = "/image/image_SCALED.jpg";
    public static final int THUMBNAIL_SIZE           = 75;

    @BeforeClass
    public static void setup () throws IOException {
        ORIGINAL_DATA = loadImage(ORIGINAL_IMAGE_FILE_PATH);
        SCALED_DATA = loadImage(SCALED_IMAGE_FILE_PATH);
    }

    private static byte[] loadImage (String imagePath) throws IOException {
        InputStream is = ImageManagerTest.class.getResourceAsStream(imagePath);
        byte[] data = new byte[is.available()];
        is.read(data);
        is.close();
        return data;
    }

    private byte[] cloneData () {
        return Arrays.copyOf(ORIGINAL_DATA, ORIGINAL_DATA.length);
    }

    @Test
    public void general () throws IOException {
        byte[] data = cloneData();
        BufferedImage image = this.manager.build(data);
        float ratio = this.manager.getScaleRatio(image, THUMBNAIL_SIZE);
        image = this.manager.scale(image, ratio);
        image = this.manager.chop(image, MIDDLE_CENTER, THUMBNAIL_SIZE, THUMBNAIL_SIZE);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "JPG", baos);
        baos.flush();
        byte[] scaledData = baos.toByteArray();
        Assert.assertTrue(Arrays.equals(scaledData, SCALED_DATA));
    }
}