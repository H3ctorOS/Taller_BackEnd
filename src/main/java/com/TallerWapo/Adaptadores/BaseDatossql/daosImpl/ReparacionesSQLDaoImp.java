package com.TallerWapo.Adaptadores.BaseDatossql.daosImpl;

import com.TallerWapo.dominio.BOs.vehiculos.ReparacionBO;
import com.TallerWapo.dominio.BOs.vehiculos.VehiculoBO;
import com.TallerWapo.dominio.interfaces.Daos.ReparacionesDao;
import com.TallerWapo.dominio.interfaces.base.DaoSQLBase;
import com.TallerWapo.dominio.utiles.XmlUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReparacionesSQLDaoImp extends DaoSQLBase implements ReparacionesDao {
    private final String ARCHIVO_SQL = "sentenciasSQL/reparacionesSQL.XML";

    private final String REPARACIONES_SELECT_ALL = XmlUtil.loadSql(ARCHIVO_SQL, "REPARACIONES_SELECT_ALL");
    private final String REPARACIONES_SELECT_VEHICULO = XmlUtil.loadSql(ARCHIVO_SQL, "REPARACIONES_SELECT_VEHICULO");
    private final String REPARACIONES_INSERT = XmlUtil.loadSql(ARCHIVO_SQL, "REPARACIONES_INSERT");
    private final String REPARACIONES_UPDATE = XmlUtil.loadSql(ARCHIVO_SQL, "REPARACIONES_UPDATE");
    private final String REPARACIONES_DELETE = XmlUtil.loadSql(ARCHIVO_SQL, "REPARACIONES_DELETE");

    @Override
    public List<ReparacionBO> buscarTodas() throws Exception {
        List<ReparacionBO> list = new ArrayList<>();

        Statement stmt = conexion.createStatement();

        ResultSet rs = stmt.executeQuery(REPARACIONES_SELECT_ALL);

        while (rs.next()) {
            list.add(mapearReparacion(rs));
        }

        return list;
    }

    @Override
    public List<ReparacionBO> buscarPorVehiculo(VehiculoBO vehiculo) throws Exception {
        List<ReparacionBO> list = new ArrayList<>();

        PreparedStatement ps = conexion.prepareStatement(REPARACIONES_SELECT_VEHICULO);

        ps.setString(1, vehiculo.getMatricula());

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(mapearReparacion(rs));
        }

        return list;
    }

    @Override
    public boolean guardarNueva(ReparacionBO reparacion) throws Exception {

        PreparedStatement ps = conexion.prepareStatement(REPARACIONES_INSERT);

        setearReparacion(ps, reparacion);
        ps.execute();

        //TODO controlar que se inserta adecuadamente
        if (false) {
            throw new SQLException("No se pudo insertar la reparacion");
        }

        return true;
    }

    @Override
    public boolean actualizar(ReparacionBO reparacion) throws Exception {

        PreparedStatement ps = conexion.prepareStatement(REPARACIONES_UPDATE);
        setearReparacion(ps, reparacion);

        int filas = ps.executeUpdate();

        if (filas == 0) {
            return false;
        }

        if (filas > 1) {
            throw new SQLException("Se actualiaron mas de una reparacion con el mismo id " + reparacion.getUuid());
        }

        return true;
    }

    @Override
    public boolean borrar(ReparacionBO reparacion) throws Exception {

        PreparedStatement ps = conexion.prepareStatement(REPARACIONES_DELETE);
        ps.setInt(1, reparacion.getUuid());

        int filas = ps.executeUpdate();

        if (filas > 1) {
            throw new SQLException("Se borraron mas de una reparacion con el uuid: " + reparacion.getUuid());
        }

        if (filas == 0) {
            return false;
        }

        return true;
    }

    private ReparacionBO mapearReparacion(ResultSet rs) throws SQLException {
        ReparacionBO reparacion = new ReparacionBO();

        reparacion.setUuid(rs.getInt("id"));
        reparacion.setConcepto(rs.getString("concepto"));
        reparacion.setVehiculoUuid(rs.getInt("vehiculo_id"));
        reparacion.setMecanicoUuid(rs.getInt("mecanico_id"));
        reparacion.setFechaInicio(rs.getDate("fecha_inicio"));
        reparacion.setFechaFin(rs.getDate("fecha_fin"));

        return reparacion;
    }

    private void setearReparacion(PreparedStatement ps, ReparacionBO reparacion) throws SQLException {
        ps.setString(1, reparacion.getConcepto());
        ps.setInt(2, reparacion.getVehiculoUuid());
        ps.setInt(3, reparacion.getMecanicoUuid());
        ps.setDate(4, new java.sql.Date(reparacion.getFechaInicio().getTime()));
        ps.setDate(5, new java.sql.Date(reparacion.getFechaFin().getTime()));
    }
}
