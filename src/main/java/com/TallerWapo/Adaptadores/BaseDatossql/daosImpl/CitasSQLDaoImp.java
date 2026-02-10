package com.TallerWapo.Adaptadores.BaseDatossql.daosImpl;

import com.TallerWapo.Adaptadores.BaseDatossql.daosImpl.base.DaoSQLBase;
import com.TallerWapo.dominio.bo.contabilidad.GastoBO;
import com.TallerWapo.dominio.bo.contabilidad.IngresoBO;
import com.TallerWapo.dominio.bo.vehiculos.CitaBO;
import com.TallerWapo.dominio.bo.vehiculos.VehiculoBO;
import com.TallerWapo.dominio.contexto.Sesion;
import com.TallerWapo.dominio.factorias.ContextoDaos;
import com.TallerWapo.dominio.interfaces.Daos.CitasDao;
import com.TallerWapo.dominio.interfaces.Daos.GastoDao;
import com.TallerWapo.dominio.interfaces.Daos.IngresosDao;
import com.TallerWapo.dominio.utiles.XmlUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitasSQLDaoImp extends DaoSQLBase implements CitasDao {

    private final IngresosDao ingresosDao;
    private final GastoDao gastoDao;

    private final String ARCHIVO_SQL = "sentenciasSQL/citasSQL.XML";

    private final String CITAS_SELECT_ALL = XmlUtil.loadSql(ARCHIVO_SQL, "CITAS_SELECT_ALL");
    private final String CITAS_SELECT_VEHICULOID = XmlUtil.loadSql(ARCHIVO_SQL, "CITAS_SELECT_VEHICULOID");
    private final String CITAS_INSERT = XmlUtil.loadSql(ARCHIVO_SQL, "CITAS_INSERT");
    private final String CITAS_UPDATE = XmlUtil.loadSql(ARCHIVO_SQL, "CITAS_UPDATE");
    private final String CITAS_DELETE = XmlUtil.loadSql(ARCHIVO_SQL, "CITAS_DELETE");

    public CitasSQLDaoImp(Sesion sesion) {
        super(sesion);
        ingresosDao = ContextoDaos.getIngresoDao(sesion);
        gastoDao = ContextoDaos.getGastoDao(sesion);
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
    public boolean guardarNueva(CitaBO cita) throws Exception {

        PreparedStatement ps = conexion.prepareStatement(CITAS_INSERT);
        setearCita(ps, cita);
        ps.execute();


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

    private CitaBO mapearCita(ResultSet rs) throws Exception {
        CitaBO cita = new CitaBO();

        cita.setUuid(rs.getInt("id"));
        cita.setVehiculoUuid(rs.getInt("vehiculo_id"));
        cita.setConcepto(rs.getString("concepto"));
        cita.setFechaInicio(rs.getDate("fechaInicio"));
        cita.setFechaFinalizada(rs.getDate("fechaFinalizada"));
        cita.setCodigoEstado(rs.getString("cod_estado"));
        cita.setObservaciones(rs.getString("observaciones"));

        //Setear ingresos y gatos
        cita.setGastos(gastoDao.buscarPorCita(cita));
        cita.setIngresos(ingresosDao.buscarPorCita(cita));

        return cita;
    }

    private void setearCita(PreparedStatement ps, CitaBO cita) throws SQLException {
        ps.setInt(1, cita.getVehiculoUuid());
        ps.setString(2, cita.getConcepto());
        ps.setDate(3, new java.sql.Date(cita.getFechaInicio().getTime()));
        ps.setDate(4, new java.sql.Date(cita.getFechaFinalizada().getTime()));
        ps.setString(5, cita.getCodigoEstado());
        ps.setString(6, cita.getObservaciones());
    }
}
