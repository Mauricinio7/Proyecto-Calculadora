package com.example.calculadora;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

public class HelloController {
    @FXML
    private Label pantalla;

    @FXML
    protected void onNumberButtonClick(ActionEvent event) {
        Button botonPresionado = (Button) event.getSource(); // Obtener el botón que se presionó
        String textoBoton = botonPresionado.getText();
        String textoActual = pantalla.getText();
        String nuevoTexto = textoActual + textoBoton;
        if(textoActual.equals("0")){
            pantalla.setText(textoBoton);
        }
        else {
            pantalla.setText(nuevoTexto);
        }
    }
    @FXML
    protected void onSimbolButtonClick(ActionEvent event) {
        Button botonPresionado = (Button) event.getSource(); // Obtener el botón que se presionó
        String textoBoton = botonPresionado.getText();
        String textoActual = pantalla.getText();
        String nuevoTexto = textoActual + textoBoton;
        if(textoActual.charAt(textoActual.length() -1) != '+' || textoActual.charAt(textoActual.length() -1) != '-' || textoActual.charAt(textoActual.length() -1) != '*' || textoActual.charAt(textoActual.length() -1) != '/' || textoActual.charAt(textoActual.length() -1) != '.'){
            pantalla.setText(nuevoTexto);
        }
    }

    @FXML
    protected void onDelButtonClick(ActionEvent event) {
        String textoActual = pantalla.getText();
        if (textoActual.length() > 1){
            textoActual = textoActual.substring(0,textoActual.length() -1);
        }
        else{
            textoActual = "0";
        }
        pantalla.setText(textoActual);
    }
    @FXML
    protected void onEqualsButtonClick(ActionEvent event) {
       
    }
}