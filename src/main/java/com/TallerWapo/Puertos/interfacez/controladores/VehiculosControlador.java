package com.TallerWapo.Puertos.interfacez.controladores;

import com.TallerWapo.dominio.interfacez.ControladoresBase;


public interface VehiculosControlador extends ControladoresBase {
    //Ruta base
    static final String rutaBase = "/api/vehiculos";

    //Rutas get
    static final String crearVehiculo = "/crearVehiculo";
    static final String actualizarVehiculo = "/actualizarVehiculo";
    static  final String eliminarVehiculo = "/eliminarVehiculo";

    //Rutas Post
    static  final String buscarVehiculoMatricula = "/buscarVehiculoPorMatricula";
}
