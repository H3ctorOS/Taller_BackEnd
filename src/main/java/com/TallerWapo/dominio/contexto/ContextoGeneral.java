package com.TallerWapo.dominio.contexto;

import com.TallerWapo.dominio.interfaces.puertos.ApiRest.ApiRestPort;
import com.TallerWapo.dominio.interfaces.puertos.baseDatos.BaseDatosSQLPort;

public class ContextoGeneral {
    public static ApiRestPort apiRest;
    public static BaseDatosSQLPort baseDatosSQL;

}
