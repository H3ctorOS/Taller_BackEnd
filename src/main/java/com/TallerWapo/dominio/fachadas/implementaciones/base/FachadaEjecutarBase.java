package com.TallerWapo.dominio.fachadas.implementaciones.base;


import com.TallerWapo.dominio.contexto.ContextoGeneral;
import com.TallerWapo.dominio.fachadas.interfaces.FachadasBase;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Supplier;

public class FachadaEjecutarBase implements FachadasBase {

    protected <T> T ejecutarEnTransaccion(Supplier<T> accion) {
        //Genera la nueva conexion
        ContextoGeneral.baseDatosSQL.generarConexionEscritura();
        Connection conexionEscritura = ContextoGeneral.baseDatosSQL.getConexion();

        try {
            T resultado = accion.get();
            conexionEscritura.commit();
            return resultado;

        } catch (Exception e) {
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
        } catch (SQLException ignored) {
        }
    }
}
