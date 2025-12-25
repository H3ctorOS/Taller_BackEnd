package com.TallerWapo.dominio.utiles;

import com.TallerWapo.Adaptadores.BaseDatossql.SQliteAdaptador;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FicherosUtil {

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
}
