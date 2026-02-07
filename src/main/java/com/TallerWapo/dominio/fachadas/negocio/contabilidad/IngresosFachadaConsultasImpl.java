package com.TallerWapo.dominio.fachadas.negocio.contabilidad;

import com.TallerWapo.dominio.bo.vehiculos.CitaBO;
import com.TallerWapo.dominio.bo.contabilidad.IngresoBO;
import com.TallerWapo.dominio.dto.contabilidad.IngresoDTO;
import com.TallerWapo.dominio.fachadas.base.FachadaConsultaBase;
import com.TallerWapo.dominio.factorias.ContextoDaos;
import com.TallerWapo.dominio.interfaces.Daos.IngresosDao;
import com.TallerWapo.dominio.servicios.IngresosService;

import java.util.List;

public class IngresosFachadaConsultasImpl extends FachadaConsultaBase {

    private final IngresosDao ingresosDao = ContextoDaos.getIngresoDao(SESION);

    /**
     * Buscar todos los ingresos
     */
    public List<IngresoDTO> buscarTodos() {
        try {
            List<IngresoBO> listaBos = ingresosDao.buscarTodos();
            return IngresosService.toDTOList(listaBos);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar todos los ingresos", e);
        }
    }

    /**
     * Buscar ingresos por cita
     */
    public List<IngresoDTO> buscarPorCita(String citaUuid) {
        try {
            // Convertimos a int y creamos BO
            CitaBO cita = new CitaBO();
            cita.setUuid(Integer.parseInt(citaUuid));

            List<IngresoBO> listaBos = ingresosDao.buscarPorCita(cita);
            return IngresosService.toDTOList(listaBos);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar ingresos por cita", e);
        }
    }
}
