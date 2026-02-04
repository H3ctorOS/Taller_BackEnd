package com.TallerWapo.dominio.fachadas.negocio.vehiculos;


import com.TallerWapo.dominio.bo.vehiculos.CitaBO;
import com.TallerWapo.dominio.bo.vehiculos.VehiculoBO;
import com.TallerWapo.dominio.dto.CitaDTO;
import com.TallerWapo.dominio.fachadas.base.FachadaConsultaBase;
import com.TallerWapo.dominio.factorias.ContextoDaos;
import com.TallerWapo.dominio.interfaces.Daos.CitasDao;
import com.TallerWapo.dominio.interfaces.Daos.VehiculosDao;
import com.TallerWapo.dominio.servicios.CitasService;

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
}
