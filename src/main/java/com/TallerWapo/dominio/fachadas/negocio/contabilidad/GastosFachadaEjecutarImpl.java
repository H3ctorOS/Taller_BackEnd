package com.TallerWapo.dominio.fachadas.negocio.contabilidad;


import com.TallerWapo.dominio.bo.vehiculos.GastoBO;
import com.TallerWapo.dominio.fachadas.base.FachadaEjecutarBase;
import com.TallerWapo.dominio.factorias.ContextoDaos;
import com.TallerWapo.dominio.interfaces.Daos.GastoDao;
import com.TallerWapo.dominio.servicios.GastosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GastosFachadaEjecutarImpl extends FachadaEjecutarBase {
    static final Logger logger = LoggerFactory.getLogger(GastosFachadaEjecutarImpl.class);

    public void guardarNuevoGasto(GastoBO gasto) throws Exception {
        logger.info("Creando nuevo gasto: {}", gasto.toString());

        ejecutarEnTransaccion(sesion -> {
            try {
                GastoDao gastoDao = ContextoDaos.getGastoDao(sesion);

                // Validaciones de negocio si se quieren implementar
                GastosService.validarGasto(sesion, gasto);

                gastoDao.guardarNuevo(gasto);

            } catch (Exception e) {
                throw new RuntimeException("Error interno al crear nuevo gasto", e);
            }
        });

        logger.info("Gasto creado");
    }

    public void actualizarGasto(GastoBO gasto) {
        logger.info("Actualizando gasto: {}", gasto.toString());

        ejecutarEnTransaccion(sesion -> {
            try {
                // Validaciones de negocio si se quieren implementar
                GastosService.validarGasto(sesion, gasto);

                GastoDao gastoDao = ContextoDaos.getGastoDao(sesion);
                gastoDao.actualizar(gasto);

            } catch (Exception e) {
                throw new RuntimeException("Error interno al actualizar gasto", e);
            }
        });

        logger.info("Gasto actualizado");
    }

    public void eliminarGasto(GastoBO gasto) {
        logger.info("Eliminando gasto: {}", gasto.getUuid());

        ejecutarEnTransaccion(sesion -> {
            try {
                GastoDao gastoDao = ContextoDaos.getGastoDao(sesion);
                gastoDao.borrar(gasto);

            } catch (Exception e) {
                throw new RuntimeException("Error interno al eliminar gasto", e);
            }
        });

        logger.info("Gasto eliminado");
    }
}
