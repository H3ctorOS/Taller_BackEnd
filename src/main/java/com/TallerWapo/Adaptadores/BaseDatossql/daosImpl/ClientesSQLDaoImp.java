package com.TallerWapo.Adaptadores.BaseDatossql.daosImpl;

import com.TallerWapo.Adaptadores.BaseDatossql.daosImpl.base.DaoSQLBase;
import com.TallerWapo.dominio.bo.Clientes.ClienteBO;
import com.TallerWapo.dominio.contexto.Sesion;
import com.TallerWapo.dominio.interfaces.Daos.ClientesDao;
import com.TallerWapo.dominio.utiles.XmlUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientesSQLDaoImp extends DaoSQLBase implements ClientesDao {
    private final String ARCHIVO_SQL = "sentenciasSQL/clientesSQL.XML";

    private final String CLIENTES_SELECT_ALL = XmlUtil.loadSql(ARCHIVO_SQL, "CLIENTES_SELECT_ALL");
    private final String CLIENTES_SELECT_DNI = XmlUtil.loadSql(ARCHIVO_SQL, "CLIENTES_SELECT_DNI");
    private final String CLIENTES_SELECT_id = XmlUtil.loadSql(ARCHIVO_SQL, "CLIENTES_SELECT_id");
    private final String CLIENTES_SELECT_NOMBRE = XmlUtil.loadSql(ARCHIVO_SQL, "CLIENTES_SELECT_NOMBRE");
    private final String CLIENTES_INSERT = XmlUtil.loadSql(ARCHIVO_SQL, "CLIENTES_INSERT");
    private final String CLIENTES_UPDATE = XmlUtil.loadSql(ARCHIVO_SQL, "CLIENTES_UPDATE");
    private final String CLIENTES_DELETE = XmlUtil.loadSql(ARCHIVO_SQL, "CLIENTES_DELETE");

    public ClientesSQLDaoImp(Sesion sesion) {
        super(sesion);
    }

    @Override
    public List<ClienteBO> buscarTodos() throws Exception {
        List<ClienteBO> list = new ArrayList<>();

        Statement stmt = conexion.createStatement();

        ResultSet rs = stmt.executeQuery(CLIENTES_SELECT_ALL);

        while (rs.next()) {
            list.add(mapearCliente(rs));
        }

        return list;
    }

    @Override
    public ClienteBO buscarPorDni(String dni) throws Exception {
        List<ClienteBO> list = new ArrayList<>();

        PreparedStatement ps = conexion.prepareStatement(CLIENTES_SELECT_DNI);

        ps.setString(1, dni);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(mapearCliente(rs));
        }

        if (list.size() > 1) {
            throw  new SQLException("Se han encontrado varios clientes para el mismo dni") ;
        }

        if (list.size() == 1) {
            return list.getFirst();
        }

        return null;
    }

    @Override
    public List<ClienteBO> buscarPorNombre(String nombre) throws Exception {
        List<ClienteBO> list = new ArrayList<>();

        PreparedStatement ps = conexion.prepareStatement(CLIENTES_SELECT_NOMBRE);

        ps.setString(1, nombre);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(mapearCliente(rs));
        }

        return list;
    }

    @Override
    public ClienteBO buscarPorId(int id) throws Exception {
        List<ClienteBO> list = new ArrayList<>();

        PreparedStatement ps = conexion.prepareStatement(CLIENTES_SELECT_id);

        ps.setInt(1,id);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(mapearCliente(rs));
        }

        if (list.size() > 1) {
            throw  new SQLException("Se han encontrado varios clientes para el mismo dni") ;
        }

        if (list.size() == 1) {
            return list.getFirst();
        }

        return null;
    }

    @Override
    public boolean guardarNuevo(ClienteBO cliente) throws Exception {
        PreparedStatement ps = conexion.prepareStatement(CLIENTES_INSERT);

        setearCliente(ps, cliente);

        ps.execute();

        return true;
    }

    @Override
    public boolean actualizar(ClienteBO cliente) throws Exception {

        PreparedStatement ps = conexion.prepareStatement(CLIENTES_UPDATE);
        setearCliente(ps, cliente);
        ps.setInt(9, cliente.getUuid());

        int filas = ps.executeUpdate();

        if (filas == 0) {
            return false;
        }

        if (filas > 1) {
            throw new SQLException("Se actualiaron mas de un vehiculo con el mismo id " + cliente.getUuid());
        }

        return true;
    }

    @Override
    public boolean borrar(ClienteBO cliente) throws Exception {

        PreparedStatement ps = conexion.prepareStatement(CLIENTES_DELETE);
        ps.setInt(1, cliente.getUuid());

        int filas = ps.executeUpdate();

        if (filas > 1) {
            throw new SQLException("Se borraron mas de un cliente con uuid: " + cliente.getUuid());
        }

        if (filas == 0) {
            return false;
        }

        return true;
    }

    private ClienteBO mapearCliente(ResultSet rs) throws SQLException {
        ClienteBO cliente = new ClienteBO();

        cliente.setUuid(rs.getInt("id"));
        cliente.setDni(rs.getString("dni"));
        cliente.setNombre(rs.getString("nombre"));
        cliente.setApellidos(rs.getString("apellidos"));
        cliente.setDireccion(rs.getString("direccion"));
        cliente.setTelefono(rs.getInt("telefono"));
        cliente.setEmail(rs.getString("email"));
        cliente.setEstado(rs.getString("cod_estado"));
        cliente.setObservaciones(rs.getString("observaciones"));

        return cliente;
    }

    private void setearCliente(PreparedStatement ps, ClienteBO cliente) throws SQLException {
        ps.setString(1, cliente.getDni());
        ps.setString(2, cliente.getNombre());
        ps.setString(3, cliente.getApellidos());
        ps.setString(4, cliente.getDireccion());
        ps.setInt(5, cliente.getTelefono());
        ps.setString(6, cliente.getEmail());
        ps.setString(7, cliente.getEstado());
        ps.setString(8, cliente.getObservaciones());
    }
}
