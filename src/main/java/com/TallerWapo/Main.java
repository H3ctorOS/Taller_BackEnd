package com.TallerWapo;

import com.TallerWapo.Adaptadores.BaseDatossql.SQliteAdaptador;
import com.TallerWapo.Adaptadores.sparkApiRest.SparkApiRestAdaptador;
import com.TallerWapo.dominio.servicios.aplicacion.InterfazService;
import com.TallerWapo.dominio.servicios.aplicacion.MainService;
import com.TallerWapo.dominio.servicios.aplicacion.PuertosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {
    static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        if(MainService.esPrimerArranque()){
            hacerCosasPrimerArranque();
        }

        arrancarAdaptadores();
       // InterfazService.arrancarInterfazLocal();
    }


    private static void hacerCosasPrimerArranque()  {
        logger.info("haciendo cosas del primer arranque");

        //Construir ficheros
        MainService.construirStructuraCarpetas();

        //Construir base datos
        try {
            SQliteAdaptador.crearBaseDatos();

        }catch (Exception e){
            logger.error(e.getMessage());
        }

    }

    private static void arrancarAdaptadores(){
        logger.info("Arrancando adaptadores");

        //iniciar Base datos con SQLITE
        //PuertosService.arrancarBaseDatosLocalSQL(new SQliteAdaptador());

        //iniciar ApiRest con Spark
        PuertosService.arrancarApiRest(new SparkApiRestAdaptador());

        logger.info("todos los puertos arrancados");
    }

}