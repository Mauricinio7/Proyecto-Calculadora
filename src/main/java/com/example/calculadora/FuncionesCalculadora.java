package com.example.calculadora;

import java.util.Stack;
import java.util.StringTokenizer;

public class FuncionesCalculadora {
    private final String expresionInfija;
    private final String expresionPosfija;
    private final double result;
    public FuncionesCalculadora(String expresionInfija){
        this.expresionInfija = expresionInfija;
        this.expresionPosfija = convertirAFormaPostfija();
        this.result = calcular(getExpresionPosfija());
    }
    public String getExpresionInfija(){
        return expresionInfija;
    }
    public String getExpresionPosfija(){
        return expresionPosfija;
    }
    public double getResult(){
        return result;
    }
    public static double calcular(String expresion) {
        Stack<Double> pila = new Stack<>();

        String[] tokens = expresion.split(" ");

        for (String token : tokens) {
            if (esNumero(token)) {
                pila.push(Double.parseDouble(token));
            } else {
                double operand2 = pila.pop();
                double operand1 = pila.pop();
                double resultado = operar(operand1, operand2, token);
                pila.push(resultado);
            }
        }

        return pila.pop();
    }
    public String convertirAFormaPostfija() {
        String expresion = getExpresionInfija();
        StringBuilder resultado = new StringBuilder();
        Stack<String> pila = new Stack<>();
        StringTokenizer tokens = new StringTokenizer(expresion, "+-*/^() ", true);

        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken().trim();

            if (!token.isEmpty()) {
                if (esNumero(token)) {
                    resultado.append(token).append(" ");
                } else if (esOperador(token)) {
                    while (!pila.isEmpty() && prioridad(pila.peek()) >= prioridad(token)) {
                        resultado.append(pila.pop()).append(" ");
                    }
                    pila.push(token);
                } else if (token.equals("(")) {
                    pila.push(token);
                } else if (token.equals(")")) {
                    while (!pila.isEmpty() && !pila.peek().equals("(")) {
                        resultado.append(pila.pop()).append(" ");
                    }
                    pila.pop();
                }
            }
        }

        while (!pila.isEmpty()) {
            resultado.append(pila.pop()).append(" ");
        }

        return resultado.toString().trim();
    }
    private static boolean esNumero(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private static boolean esOperador(String token) {
        return "+-*/^".contains(token);
    }
    private static int prioridad(String operador) {
        switch (operador) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
            default:
                return 0;
        }
    }
    private static double operar(double operand1, double operand2, String operador) {
        switch (operador) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 == 0) {
                    throw new ArithmeticException("División por cero");
                }
                return operand1 / operand2;
            case "^":
                return Math.pow(operand1, operand2);
            default:
                throw new IllegalArgumentException("Operador inválido: " + operador);
        }
    }
    public static void main(String[] args) {
        FuncionesCalculadora newCalcul = new FuncionesCalculadora("5 * 7 + 4 * 2");
        System.out.println("El resultado de la expresión '" + newCalcul.getExpresionInfija() + "' es: " + newCalcul.getResult());
    }

}
