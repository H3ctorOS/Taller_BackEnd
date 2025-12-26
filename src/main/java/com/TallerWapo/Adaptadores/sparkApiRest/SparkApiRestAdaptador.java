package com.TallerWapo.Adaptadores.sparkApiRest;

import com.TallerWapo.Adaptadores.sparkApiRest.controladores.ClientesSparkControlador;
import com.TallerWapo.Adaptadores.sparkApiRest.controladores.VehiculosSparkControlador;
import com.TallerWapo.dominio.interfaces.puertos.ApiRest.ApiRestPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.port;

public class SparkApiRestAdaptador implements ApiRestPort  {
    static final Logger logger = LoggerFactory.getLogger(SparkApiRestAdaptador.class);

    private int puerto;
    private String nombre = "ADAPTADOR SPARK API REST";

    @Override
    public void setNombreAdaptador(String nombre) {this.nombre = nombre;}

    @Override
    public String getNombreAdaptador() {return nombre;}

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
        //seter el puertro del SPARK, estatico
        port(puerto);

        return true;
    }

    @Override
    public void iniciarControllers() {
        VehiculosSparkControlador.init();
        ClientesSparkControlador.init();
    }
}
