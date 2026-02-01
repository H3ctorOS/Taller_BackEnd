package com.TallerWapo.Adaptadores.BaseDatossql.daosImpl;

import com.TallerWapo.Adaptadores.BaseDatossql.daosImpl.base.DaoSQLBase;
import com.TallerWapo.dominio.BOs.Clientes.ClienteBO;
import com.TallerWapo.dominio.BOs.gestion.EstadoBO;
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
    private final String VEHICULOS_SELECT_CLIENTE = XmlUtil.loadSql(ARCHIVO_SQL, "VEHICULOS_SELECT_CLIENTE");
    private final String VEHICULOS_SELECT_ID = XmlUtil.loadSql(ARCHIVO_SQL, "VEHICULOS_SELECT_ID");
    private final String VEHICULOS_INSERT = XmlUtil.loadSql(ARCHIVO_SQL, "VEHICULOS_INSERT");
    private final String VEHICULOS_UPDATE = XmlUtil.loadSql(ARCHIVO_SQL, "VEHICULOS_UPDATE");
    private final String VEHICULOS_DELETE = XmlUtil.loadSql(ARCHIVO_SQL, "VEHICULOS_DELETE");
    private final String VEHICULOS_PROPIETARIO_INSERT = XmlUtil.loadSql(ARCHIVO_SQL, "VEHICULOS_PROPIETARIO_INSERT");

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

    public List<VehiculoBO> buscarPorCliente(ClienteBO cliente) throws Exception {
        List<VehiculoBO> list = new ArrayList<>();

        PreparedStatement ps = conexion.prepareStatement(VEHICULOS_SELECT_CLIENTE);

        // aquí pasas el uuid del cliente
        ps.setInt(1, cliente.getUuid());

        ResultSet rs = ps.executeQuery();

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

        PreparedStatement ps = conexion.prepareStatement(VEHICULOS_INSERT,Statement.RETURN_GENERATED_KEYS);

        setearVehiculo(ps, vehiculo);
        int filas = ps.executeUpdate();

        if (filas == 0) {
            throw new SQLException("No se pudo insertar el vehiculo");
        }

        try (ResultSet rs = ps.getGeneratedKeys()) {
            if (rs.next()) {
                int idGenerado = rs.getInt(1);
                vehiculo.setUuid(idGenerado);  // Actualizamos el uuid del objeto
            } else {
                throw new SQLException("No se pudo obtener el id generado del vehiculo");
            }
        }

        return true;
    }


    @Override
    public boolean actualizar(VehiculoBO vehiculo) throws Exception {

        PreparedStatement ps = conexion.prepareStatement(VEHICULOS_UPDATE);
        setearVehiculo(ps, vehiculo);
        ps.setInt(6 , vehiculo.getUuid());

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

    @Override
    public boolean altaPropietario(VehiculoBO vehiculo, ClienteBO cliente, String observaciones) throws Exception {
        PreparedStatement ps = conexion.prepareStatement(VEHICULOS_PROPIETARIO_INSERT);

        ps.setInt(1, vehiculo.getUuid());          // vehiculo_id
        ps.setInt(2, cliente.getUuid());           // cliente_id
        ps.setString(3, "");                    // cod_estado
        ps.setString(4, observaciones);            // observacioenes

        int filas = ps.executeUpdate();

        if (filas == 0) {
            return false;
        }

        if (filas > 1) {
            throw new SQLException("Se insertaron varias relaciones propietario-vehiculo");
        }

        return true;
    }


    private VehiculoBO mapearVehiculo(ResultSet rs) throws SQLException {
        VehiculoBO vehiculo = new VehiculoBO();
        vehiculo.setMatricula(rs.getString("matricula"));
        vehiculo.setModelo(rs.getString("modelo"));
        vehiculo.setMarca(rs.getString("marca"));
        vehiculo.setEstado(EstadoBO.fromCodigo(rs.getString("cod_estado")));
        vehiculo.setUuid(rs.getInt("id"));
        vehiculo.setObservaciones(rs.getString("observaciones"));
        return vehiculo;
    }

    private void setearVehiculo(PreparedStatement ps,VehiculoBO vehiculo) throws SQLException {
        ps.setString(1, vehiculo.getMatricula());
        ps.setString(2, vehiculo.getModelo());
        ps.setString(3, vehiculo.getMarca());
        ps.setString(4, vehiculo.getEstado().getCodigo());
        ps.setString(5, vehiculo.getObservaciones());
    }
}