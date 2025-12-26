package com.TallerWapo.dominio.interfaces.puertos.baseDatos;

import java.sql.Connection;

public interface BaseDatosSQLPort extends BaseDatosPort {
    Connection getConexionLectura();
    Connection getNuevaConexionEscritura();
    void finalizarConexion(Connection conexion);
}
