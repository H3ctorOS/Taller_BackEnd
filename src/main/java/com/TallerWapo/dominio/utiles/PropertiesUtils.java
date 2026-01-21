package com.TallerWapo.dominio.utiles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtils {
    static final Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

    // Cache para propiedades cargadas (genérico para múltiples archivos)
    private static final Map<String, Properties> cache = new HashMap<>();


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
        try (InputStream input = PropertiesUtils.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                String texto = "Archivo " + fileName + " no encontrado en resources";

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
            return props.getProperty(key);

        } catch (IOException e) {
            // Integra con LoggerUtil si existe
            logger.error("Error cargando " + fileName + ": " + e.getMessage() + ". Usando default.",e);
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


    /**
     * Crea un fichero .properties vacío si no existe.
     * Si ya existe, no realiza ninguna acción.
     *
     * @param filePath ruta completa al fichero .properties
     * @throws IOException si ocurre un error de E/S
     */
    public static void createPropertiesIfNotExists(String filePath) throws IOException {

        Path path = Paths.get(filePath);

        // Si el fichero ya existe, no hacer nada
        if (FicherosUtil.fileExists(filePath)) {
            return;
        }

        // Crear directorios padres si no existen
        Path parentDir = path.getParent();
        if (parentDir != null) {
            Files.createDirectories(parentDir);
        }

        // Crear fichero vacío .properties
        try (FileOutputStream fos = new FileOutputStream(path.toFile())) {
            Properties properties = new Properties();
            properties.store(fos, "Fichero .properties creado automáticamente");
        }
    }




    /**
     * Lee el valor de una clave desde un fichero .properties en disco.
     *
     * @param filePath ruta al archivo .properties
     * @param key      clave a leer
     * @return valor asociado a la clave o null si no existe
     * @throws IOException si el fichero no existe o no se puede leer
     */
    public static String leerPropiedadDeFichero(String filePath, String key) throws IOException {

        if (!FicherosUtil.fileExists(filePath)) {
            throw new IOException("El fichero .properties no existe: " + filePath);
        }

        Properties properties = new Properties();
        Path path = Paths.get(filePath);

        try (InputStream inputStream = Files.newInputStream(path)) {
            properties.load(inputStream);

        } catch (IOException e) {
            throw new IOException("Error leyendo el fichero .properties: " + filePath, e);
        }

        return properties.getProperty(key);
    }

    /**
     * Actualiza el valor de una clave en un fichero .properties existente.
     * Si la clave no existe, se crea.
     * Si el fichero no existe, lanza excepción.
     *
     * @param filePath ruta al fichero .properties
     * @param key      clave a actualizar o crear
     * @param value    valor a establecer
     * @throws IOException si el fichero no existe o hay error de E/S
     */
    public static void setPropertyDeFichero(String filePath, String key, String value) throws IOException {

        if (!FicherosUtil.fileExists(filePath)) {
            throw new IOException("El fichero .properties no existe: " + filePath);
        }

        Path path = Paths.get(filePath);
        Properties properties = new Properties();

        // Cargar propiedades existentes
        try (InputStream is = Files.newInputStream(path)) {
            properties.load(is);
        }

        // Crear o actualizar la clave
        properties.setProperty(key, value);

        // Guardar cambios
        try (FileOutputStream fos = new FileOutputStream(path.toFile())) {
            properties.store(fos, "Propiedad creada/actualizada automáticamente");
        }
    }


}
