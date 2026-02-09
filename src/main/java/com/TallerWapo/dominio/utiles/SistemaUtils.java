package com.TallerWapo.dominio.utiles;

public class SistemaUtils {

    /**
     * Apaga el equipo donde se está ejecutando la aplicación (Windows 10).
     *
     * Funcionamiento:
     * - Ejecuta el comando nativo de Windows: "shutdown /s /t 0"
     *   /s  → apaga el sistema
     *   /t 0 → apagado inmediato, sin tiempo de espera
     *
     * Consideraciones importantes:
     * - El apagado es inmediato y NO solicita confirmación al usuario.
     * - Se cerrarán todas las sesiones y aplicaciones en ejecución.
     * - La aplicación debe tener permisos suficientes para ejecutar comandos del sistema.
     * - En entornos corporativos o con políticas restrictivas puede fallar.
     */
    public static void apagarPC() throws Exception {
        // /s = apagar
        // /t 0 = sin espera (apagado inmediato)
        new ProcessBuilder("shutdown", "/s", "/t", "0")
                .inheritIO()
                .start();
    }


}
