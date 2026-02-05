package com.TallerWapo.dominio.fachadas.negocio.contabilidad;

import com.TallerWapo.dominio.bo.vehiculos.IngresoBO;
import com.TallerWapo.dominio.fachadas.base.FachadaEjecutarBase;
import com.TallerWapo.dominio.factorias.ContextoDaos;
import com.TallerWapo.dominio.interfaces.Daos.IngresosDao;
import com.TallerWapo.dominio.servicios.IngresosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IngresosFachadaEjecutarImpl extends FachadaEjecutarBase {
    static final Logger logger = LoggerFactory.getLogger(IngresosFachadaEjecutarImpl.class);

    public void guardarNuevoIngreso(IngresoBO ingreso) throws Exception {
        logger.info("Creando nuevo ingreso: {}", ingreso.toString());

        ejecutarEnTransaccion(sesion -> {
            try {
                IngresosDao ingresosDao = ContextoDaos.getIngresoDao(sesion);

                // Validaciones de negocio
                IngresosService.validarIngreso(sesion, ingreso);

                ingresosDao.guardarNuevo(ingreso);

            } catch (Exception e) {
                throw new RuntimeException("Error interno al crear nuevo ingreso", e);
            }
        });

        logger.info("Ingreso creado");
    }

    public void actualizarIngreso(IngresoBO ingreso) {
        logger.info("Actualizando ingreso: {}", ingreso.toString());

        ejecutarEnTransaccion(sesion -> {
            try {
                // Validaciones de negocio
                IngresosService.validarIngreso(sesion, ingreso);

                IngresosDao ingresosDao = ContextoDaos.getIngresoDao(sesion);
                ingresosDao.actualizar(ingreso);

            } catch (Exception e) {
                throw new RuntimeException("Error interno al actualizar ingreso", e);
            }
        });

        logger.info("Ingreso actualizado");
    }

    public void eliminarIngreso(IngresoBO ingreso) {
        logger.info("Eliminando ingreso: {}", ingreso.getUuid());

        ejecutarEnTransaccion(sesion -> {
            try {
                IngresosDao ingresosDao = ContextoDaos.getIngresoDao(sesion);
                ingresosDao.borrar(ingreso);

            } catch (Exception e) {
                throw new RuntimeException("Error interno al eliminar ingreso", e);
            }
        });

        logger.info("Ingreso eliminado");
    }
}
