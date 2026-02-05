package com.TallerWapo.dominio.factorias;

import com.TallerWapo.Adaptadores.BaseDatossql.daosImpl.*;
import com.TallerWapo.dominio.contexto.Sesion;
import com.TallerWapo.dominio.interfaces.Daos.*;

public class ContextoDaos {

    public static VehiculosDao getVehiculoDao(Sesion sesion) {
        return new VehiculoSQLDaoImp(sesion);
    }
    public static ClientesDao getClienteDao(Sesion sesion) {
        return new ClientesSQLDaoImp(sesion);
    }
    public static CitasDao getCitaDao(Sesion sesion) {return new CitasSQLDaoImp(sesion);}
    public static IngresosDao getIngresoDao(Sesion sesion) {return new IngresosSQLDaoImp(sesion);}
    public static GastoDao getGastoDao(Sesion sesion) {return new GastosSQLDaoImp(sesion);}
    public static ReparacionesDao getReparacionesDao(Sesion sesion) {return new ReparacionesSQLDaoImp(sesion);}
}
