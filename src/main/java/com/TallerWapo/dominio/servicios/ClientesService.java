package com.TallerWapo.dominio.servicios;

import com.TallerWapo.dominio.BOs.Clientes.ClienteBO;
import com.TallerWapo.dominio.contexto.Contexto;
import com.TallerWapo.dominio.factorias.ContextoDaos;
import com.TallerWapo.dominio.interfaces.Daos.ClientesDao;
import com.TallerWapo.dominio.utiles.MensajeriaUtils;

import java.util.List;

public class ClientesService {

    private static final ClientesDao clientesDao = ContextoDaos.getClienteDao(Contexto.SESION_SOLO_LECTURA);

    public static void validarCliente(ClienteBO cliente, boolean isActualizar) throws Exception

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

        //validar formato DNI
        /*
        if(cliente.getDni() == null || !esDniValido(cliente.getDni())){
            throw new IllegalArgumentException("El DNI no tiene el formato correcto");
        }
*/

        //No pueden haber nifs repetidos
        if (clienteGemeloDNI != null) {
            if(isActualizar && (clienteGemeloDNI.getUuid() == cliente.getUuid())) {
                return;
            }
            throw new IllegalArgumentException("Ya existe un cliente con el mismo dni, es: " + clienteGemeloDNI.toString());
        }

        //No pueden haber clientes con los mismos nombres y apellidos
        if(!clientesGemelosNombre.isEmpty()){
            for(ClienteBO clienteGemelo : clientesGemelosNombre){
                if(isActualizar && (clienteGemelo.getUuid() == cliente.getUuid())) {
                    continue;
                }

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


    public static boolean esDniValido(String dni) {
        if (dni == null) {
            return false;
        }

        dni = dni.toUpperCase().trim();

        // Comprobar formato básico: 8 dígitos + 1 letra
        if (!dni.matches("\\d{8}[A-Z]")) {
            return false;
        }

        // Comprobar letra del DNI
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        String numero = dni.substring(0, 8);
        char letraCorrecta = letras.charAt(Integer.parseInt(numero) % 23);

        return dni.charAt(8) == letraCorrecta;
    }


    public static boolean esDniONieValido(String documento) {
        if (documento == null) return false;

        documento = documento.toUpperCase().trim();

        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";

        // Comprobar si es DNI normal: 8 dígitos + letra
        if (documento.matches("\\d{8}[A-Z]")) {
            String numero = documento.substring(0, 8);
            char letraCorrecta = letras.charAt(Integer.parseInt(numero) % 23);
            return documento.charAt(8) == letraCorrecta;
        }

        // Comprobar si es NIE: X/Y/Z + 7 dígitos + letra
        if (documento.matches("[XYZ]\\d{7}[A-Z]")) {
            char primeraLetra = documento.charAt(0);
            String numero = documento.substring(1, 8);
            int prefijo = switch (primeraLetra) {
                case 'X' -> 0;
                case 'Y' -> 1;
                case 'Z' -> 2;
                default -> -1;
            };
            if (prefijo == -1) return false;

            int numeroCompleto = Integer.parseInt(prefijo + numero);
            char letraCorrecta = letras.charAt(numeroCompleto % 23);
            return documento.charAt(8) == letraCorrecta;
        }

        return false; // No cumple formato DNI ni NIE
    }


}
