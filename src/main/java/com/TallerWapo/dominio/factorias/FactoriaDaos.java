package com.TallerWapo.dominio.factorias;

import com.TallerWapo.Adaptadores.BaseDatossql.daosImpl.ClientesSQLDaoImp;
import com.TallerWapo.Adaptadores.BaseDatossql.daosImpl.VehiculoSQLDaoImp;
import com.TallerWapo.dominio.contexto.Sesion;
import com.TallerWapo.dominio.interfaces.Daos.ClientesDao;
import com.TallerWapo.dominio.interfaces.Daos.VehiculosDao;

public class FactoriaDaos {

    public static VehiculosDao getVehiculoDao(Sesion sesion) {
        return new VehiculoSQLDaoImp(sesion);
    }

    public static ClientesDao getClienteDao(Sesion sesion) {
        return new ClientesSQLDaoImp(sesion);
    }

}
