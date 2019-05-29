package com.proeza.core.persistence;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.proeza.core.persistence.exception.QueryNotFoundException;

public class QueryRegistry {

    public static final Map<String, String> REGISTRY = new HashMap<String, String>(0);

    public QueryRegistry (String queryLocation) {
        loadQueries(queryLocation);
    }

    public String getQuery (String name) {
        if (REGISTRY.get(name) == null) {
            throw new QueryNotFoundException("No existe query registrada bajo el nombre: " + name);
        }
        return REGISTRY.get(name);
    }

    public String addQuery (String name, String query) {
        synchronized (REGISTRY) {
            return REGISTRY.put(name, query);
        }
    }

    private void loadQueries (String queryLocation) {
        synchronized (REGISTRY) {
            URL url = getClass().getResource(queryLocation);
            File dir = new File(url.getFile());
            if (dir.isDirectory()) {
                loadDir(dir);
            }
        }
    }

    private void loadDir (File dir) {
        File[] files = dir.listFiles(new SqlFileFilter());
        for (File file : files) {
            if (file.isDirectory()) {
                loadDir(file);
            } else {
                registryQuery(file);
            }
        }
    }

    private void registryQuery (File file) {
        String name = file.getName();
        name = name.substring(0, name.indexOf('.'));
        REGISTRY.put(name, loadQuery(file));
    }

    private String loadQuery (File file) {
        try {
            InputStream fis = new FileInputStream(file);
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            fis.close();
            return new String(bytes);
        } catch (IOException e) {
            throw new RuntimeException("Error registrando las consultas desde el directorio configurado, causado por: " + e.getMessage(), e);
        }
    }

    class SqlFileFilter implements FileFilter {

        @Override
        public boolean accept (File file) {
            if (file.isDirectory()) {
                return true;
            }
            if (file.isHidden()) {
                return false;
            }
            int index = file.getAbsolutePath().lastIndexOf('.');
            if (index >= 0) {
                return file.getAbsolutePath().substring(index, file.getAbsolutePath().length()).equalsIgnoreCase(".sql");
            }
            return false;
        }
    }
}