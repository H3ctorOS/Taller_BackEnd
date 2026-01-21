package com.TallerWapo.dominio.utiles;

import com.TallerWapo.Adaptadores.BaseDatossql.SQliteAdaptador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Properties;

public class FicherosUtil {

    static final Logger logger = LoggerFactory.getLogger(FicherosUtil.class);

    /**
     * Crea un fichero nuevo o sobrescribe si existe. Opcional: agrega contenido.
     * @param filePath Ruta relativa o absoluta (e.g., "logs/backup.db")
     * @param content Contenido a escribir (puede ser null para fichero vacío)
     */
    public static void createFile(String filePath, byte[] content) throws IOException {
        Path path = Paths.get(filePath);
        Files.createDirectories(path.getParent());  // Crea directorios padres si no existen
        if (content != null) {
            Files.write(path, content, StandardOpenOption.CREATE);
        } else {
            Files.createFile(path);
        }
    }

    /**
     * Borra un fichero si existe.
     * @param filePath Ruta del fichero
     * @return true si se borró, false si no existía
     */
    public static boolean deleteFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.deleteIfExists(path);
    }

    /**
     * Verifica si un fichero existe.
     */
    public static boolean fileExists(String filePath) {
        return Files.exists(Paths.get(filePath));
    }

    // Opcional: Método para copiar fichero (útil para backups de BD)
    public static void copyFile(String sourcePath, String destPath) throws IOException {
        Files.copy(Paths.get(sourcePath), Paths.get(destPath));
    }

    public static String leerArchivoDeResources(String ruta) throws IOException {
        try {
            InputStream archivoLeido = SQliteAdaptador.class
                    .getClassLoader()
                    .getResourceAsStream(ruta);

            if (archivoLeido == null) {
                throw new RuntimeException("No se encontró el archivo: " + ruta);
            }

            return new String(archivoLeido.readAllBytes(), StandardCharsets.UTF_8);

        }catch (Exception e){
            throw new IOException("Error al leer archivo: " + ruta, e);
        }
    }

    /**
     * Método estático para crear un fichero .properties en una carpeta determinada.
     * Crea la carpeta si no existe, y el fichero con contenido inicial opcional (Map de claves-valores).
     * Idempotente: Si fichero existe, sobrescribe si content no null.
     * @param ruta Ruta de la carpeta (relativa o absoluta, e.g., "config/custom")
     * @param nombreFichero Nombre del fichero (e.g., "mi-config.properties")
     * @param contenidoInicial Map<String, String> con claves-valores iniciales (opcional, null para vacío)
     * @throws IOException si error en creación (e.g., permisos)
     */
    public static void createPropertiesFile(String ruta, String nombreFichero, Map<String, String> contenidoInicial) throws IOException {
        Path dirPath = Paths.get(ruta);
        Files.createDirectories(dirPath);  // Crea carpeta si no existe (idempotente)

        Path filePath = dirPath.resolve(nombreFichero);  // Ruta completa: ruta/fileName
        logger.info("Creando fichero .properties en: " + filePath.toAbsolutePath());

        try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
            Properties props = new Properties();
            if (contenidoInicial != null) {
                props.putAll(contenidoInicial);  // Agrega contenido inicial
            }
            props.store(fos, "Fichero .properties creado automáticamente");  // Guarda con comentario
            logger.info("Fichero .properties creado exitosamente.");

        } catch (IOException e) {
            logger.info("Error creando fichero .properties", e);
            throw e;
        }
    }


}
