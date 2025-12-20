package com.TallerWapo.dominio.utiles;

public class LoggerUtil {

    public static void logInfo (String mensaje){
        System.out.println(mensaje);
    }

    public static void logWarning(String mensaje) {
        System.out.println(mensaje);
    }

    public static void logError(String mensaje, Throwable t) {
        System.out.println(mensaje + " Mensaje del error: "+ t.getMessage());
    }
}
