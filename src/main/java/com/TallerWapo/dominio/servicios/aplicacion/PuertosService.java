package com.TallerWapo.dominio.servicios.aplicacion;

import com.TallerWapo.Puertos.implementaciones.ApiRestSpark.SparkApiRestPORT;
import com.TallerWapo.dominio.interfacez.puertos.ApiRestPort;
import com.TallerWapo.dominio.utiles.LoggerUtil;
import com.TallerWapo.dominio.utiles.PropertiesUtils;

public class PuertosService {

    public static void arrancarPuertos(){
        //iniciar ApiRest con Spark
        SparkApiRestPORT spark = new SparkApiRestPORT();
        spark.setNombre("SPARK API REST");
        arrancarApiRest(spark);

        //iniciar Base datos con SQLITE
        //TODO: arrancar base datos

    }

    private static void arrancarApiRest(ApiRestPort apiRest) {
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
