package com.TallerWapo.Adaptadores.BaseDatossql;


import com.TallerWapo.dominio.Puertos.baseDatos.BaseDatosSQLPort;
import com.TallerWapo.dominio.servicios.aplicacion.BaseDatosSQLService;
import com.TallerWapo.dominio.servicios.aplicacion.FicherosService;
import com.TallerWapo.dominio.utiles.FileUtil;
import com.TallerWapo.dominio.utiles.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQliteAdaptador implements BaseDatosSQLPort {
    static final Logger logger = LoggerFactory.getLogger(SQliteAdaptador.class);

    private static final String RUTA_BASEDATOS = PropertiesUtils.getString("constantes/config.properties", "ruta.BaseDatosSQL", null);
    private static final String NOMBRE_ARCHIVO_BASEDATOS = PropertiesUtils.getString("constantes/sql.properties", "sql.fileName", null);
    private static final String FICHERO_BASEDATOS = RUTA_BASEDATOS + "/" + NOMBRE_ARCHIVO_BASEDATOS;
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
        return true;
    }


    @Override
    public Connection getConexion() {
        return conexion;
    }

    @Override
    public void generarConexionEscritura() {
        try {
            conexion = crearConexion();
            conexion.setAutoCommit(false);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void generarConexionLectura() {
        try {
            conexion = crearConexion();
            conexion.isReadOnly();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void finalizarConexion() {
        try {
            conexion.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        conexion = null;
    }


    private static Connection crearConexion() throws Exception{
        Class.forName(CLASS_NAME);  // Carga driver (sqlite-jdbc)
        Connection conexion = DriverManager.getConnection("jdbc:sqlite:" + FICHERO_BASEDATOS);

        logger.info("Conexion BD SQLite establecida en: {}", FICHERO_BASEDATOS);

        return conexion;
    }


    /**
     * Monta la base de datos en la ruta por defecto
     */
    public static void construirBaseDatos(){
        logger.info("Construyendo archivo BD en: " + FICHERO_BASEDATOS);  // Azul para info

        try {
            // Crea archivo implícitamente al conectar
            Connection conexion = crearConexion();

            //Verificar existencia del archivo
            if (!FileUtil.fileExists(FICHERO_BASEDATOS)) {
                throw new IOException("Fallo al crear archivo BD: " + FICHERO_BASEDATOS);
            }

            logger.info("Archivo BD creado/existente.");

            //Montar el esquema de la base de datos
            crearEsquemaBaseDatos(conexion);

            conexion.close();

        }catch (Exception e){

            logger.error("Error creando la base de datos",e);
        }

    }



    private static void crearEsquemaBaseDatos(Connection conexion) throws IOException, SQLException {
        logger.info("Creando el esquema de la base de datos");

        try {
            // Carga y ejecuta schema.sql
            String rutaEsquema = PropertiesUtils.getString("constantes/sql.properties", "sql.dbEsquema", null);
            String esquemaSql = FicherosService.leerArchivoDeResources(rutaEsquema);

            BaseDatosSQLService.crearEsquema(conexion,esquemaSql);

            conexion.close();

        } catch (Exception e) {
            logger.info("Error ejecutando schema.sql", e);  // Rojo para error
            throw e;
        }
    }

}
