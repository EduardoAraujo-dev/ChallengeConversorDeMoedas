package br.com.alura.conversor.modelos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;

public class JanelaConversor extends JFrame {

    private JComboBox<String> comboBase;
    private JComboBox<String> comboDestino;
    private JTextField campoValor;
    private JLabel labelResultado;

    private Map<String, String> moedas;

    public JanelaConversor() {
        setTitle("Conversor de Moedas");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        ConversorMoeda conversor = new ConversorMoeda();
        moedas = conversor.listarMoedas();

        add(new JLabel("Moeda de Origem:"));
        comboBase = new JComboBox<>();
        add(new JLabel("Moeda de Destino:"));
        comboDestino = new JComboBox<>();

        for (String codigo : moedas.keySet()) {
            comboBase.addItem(codigo + " - " + moedas.get(codigo));
            comboDestino.addItem(codigo + " - " + moedas.get(codigo));
        }

        add(comboBase);
        add(comboDestino);

        add(new JLabel("Valor:"));
        campoValor = new JTextField();
        add(campoValor);

        JButton botaoConverter = new JButton("Converter");
        add(botaoConverter);

        labelResultado = new JLabel("Resultado: ");
        add(labelResultado);

        botaoConverter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String base = comboBase.getSelectedItem().toString().split(" - ")[0];
                String destino = comboDestino.getSelectedItem().toString().split(" - ")[0];
                double valor = Double.parseDouble(campoValor.getText());

                double taxa = conversor.obterTaxaDeCambio(base, destino);
                if (taxa != -1) {
                    double resultado = conversor.converter(valor, taxa);
                    labelResultado.setText("Resultado: " + Utilitarios.formatar(resultado, destino));
                } else {
                    labelResultado.setText("Erro na conversão.");
                }
            }
        });
    }
}
