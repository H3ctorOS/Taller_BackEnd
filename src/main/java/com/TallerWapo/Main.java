package com.TallerWapo;

import com.TallerWapo.Adaptadores.BaseDatossql.SQliteAdaptador;
import com.TallerWapo.Adaptadores.sparkApiRest.SparkApiRestAdaptador;
import com.TallerWapo.dominio.utiles.InterfazGraficaUtils;
import com.TallerWapo.dominio.utiles.MainUtil;
import com.TallerWapo.dominio.utiles.PuertosUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {
    static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Servidor SUPER TALLER arrancando");

        if(MainUtil.esPrimerArranque()){
            hacerCosasPrimerArranque();
        }

        arrancarAdaptadores();
        InterfazGraficaUtils.arrancarInterfazLocal();
    }


    private static void hacerCosasPrimerArranque()  {
        logger.info("haciendo cosas del primer arranque");

        //Construir estructura de ficheros
        MainUtil.construirStructuraCarpetas();

        //Contruir archivos properties
        MainUtil.construirFicherosProperties();

        //Setear las properties base iniciales
        MainUtil.guardarPropertiesBaseIniciales();

        //Construir base datos
        SQliteAdaptador.construirBaseDatos();

        logger.info("Terminadas cosas del primer arranque");
    }

    private static void arrancarAdaptadores(){
        logger.info("Arrancando adaptadores");

        //iniciar Base datos SQL con SQLITE
        PuertosUtil.arrancarBaseDatosLocalSQL(new SQliteAdaptador());

        //iniciar ApiRest con Spark
        PuertosUtil.arrancarApiRest(new SparkApiRestAdaptador());

        logger.info("todos los arrancadores en servicio");
    }
}