package com.TallerWapo.Adaptadores.BaseDatossql.daosImpl;

import com.TallerWapo.dominio.BOs.vehiculos.VehiculoBO;
import com.TallerWapo.dominio.Puertos.baseDatos.Daos.vehiculoDao;
import com.TallerWapo.dominio.contexto.ContextoGeneral;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VehiculoSQLDaoImp implements vehiculoDao {

    private final Connection conn = ContextoGeneral.baseDatosSQL.getConnection();

    @Override
    public List<VehiculoBO> findAll() throws Exception {

        List<VehiculoBO> list = new ArrayList<>();

        Statement stmt = conn.createStatement();

        //TODO colocar en un properties
        String query = "SELECT * FROM vehiculos";

        ResultSet rs = stmt.executeQuery(query);{  // Asume tabla vehiculos
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

        Statement stmt = conn.createStatement();

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
    public void save(VehiculoBO vehiculo) throws Exception {

    }

    @Override
    public void update(VehiculoBO vehiculo) throws Exception {

    }

    @Override
    public void deleteByMatricula(String matricula) throws Exception {

    }

}