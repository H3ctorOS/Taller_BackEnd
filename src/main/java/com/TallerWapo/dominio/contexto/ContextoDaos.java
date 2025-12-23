package com.TallerWapo.dominio.contexto;

import com.TallerWapo.Adaptadores.BaseDatossql.daosImpl.VehiculoSQLDaoImp;
import com.TallerWapo.dominio.Puertos.baseDatos.Daos.vehiculoDao;

public class ContextoDaos {

    public static vehiculoDao getVehiculoDao() {
        return new VehiculoSQLDaoImp();
    }

}
