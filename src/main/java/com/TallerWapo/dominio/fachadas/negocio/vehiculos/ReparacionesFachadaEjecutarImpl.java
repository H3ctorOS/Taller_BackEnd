package com.TallerWapo.dominio.fachadas.negocio.vehiculos;

import com.TallerWapo.dominio.BOs.vehiculos.ReparacionBO;
import com.TallerWapo.dominio.BOs.vehiculos.VehiculoBO;
import com.TallerWapo.dominio.fachadas.base.FachadaEjecutarBase;
import com.TallerWapo.dominio.factorias.ContextoDaos;
import com.TallerWapo.dominio.interfaces.Daos.ReparacionesDao;
import com.TallerWapo.dominio.interfaces.Daos.VehiculosDao;
import com.TallerWapo.dominio.servicios.ReparacionesService;
import com.TallerWapo.dominio.servicios.VehiculosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ReparacionesFachadaEjecutarImpl extends FachadaEjecutarBase {
    static final Logger logger = LoggerFactory.getLogger(ReparacionesFachadaEjecutarImpl.class);

    public void guardarNueva(ReparacionBO reparacion) throws Exception {
        logger.info("Guardando nueva reparacion: {}", reparacion.toString());

        ejecutarEnTransaccion(sesion ->{
            try {
                ReparacionesService.validarReparacion(sesion,reparacion);

                ReparacionesDao reparacionesDao = ContextoDaos.getReparacionesDao(sesion);
                reparacion.setCodigoEstado("ACTI");
                reparacionesDao.guardarNueva(reparacion);

            }catch (Exception e) {
                throw new RuntimeException("Error interno al guardar la nueva reparacion",e);
            }
        });

        logger.info("Reparacion creada");
    }

}
