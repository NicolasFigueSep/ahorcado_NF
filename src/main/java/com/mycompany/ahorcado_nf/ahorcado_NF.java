/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.ahorcado_nf;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Alumno Tarde
 */
public class ahorcado_NF {

    /**
     * @param args the command line arguments
     */
    /*
        Para usar en todo el programa
    */
    static Random rnd = new Random();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        //Tamaño de palabras y fila de pistas
        String [][] palabras = new String[2][3];
        boolean salir= false;
        int jugada = 0;
        char letra;
        //Introducimos las palabras por defecto
        palabras = metePalabras(palabras);
        //Se escoge una palabra aleatoria
        int posPalabra =posAlea(palabras[0].length);
        //Recogemos la pista de la matriz bidimensional. Siempre sera fila 1 ya que es donde se almacenna
        // todas las pistas
        String pista = palabras[1][posPalabra];
        /*
            El array palabra será para tener la palabra: _ _ _ _. Irá almacenando las respuestas
            correctas del jugador
            El array palabra tendra la palabra devidida en caracteres: v a c a
        */
        char [] palabra = new char[palabras[0][posPalabra].length()];
        char [] palabra2 = new char[palabras[0][posPalabra].length()];
        palabra=rellena(palabra, palabras[0][posPalabra],0);
        palabra2=rellena(palabra2,palabras[0][posPalabra],1);
        // Se pintara el estado inicial 
        pintaIni(palabra, pista);
        do{
           letra = pedirDato();
           palabra = compara(palabra,palabra2,letra);
           pintaIni(palabra,pista);
           salir= controlJuego(palabra);
           jugada++;
           System.out.println(" Jugada: " + jugada);
           if(jugada>9){
               salir=true;
           }           
        }while(!salir);
        
        if(controlJuego(palabra)){
            System.out.println("Enhorabuena has ganado");
        }
        else{
            System.out.println("Has perdido");
        }        
    }
    /*
        Rellena los contenedores dependiendo si va a ser contenedor de respuestas del jugador
        o la palabra escogida
    */
    public static char[] rellena(char [] contenedor,String palabra, int tipo){
        if(tipo==0){
            for (int i = 0; i < palabra.length(); i++) {
                contenedor[i]='_';
            }
        }
        else if(tipo ==1){
            for (int i = 0; i < palabra.length(); i++) {
                contenedor[i]= palabra.charAt(i);
            }
        }
        return contenedor;
    }
    public static String[][] metePalabras(String [][] palabras){
        palabras[0][0] = "vaca";
        palabras[1][0] = "animal";
        palabras [0][1] = "agua";
        palabras[1][1] = "líquido";
        palabras[0][2] = "mandarina";
        palabras [1][2] = "fruta";
        
        return palabras;
    }
    /*
     Pintara el Array palabra, que es quién almacena las respuestas del jugador
    */
    public static void pintaIni(char [] palabra, String pista){
        for (int i = 0; i < palabra.length; i++) {
            System.out.print(palabra[i] + " ");
        }
        System.out.println("");
        System.out.print("Adivinar " + pista + ".");
    }
    public static int posAlea(int rango){
        int posicion =  rnd.nextInt(rango);
        return posicion;
    }
    public static char pedirDato(){
        char letra;
        System.out.println("Introducir letra: ");
        letra = sc.nextLine().toLowerCase().charAt(0);        
        return letra;
    }
    /*
        Comparara el array de respuestas del jugador con el array de la palabra escogida
        Si la letra introducida por el jugador está en el array de palabra2(palabra escogida)
        La introduciremos en el array palabra(respuesta del jugador) y será exactamente en la misma posicion
    */
    public static char [] compara(char palabra [],char palabra2[], char letra){
        for (int i = 0; i < palabra.length; i++) {
            if(letra == palabra2[i]){
                palabra[i]= letra;
            }
        }
        return palabra;
    }
    /*
        Recorremos todo el array de las respuestas del jugador si hubiera
        un caracter '_' es que aun no se ha conseguido la palabra
        Si no hubiera '_' es que ya ha adivinado la palbra
    */
    public static boolean controlJuego(char palabra[]){
        boolean salir = true;
        for (int i = 0; i < palabra.length; i++) {
            if(palabra[i]=='_'){
                salir = false;
            }
        }
        return salir;
    }
}
