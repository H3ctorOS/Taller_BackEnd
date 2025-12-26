package com.TallerWapo.dominio.contexto;

import com.TallerWapo.Adaptadores.BaseDatossql.daosImpl.ClientesSQLDaoImp;
import com.TallerWapo.Adaptadores.BaseDatossql.daosImpl.VehiculoSQLDaoImp;
import com.TallerWapo.dominio.interfaces.Daos.ClientesDao;
import com.TallerWapo.dominio.interfaces.Daos.VehiculosDao;

public class ContextoDaos {

    public static VehiculosDao getVehiculoDao() {
        return new VehiculoSQLDaoImp();
    }
    public static ClientesDao getClienteDao() {
        return new ClientesSQLDaoImp();
    }

}
