package com.TallerWapo.dominio.Puertos.ApiRest.controladores;

import com.TallerWapo.dominio.Puertos.ApiRest.EstadoRespuestaHTTP;
import com.TallerWapo.dominio.BOs.RespuestaRestBO;
import com.TallerWapo.dominio.BOs.vehiculos.VehiculoBO;
import com.TallerWapo.dominio.fachadas.implementaciones.VehiculosFachadaAccionesImpl;
import com.TallerWapo.dominio.fachadas.implementaciones.VehiculosFachadaConsultasImpl;
import com.TallerWapo.dominio.interfacez.base.ControladoresBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class VehiculosControlador implements ControladoresBase {
    static final Logger logger = LoggerFactory.getLogger(VehiculosControlador.class);
    static final VehiculosFachadaConsultasImpl vehiculosFachadaConsultas = new VehiculosFachadaConsultasImpl();
    static final VehiculosFachadaAccionesImpl vehiculosFachadaAcciones = new VehiculosFachadaAccionesImpl();

    //RUTAS
    protected static final String rutaBase = "/api/vehiculos";
    protected static final String crearVehiculo = "/crearVehiculo";
    protected static final String actualizarVehiculo = "/actualizarVehiculo";
    protected static  final String eliminarVehiculo = "/eliminarVehiculo";
    protected static  final String buscarVehiculoMatricula = "/buscarVehiculoPorMatricula";


    protected static RespuestaRestBO buscarVehiculoPorMatricula(String matricula) {
        logger.info("Buscando vehiculo por matricula");
        RespuestaRestBO respuesta = new RespuestaRestBO();

        VehiculoBO vehiculo = vehiculosFachadaConsultas.buscarVehiculoMatricula(matricula);

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


    protected static RespuestaRestBO crearVehiculo(VehiculoBO  vehiculo){
        logger.info("Creando vehiculo");
        RespuestaRestBO respuesta = new RespuestaRestBO();

        //llamar a la facahda
        vehiculosFachadaAcciones.crearNuevoVehiculo(vehiculo);
        respuesta.setIsOk(true);
        respuesta.setStatus(EstadoRespuestaHTTP.OK.getCodigo());
        respuesta.setMensaje("Vehiculo creado correctamente");

        return respuesta;
    }


    protected static RespuestaRestBO actualizarVehiculo(VehiculoBO vehiculo){
        logger.info("Creando vehiculo");
        RespuestaRestBO respuesta = new RespuestaRestBO();

        //llamar a la facahda
        vehiculosFachadaAcciones.actualizarVehiculo(vehiculo);
        respuesta.setIsOk(true);
        respuesta.setStatus(EstadoRespuestaHTTP.OK.getCodigo());
        respuesta.setMensaje("Vehiculo creado correctamente");

        return respuesta;
    }


    protected static RespuestaRestBO eliminarVehiculo(String matricula){
        logger.info("Creando vehiculo");
        RespuestaRestBO respuesta = new RespuestaRestBO();

        //llamar a la facahda
        vehiculosFachadaAcciones.eliminarVehiculo(matricula);
        respuesta.setIsOk(true);
        respuesta.setStatus(EstadoRespuestaHTTP.OK.getCodigo());
        respuesta.setMensaje("Vehiculo eliminado correctamente");

        return respuesta;
    }
}
