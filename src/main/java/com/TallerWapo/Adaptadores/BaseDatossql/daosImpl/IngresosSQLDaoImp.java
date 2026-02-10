package com.TallerWapo.Adaptadores.BaseDatossql.daosImpl;

import com.TallerWapo.Adaptadores.BaseDatossql.daosImpl.base.DaoSQLBase;
import com.TallerWapo.dominio.bo.vehiculos.CitaBO;
import com.TallerWapo.dominio.bo.contabilidad.IngresoBO;
import com.TallerWapo.dominio.contexto.Sesion;
import com.TallerWapo.dominio.interfaces.Daos.IngresosDao;
import com.TallerWapo.dominio.utiles.XmlUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngresosSQLDaoImp extends DaoSQLBase implements IngresosDao {

    private final String ARCHIVO_SQL = "sentenciasSQL/ingresosSQL.XML";

    private final String INGRESOS_SELECT_ALL = XmlUtil.loadSql(ARCHIVO_SQL, "INGRESOS_SELECT_ALL");
    private final String INGRESOS_SELECT_CITAID = XmlUtil.loadSql(ARCHIVO_SQL, "INGRESOS_SELECT_CITAID");
    private final String INGRESOS_INSERT = XmlUtil.loadSql(ARCHIVO_SQL, "INGRESOS_INSERT");
    private final String INGRESOS_UPDATE = XmlUtil.loadSql(ARCHIVO_SQL, "INGRESOS_UPDATE");
    private final String INGRESOS_DELETE = XmlUtil.loadSql(ARCHIVO_SQL, "INGRESOS_DELETE");

    private final String INGRESOS_INSERT_CITA_RELACION = XmlUtil.loadSql(ARCHIVO_SQL, "INGRESOS_INSERT_CITA_RELACION");

    public IngresosSQLDaoImp(Sesion sesion) {
        super(sesion);
    }

    @Override
    public List<IngresoBO> buscarTodos() throws Exception {
        List<IngresoBO> list = new ArrayList<>();
        Statement stmt = conexion.createStatement();
        ResultSet rs = stmt.executeQuery(INGRESOS_SELECT_ALL);

        while (rs.next()) {
            list.add(mapearIngreso(rs));
        }

        return list;
    }

    @Override
    public List<IngresoBO> buscarPorCita(CitaBO cita) throws Exception {
        List<IngresoBO> list = new ArrayList<>();

        PreparedStatement ps = conexion.prepareStatement(INGRESOS_SELECT_CITAID);
        ps.setInt(1, cita.getUuid());

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(mapearIngreso(rs));
        }

        return list;
    }

    @Override
    public boolean guardarNuevo(IngresoBO ingreso) throws Exception {
        PreparedStatement ps = conexion.prepareStatement(INGRESOS_INSERT, Statement.RETURN_GENERATED_KEYS);
        setearIngreso(ps, ingreso);
        ps.executeUpdate();

        // Obtener el id generado
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            ingreso.setUuid(rs.getInt(1));
        } else {
            throw new SQLException("No se pudo insertar el ingreso");
        }

        return true;
    }

    @Override
    public boolean guardarRelacionCita(IngresoBO ingreso, CitaBO cita) throws Exception {
        if (ingreso == null || cita == null) {
            throw new IllegalArgumentException("Ingreso y Cita no pueden ser nulos");
        }

        try (PreparedStatement ps = conexion.prepareStatement(INGRESOS_INSERT_CITA_RELACION)) {
            ps.setInt(1, ingreso.getUuid()); // movimiento_id
            ps.setInt(2, cita.getUuid());    // entidad_id
            ps.setString(3, ingreso.getObservaciones()); // observaciones, opcional

            int filas = ps.executeUpdate();

            //Confirmar que se inserto algo
            return filas > 0;
        }

    }

    @Override
    public boolean actualizar(IngresoBO ingreso) throws Exception {
        PreparedStatement ps = conexion.prepareStatement(INGRESOS_UPDATE);
        setearIngreso(ps, ingreso);
        ps.setInt(6, ingreso.getUuid());

        int filas = ps.executeUpdate();
        if (filas == 0) return false;
        if (filas > 1) throw new SQLException("Se actualizaron más de un ingreso con uuid: " + ingreso.getUuid());
        return true;
    }

    @Override
    public boolean borrar(IngresoBO ingreso) throws Exception {
        PreparedStatement ps = conexion.prepareStatement(INGRESOS_DELETE);
        ps.setInt(1, ingreso.getUuid());

        int filas = ps.executeUpdate();
        if (filas == 0) return false;
        if (filas > 1) throw new SQLException("Se borraron más de un ingreso con uuid: " + ingreso.getUuid());
        return true;
    }





    public IngresoBO mapearIngreso(ResultSet rs) throws SQLException {
        IngresoBO ingreso = new IngresoBO();
        ingreso.setUuid(rs.getInt("id"));
        ingreso.setConcepto(rs.getString("concepto"));
        ingreso.setImporte(rs.getDouble("importe"));
        ingreso.setFecha(rs.getDate("fecha"));
        ingreso.setCodEstado(rs.getString("cod_estado"));
        ingreso.setObservaciones(rs.getString("observaciones"));
        return ingreso;
    }

    private void setearIngreso(PreparedStatement ps, IngresoBO ingreso) throws SQLException {
        ps.setString(1, ingreso.getConcepto());
        ps.setDouble(2, ingreso.getImporte());
        ps.setDate(3, new java.sql.Date(ingreso.getFecha().getTime()));
        ps.setString(4, ingreso.getCodEstado());
        ps.setString(5, ingreso.getObservaciones());
    }
}
