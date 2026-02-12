package com.TallerWapo.dominio.fachadas.negocio.calendario;


import com.TallerWapo.dominio.bo.vehiculos.CitaBO;
import com.TallerWapo.dominio.bo.vehiculos.VehiculoBO;
import com.TallerWapo.dominio.dto.calendario.CitaDTO;
import com.TallerWapo.dominio.dto.calendario.CitaSemanaDTO;
import com.TallerWapo.dominio.fachadas.base.FachadaConsultaBase;
import com.TallerWapo.dominio.factorias.ContextoDaos;
import com.TallerWapo.dominio.interfaces.Daos.CitasDao;
import com.TallerWapo.dominio.interfaces.Daos.VehiculosDao;
import com.TallerWapo.dominio.servicios.CitasService;
import com.TallerWapo.dominio.utiles.calendario.CalendarioUtil;

import java.util.List;

public class CitasFachadaConsultasImpl extends FachadaConsultaBase {
    private final CitasDao citasDao = ContextoDaos.getCitaDao(SESION);
    private final VehiculosDao vehiculosDao = ContextoDaos.getVehiculoDao(SESION);

    public List<CitaDTO> buscarPorVehiculo(String vehiculoUuid) {
        try {
            VehiculoBO vehiculo =vehiculosDao.buscarPorId(Integer.parseInt(vehiculoUuid));
            List<CitaBO> listaBos =  citasDao.buscarPorVehiculo(vehiculo);
            return CitasService.toDTOList(listaBos);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public List<CitaDTO> buscarTodas() {
        try {
            List<CitaBO> listaBos = citasDao.buscarTodas();
            return CitasService.toDTOList(listaBos);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public List<CitaDTO> buscarDia(Long dia) {
        try {
            List<CitaBO> listaBos = citasDao.buscarAcivasDia(dia);
            return CitasService.toDTOList(listaBos);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /** Busca todas las citas activas de una semana ISO dada */
    public CitaSemanaDTO buscarPorSemana(int numeroSemana, int anio) {
        try {
            return CitasService.buscarCitaSemana(SESION,numeroSemana,anio);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
