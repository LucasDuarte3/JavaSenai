/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package lucas;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
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
    
    private List<Backend> cidades;
    private final double consumo = 10.0; // consumo do carro
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarCidades();
        comboBoxPartida.getItems().addAll(getNomesCidades());
        comboBoxDestino.getItems().addAll(getNomesCidades());
    }
    private void inicializarCidades() {
        cidades = new ArrayList<>();
        cidades.add(new Backend("São Paulo", -23.5505, -46.6333));
        cidades.add(new Backend("Rio de Janeiro", -22.9068, -43.1729));
        cidades.add(new Backend("Belo Horizonte", -19.9191, -43.9378));
        cidades.add(new Backend("Curitiba", -25.4284, -49.2733));
        cidades.add(new Backend("Florianópolis", -27.5956, -48.5480));
        cidades.add(new Backend("Brasília", -15.7801, -47.9292));
        cidades.add(new Backend("Vitória", -20.3155, -40.3128));
        cidades.add(new Backend("Salvador", -12.9714, -38.5014));
        cidades.add(new Backend("Natal", -5.7945, -35.2110));
        cidades.add(new Backend("Fortaleza", -3.7172, -38.5433));
    }
    private List<String> getNomesCidades() {
        List<String> nomes = new ArrayList<>();
        for (Backend cidade : cidades) {
            nomes.add(cidade.nome);
        }
        return nomes;
    }
    @FXML
    private double calcularDistancia(Backend cidade1, Backend cidade2) {
        final int R = 6371; // Raio da Terra em km
        double lat1 = Math.toRadians(cidade1.latitude);
        double lon1 = Math.toRadians(cidade1.longitude);
        double lat2 = Math.toRadians(cidade2.latitude);
        double lon2 = Math.toRadians(cidade2.longitude);

        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;

        double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) +
                   Math.cos(lat1) * Math.cos(lat2) *
                   Math.sin(dlon / 2) * Math.sin(dlon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c; // Distância em km
    }    
    public void calcular() {
        String partida = comboBoxPartida.getValue();
        String destino = comboBoxDestino.getValue();

        if (partida == null || destino == null) {
            textFieldDistancia.setText("Selecione a partida e o destino");
            textFieldLitros.setText("");
            return;
        }

        Backend cidadePartida = cidades.stream().filter(c -> c.nome.equals(partida)).findFirst().orElse(null);
        Backend cidadeDestino = cidades.stream().filter(c -> c.nome.equals(destino)).findFirst().orElse(null);

        if (cidadePartida == null || cidadeDestino == null) {
            textFieldDistancia.setText("Destino inválido");
            textFieldLitros.setText("");
            return;
        }
        
        if (partida.equals(destino)) {
            textFieldDistancia.setText("Partida e destino não podem ser iguais");
            textFieldLitros.setText("");
            return;
        }

        double distancia = calcularDistancia(cidadePartida, cidadeDestino);
        double litros = distancia / consumo;

        // Formatação
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');

        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);
        
        textFieldDistancia.setText(decimalFormat.format(distancia) + " km");
        textFieldLitros.setText(decimalFormat.format(litros) + " litros");
     }    
}
