package com.TallerWapo.dominio.fachadas.base;


import com.TallerWapo.dominio.contexto.ContextoGeneral;
import com.TallerWapo.dominio.interfaces.fachadas.FachadasBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

public class FachadaConsultaBase implements FachadasBase {
    static final Logger logger = LoggerFactory.getLogger(FachadaConsultaBase.class);

    protected <T> T ejecutarConsulta(Supplier<T> accion) {
        //Genera la nueva conexion
        ContextoGeneral.baseDatosSQL.generarConexionEscritura();
        try {

            return accion.get();

        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error("Error en transacción", e);
            throw new RuntimeException("Error en transacción", e);

        } finally {
            ContextoGeneral.baseDatosSQL.finalizarConexion();
        }
    }
}
