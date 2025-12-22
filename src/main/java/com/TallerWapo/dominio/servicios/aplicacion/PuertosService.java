package com.TallerWapo.dominio.servicios.aplicacion;

import com.TallerWapo.dominio.Puertos.ApiRest.ApiRestPort;
import com.TallerWapo.dominio.Puertos.baseDatos.BaseDatosPort;
import com.TallerWapo.dominio.Puertos.baseDatos.BaseDatosSQLPort;
import com.TallerWapo.dominio.contexto.ContextoGeneral;
import com.TallerWapo.dominio.utiles.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PuertosService {
    static final Logger logger = LoggerFactory.getLogger(PuertosService.class);


    public static void arrancarBaseDatosLocalSQL(BaseDatosSQLPort baseDatos) {
        logger.info("arrancando baseDatos local");

        //Arrancar la base de datos
        baseDatos.iniciar();
        ContextoGeneral.baseDatosSQL = baseDatos;

        logger.info("BaseDatos local arrancada");
    }

    public static void arrancarApiRest(ApiRestPort apiRest) {
        logger.info("Arrancando servidor peticiones: " +  apiRest.getNombreAdaptador());

        // Carga configs (puerto, etc.)
        int serverPort = PropertiesUtils.getInt("constantes/server-config.properties", "server.port", 8080);
        apiRest.setPuerto(serverPort);

        //Arrancar
        apiRest.iniciar();
        apiRest.iniciarControllers();

        ContextoGeneral.apiRest = apiRest;

        logger.info("Servidor ApiRest iniciado en puerto " + serverPort );
    }
}
