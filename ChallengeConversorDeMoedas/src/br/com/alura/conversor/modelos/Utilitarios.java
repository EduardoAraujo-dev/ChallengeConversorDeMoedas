package br.com.alura.conversor.modelos;

import java.text.DecimalFormat;

public class Utilitarios {
    public static String formatar(double valor, String codigoMoeda) {
        DecimalFormat df = new DecimalFormat("###,##0.00");
        return codigoMoeda + " " + df.format(valor);
    }
}


