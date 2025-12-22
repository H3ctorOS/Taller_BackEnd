package com.TallerWapo.Adaptadores.sparkApiRest.controladores;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;


public interface SparkController {
    final static String tipoJSON = "application/json";
    static final Gson gson = new Gson();
    static final Logger logger = LoggerFactory.getLogger(SparkController.class);

    /**
     * Metodo generico para parsear body JSON a un objeto T.
     * Maneja validaciones y errores, seteando res y retornando JSON de error si falla.
     * Si éxito, retorna el objeto T parseado.
     * @param req Request de Spark
     * @param clazz Class<T> del tipo de objeto deseado (e.g., VehiculoBO.class)
     * @return T si éxito, o null si error (pero el caller debe manejar el return de error en ruta)
     * @throws RuntimeException si error (para propagar y catch en ruta)
     */
    public static <T> T JsonToBO(Request req, Class<T> clazz) {


        String body = req.body();

        if (body == null || body.isEmpty()) {
            logger.warn("Petición POST sin body válido");  // Amarillo si Logback configurado
            throw new RuntimeException("Petición POST sin body válido");  // Lanza para que ruta retorne JSON error
        }

        try {
            T objeto = gson.fromJson(body, clazz);  // Parsea a T (e.g., VehiculoBO)
            if (objeto == null) {
                String msgError = "error";
                logger.warn(msgError);
                throw new RuntimeException(gson.toJson(msgError));
            }
            return objeto;  // Éxito: Retorna el objeto

        } catch (JsonSyntaxException e) {
            String msgError = "error";
            logger.error(msgError, e);  // Rojo para error
            throw new RuntimeException(gson.toJson(msgError));
        }
    }
}
