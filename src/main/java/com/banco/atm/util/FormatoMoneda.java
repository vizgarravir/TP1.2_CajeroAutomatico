package com.banco.atm.util;

import java.text.NumberFormat;
import java.util.Locale;

public class FormatoMoneda {

    private static final NumberFormat formato =
            NumberFormat.getCurrencyInstance(Locale.of("es", "AR"));

    public static String formatear(double monto) {
        return formato.format(monto);
    }
}