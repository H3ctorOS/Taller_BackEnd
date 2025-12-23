package com.TallerWapo.Adaptadores.BaseDatossql.daosImpl;

import com.TallerWapo.dominio.BOs.vehiculos.VehiculoBO;
import com.TallerWapo.dominio.Puertos.baseDatos.Daos.vehiculoDao;
import com.TallerWapo.dominio.contexto.ContextoGeneral;
import com.TallerWapo.dominio.utiles.PropertiesUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VehiculoSQLDaoImp implements vehiculoDao {

    private final Connection conexion = ContextoGeneral.baseDatosSQL.getConexion();

    private final String VEHICULOS_SELECT_ALL = PropertiesUtils.getString("sentenciasSQL/vehiculosSQL.properties","VEHICULOS_SELECT_ALL",null);

    @Override
    public List<VehiculoBO> findAll() throws Exception {
        List<VehiculoBO> list = new ArrayList<>();

        Statement stmt = conexion.createStatement();

        ResultSet rs = stmt.executeQuery(VEHICULOS_SELECT_ALL);{  // Asume tabla vehiculos
            while (rs.next()) {
                VehiculoBO v = new VehiculoBO();
                v.setMarca(rs.getString("marca"));
                v.setMatricula(rs.getString("matricula"));
                v.setModelo(rs.getString("modelo"));
                list.add(v);
            }
        }
        return list;
    }

    @Override
    public VehiculoBO findByMatricula(String matricula) throws Exception {
        List<VehiculoBO> list = new ArrayList<>();

        Statement stmt = conexion.createStatement();

        //TODO colocar en un properties
        String query = "SELECT * FROM vehiculos where matricula ='" + matricula + "'";

        ResultSet rs = stmt.executeQuery(query);{  // Asume tabla vehiculos
            while (rs.next()) {
                VehiculoBO v = new VehiculoBO();
                v.setMarca(rs.getString("marca"));
                v.setMatricula(rs.getString("matricula"));
                v.setModelo(rs.getString("modelo"));
                list.add(v);
            }
        }

        if (list.size() > 1) {
            throw  new SQLException("Se han encontrado varios vehiculos para la misma matricula") ;
        }
        if (list.size() == 1) {
            return list.get(0);
        }

        return null;
    }

    @Override
    public void guardarNuevoVehiculo(VehiculoBO vehiculo) throws Exception {

    }

    @Override
    public void actualizarVehiculo(VehiculoBO vehiculo) throws Exception {

    }

    @Override
    public void borrarVehiculo(VehiculoBO vehiculo) throws Exception {

    }
}