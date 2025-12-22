package com.TallerWapo.Adaptadores.BaseDatossql;


import com.TallerWapo.dominio.Puertos.baseDatos.BaseDatosSQLPort;
import com.TallerWapo.dominio.utiles.FileUtil;
import com.TallerWapo.dominio.utiles.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQliteAdaptador implements BaseDatosSQLPort {
    static final Logger logger = LoggerFactory.getLogger(SQliteAdaptador.class);

    private static final String DB_RUTA = PropertiesUtils.getString("constantes/db-config.properties", "db.path", "db/taller.db");
    private static final String CLASS_NAME = "org.sqlite.JDBC";
    private Connection conexion;
    private String nombreAdaptador = "Adaptador base datos SQLite";



    @Override
    public void setNombreAdaptador(String nombre) {
        nombreAdaptador = nombre;
    }

    @Override
    public String getNombreAdaptador() {
        return nombreAdaptador;
    }

    @Override
    public boolean iniciar() {
        try {
            crearConexion();

        } catch (Exception e) {
            logger.info("Conexion no creada", e);
            return false;
        }
        return true;
    }

    @Override
    public Connection getConnection() {
        return conexion;
    }


    private void crearConexion() throws Exception{
        Class.forName(CLASS_NAME);  // Carga driver (sqlite-jdbc)
        conexion = DriverManager.getConnection(CLASS_NAME + DB_RUTA);

        logger.info("Conexión BD SQLite establecida en: {}", DB_RUTA);
    }

    /**
     * Monta la base de datos en la ruta por defecto
     */
    public static boolean crearBaseDatos() throws SQLException, ClassNotFoundException, IOException {
        logger.info("Construyendo archivo BD en: " + DB_RUTA);  // Azul para info

        // Crea archivo implícitamente al conectar
        Class.forName("org.sqlite.JDBC");  // Carga driver (sqlite-jdbc)
        Connection conexion = DriverManager.getConnection(CLASS_NAME + DB_RUTA);


        // Opcional: Verifica existencia del archivo post-conexión
        if (!FileUtil.fileExists(DB_RUTA)) {
            throw new IOException("Fallo al crear archivo BD: " + DB_RUTA);
        }
        logger.info("Archivo BD creado/existente.");

        // Carga y ejecuta schema.sql
        String schemaFile = PropertiesUtils.getString("constantes/db-config.properties", "db.schema.file", "constantes/schema.sql");
        InputStream input = SQliteAdaptador.class.getClassLoader().getResourceAsStream(schemaFile);
        if (input == null) {
            throw new IOException("Schema file no encontrado: " + schemaFile);
        }

        try (Statement stmt = conexion.createStatement();
             BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            StringBuilder sqlBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sqlBuilder.append(line);
                if (line.trim().endsWith(";")) {
                    stmt.execute(sqlBuilder.toString());
                    sqlBuilder.setLength(0);
                }
            }
            logger.info("Schema cargado y ejecutado correctamente.");  // Azul para éxito

        } catch (SQLException e) {
            logger.info("Error ejecutando schema.sql", e);  // Rojo para error
            throw e;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}
