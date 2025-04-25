package br.com.alura.conversor;

import br.com.alura.conversor.modelos.ConversorMoeda;
import br.com.alura.conversor.modelos.JanelaConversor;
import br.com.alura.conversor.modelos.Utilitarios;

import java.util.Scanner;

import javax.swing.SwingUtilities;

public class Principal {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JanelaConversor janela = new JanelaConversor();
            janela.setVisible(true);
        });
    }
}


