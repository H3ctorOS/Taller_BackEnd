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

    static final String rutaProperties = PropertiesUtils.getString(ARCHIVO_CONFIGURACION, "ruta.Config", null);
    static final String nomnbreProperties = PropertiesUtils.getString(ARCHIVO_CONFIGURACION, "properties.nombreArhivoConfiguracionBase", null);
    static final String rutaCompletaProperties = rutaProperties+"/"+nomnbreProperties;

    public static boolean esPrimerArranque(){

        try {
            if(FicherosUtil.fileExists(rutaCompletaProperties)){
                String version = PropertiesUtils.leerPropiedadDeFichero(rutaCompletaProperties,"Version");
                String cantidadArranques = PropertiesUtils.leerPropiedadDeFichero(rutaCompletaProperties,"cantidadArranques");

                int cantidad =  Integer.parseInt(cantidadArranques)+1;
                PropertiesUtils.setPropertyDeFichero(rutaCompletaProperties,"cantidadArranques", String.valueOf(cantidad));

                logger.info("Version: "+version);
                logger.info("Cantidad arranques: "+cantidad);

                return false;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
            PropertiesUtils.getString(ARCHIVO_CONFIGURACION, "ruta.logs", null),
            PropertiesUtils.getString(ARCHIVO_CONFIGURACION, "ruta.Config", null)
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


    public static void construirFicherosProperties() {
        try {
            PropertiesUtils.createPropertiesIfNotExists(rutaCompletaProperties);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void guardarPropertiesBaseIniciales() {
        String version = PropertiesUtils.getString(ARCHIVO_CONFIGURACION, "app.version", null);

        try {
            PropertiesUtils.setPropertyDeFichero(rutaCompletaProperties,"Version",version);
            PropertiesUtils.setPropertyDeFichero(rutaCompletaProperties,"cantidadArranques","1");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
