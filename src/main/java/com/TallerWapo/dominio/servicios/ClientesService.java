package com.TallerWapo.dominio.servicios;

import com.TallerWapo.dominio.BOs.Clientes.ClienteBO;

public class ClientesService {
    public static void validarCliente(ClienteBO cliente) throws Exception
    {
        //El nombre debe de venir informado
        if (cliente.getNombre() == null || "".equals(cliente.getNombre())) {
            throw new IllegalArgumentException("El nombre del cliente es obligatorio");
        }

    }
}
