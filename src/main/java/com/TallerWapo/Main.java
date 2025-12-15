package com.TallerWapo;

import com.TallerWapo.utiles.LoggerUtil;
import com.TallerWapo.utiles.PropertiesUtils;

import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        LoggerUtil.logInfo("Hola hijo de puta");

        if(comprobarSiPrimerArranque()){
            LoggerUtil.logInfo("Haciendo cosas de primer arranque");

            String dbPath = PropertiesUtils.getString("config.properties", "db.path", "db/taller.db");

            LoggerUtil.logInfo("Ruta bbdd = " + dbPath);

        }
    }

    private static boolean comprobarSiPrimerArranque(){
        LoggerUtil.logInfo("Comprobando si es el primer arranque");

        LoggerUtil.logInfo("Es el primer arranque");
        return true;
    }
}