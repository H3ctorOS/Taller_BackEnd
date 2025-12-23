package com.TallerWapo.dominio.fachadas.implementaciones.base;


import com.TallerWapo.dominio.contexto.ContextoGeneral;
import com.TallerWapo.dominio.fachadas.interfaces.FachadasBase;

import java.util.function.Supplier;

public class FachadaConsultaBase implements FachadasBase {


    protected <T> T ejecutarConsulta(Supplier<T> accion) {
        //Genera la nueva conexion
        ContextoGeneral.baseDatosSQL.generarConexionEscritura();
        try {
            T resultado = accion.get();
            return resultado;

        } catch (Exception e) {
            throw new RuntimeException("Error en transacción", e);

        } finally {
            ContextoGeneral.baseDatosSQL.finalizarConexion();
        }
    }
}
