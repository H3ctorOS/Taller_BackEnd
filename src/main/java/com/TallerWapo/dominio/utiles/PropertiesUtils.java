package com.TallerWapo.dominio.utiles;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtils {
    // Cache para propiedades cargadas (genérico para múltiples archivos)
    private static final Map<String, Properties> cache = new HashMap<>();

    private static String rutaBase = "constantes/";

    private PropertiesUtils() {
        throw new AssertionError("No se puede instanciar PropertiesLoader");
    }

    /**
     * Carga un archivo .properties desde resources. Si ya está cacheado, lo retorna.
     * @param fileName Nombre del archivo (e.g., "db-config.properties")
     * @return Properties cargado
     * @throws IOException Si no se encuentra o falla la carga
     */
    public static Properties loadProperties(String fileName) throws IOException {
        if (cache.containsKey(fileName)) {
            return cache.get(fileName);
        }

        Properties props = new Properties();
        try (InputStream input = PropertiesUtils.class.getClassLoader().getResourceAsStream(rutaBase + fileName)) {
            if (input == null) {
                String texto = "Archivo " + fileName + " no encontrado en resources";
                LoggerUtil.logWarning(texto);
                throw new IOException(texto);
            }
            props.load(input);
            cache.put(fileName, props);  // Cachea para reutilización
        }
        return props;
    }

    /**
     * Metodo genérico para obtener un valor String de un .properties específico.
     * Carga el archivo si no está cacheado.
     * @param fileName Nombre del archivo
     * @param key Clave del valor
     * @param defaultValue Valor por default si no existe
     * @return Valor o default
     */
    public static String getString(String fileName, String key, String defaultValue) {
        try {
            Properties props = loadProperties(fileName);
            return props.getProperty(key, defaultValue);

        } catch (IOException e) {
            // Integra con LoggerUtil si existe
            LoggerUtil.logError("Error cargando " + fileName + ": " + e.getMessage() + ". Usando default.",e);
            return defaultValue;
        }
    }


    public static int getInt(String fileName, String key, int defaultValue) {
        String value = getString(fileName, key, null);
        return (value != null) ? Integer.parseInt(value) : defaultValue;
    }

    public static boolean getBoolean(String fileName, String key, boolean defaultValue) {
        String value = getString(fileName, key, null);
        return (value != null) ? Boolean.parseBoolean(value) : defaultValue;
    }

}
