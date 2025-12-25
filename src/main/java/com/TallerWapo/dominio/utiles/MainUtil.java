package com.TallerWapo.dominio.utiles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainUtil {
    static final Logger logger = LoggerFactory.getLogger(MainUtil.class);
    static final String ARCHIVO_CONFIGURACION = "constantes/config.properties";

    public static boolean esPrimerArranque(){
        logger.info("Este es el primer arranque");

        return true;
    }

    public static void construirStructuraCarpetas(){
        logger.info("Creando estructura de carpetas del proyecto...");

        //Listado con rutas a crear
        String[] carpetas = {
            PropertiesUtils.getString(ARCHIVO_CONFIGURACION, "ruta.base", null),
            PropertiesUtils.getString(ARCHIVO_CONFIGURACION, "ruta.Datos", null),
            PropertiesUtils.getString(ARCHIVO_CONFIGURACION, "ruta.BaseDatosSQL", null),
            PropertiesUtils.getString(ARCHIVO_CONFIGURACION, "ruta.logs", null)
        };

        //Crear carpetas
        try {
            for (String carpeta : carpetas) {
                Path path = Paths.get(carpeta);
                Files.createDirectories(path); // Crea si no existe, idempotente
                logger.info("Carpeta creada/existente: {}", carpeta);
            }
            logger.info("Estructura de carpetas creada correctamente.");

        } catch (IOException e) {
            logger.error("Error creando estructura de carpetas", e);  // Rojo para error
        }
    }
}
