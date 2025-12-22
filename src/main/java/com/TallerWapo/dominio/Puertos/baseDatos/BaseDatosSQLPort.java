package com.TallerWapo.dominio.Puertos.baseDatos;

import com.TallerWapo.Adaptadores.BaseDatossql.SQliteAdaptador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

public interface BaseDatosSQLPort extends BaseDatosPort {
    Connection getConnection();

}
