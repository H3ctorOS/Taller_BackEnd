package com.TallerWapo.dominio.utiles;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDatosSqlUtils {
    static final Logger logger = LoggerFactory.getLogger(BaseDatosSqlUtils.class);

    public static void crearEsquema(Connection conexion, String esquemaSql) throws SQLException {
        try {
            // Separar sentencias
            String[] sentencias = esquemaSql.split(";");
            Statement stmt = conexion.createStatement();

            for (String sentencia : sentencias) {
                sentencia = sentencia.trim();
                if (!sentencia.isEmpty()) {
                    logger.info(sentencia);
                    stmt.execute(sentencia);
                }
            }

            logger.info("Esquema cargado y ejecutado correctamente.");  // Azul para éxito
            stmt.close();

        } catch (Exception e) {
            logger.info("Error ejecutando schema.sql", e);  // Rojo para error
            throw e;
        }
    }

}
