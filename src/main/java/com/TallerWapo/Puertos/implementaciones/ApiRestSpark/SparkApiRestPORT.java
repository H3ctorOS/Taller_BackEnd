package com.TallerWapo.Puertos.implementaciones.ApiRestSpark;

import com.TallerWapo.Puertos.implementaciones.ApiRestSpark.controladores.VehiculosSpark;
import com.TallerWapo.dominio.interfacez.puertos.ApiRestPort;
import com.TallerWapo.dominio.utiles.LoggerUtil;
import static spark.Spark.port;

public class SparkApiRestPORT implements ApiRestPort {
    private int puerto;
    private String nombre;

    @Override
    public void setNombre(String nombre) {this.nombre = nombre;}

    @Override
    public String getNombre() {return nombre;}

    @Override
    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    @Override
    public int getPuerto() {
        return puerto;
    }

    @Override
    public boolean iniciar() {
        LoggerUtil.logInfo("Arrancando servidor ApiRest Spark");

        //seter el puertro del SPARK, estatico
        port(puerto);

        LoggerUtil.logInfo("Servidor ApiRest Spark arrancado");
        return true;
    }

    @Override
    public void iniciarControllers() {
        VehiculosSpark.init();
    }
}
