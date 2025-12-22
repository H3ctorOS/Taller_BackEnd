package com.TallerWapo.dominio.servicios.aplicacion;

import com.TallerWapo.Adaptadores.BaseDatossql.SQliteAdaptador;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class FicherosService {

    public static String leerArchivoDeResources(String ruta) throws IOException {
        try {
            InputStream archivoLeido = SQliteAdaptador.class
                    .getClassLoader()
                    .getResourceAsStream(ruta);

            if (archivoLeido == null) {
                throw new RuntimeException("No se encontró el archivo: " + ruta);
            }

            return new String(archivoLeido.readAllBytes(), StandardCharsets.UTF_8);

        }catch (Exception e){
            throw new IOException("Error al leer archivo: " + ruta, e);
        }
    }
}
