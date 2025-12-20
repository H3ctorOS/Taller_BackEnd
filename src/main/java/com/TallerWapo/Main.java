package com.TallerWapo;

import com.TallerWapo.Puertos.implementaciones.ApiRestSpark.SparkApiRestPORT;
import com.TallerWapo.dominio.interfacez.puertos.ApiRestPort;
import com.TallerWapo.dominio.utiles.LoggerUtil;
import com.TallerWapo.dominio.utiles.PropertiesUtils;


public class Main {

    public static void main(String[] args) {
        LoggerUtil.logInfo("Hola hijo de puta");

        if(comprobarSiPrimerArranque()){
            LoggerUtil.logInfo("Haciendo cosas de primer arranque");
            String dbPath = PropertiesUtils.getString("config.properties", "db.path", "db/taller.db");

            //TODO: construir directorio carpetas
            //TODO: construir base datos

            LoggerUtil.logInfo("Ruta bbdd = " + dbPath);
        }

        //iniciar ApiRest con Spark
        SparkApiRestPORT spark = new SparkApiRestPORT();
        spark.setNombre("SPARK API REST");
        arrancarServidorPeticionesApiRest(spark);

        //TODO: arrancar base datos


    }

    private static boolean comprobarSiPrimerArranque(){
        LoggerUtil.logInfo("Comprobando si es el primer arranque");

        LoggerUtil.logInfo("Es el primer arranque");
        return true;
    }


    private static void arrancarServidorPeticionesApiRest(ApiRestPort apiRest) {
        LoggerUtil.logInfo("Arrancando servidor peticiones: " +  apiRest.getNombre());

        // Carga configs (puerto, etc.)
        int serverPort = PropertiesUtils.getInt("constantes/server-config.properties", "server.port", 8080);
        apiRest.setPuerto(serverPort);

        //Arrancar
        apiRest.iniciar();
        apiRest.iniciarControllers();

        LoggerUtil.logInfo("Servidor ApiRest iniciado en puerto " + serverPort );
    }
}