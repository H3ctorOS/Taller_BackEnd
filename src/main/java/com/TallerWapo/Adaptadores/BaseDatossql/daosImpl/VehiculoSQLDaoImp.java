package com.TallerWapo.Adaptadores.BaseDatossql.daosImpl;

import com.TallerWapo.dominio.BOs.vehiculos.VehiculoBO;
import com.TallerWapo.dominio.Puertos.baseDatos.Daos.vehiculoDao;
import com.TallerWapo.dominio.contexto.ContextoGeneral;
import com.TallerWapo.dominio.utiles.PropertiesUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehiculoSQLDaoImp implements vehiculoDao {
    private final String ARCHIVO_SENTENCIAS_SQL = "sentenciasSQL/vehiculosSQL.properties";


    private final String VEHICULOS_SELECT_ALL = PropertiesUtils.getString(ARCHIVO_SENTENCIAS_SQL,"VEHICULOS_SELECT_ALL",null);
    private final String VEHICULOS_SELECT_MATRICULA = PropertiesUtils.getString(ARCHIVO_SENTENCIAS_SQL,"VEHICULOS_SELECT_MATRICULA",null);

    private final String VEHICULOS_INSERT = PropertiesUtils.getString(ARCHIVO_SENTENCIAS_SQL,"VEHICULOS_INSERT",null);
    private final String VEHICULOS_UPDATE = PropertiesUtils.getString(ARCHIVO_SENTENCIAS_SQL,"VEHICULOS_INSERT",null);
    private final String VEHICULOS_DELETE = PropertiesUtils.getString(ARCHIVO_SENTENCIAS_SQL,"VEHICULOS_DELETE",null);

    @Override
    public List<VehiculoBO> findAll() throws Exception {
        List<VehiculoBO> list = new ArrayList<>();

        Connection conexion = ContextoGeneral.baseDatosSQL.getConexion();
        Statement stmt = conexion.createStatement();

        ResultSet rs = stmt.executeQuery(VEHICULOS_SELECT_ALL);

        while (rs.next()) {
            list.add(mapearVehiculo(rs));
        }

        return list;
    }

    @Override
    public VehiculoBO findByMatricula(String matricula) throws Exception {
        List<VehiculoBO> list = new ArrayList<>();

        Connection conexion = ContextoGeneral.baseDatosSQL.getConexion();
        PreparedStatement ps = conexion.prepareStatement(VEHICULOS_SELECT_MATRICULA);

        ps.setString(1, matricula);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(mapearVehiculo(rs));
        }

        if (list.size() > 1) {
            throw  new SQLException("Se han encontrado varios vehiculos para la misma matricula") ;
        }

        if (list.size() == 1) {
            return list.getFirst();
        }

        return null;
    }

    @Override
    public boolean guardarNuevoVehiculo(VehiculoBO vehiculo) throws Exception {

        Connection conexion = ContextoGeneral.baseDatosSQL.getConexion();
        PreparedStatement ps = conexion.prepareStatement(VEHICULOS_INSERT);

        setearVehiculo(ps, vehiculo);
        ps.execute();

        //TODO controlar que se inserta adecuadamente
        if (false) {
            throw new SQLException("No se pudo insertar el vehiculo");
        }

        return true;
    }

    @Override
    public boolean actualizarVehiculo(VehiculoBO vehiculo) throws Exception {

        Connection conexion = ContextoGeneral.baseDatosSQL.getConexion();
        PreparedStatement ps = conexion.prepareStatement(VEHICULOS_UPDATE);
        setearVehiculo(ps, vehiculo);

        int filas = ps.executeUpdate();

        if (filas == 0) {
            return false;
        }

        if (filas > 1) {
            throw new SQLException("Se actualiaron mas de un vehiculo con uuid: " + vehiculo.getUuid());
        }

        return true;
    }

    @Override
    public boolean borrarVehiculo(VehiculoBO vehiculo) throws Exception {

        Connection conexion = ContextoGeneral.baseDatosSQL.getConexion();
        PreparedStatement ps = conexion.prepareStatement(VEHICULOS_DELETE);
        ps.setInt(1, vehiculo.getUuid());

        int filas = ps.executeUpdate();

        if (filas > 1) {
            throw new SQLException("Se borraron mas de un vehiculo con uuid: " + vehiculo.getUuid());
        }

        if (filas == 0) {
            return false;
        }

        return true;
    }

    private VehiculoBO mapearVehiculo(ResultSet rs) throws SQLException {
        VehiculoBO vehiculo = new VehiculoBO();
        vehiculo.setMatricula(rs.getString("matricula"));
        vehiculo.setModelo(rs.getString("modelo"));
        vehiculo.setMarca(rs.getString("marca"));
        vehiculo.setCodidoEstado(rs.getString("cod_estado"));
        return vehiculo;
    }

    private void setearVehiculo(PreparedStatement ps,VehiculoBO vehiculo) throws SQLException {
        ps.setString(1, vehiculo.getMatricula());
        ps.setString(2, vehiculo.getModelo());
        ps.setString(3, vehiculo.getMarca());
        ps.setString(4, vehiculo.getCodidoEstado());
    }
}
