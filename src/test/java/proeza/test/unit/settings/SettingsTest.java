package proeza.test.unit.settings;

import java.lang.reflect.Field;
import java.util.Map.Entry;
import java.util.Properties;

import org.junit.Test;

import com.proeza.core.config.MailSettings;

import static org.junit.Assert.*;

public class SettingsTest {

    @Test
    public void asProperties () throws IllegalArgumentException, IllegalAccessException {
        MailSettings settings = new MailSettings();
        Field[] fields = MailSettings.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (String.class.isAssignableFrom(field.getType())) {
                field.set(settings, "A");
            } else {
                field.set(settings, 1);
            }
            field.setAccessible(false);
        }
        Properties p = settings.asProperties();
        for (Entry<Object, Object> entry : p.entrySet()) {
            if (String.class.isAssignableFrom(entry.getValue().getClass())) {
                assertEquals("A", entry.getValue());
            } else {
                assertEquals(1, entry.getValue());
            }
        }
    }
}
