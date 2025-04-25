package br.com.alura.conversor.modelos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ConversorMoeda {

    private final String API_KEY = "e4eaab3cca0ee7f8cfcfdf8e";

    public double obterTaxaDeCambio(String base, String destino) {
        try {
            String urlStr = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + base + "/" + destino;
            URL url = new URL(urlStr);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            StringBuilder resposta = new StringBuilder();
            String linha;
            while ((linha = leitor.readLine()) != null) {
                resposta.append(linha);
            }
            leitor.close();

            Gson gson = new Gson();
            JsonObject json = gson.fromJson(resposta.toString(), JsonObject.class);

            if (json.get("result").getAsString().equals("success")) {
                return json.get("conversion_rate").getAsDouble();
            } else {
                System.out.println("Erro na resposta da API: " + json);
                return -1;
            }

        } catch (Exception e) {
            System.out.println("Erro ao acessar API: " + e.getMessage());
            return -1;
        }
    }

    public Map<String, String> listarMoedas() {
        Map<String, String> moedas = new LinkedHashMap<>();
        try {
            String urlStr = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/codes";
            URL url = new URL(urlStr);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            StringBuilder resposta = new StringBuilder();
            String linha;
            while ((linha = leitor.readLine()) != null) {
                resposta.append(linha);
            }
            leitor.close();

            Gson gson = new Gson();
            JsonObject json = gson.fromJson(resposta.toString(), JsonObject.class);
            JsonArray lista = json.getAsJsonArray("supported_codes");

            for (int i = 0; i < lista.size(); i++) {
                JsonArray item = lista.get(i).getAsJsonArray();
                moedas.put(item.get(0).getAsString(), item.get(1).getAsString());
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar moedas: " + e.getMessage());
        }

        return moedas;
    }

    public double converter(double valor, double taxaCambio) {
        return valor * taxaCambio;
    }
}


