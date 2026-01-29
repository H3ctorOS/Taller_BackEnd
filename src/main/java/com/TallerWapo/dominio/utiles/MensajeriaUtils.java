package com.TallerWapo.dominio.utiles;

import java.util.regex.Pattern;

public class MensajeriaUtils {

    // Expresión regular estándar para emails
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);


    // Teléfono español: +34 opcional y 9 dígitos
    private static final String TELEFONO_REGEX = "^(\\+34)?[6-9]\\d{8}$";
    private static final Pattern TELEFONO_PATTERN = Pattern.compile(TELEFONO_REGEX);


    public static boolean esEmailValido(String email) {
        if (email == null || email.isBlank()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean esTelefonoValido(String telefono) {
        if (telefono == null || telefono.isBlank()) {
            return false;
        }

        // Eliminamos espacios
        telefono = telefono.replaceAll("\\s+", "");

        return TELEFONO_PATTERN.matcher(telefono).matches();
    }

}
