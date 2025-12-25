package com.TallerWapo.dominio.fachadas.base;


import com.TallerWapo.dominio.contexto.ContextoGeneral;
import com.TallerWapo.dominio.interfaces.fachadas.FachadasBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.function.Supplier;

public class FachadaEjecutarBase implements FachadasBase {
    static final Logger logger = LoggerFactory.getLogger(FachadaEjecutarBase.class);

    protected <T> T ejecutarEnTransaccion(Supplier<T> accion) {
        //Genera la nueva conexion
        ContextoGeneral.baseDatosSQL.generarConexionEscritura();
        Connection conexionEscritura = ContextoGeneral.baseDatosSQL.getConexion();

        try {
            T resultado = accion.get();
            conexionEscritura.commit();
            return resultado;

        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error("Error en transacción", e);
            rollbackSilencioso(conexionEscritura);
            throw new RuntimeException("Error en transacción", e);

        } finally {
            //Terminar la conexion
            ContextoGeneral.baseDatosSQL.finalizarConexion();
        }
    }


    protected void ejecutarEnTransaccion(Runnable accion) {
        ejecutarEnTransaccion(() -> {
            accion.run();
            return null;
        });
    }


    private void rollbackSilencioso(Connection conexionEscritura) {
        try {
            conexionEscritura.rollback();

        } catch (SQLException e) {
            logger.error(Arrays.toString(e.getStackTrace()));
        }
    }
}
