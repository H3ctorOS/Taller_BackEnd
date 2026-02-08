package com.TallerWapo.dominio.fachadas.negocio.vehiculos;

import com.TallerWapo.dominio.bo.vehiculos.CitaBO;
import com.TallerWapo.dominio.dto.CitaDTO;
import com.TallerWapo.dominio.fachadas.base.FachadaEjecutarBase;
import com.TallerWapo.dominio.factorias.ContextoDaos;
import com.TallerWapo.dominio.interfaces.Daos.CitasDao;
import com.TallerWapo.dominio.servicios.CitasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class CitasFachadaEjecutarImpl extends FachadaEjecutarBase {

    private static final Logger logger = LoggerFactory.getLogger(CitasFachadaEjecutarImpl.class);

    public void guardarNuevaCita(CitaDTO citaDTO) throws Exception {
        logger.info("Creando nueva cita");

        ejecutarEnTransaccion(sesion -> {
            try {
                CitaBO citaBO = mapearABO(citaDTO);

                CitasDao citasDao = ContextoDaos.getCitaDao(sesion);

                CitasService.validarCita(sesion, citaBO);

                citaBO.setCodigoEstado("ACTI");
                citasDao.guardarNueva(citaBO);

            } catch (Exception e) {
                throw new RuntimeException("Error interno al crear nueva cita", e);
            }
        });

        logger.info("Cita creada");
    }

    public void actualizarCita(CitaDTO citaDTO) {
        logger.info("Actualizando cita");

        ejecutarEnTransaccion(sesion -> {
            try {
                CitaBO citaBO = mapearABO(citaDTO);

                CitasService.validarCita(sesion, citaBO);

                CitasDao citasDao = ContextoDaos.getCitaDao(sesion);
                citasDao.actualizar(citaBO);

            } catch (Exception e) {
                throw new RuntimeException("Error interno al actualizar cita", e);
            }
        });

        logger.info("Cita actualizada");
    }

    public void eliminarCita(CitaDTO citaDTO) {
        logger.info("Eliminando cita: {}", citaDTO.getUuid());

        ejecutarEnTransaccion(sesion -> {
            try {
                CitasDao citasDao = ContextoDaos.getCitaDao(sesion);

                CitaBO citaBO = new CitaBO();
                citaBO.setUuid(citaDTO.getUuid());

                citasDao.borrar(citaBO);

            } catch (Exception e) {
                throw new RuntimeException("Error interno al eliminar cita", e);
            }
        });

        logger.info("Cita eliminada");
    }

    /**
     * Mapper DTO → BO (privado a la fachada)
     */
    private CitaBO mapearABO(CitaDTO dto) {
        CitaBO bo = new CitaBO();

        bo.setUuid(dto.getUuid());
        bo.setVehiculoUuid(dto.getVehiculoUuid());
        bo.setConcepto(dto.getConcepto());
        bo.setCodigoEstado(dto.getCodigoEstado());
        bo.setObservaciones(dto.getObservaciones());

        bo.setFechaInicio(
                dto.getFechaInicio() > 0
                        ? new Date(dto.getFechaInicio())
                        : null
        );

        bo.setFechaFinalizada(
                dto.getFechaFinalizada() != null
                        ? new Date(dto.getFechaFinalizada())
                        : null
        );

        return bo;
    }
}
