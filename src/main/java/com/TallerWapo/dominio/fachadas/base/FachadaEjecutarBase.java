package com.TallerWapo.dominio.fachadas.base;


import com.TallerWapo.dominio.contexto.ContextoGeneral;
import com.TallerWapo.dominio.interfaces.fachadas.FachadasBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Consumer;
import java.util.function.Function;

public class FachadaEjecutarBase implements FachadasBase {
    static final Logger logger = LoggerFactory.getLogger(FachadaEjecutarBase.class);


    protected <T> T ejecutarEnTransaccion(Function<Connection, T> accion) {
        Connection conexion = ContextoGeneral.baseDatosSQL.getNuevaConexionEscritura();

        try {
            T resultado = accion.apply(conexion);
            conexion.commit();
            return resultado;

        } catch (Exception e) {
            logger.error("Error en transacción", e);
            rollbackSilencioso(conexion);
            throw new RuntimeException("Error en transacción", e);

        } finally {
            ContextoGeneral.baseDatosSQL.finalizarConexion(conexion);
        }
    }

    protected void ejecutarEnTransaccion(Consumer<Connection> accion) {
        ejecutarEnTransaccion(conexion -> {
            accion.accept(conexion);
            return null;
        });
    }

    private void rollbackSilencioso(Connection conexion) {
        try {
            conexion.rollback();
        } catch (SQLException e) {
            logger.error("Error al hacer rollback", e);
        }
    }
}
