package com.TallerWapo.dominio.fachadas.negocio.contabilidad;

import com.TallerWapo.dominio.bo.contabilidad.IngresoBO;
import com.TallerWapo.dominio.bo.vehiculos.CitaBO;
import com.TallerWapo.dominio.dto.contabilidad.IngresoConCitaDTO;
import com.TallerWapo.dominio.dto.contabilidad.IngresoDTO;
import com.TallerWapo.dominio.fachadas.base.FachadaEjecutarBase;
import com.TallerWapo.dominio.factorias.ContextoDaos;
import com.TallerWapo.dominio.interfaces.Daos.IngresosDao;
import com.TallerWapo.dominio.servicios.IngresosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IngresosFachadaEjecutarImpl extends FachadaEjecutarBase {
    static final Logger logger = LoggerFactory.getLogger(IngresosFachadaEjecutarImpl.class);

    public void guardarNuevoIngresoCita(IngresoConCitaDTO ingresoCitaDTO) throws Exception {
        logger.info("Creando nuevo ingreso DTO: {}, en cita:", ingresoCitaDTO.getIngreso().toString() , ingresoCitaDTO.getCita().toString());

        // Convertir DTO a BO
        IngresoBO ingreso = new IngresoBO(ingresoCitaDTO.getIngreso());
        CitaBO cita = new CitaBO(ingresoCitaDTO.getCita());

        ejecutarEnTransaccion(sesion -> {
            try {
                IngresosDao ingresosDao = ContextoDaos.getIngresoDao(sesion);

                // Validaciones de negocio
                IngresosService.validarIngreso(sesion, ingreso);

                //Guardar el intreso
                ingresosDao.guardarNuevo(ingreso);

                //Crear la relacion
                ingresosDao.guardarRelacionCita(ingreso, cita);

            } catch (Exception e) {
                throw new RuntimeException("Error interno al crear nuevo ingreso", e);
            }
        });

        logger.info("Ingreso creado");
    }

    public void actualizarIngreso(IngresoDTO ingresoDTO) {
        logger.info("Actualizando ingreso DTO: {}", ingresoDTO.toString());

        // Convertir DTO a BO
        IngresoBO ingresoBO = new IngresoBO(ingresoDTO);

        ejecutarEnTransaccion(sesion -> {
            try {
                // Validaciones de negocio
                IngresosService.validarIngreso(sesion, ingresoBO);

                IngresosDao ingresosDao = ContextoDaos.getIngresoDao(sesion);
                ingresosDao.actualizar(ingresoBO);

            } catch (Exception e) {
                throw new RuntimeException("Error interno al actualizar ingreso", e);
            }
        });

        logger.info("Ingreso actualizado");
    }

    public void eliminarIngreso(IngresoDTO ingresoDTO) {
        logger.info("Eliminando ingreso DTO: {}", ingresoDTO.getUuid());

        // Convertir DTO a BO
        IngresoBO ingresoBO = new IngresoBO(ingresoDTO);

        ejecutarEnTransaccion(sesion -> {
            try {
                IngresosDao ingresosDao = ContextoDaos.getIngresoDao(sesion);
                ingresosDao.borrar(ingresoBO);

            } catch (Exception e) {
                throw new RuntimeException("Error interno al eliminar ingreso", e);
            }
        });

        logger.info("Ingreso eliminado");
    }
}
