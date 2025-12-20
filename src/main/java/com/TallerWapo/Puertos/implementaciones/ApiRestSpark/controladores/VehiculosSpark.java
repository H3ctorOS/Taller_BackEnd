package com.TallerWapo.Puertos.implementaciones.ApiRestSpark.controladores;

import com.TallerWapo.Puertos.interfacez.controladores.VehiculosControlador;
import static spark.Spark.*;

public class VehiculosSpark implements VehiculosControlador {

    private VehiculosSpark() {
        throw new AssertionError("No se puede instanciar vehiculosSpark");
    }


    public static void init() {
        path(rutaBase, () -> {

            // GET: buscarVehiculoPorMatricula
            get(buscarVehiculoMatricula, (req, res) -> {
                res.type("application/json");
                try {


                } catch (Exception e) {

                }
                return null;
            });



        });
    }
}
