package com.TallerWapo;


import com.TallerWapo.dominio.servicios.aplicacion.FachadasService;
import com.TallerWapo.dominio.servicios.aplicacion.InterfazService;
import com.TallerWapo.dominio.servicios.aplicacion.MainService;
import com.TallerWapo.dominio.servicios.aplicacion.PuertosService;
import com.TallerWapo.dominio.utiles.LoggerUtil;
import com.TallerWapo.dominio.utiles.PropertiesUtils;


public class Main {

    public static void main(String[] args) {
        LoggerUtil.logInfo("Arrancando puta mierda de backEnd");

        if(MainService.esPrimerArranque()){
            hacerCosasPrimerArranque();
        }

        FachadasService.arrancarFachadas();
        PuertosService.arrancarPuertos();
        InterfazService.arrancarInterfazLocal();
    }


    private static void hacerCosasPrimerArranque(){
        LoggerUtil.logInfo("Haciendo cosas de primer arranque");

        //TODO: construir directorio carpetas
        String dbPath = PropertiesUtils.getString("config.properties", "db.path", "db/taller.db");

        //TODO: construir base datos
        LoggerUtil.logInfo("Ruta bbdd = " + dbPath);

    }

}