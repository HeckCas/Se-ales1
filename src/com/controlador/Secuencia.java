/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controlador;

import javax.swing.JOptionPane;

/**
 *
 * @author Carlos
 */
public class Secuencia {
    public Double []funcion;
    public int indiceDeOrigen;
    public String []arregloOrigen;
    public Secuencia(){
    
    }
    public Secuencia(String datos){
        if(datos.contains(",")){
            String []datosSeparados=datos.split(",");
            this.funcion=new Double[datosSeparados.length];
            for (int i = 0; i < datosSeparados.length; i++) {
                if(datosSeparados[i].contains("o")){
                    this.indiceDeOrigen=i+1;
                    datosSeparados[i]=datosSeparados[i].split("o")[0];
                    this.funcion[i]=Double.parseDouble(datosSeparados[i]);
                }
                else{
                this.funcion[i]=Double.parseDouble(datosSeparados[i]);            
                }            
            }
        }
        else if(!datos.contains(",") && !datos.isEmpty())
        {
            funcion=new Double[1];
            String datosSeparados[]=null;
            if(datos.contains("o"))
            {
                datosSeparados=datos.split("o");
                funcion[0]=Double.parseDouble(datosSeparados[0]);
                indiceDeOrigen=1;
                
                
            }
            else
            {
                funcion[0]=Double.parseDouble(datos);
                indiceDeOrigen=1;
            }
            
        }
    }
    
    public static void agregarCeros(String direccion,Secuencia secuencia,int cantidadDeCeros){
        if(direccion.equals("izquierda")){
            int nuevoTamanio=secuencia.funcion.length+cantidadDeCeros;
            Double []secuenciaAuxiliar=new Double[nuevoTamanio];
            for (int i = 0; i <=cantidadDeCeros-1; i++) {
                secuenciaAuxiliar[i]=0.0;
            }
            int indiceSecuenciaOriginal=0;
            for (int i = cantidadDeCeros; i < secuenciaAuxiliar.length; i++) {
                secuenciaAuxiliar[i]=secuencia.funcion[indiceSecuenciaOriginal];
                indiceSecuenciaOriginal++;
            }
            secuencia.funcion=secuenciaAuxiliar;
        }
        //derecho
        else{
          int nuevoTamanio=secuencia.funcion.length+cantidadDeCeros;
            Double []secuenciaAuxiliar=new Double[nuevoTamanio];
            //copia los valores de la secuencia original al nuevo conjunto
            //hasta qqui nos quedamos... borra el ultimo dato de la secuancia 2 y escribe un cero
            for (int i = 0; i <secuencia.funcion.length; i++) {
                secuenciaAuxiliar[i]=secuencia.funcion[i];
            }
            
            for (int i = secuencia.funcion.length; i <= nuevoTamanio-1; i++) {
                secuenciaAuxiliar[i]=0.0;
            }
            secuencia.funcion=secuenciaAuxiliar;
        }
    }
    static void actualizarOrigen(Secuencia secuenciaAActualizar,int desplazamientoDeOrigen){
        secuenciaAActualizar.indiceDeOrigen+=desplazamientoDeOrigen;
    }
    public static void centrarOrigen(Secuencia secuencia1,Secuencia secuencia2)
    {
        //si el indice es igual, no hace nada
        //Resuelve la cantidad de datos a la izquierda
        if(secuencia1.indiceDeOrigen==secuencia2.indiceDeOrigen)
            return ;
        
        int restaDeIndices=Math.abs(secuencia1.indiceDeOrigen-secuencia2.indiceDeOrigen);
        if(secuencia1.indiceDeOrigen>secuencia2.indiceDeOrigen)
        {
            //agregamos ceros a la izquierda de secuancia 2
            //metodoAgregarCeros(izquierda,secuancia2,restaDeIndices)
            agregarCeros("izquierda",secuencia2,restaDeIndices);
            actualizarOrigen(secuencia2, restaDeIndices);
            //metodo actulizarIndice(indiceAnterior, restaDeIndices)
        }else{
            //metodoAgregarCeros(izquierda,secuancia1,restaDeIndices)
            agregarCeros("izquierda",secuencia1,restaDeIndices);
            actualizarOrigen(secuencia1, restaDeIndices);
        }

        //derecha
        int distanciaOrigenAFin1=Math.abs(secuencia1.indiceDeOrigen-secuencia1.funcion.length);
        int distanciaOrigenAFin2=Math.abs(secuencia2.indiceDeOrigen-secuencia2.funcion.length);
        int restaDistancias=Math.abs(distanciaOrigenAFin1-distanciaOrigenAFin2);
        //si distanciaOrigenAFin1>distanciaOrigenAFin2 agregamos restaDistancias ceros a secuencia2
        if(distanciaOrigenAFin1>distanciaOrigenAFin2){
            agregarCeros("derecha", secuencia2, restaDistancias);
        }
        //si distanciaOrigenAFin2>distanciaOrigenAFin1 agregamos restaDistancias ceros a secuencia1
        else if(distanciaOrigenAFin2>distanciaOrigenAFin1){
            agregarCeros("derecha", secuencia1, restaDistancias);
        }
        //caso de datos a la derecha del origen
        //mandara a llamar metodoAgregarCeros(derecha,secuanciax,diferencia de tamaño de datos a la derecha)
    }
    
    public static void main(String[] args) {
        Secuencia s1=new Secuencia();
        s1.funcion=new Double[]{9.0,1.0,2.0,3.0,4.0,5.0};
        s1.indiceDeOrigen=4;
        Secuencia s2=new Secuencia();
        s2.funcion=new Double[]{90.0,10.0,20.0,30.0,40.0,50.0};
        s2.indiceDeOrigen=6;
        centrarOrigen(s1, s2);
        System.out.println("SECUACIA 1");
        for (int i = 0; i <= s1.funcion.length-1; i++) {
            System.out.println(s1.funcion[i]);            
        }
        System.out.println("SECUACIA 2");
        for (int i = 0; i <= s2.funcion.length-1; i++) {
            System.out.println(s2.funcion[i]);            
        }
        System.out.println("el origen de la sec1:"+s1.indiceDeOrigen);
        System.out.println("el origen de la sec2:"+s2.indiceDeOrigen);
        System.out.println("Prueba del constructor");
        Secuencia s3= new Secuencia("1.0,2.0,3.0,4.0,5.0o");
        for (int i = 0; i <= s3.funcion.length-1; i++) {
            System.out.println(s3.funcion[i]); 
        }
        System.out.println("el origen de la sec3:"+s3.indiceDeOrigen);
    }
}
