package com.TallerWapo.Adaptadores.BaseDatossql.daosImpl;

import com.TallerWapo.Adaptadores.BaseDatossql.daosImpl.base.DaoSQLBase;
import com.TallerWapo.dominio.bo.vehiculos.CitaBO;
import com.TallerWapo.dominio.bo.contabilidad.GastoBO;
import com.TallerWapo.dominio.contexto.Sesion;
import com.TallerWapo.dominio.interfaces.Daos.GastoDao;
import com.TallerWapo.dominio.utiles.XmlUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GastosSQLDaoImp extends DaoSQLBase implements GastoDao {

    private final String ARCHIVO_SQL = "sentenciasSQL/gastosSQL.XML";

    private final String GASTOS_SELECT_ALL = XmlUtil.loadSql(ARCHIVO_SQL, "GASTOS_SELECT_ALL");
    private final String GASTOS_SELECT_CITAID = XmlUtil.loadSql(ARCHIVO_SQL, "GASTOS_SELECT_CITAID");
    private final String GASTOS_INSERT = XmlUtil.loadSql(ARCHIVO_SQL, "GASTOS_INSERT");
    private final String GASTOS_UPDATE = XmlUtil.loadSql(ARCHIVO_SQL, "GASTOS_UPDATE");
    private final String GASTOS_DELETE = XmlUtil.loadSql(ARCHIVO_SQL, "GASTOS_DELETE");
    private final String GASTOS_INSERT_CITA_RELACION = XmlUtil.loadSql(ARCHIVO_SQL, "GASTOS_INSERT_CITA_RELACION");

    public GastosSQLDaoImp(Sesion sesion) {
        super(sesion);
    }

    @Override
    public List<GastoBO> buscarTodos() throws Exception {
        List<GastoBO> list = new ArrayList<>();
        Statement stmt = conexion.createStatement();
        ResultSet rs = stmt.executeQuery(GASTOS_SELECT_ALL);

        while (rs.next()) {
            list.add(mapearGasto(rs));
        }

        return list;
    }

    @Override
    public List<GastoBO> buscarPorCita(CitaBO cita) throws Exception {
        List<GastoBO> list = new ArrayList<>();

        PreparedStatement ps = conexion.prepareStatement(GASTOS_SELECT_CITAID);
        ps.setInt(1, cita.getUuid());

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(mapearGasto(rs));
        }

        return list;
    }

    @Override
    public boolean guardarNuevo(GastoBO gasto) throws Exception {
        PreparedStatement ps = conexion.prepareStatement(GASTOS_INSERT, Statement.RETURN_GENERATED_KEYS);
        setearGasto(ps, gasto);
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            gasto.setUuid(rs.getInt(1));
        } else {
            throw new SQLException("No se pudo insertar el gasto");
        }

        return true;
    }

    @Override
    public boolean guardarRelacionCita(GastoBO gasto, CitaBO cita) throws Exception {
        if (gasto == null || cita == null) {
            throw new IllegalArgumentException("Gasto y Cita no pueden ser nulos");
        }

        try (PreparedStatement ps = conexion.prepareStatement(GASTOS_INSERT_CITA_RELACION)) {
            ps.setInt(1, gasto.getUuid());
            ps.setInt(2, cita.getUuid());
            ps.setString(3, gasto.getObservaciones());

            int filas = ps.executeUpdate();
            return filas > 0;
        }
    }

    @Override
    public boolean actualizar(GastoBO gasto) throws Exception {
        PreparedStatement ps = conexion.prepareStatement(GASTOS_UPDATE);
        setearGasto(ps, gasto);
        ps.setInt(5, gasto.getUuid());

        int filas = ps.executeUpdate();
        if (filas == 0) return false;
        if (filas > 1) throw new SQLException("Se actualizaron más de un gasto con uuid: " + gasto.getUuid());
        return true;
    }

    @Override
    public boolean borrar(GastoBO gasto) throws Exception {
        PreparedStatement ps = conexion.prepareStatement(GASTOS_DELETE);
        ps.setInt(1, gasto.getUuid());

        int filas = ps.executeUpdate();
        if (filas == 0) return false;
        if (filas > 1) throw new SQLException("Se borraron más de un gasto con uuid: " + gasto.getUuid());
        return true;
    }

    private GastoBO mapearGasto(ResultSet rs) throws SQLException {
        GastoBO gasto = new GastoBO();
        gasto.setUuid(rs.getInt("id"));
        gasto.setDescripcion(rs.getString("descripcion"));
        gasto.setImporte(rs.getDouble("importe"));
        gasto.setFecha(rs.getDate("fecha"));
        gasto.setObservaciones(rs.getString("observaciones"));
        return gasto;
    }

    private void setearGasto(PreparedStatement ps, GastoBO gasto) throws SQLException {
        ps.setString(1, gasto.getDescripcion());
        ps.setDouble(2, gasto.getImporte());
        ps.setDate(3, new java.sql.Date(gasto.getFecha().getTime()));
        ps.setString(4, gasto.getObservaciones());
    }
}
