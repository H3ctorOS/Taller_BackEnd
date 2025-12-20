package com.TallerWapo.Puertos.implementaciones.ApiRestSpark.controladores;

import com.TallerWapo.Puertos.interfacez.controladores.VehiculosControlador;
import com.TallerWapo.dominio.contexto.FachadasContexto;
import com.TallerWapo.fachadas.interfaces.Vehiculos.VehiculosFachadaAcciones;

import static spark.Spark.*;

public class VehiculosSpark implements VehiculosControlador ,SparkController{
    private static final VehiculosFachadaAcciones vehiculosFachadaAcciones = FachadasContexto.vehiculosFachadaAcciones;

    private VehiculosSpark() {
        throw new AssertionError("No se puede instanciar vehiculosSpark");
    }

    public static void init() {
        path(rutaBase, () -> {

            post(crearVehiculo, (req, res) -> {
                res.type(tipoJSON);
                try {
                    vehiculosFachadaAcciones.crearNuevoVehiculo();
                } catch (Exception e) {

                }
                return null;
            });

            post(actualizarVehiculo, (req, res) -> {
                res.type(tipoJSON);
                try {
                    vehiculosFachadaAcciones.actualizarVehiculo();
                } catch (Exception e) {

                }
                return null;
            });

            post(eliminarVehiculo, (req, res) -> {
                res.type(tipoJSON);
                try {
                    vehiculosFachadaAcciones.borrarVehiculo();
                } catch (Exception e) {

                }
                return null;
            });


            get(buscarVehiculoMatricula, (req, res) -> {
                res.type(tipoJSON);
                try {


                } catch (Exception e) {

                }
                return null;
            });

        });
    }
}