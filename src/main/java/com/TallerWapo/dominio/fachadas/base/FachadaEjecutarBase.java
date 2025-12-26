package com.TallerWapo.dominio.fachadas.base;


import com.TallerWapo.dominio.contexto.Contexto;
import com.TallerWapo.dominio.contexto.Sesion;
import com.TallerWapo.dominio.interfaces.fachadas.FachadasBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Consumer;
import java.util.function.Function;

public class FachadaEjecutarBase implements FachadasBase {
    static final Logger logger = LoggerFactory.getLogger(FachadaEjecutarBase.class);


    protected <T> T ejecutarEnTransaccion(Function<Sesion, T> accion) {
        Sesion sesion = new Sesion(Contexto.baseDatosSQL.getNuevaConexionEscritura());

        try {
            T resultado = accion.apply(sesion);
            sesion.getConexion().commit();
            return resultado;

        } catch (Exception e) {
            logger.error("Error en transacción", e);
            rollbackSilencioso(sesion.getConexion());
            throw new RuntimeException("Error en transacción", e);

        } finally {
            Contexto.baseDatosSQL.finalizarConexion(sesion.getConexion());
        }
    }

    protected void ejecutarEnTransaccion(Consumer<Sesion> accion) {
        ejecutarEnTransaccion(sesion -> {
            accion.accept(sesion);
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
