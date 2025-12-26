package com.TallerWapo.Adaptadores.BaseDatossql.daosImpl;

import com.TallerWapo.Adaptadores.BaseDatossql.daosImpl.base.DaoSQLBase;
import com.TallerWapo.dominio.BOs.Clientes.ClienteBO;
import com.TallerWapo.dominio.BOs.vehiculos.CitaBO;
import com.TallerWapo.dominio.BOs.vehiculos.VehiculoBO;
import com.TallerWapo.dominio.contexto.Sesion;
import com.TallerWapo.dominio.interfaces.Daos.CitasDao;
import com.TallerWapo.dominio.utiles.XmlUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitasSQLDaoImp extends DaoSQLBase implements CitasDao {
    private final String ARCHIVO_SQL = "sentenciasSQL/citasSQL.XML";

    private final String CITAS_SELECT_ALL = XmlUtil.loadSql(ARCHIVO_SQL, "CITAS_SELECT_ALL");
    private final String CITAS_SELECT_CLIENTEID = XmlUtil.loadSql(ARCHIVO_SQL, "CITAS_SELECT_CLIENTEID");
    private final String CITAS_SELECT_VEHICULOID = XmlUtil.loadSql(ARCHIVO_SQL, "CITAS_SELECT_VEHICULOID");
    private final String CITAS_INSERT = XmlUtil.loadSql(ARCHIVO_SQL, "CITAS_INSERT");
    private final String CITAS_UPDATE = XmlUtil.loadSql(ARCHIVO_SQL, "CITAS_UPDATE");
    private final String CITAS_DELETE = XmlUtil.loadSql(ARCHIVO_SQL, "CITAS_DELETE");

    public CitasSQLDaoImp(Sesion sesion) {
        super(sesion);
    }

    @Override
    public List<CitaBO> buscarTodas() throws Exception {
        List<CitaBO> list = new ArrayList<>();
        Statement stmt = conexion.createStatement();
        ResultSet rs = stmt.executeQuery(CITAS_SELECT_ALL);

        while (rs.next()) {
            list.add(mapearCita(rs));
        }

        return list;
    }

    @Override
    public List<CitaBO> buscarPorVehiculo(VehiculoBO vehiculo) throws Exception {
        List<CitaBO> list = new ArrayList<>();

        PreparedStatement ps = conexion.prepareStatement(CITAS_SELECT_VEHICULOID);
        ps.setInt(1, vehiculo.getUuid());

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(mapearCita(rs));
        }


        return list;
    }

    @Override
    public List<CitaBO> buscarPorCliente(ClienteBO cliente) throws Exception {
        List<CitaBO> list = new ArrayList<>();

        PreparedStatement ps = conexion.prepareStatement(CITAS_SELECT_CLIENTEID);
        ps.setInt(1, cliente.getUuid());

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(mapearCita(rs));
        }

        return list;
    }

    @Override
    public boolean guardarNueva(CitaBO cita) throws Exception {

        PreparedStatement ps = conexion.prepareStatement(CITAS_INSERT);

        setearCita(ps, cita);
        ps.execute();

        //TODO controlar que se inserta adecuadamente
        if (false) {
            throw new SQLException("No se pudo insertar la cita");
        }

        return true;
    }

    @Override
    public boolean actualizar(CitaBO cita) throws Exception {

        PreparedStatement ps = conexion.prepareStatement(CITAS_UPDATE);
        setearCita(ps, cita);

        int filas = ps.executeUpdate();

        if (filas == 0) {
            return false;
        }

        if (filas > 1) {
            throw new SQLException("Se actualizaron mas de una cita para el uuid: " + cita.getUuid());
        }

        return true;
    }

    @Override
    public boolean borrar(CitaBO cita) throws Exception {

        PreparedStatement ps = conexion.prepareStatement(CITAS_DELETE);
        ps.setInt(1, cita.getUuid());

        int filas = ps.executeUpdate();

        if (filas > 1) {
            throw new SQLException("Se borraron mas de una cita con uuid: " + cita.getUuid());
        }

        if (filas == 0) {
            return false;
        }

        return true;
    }

    private CitaBO mapearCita(ResultSet rs) throws SQLException {
        CitaBO cita = new CitaBO();

        cita.setUuid(rs.getInt("id"));
        cita.setVehiculoUuid(rs.getInt("vehiculo_id"));
        cita.setClienteUuid(rs.getInt("cliente_id"));
        cita.setReparacionUuid(rs.getInt("reparacion_id"));
        cita.setConcepto(rs.getString("concepto"));
        cita.setFecha(rs.getDate("fecha"));
        cita.setCodigoEstado(rs.getString("cod_estado"));

        return cita;
    }

    private void setearCita(PreparedStatement ps, CitaBO cita) throws SQLException {
        ps.setInt(1, cita.getVehiculoUuid());
        ps.setInt(2, cita.getClienteUuid());
        ps.setInt(3, cita.getReparacionUuid());
        ps.setString(4, cita.getConcepto());
        ps.setDate(5, new java.sql.Date(cita.getFecha().getTime()));
        ps.setString(6, cita.getCodigoEstado());
        ps.setInt(7, cita.getUuid());
    }
}
