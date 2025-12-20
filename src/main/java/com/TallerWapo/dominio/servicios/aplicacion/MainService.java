package com.TallerWapo.dominio.servicios.aplicacion;

import com.TallerWapo.dominio.utiles.LoggerUtil;

public class MainService {

    public static boolean esPrimerArranque(){
        LoggerUtil.logInfo("Comprobando si es el primer arranque");

        LoggerUtil.logInfo("Es el primer arranque");
        return true;
    }

}
