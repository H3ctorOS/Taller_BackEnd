package com.TallerWapo.Adaptadores.BaseDatossql.daosImpl;

import com.TallerWapo.dominio.BOs.Clientes.ClienteBO;
import com.TallerWapo.dominio.contexto.ContextoGeneral;
import com.TallerWapo.dominio.interfaces.Daos.ClientesDao;
import com.TallerWapo.dominio.utiles.XmlUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientesSQLDaoImp implements ClientesDao {
    private final String ARCHIVO_SQL = "sentenciasSQL/clientesSQL.XML";

    private final String CLIENTES_SELECT_ALL = XmlUtil.loadSql(ARCHIVO_SQL, "CLIENTES_SELECT_ALL");
    private final String CLIENTES_SELECT_DNI = XmlUtil.loadSql(ARCHIVO_SQL, "CLIENTES_SELECT_DNI");
    private final String CLIENTES_INSERT = XmlUtil.loadSql(ARCHIVO_SQL, "CLIENTES_INSERT");
    private final String CLIENTES_UPDATE = XmlUtil.loadSql(ARCHIVO_SQL, "CLIENTES_UPDATE");
    private final String CLIENTES_DELETE = XmlUtil.loadSql(ARCHIVO_SQL, "CLIENTES_DELETE");

    @Override
    public List<ClienteBO> buscarTodos() throws Exception {
        List<ClienteBO> list = new ArrayList<>();

        Connection conexion = ContextoGeneral.baseDatosSQL.getConexion();
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

        Connection conexion = ContextoGeneral.baseDatosSQL.getConexion();
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
    public boolean guardarNuevo(ClienteBO cliente) throws Exception {

        Connection conexion = ContextoGeneral.baseDatosSQL.getConexion();
        PreparedStatement ps = conexion.prepareStatement(CLIENTES_INSERT);

        setearCliente(ps, cliente);
        ps.execute();

        //TODO controlar que se inserta adecuadamente
        if (false) {
            throw new SQLException("No se pudo insertar el cliente");
        }

        return true;
    }

    @Override
    public boolean actualizar(ClienteBO cliente) throws Exception {

        Connection conexion = ContextoGeneral.baseDatosSQL.getConexion();
        PreparedStatement ps = conexion.prepareStatement(CLIENTES_UPDATE);
        setearCliente(ps, cliente);

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

        Connection conexion = ContextoGeneral.baseDatosSQL.getConexion();
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
        cliente.setEstado(rs.getString("estado"));

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
    }
}
