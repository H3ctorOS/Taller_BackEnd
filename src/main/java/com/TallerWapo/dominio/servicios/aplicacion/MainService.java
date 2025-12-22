package com.TallerWapo.dominio.servicios.aplicacion;

import com.TallerWapo.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainService {

    public static boolean esPrimerArranque(){
        final Logger logger = LoggerFactory.getLogger(Main.class);

        logger.info("Es primer arranque");
        return true;
    }

}
