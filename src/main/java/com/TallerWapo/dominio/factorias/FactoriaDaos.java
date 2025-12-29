package com.TallerWapo.dominio.factorias;

import com.TallerWapo.Adaptadores.BaseDatossql.daosImpl.CitasSQLDaoImp;
import com.TallerWapo.Adaptadores.BaseDatossql.daosImpl.ClientesSQLDaoImp;
import com.TallerWapo.Adaptadores.BaseDatossql.daosImpl.ReparacionesSQLDaoImp;
import com.TallerWapo.Adaptadores.BaseDatossql.daosImpl.VehiculoSQLDaoImp;
import com.TallerWapo.dominio.contexto.Sesion;
import com.TallerWapo.dominio.interfaces.Daos.CitasDao;
import com.TallerWapo.dominio.interfaces.Daos.ClientesDao;
import com.TallerWapo.dominio.interfaces.Daos.ReparacionesDao;
import com.TallerWapo.dominio.interfaces.Daos.VehiculosDao;

public class FactoriaDaos {

    public static VehiculosDao getVehiculoDao(Sesion sesion) {
        return new VehiculoSQLDaoImp(sesion);
    }
    public static ClientesDao getClienteDao(Sesion sesion) {
        return new ClientesSQLDaoImp(sesion);
    }
    public static CitasDao getCitaDao(Sesion sesion) {return new CitasSQLDaoImp(sesion);}
    public static ReparacionesDao getReparacionesDao(Sesion sesion) {return new ReparacionesSQLDaoImp(sesion);}
}
