package com.TallerWapo.dominio.servicios;

import com.TallerWapo.dominio.BOs.Clientes.ClienteBO;
import com.TallerWapo.dominio.contexto.Contexto;
import com.TallerWapo.dominio.factorias.ContextoDaos;
import com.TallerWapo.dominio.interfaces.Daos.ClientesDao;
import com.TallerWapo.dominio.utiles.MensajeriaUtils;

import java.util.List;

public class ClientesService {

    private static final ClientesDao clientesDao = ContextoDaos.getClienteDao(Contexto.SESION_SOLO_LECTURA);

    public static void validarCliente(ClienteBO cliente) throws Exception

    {
        //El nombre debe de venir informado
        if (cliente.getNombre() == null || "".equals(cliente.getNombre())) {
            throw new IllegalArgumentException("El nombre del cliente es obligatorio");
        }

        //Buscar
        ClienteBO clienteGemeloDNI;
        List<ClienteBO> clientesGemelosNombre;
        try {
            clienteGemeloDNI = clientesDao.buscarPorDni(cliente.getDni());
            clientesGemelosNombre = clientesDao.buscarPorNombre(cliente.getNombre());

        }catch (Exception e){
            throw new RuntimeException(e);
        }

        //No pueden haber nifs repetidos
        if (clienteGemeloDNI != null) {
            throw new IllegalArgumentException("Ya existe un cliente con el mismo dni, es: " + clienteGemeloDNI.toString());
        }

        //No pueden haber clientes con los mismos nombres y apellidos
        if(!clientesGemelosNombre.isEmpty()){
            for(ClienteBO clienteGemelo : clientesGemelosNombre){
                if(clienteGemelo.getNombre().equals(cliente.getNombre())
                 && clienteGemelo.getApellidos().equals(cliente.getApellidos())){
                    throw new IllegalArgumentException("Ya existe un cliente con el mismo nombre y apellidos. Es: " + clienteGemelo.toString() +". Si no es el mismo cambiale el nombre para que puedas diferenciarlos");
                }
            }
        }

        //Si el email viene informado, comprobar si es valido
        if(cliente.getEmail() != null && !cliente.getEmail().isBlank()) {
            if(!MensajeriaUtils.esEmailValido(cliente.getEmail())){
                throw new IllegalArgumentException("El email no tiene el formato correcto. Corrigelo o quitalo");
            }
        }

        //Si el telefono viene informado, comprobar si es valido
        if(cliente.getTelefono() != 0) {
            if(!MensajeriaUtils.esEmailValido(cliente.getEmail())){
                throw new IllegalArgumentException("El email no tiene el formato correcto. Corrigelo o quitalo");
            }
        }

    }
}
