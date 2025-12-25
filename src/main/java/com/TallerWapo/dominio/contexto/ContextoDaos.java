package com.TallerWapo.dominio.contexto;

import com.TallerWapo.Adaptadores.BaseDatossql.daosImpl.VehiculoSQLDaoImp;
import com.TallerWapo.dominio.interfaces.Daos.vehiculosDao;

public class ContextoDaos {

    public static vehiculosDao getVehiculoDao() {
        return new VehiculoSQLDaoImp();
    }

}
