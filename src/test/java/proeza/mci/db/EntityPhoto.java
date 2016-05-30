package proeza.mci.db;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EntityPhoto<T> {
    private final Map<String, Method> getters;

    private final Map<String, Method> setters;

    private final Map<String, Object> photo;

    public Collection<String> getAttributes() {
        return this.photo.keySet();
    }

    public Collection<Object> getValues() {
        return this.photo.values();
    }

    public Object getValue(String att) {
        return this.photo.get(att);
    }

    public EntityPhoto(T entity) {
        this.photo = new HashMap<String, Object>();
        this.getters = new HashMap<String, Method>();
        this.setters = new HashMap<String, Method>();
        getGetters(entity);
        getSetters(entity);
        buildPhoto(entity);
    }

    private void buildPhoto(T e) {
        for (Method getter : this.getters.values()) {
            try {
                String attName = getAttName(getter);
                this.photo.put(attName, getter.invoke(e));
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
                e1.printStackTrace();
            }
        }
    }

    private String getAttName(Method getter) {
        String getterName = getter.getName();
        String attName = getterName.substring("get".length(), getterName.length());
        attName = attName.substring(0, 1).toLowerCase() + attName.substring(1, attName.length());
        return attName;
    }

    private void getGetters(T e) {
        Method[] methods = e.getClass().getMethods();
        for (Method method : methods) {
            if (method.getName().startsWith("get") && !method.getName().endsWith("Class")) {
                this.getters.put(getAttName(method), method);
            }
        }
    }

    private void getSetters(T e) {
        Method[] methods = e.getClass().getMethods();
        for (Method method : methods) {
            if (method.getName().startsWith("set") && !method.getName().endsWith("Class")) {
                this.setters.put(getAttName(method), method);
            }
        }
    }

    public Method getSetter(String att) {
        return this.setters.get(att);
    }

    @Override
    public String toString() {
        return "EntityPhoto [photo=" + this.photo + "]";
    }

}
