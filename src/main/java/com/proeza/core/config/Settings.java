package com.proeza.core.config;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;

public abstract class Settings {
    public Properties asProperties () {
        Field[] fields = getClass().getDeclaredFields();
        Properties properties = new Properties();
        for (Field field : fields) {
            Value annValue;
            if ((annValue = findAnnotation(field)) != null) {
                try {
                    String key = getKey(annValue);
                    Object value = getValue(field);
                    if (key != null && value != null) {
                        properties.put(key, value);
                    }
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return properties;
    }

    private String getKey (Value annValue) {
        String raw = annValue.value();
        raw = raw.replaceAll("\\{", "");
        raw = raw.replaceAll("\\}", "");
        raw = raw.replaceAll("\\$", "");
        return raw.trim();
    }

    private Object getValue (Field field) throws IllegalAccessException {
        boolean isAccesible = field.isAccessible();
        field.setAccessible(true);
        Object value = field.get(this);
        field.setAccessible(isAccesible);
        return value;
    }

    private Value findAnnotation (Field field) {
        Annotation[] anns = field.getAnnotations();
        for (Annotation ann : anns) {
            if (ann instanceof Value) {
                return (Value) ann;
            }
        }
        return null;
    }
}