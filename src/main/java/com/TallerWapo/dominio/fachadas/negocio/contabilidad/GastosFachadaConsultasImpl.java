package com.TallerWapo.dominio.fachadas.negocio.contabilidad;

import com.TallerWapo.dominio.bo.vehiculos.CitaBO;
import com.TallerWapo.dominio.bo.contabilidad.GastoBO;
import com.TallerWapo.dominio.dto.contabilidad.GastoDTO;
import com.TallerWapo.dominio.fachadas.base.FachadaConsultaBase;
import com.TallerWapo.dominio.factorias.ContextoDaos;
import com.TallerWapo.dominio.interfaces.Daos.GastoDao;
import com.TallerWapo.dominio.servicios.GastosService;

import java.util.List;

public class GastosFachadaConsultasImpl extends FachadaConsultaBase {

    private final GastoDao gastoDao = ContextoDaos.getGastoDao(SESION);

    /**
     * Buscar todos los gastos
     */
    public List<GastoDTO> buscarTodos() {
        try {
            List<GastoBO> listaBos = gastoDao.buscarTodos();
            return GastosService.toDTOList(listaBos);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar todos los gastos", e);
        }
    }

    /**
     * Buscar gastos por cita
     */
    public List<GastoDTO> buscarPorCita(String citaUuid) {
        try {
            // Convertimos a int
            CitaBO cita = new CitaBO();
            cita.setUuid(Integer.parseInt(citaUuid));

            List<GastoBO> listaBos = gastoDao.buscarPorCita(cita);
            return GastosService.toDTOList(listaBos);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar gastos por cita", e);
        }
    }
}
