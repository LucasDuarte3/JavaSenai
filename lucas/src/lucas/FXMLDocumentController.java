/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package lucas;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Aluno Matutino
 */
public class FXMLDocumentController implements Initializable {
    
     @FXML
    private ComboBox<String> comboBoxPartida;
    @FXML
    private ComboBox<String> comboBoxDestino;
    @FXML
    private TextField textFieldDistancia;
    @FXML
    private TextField textFieldLitros;

    private Map<String, Map<String, Integer>> distancias;
    private final double consumo = 10.0; // 10 km/l
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializando cidades e distâncias
    distancias = new HashMap<>();

    Map<String, Integer> distanciasSaoPaulo  = new HashMap<>();
    distanciasSaoPaulo.put("Rio de Janeiro", 430);
    distanciasSaoPaulo.put("Belo Horizonte", 586);
    distanciasSaoPaulo.put("Curitiba", 408);
    distanciasSaoPaulo.put("Florianópolis", 700);
    adicionarCidade("São Paulo", distanciasSaoPaulo);

    Map<String, Integer> distanciasRioDeJaneiro = new HashMap<>();
    distanciasRioDeJaneiro.put("São Paulo", 430);
    distanciasRioDeJaneiro.put("Belo Horizonte", 360);
    distanciasRioDeJaneiro.put("Brasília", 1170);
    distanciasRioDeJaneiro.put("Salvador", 1500);
    adicionarCidade("Rio de Janeiro", distanciasRioDeJaneiro);

    Map<String, Integer> distanciasBeloHorizonte = new HashMap<>();
    distanciasBeloHorizonte.put("São Paulo", 586);
    distanciasBeloHorizonte.put("Rio de Janeiro", 360);
    distanciasBeloHorizonte.put("Brasília", 740);
    distanciasBeloHorizonte.put("Vitória", 520);
    adicionarCidade("Belo Horizonte", distanciasBeloHorizonte);

    Map<String, Integer> distanciasCuritiba = new HashMap<>();
    distanciasCuritiba.put("São Paulo", 408);
    distanciasCuritiba.put("Florianópolis", 301);
    adicionarCidade("Curitiba", distanciasCuritiba);

    Map<String, Integer> distanciasBrasilia = new HashMap<>();
    distanciasBrasilia.put("Rio de Janeiro", 1170);
    distanciasBrasilia.put("Belo Horizonte", 740);
    distanciasBrasilia.put("Salvador", 1400);
    adicionarCidade("Brasília", distanciasBrasilia);

    Map<String, Integer> distanciasFlorianopolis = new HashMap<>();
    distanciasFlorianopolis.put("Curitiba", 301);
    distanciasFlorianopolis.put("São Paulo", 700);
    distanciasFlorianopolis.put("Rio de Janeiro", 960);
    adicionarCidade("Florianópolis", distanciasFlorianopolis);

    Map<String, Integer> distanciasSalvador = new HashMap<>();
    distanciasSalvador.put("Rio de Janeiro", 1500);
    distanciasSalvador.put("Brasília", 1400);
    adicionarCidade("Salvador", distanciasSalvador);

    Map<String, Integer> distanciasVitoria = new HashMap<>();
    distanciasVitoria.put("Belo Horizonte", 520);
    distanciasVitoria.put("Rio de Janeiro", 520);
    adicionarCidade("Vitória", distanciasVitoria);

    Map<String, Integer> distanciasNatal = new HashMap<>();
    distanciasNatal.put("Salvador", 570);
    distanciasNatal.put("Brasília", 2200);
    adicionarCidade("Natal", distanciasNatal);

    Map<String, Integer> distanciasFortaleza = new HashMap<>();
    distanciasFortaleza.put("Natal", 200);
    distanciasFortaleza.put("Salvador", 1000);
    adicionarCidade("Fortaleza", distanciasFortaleza);

    // Adicionando cidades ao ComboBox
    comboBoxPartida.getItems().addAll(distancias.keySet());
    comboBoxDestino.getItems().addAll(distancias.keySet());
    }

    private void adicionarCidade(String cidade, Map<String, Integer> distanciasCidade) {
        distancias.put(cidade, distanciasCidade);
    }

    @FXML
    public void calcular() {
        String partida = comboBoxPartida.getValue();
        String destino = comboBoxDestino.getValue();

        System.out.println("Partida: " + partida + ", Destino: " + destino); // Para depuração

        if (partida == null) {
            textFieldDistancia.setText("Selecione a partida");
            textFieldLitros.setText("");
        } else if (destino == null) {
            textFieldDistancia.setText("Selecione o destino");
            textFieldLitros.setText("");
        } else if (!distancias.containsKey(partida)) {
            textFieldDistancia.setText("Partida inválida");
            textFieldLitros.setText("");
        } else {
            Map<String, Integer> destinosPartida = distancias.get(partida);
            if (!destinosPartida.containsKey(destino)) {
                textFieldDistancia.setText("Destino inválido");
                textFieldLitros.setText("");
                System.out.println("Destino não encontrado na lista de destinos para " + partida);
            } else {
                int distancia = destinosPartida.get(destino);
                double litros = distancia / consumo;

                textFieldDistancia.setText(String.valueOf(distancia) + " km");
                textFieldLitros.setText(String.valueOf(litros) + " litros");
            }
        }
    }    
    
}
