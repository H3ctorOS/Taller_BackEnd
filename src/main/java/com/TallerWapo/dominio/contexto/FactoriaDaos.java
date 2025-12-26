package com.TallerWapo.dominio.contexto;

import com.TallerWapo.Adaptadores.BaseDatossql.daosImpl.ClientesSQLDaoImp;
import com.TallerWapo.Adaptadores.BaseDatossql.daosImpl.VehiculoSQLDaoImp;
import com.TallerWapo.dominio.interfaces.Daos.ClientesDao;
import com.TallerWapo.dominio.interfaces.Daos.VehiculosDao;

import java.sql.Connection;

public class FactoriaDaos {

    public static VehiculosDao getVehiculoDao(Connection conexion) {
        return new VehiculoSQLDaoImp(conexion);
    }
    public static ClientesDao getClienteDao(Connection conexion) {return new ClientesSQLDaoImp();}

}
