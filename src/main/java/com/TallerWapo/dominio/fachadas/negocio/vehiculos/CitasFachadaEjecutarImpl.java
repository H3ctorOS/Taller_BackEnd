package com.TallerWapo.dominio.fachadas.negocio.vehiculos;

import com.TallerWapo.dominio.BOs.vehiculos.CitaBO;
import com.TallerWapo.dominio.fachadas.base.FachadaEjecutarBase;
import com.TallerWapo.dominio.factorias.ContextoDaos;
import com.TallerWapo.dominio.interfaces.Daos.CitasDao;
import com.TallerWapo.dominio.servicios.CitasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CitasFachadaEjecutarImpl extends FachadaEjecutarBase {
    static final Logger logger = LoggerFactory.getLogger(CitasFachadaEjecutarImpl.class);

    public void guardarNuevaCita(CitaBO cita) throws Exception {
        logger.info("Creando nueva cita: {}", cita.toString());

        ejecutarEnTransaccion(sesion ->{
            try {
                CitasService.validarCita(sesion,cita);

                CitasDao citasDao = ContextoDaos.getCitaDao(sesion);
                cita.setCodigoEstado("ACTI");
                citasDao.guardarNueva(cita);

            } catch (Exception e) {
                throw new RuntimeException("Error interno al crear nueva cita",e);
            }
        });

        logger.info("Cita creada");
    }


    public void actualizarCita(CitaBO cita) {
        logger.info("Actualizando cita: {}", cita.toString());

        ejecutarEnTransaccion(sesion ->{
            try {
                CitasService.validarCita(sesion,cita);

                CitasDao citasDao = ContextoDaos.getCitaDao(sesion);
                citasDao.actualizar(cita);

            } catch (Exception e) {
                throw new RuntimeException("Error interno al actualizar cita",e);
            }
        });

        logger.info("Cita actualizada");
    }

    public void eliminarCita(CitaBO cita){
        logger.info("Eliminando cita: {}", cita.getUuid());

        ejecutarEnTransaccion(sesion ->{
            try {
                CitasDao citasDao = ContextoDaos.getCitaDao(sesion);
                citasDao.borrar(cita);

            } catch (Exception e) {
                throw new RuntimeException("Error interno al eliminar cita",e);
            }
        });

        logger.info("Cita eliminada");
    }
}