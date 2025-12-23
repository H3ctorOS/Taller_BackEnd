package com.TallerWapo.dominio.Puertos.ApiRest.controladores;

import com.TallerWapo.dominio.Puertos.ApiRest.EstadoRespuestaHTTP;
import com.TallerWapo.dominio.BOs.RespuestaHttpBO;
import com.TallerWapo.dominio.BOs.vehiculos.VehiculoBO;
import com.TallerWapo.dominio.fachadas.implementaciones.VehiculosFachadaEjecutarImpl;
import com.TallerWapo.dominio.fachadas.implementaciones.VehiculosFachadaConsultasImpl;
import com.TallerWapo.dominio.interfacez.base.ControladoresBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class VehiculosControlador implements ControladoresBase {
    static final Logger logger = LoggerFactory.getLogger(VehiculosControlador.class);
    static final VehiculosFachadaConsultasImpl vehiculosFachadaConsultas = new VehiculosFachadaConsultasImpl();
    static final VehiculosFachadaEjecutarImpl vehiculosFachadaAcciones = new VehiculosFachadaEjecutarImpl();

    //RUTAS
    protected static final String rutaBase = "/api/vehiculos";
    protected static final String crearVehiculo = "/crearVehiculo";
    protected static final String actualizarVehiculo = "/actualizarVehiculo";
    protected static  final String eliminarVehiculo = "/eliminarVehiculo";
    protected static  final String buscarVehiculoMatricula = "/buscarVehiculoPorMatricula";


    protected static RespuestaHttpBO buscarVehiculoPorMatricula(String matricula) {
        logger.info("Buscando vehiculo por matricula");
        RespuestaHttpBO respuesta = new RespuestaHttpBO();

        VehiculoBO vehiculo = vehiculosFachadaConsultas.buscarVehiculo(matricula);

        if (vehiculo != null) {
            respuesta.setObjeto(vehiculo);
            respuesta.setIsOk(true);
            respuesta.setStatus(EstadoRespuestaHTTP.OK.getCodigo());
            respuesta.setMensaje("Vehiculo buscado correctamente");
        }else {
            respuesta.setObjeto(null);
            respuesta.setIsOk(false);
            respuesta.setStatus(EstadoRespuestaHTTP.NO_CONTENT.getCodigo());
            respuesta.setMensaje("Vehiculo no encontrado");
        }

        return respuesta;
    }


    protected static RespuestaHttpBO crearVehiculo(VehiculoBO  vehiculo){
        logger.info("Creando vehiculo");
        RespuestaHttpBO respuesta = new RespuestaHttpBO();

        try {
            vehiculosFachadaAcciones.crearNuevoVehiculo(vehiculo);
            respuesta.setIsOk(true);
            respuesta.setStatus(EstadoRespuestaHTTP.OK.getCodigo());
            respuesta.setMensaje("Vehiculo creado correctamente");

        } catch (Exception e) {
            respuesta.setIsOk(false);
            respuesta.setStatus(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
            respuesta.setMensaje("Error creando vehiculo" + e.getMessage());
        }

        return respuesta;
    }


    protected static RespuestaHttpBO actualizarVehiculo(VehiculoBO vehiculo){
        logger.info("Actualizando vehiculo");
        RespuestaHttpBO respuesta = new RespuestaHttpBO();

        try {
            vehiculosFachadaAcciones.actualizarVehiculo(vehiculo);
            respuesta.setIsOk(true);
            respuesta.setStatus(EstadoRespuestaHTTP.OK.getCodigo());
            respuesta.setMensaje("Vehiculo actualizado correctamente");

        }catch (Exception e) {
            respuesta.setIsOk(false);
            respuesta.setStatus(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
            respuesta.setMensaje("Error actualizando  vehiculo" + e.getMessage());
        }

        return respuesta;
    }


    protected static RespuestaHttpBO eliminarVehiculo(String matricula){
        logger.info("Creando vehiculo");
        RespuestaHttpBO respuesta = new RespuestaHttpBO();

        try {
            vehiculosFachadaAcciones.eliminarVehiculo(matricula);
            respuesta.setIsOk(true);
            respuesta.setStatus(EstadoRespuestaHTTP.OK.getCodigo());
            respuesta.setMensaje("Vehiculo eliminado correctamente");

        }catch (Exception e) {
            respuesta.setIsOk(false);
            respuesta.setStatus(EstadoRespuestaHTTP.INTERNAL_SERVER_ERROR.getCodigo());
            respuesta.setMensaje("Error eliminando  vehiculo" + e.getMessage());
        }
        return respuesta;
    }
}
