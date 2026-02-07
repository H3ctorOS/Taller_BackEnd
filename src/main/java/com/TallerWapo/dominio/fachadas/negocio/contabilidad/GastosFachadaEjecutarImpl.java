package com.TallerWapo.dominio.fachadas.negocio.contabilidad;

import com.TallerWapo.dominio.bo.contabilidad.GastoBO;
import com.TallerWapo.dominio.bo.vehiculos.CitaBO;
import com.TallerWapo.dominio.dto.contabilidad.GastoConCitaDTO;
import com.TallerWapo.dominio.dto.contabilidad.GastoDTO;
import com.TallerWapo.dominio.factorias.ContextoDaos;
import com.TallerWapo.dominio.interfaces.Daos.GastoDao;
import com.TallerWapo.dominio.servicios.GastosService;
import com.TallerWapo.dominio.fachadas.base.FachadaEjecutarBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GastosFachadaEjecutarImpl extends FachadaEjecutarBase {
    static final Logger logger = LoggerFactory.getLogger(GastosFachadaEjecutarImpl.class);

    /** Guardar un nuevo gasto asociado a una cita */
    public void guardarNuevoGastoCita(GastoConCitaDTO gastoCitaDTO) throws Exception {
        logger.info("Creando nuevo gasto DTO: {}, para la cita: {}", gastoCitaDTO.getGasto().toString(),
                gastoCitaDTO.getCita().toString());

        // Convertir DTO a BO
        GastoBO gastoBO = new GastoBO(gastoCitaDTO.getGasto());
        CitaBO citaBO = new CitaBO(gastoCitaDTO.getCita());

        ejecutarEnTransaccion(sesion -> {
            try {
                GastoDao gastoDao = ContextoDaos.getGastoDao(sesion);

                // Validar negocio
                GastosService.validarGasto(sesion, gastoBO);

                // Guardar el gasto
                gastoDao.guardarNuevo(gastoBO);

                // Guardar la relación con la cita
                gastoDao.guardarRelacionCita(gastoBO, citaBO);

                // Actualizar DTO con UUID generado
                gastoCitaDTO.getGasto().setUuid(gastoBO.getUuid());

            } catch (Exception e) {
                throw new RuntimeException("Error interno al crear nuevo gasto", e);
            }
        });

        logger.info("Gasto creado");
    }

    /** Actualizar un gasto */
    public void actualizarGasto(GastoDTO gastoDTO) {
        logger.info("Actualizando gasto DTO: {}", gastoDTO.toString());

        // Convertir DTO a BO
        GastoBO gastoBO = new GastoBO(gastoDTO);

        ejecutarEnTransaccion(sesion -> {
            try {
                GastosService.validarGasto(sesion, gastoBO);
                GastoDao gastoDao = ContextoDaos.getGastoDao(sesion);
                gastoDao.actualizar(gastoBO);
            } catch (Exception e) {
                throw new RuntimeException("Error interno al actualizar gasto", e);
            }
        });

        logger.info("Gasto actualizado");
    }

    /** Eliminar un gasto */
    public void eliminarGasto(GastoDTO gastoDTO) {
        logger.info("Eliminando gasto DTO: {}", gastoDTO.getUuid());

        // Convertir DTO a BO
        GastoBO gastoBO = new GastoBO(gastoDTO);

        ejecutarEnTransaccion(sesion -> {
            try {
                GastoDao gastoDao = ContextoDaos.getGastoDao(sesion);
                gastoDao.borrar(gastoBO);
            } catch (Exception e) {
                throw new RuntimeException("Error interno al eliminar gasto", e);
            }
        });

        logger.info("Gasto eliminado");
    }
}
