package com.TallerWapo.dominio.utiles;

import com.TallerWapo.dominio.contexto.Sesion;
import com.TallerWapo.dominio.interfaces.puertos.ApiRest.ApiRestPort;
import com.TallerWapo.dominio.interfaces.puertos.baseDatos.BaseDatosSQLPort;
import com.TallerWapo.dominio.contexto.Contexto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PuertosUtil {
    static final Logger logger = LoggerFactory.getLogger(PuertosUtil.class);


    public static void arrancarBaseDatosLocalSQL(BaseDatosSQLPort baseDatos) {
        logger.info("arrancando baseDatos local");

        //Arrancar la base de datos
        baseDatos.iniciar();
        Contexto.baseDatosSQL = baseDatos;
        Contexto.SESION_SOLO_LECTURA = new Sesion(Contexto.baseDatosSQL.getConexionLectura());

        logger.info("BaseDatos local arrancada");
    }

    public static void arrancarApiRest(ApiRestPort apiRest) {
        logger.info("Arrancando servidor peticiones: {}", apiRest.getNombreAdaptador());

        // Carga configs (puerto, etc.)
        int serverPort = PropertiesUtils.getInt("constantes/config.properties", "puerto.ApiRest", 8080);
        apiRest.setPuerto(serverPort);

        //Arrancar
        apiRest.iniciar();
        Contexto.apiRest = apiRest;

        logger.info("Servidor ApiRest iniciado en puerto {}", serverPort);
    }
}
