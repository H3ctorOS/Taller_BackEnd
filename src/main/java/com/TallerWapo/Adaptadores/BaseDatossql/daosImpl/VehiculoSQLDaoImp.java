package com.TallerWapo.Adaptadores.BaseDatossql.daosImpl;

import com.TallerWapo.Adaptadores.BaseDatossql.daosImpl.base.DaoSQLBase;
import com.TallerWapo.dominio.BOs.vehiculos.VehiculoBO;
import com.TallerWapo.dominio.contexto.Sesion;
import com.TallerWapo.dominio.interfaces.Daos.VehiculosDao;
import com.TallerWapo.dominio.utiles.XmlUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehiculoSQLDaoImp extends DaoSQLBase implements VehiculosDao {
    private final String ARCHIVO_SQL = "sentenciasSQL/vehiculosSQL.XML";

    private final String VEHICULOS_SELECT_ALL = XmlUtil.loadSql(ARCHIVO_SQL, "VEHICULOS_SELECT_ALL");
    private final String VEHICULOS_SELECT_MATRICULA = XmlUtil.loadSql(ARCHIVO_SQL, "VEHICULOS_SELECT_MATRICULA");
    private final String VEHICULOS_SELECT_ID = XmlUtil.loadSql(ARCHIVO_SQL, "VEHICULOS_SELECT_ID");
    private final String VEHICULOS_INSERT = XmlUtil.loadSql(ARCHIVO_SQL, "VEHICULOS_INSERT");
    private final String VEHICULOS_UPDATE = XmlUtil.loadSql(ARCHIVO_SQL, "VEHICULOS_UPDATE");
    private final String VEHICULOS_DELETE = XmlUtil.loadSql(ARCHIVO_SQL, "VEHICULOS_DELETE");

    public VehiculoSQLDaoImp(Sesion sesion) {
        super(sesion);
    }

    @Override
    public List<VehiculoBO> buscarTodos() throws Exception {
        List<VehiculoBO> list = new ArrayList<>();

        Statement stmt = conexion.createStatement();

        ResultSet rs = stmt.executeQuery(VEHICULOS_SELECT_ALL);

        while (rs.next()) {
            list.add(mapearVehiculo(rs));
        }

        return list;
    }

    @Override
    public VehiculoBO buscarPorMatricula(String matricula) throws Exception {
        List<VehiculoBO> list = new ArrayList<>();

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
    public VehiculoBO buscarPorId(int id) throws Exception {
        List<VehiculoBO> list = new ArrayList<>();

        PreparedStatement ps = conexion.prepareStatement(VEHICULOS_SELECT_ID);

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(mapearVehiculo(rs));
        }

        if (list.size() > 1) {
            throw  new SQLException("Se han encontrado varios vehiculos para el mismo id");
        }

        if (list.size() == 1) {
            return list.getFirst();
        }

        return null;
    }

    @Override
    public boolean guardarNuevo(VehiculoBO vehiculo) throws Exception {

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
    public boolean actualizar(VehiculoBO vehiculo) throws Exception {

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
    public boolean borrar(VehiculoBO vehiculo) throws Exception {

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
        vehiculo.setUuid(rs.getInt("id"));
        return vehiculo;
    }

    private void setearVehiculo(PreparedStatement ps,VehiculoBO vehiculo) throws SQLException {
        ps.setString(1, vehiculo.getMatricula());
        ps.setString(2, vehiculo.getModelo());
        ps.setString(3, vehiculo.getMarca());
        ps.setString(4, vehiculo.getCodidoEstado());
    }
}