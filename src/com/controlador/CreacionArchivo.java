/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controlador;

import java.io.FileOutputStream;

/**
 *
 * @author Carlos
 */
public class CreacionArchivo {
    public CreacionArchivo(Secuencia sec1,Secuencia sec2, String operacion){
        //Agrega ceros a la derecha cuando hace falta
            int diferenciaSecuencia1=sec1.funcion.length-sec1.indiceDeOrigen;
            int diferenciaSecuencia2=sec2.funcion.length-sec2.indiceDeOrigen;
            if(diferenciaSecuencia1>diferenciaSecuencia2)
            {
                Secuencia.agregarCeros("derecha", sec2, diferenciaSecuencia1-diferenciaSecuencia2);
            }
            else
            {
                Secuencia.agregarCeros("izquierda", sec2, diferenciaSecuencia2-diferenciaSecuencia1);
            }
        //
        if(operacion.equals("suma")){
            System.out.println(concatenarDosSecuencias(sec1, sec2,"suma"));
            Terminal.ejecutarComando();
        }
        else if(operacion.equals("resta")){
            System.out.println(concatenarDosSecuencias(sec1, sec2,"resta"));
            Terminal.ejecutarComando();
        }
        else if(operacion.equals("multiplicacion")){
            System.out.println(concatenarDosSecuencias(sec1, sec2,"multiplicacion"));
            Terminal.ejecutarComando();
        }
    }
    public CreacionArchivo(Secuencia sec1,String operacion,String factor){
        if(operacion.equals("amplificacionatenuacion")){
            System.out.println(concatenarAmplificacionAtenuacion(sec1,factor));
            Terminal.ejecutarComando();
        }
        else if(operacion.equals("desplazamiento")){
            if(Integer.parseInt(factor)>sec1.funcion.length-sec1.indiceDeOrigen){
               Secuencia.agregarCeros("derecha", sec1,Integer.parseInt(factor)-(sec1.funcion.length-sec1.indiceDeOrigen) );
            }
            else if(Integer.parseInt(factor)*(-1)>sec1.indiceDeOrigen){
                Secuencia.agregarCeros("izquierda", sec1,Integer.parseInt(factor)*(-1)-sec1.indiceDeOrigen+1);
                sec1.indiceDeOrigen+=Integer.parseInt(factor)*(-1)-sec1.indiceDeOrigen+1;
            }
            System.out.println(concatenarDesplazamiento(sec1,factor));
            Terminal.ejecutarComando();
        }
        else if (operacion.equals("reflejo")){
            System.out.println(concatenarReflejo(sec1));
            Terminal.ejecutarComando();
        }
        else if (operacion.equals("diezmacion")){
            System.out.println(concatenarDiezmacion(sec1,factor));
            Terminal.ejecutarComando();
        }
        else if (operacion.equals("interpolacionacero")){
            System.out.println(concatenarInterpolacion(sec1,factor,"A Cero"));
            Terminal.ejecutarComando();
        }
        else if (operacion.equals("interpolacionaescalon")){
            System.out.println(concatenarInterpolacion(sec1,factor,"A Escalon"));
            Terminal.ejecutarComando();
        }
        else if (operacion.equals("interpolacionlineal")){
            System.out.println(concatenarInterpolacion(sec1,factor,"Lineal"));
            Terminal.ejecutarComando();
        }
        
    }
    
    public String concatenarDosSecuencias(Secuencia secuencia1,Secuencia secuencia2,String operacion)
    {
        inicializarArchivoSalida("./salidaGUI/","salidaGUI.txt" );
        
        
        
        String resultado=new String("");        
        //encabezado
        resultado+=encabezado;
        escribirArchivoSalida(encabezado.getBytes());
        
        //funcion1 
        resultado+=funcion1;
        escribirArchivoSalida(funcion1.getBytes());
        
        //complemento funcion1()
        String fragmento=complementarSecuencia(secuencia1);
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //funcion2
        resultado+=funcion2;
        escribirArchivoSalida(funcion2.getBytes());
        
        //complemento funcion2()
        fragmento=complementarSecuencia(secuencia2);
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //corrimiento
        resultado+=corrimiento;
        escribirArchivoSalida(corrimiento.getBytes());
        
        //inicializar corrimiento con valor de origen
        fragmento=secuencia1.indiceDeOrigen+";\n";;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //SecuenciaDeEntrada
        fragmento=graficaSecuenciaEntrada1;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //SecuenciaDeEntrada
        fragmento=graficaSecuenciaEntrada2;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //definicionDeFunciones
        fragmento=definicionDeFunciones;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //centrar en el origen
        fragmento="corrimiento=desplazamiento(corrimiento-1)\n";
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //opercion a realizar
        if(operacion.equals("suma")){
            fragmento=suma;
            resultado+=fragmento;
            escribirArchivoSalida(fragmento.getBytes());
        }
        else if(operacion.equals("resta")){
            fragmento=resta;
            resultado+=fragmento;
            escribirArchivoSalida(fragmento.getBytes());
        }
        else{
            fragmento=multiplicacion;
            resultado+=fragmento;
            escribirArchivoSalida(fragmento.getBytes());
        }
        //inicializarXY()
        inicializarXY(secuencia1.funcion.length);
        
        //cadenaX
        fragmento=cadenaX;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //cadenaY
        fragmento=cadenaY;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //datosAGraficar()
        datosAGraficar(secuencia1.funcion.length);
        //cadenaStemX
        fragmento=cadenaStemX;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //cadenaStemY
        fragmento=cadenaStemY;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //cadenaGraficacion
        fragmento=cadenaGraficacion;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //complemento
        fragmento=complemento;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        cerrarArchivoSalida();
        return resultado;
    }
    public String concatenarAmplificacionAtenuacion(Secuencia secuencia1,String factor)
    {
        inicializarArchivoSalida("./salidaGUI/","salidaGUI.txt" );
        String resultado=new String("");        
        
        String fragmento="";
        //encabezado
        fragmento=encabezado;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //funcion1 
        fragmento=funcion1;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //complemento funcion1()
        fragmento=complementarSecuencia(secuencia1);;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //corrimiento
        fragmento=corrimiento;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //inicializar corrimiento con valor de origen
        fragmento=secuencia1.indiceDeOrigen+";\n";
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //SecuenciaDeEntrada
        fragmento=graficaSecuenciaEntrada1;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //definicionDeFunciones
        fragmento=definicionDeFunciones;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //centrar en el origen
        fragmento="corrimiento=desplazamiento(corrimiento-1)\n";
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //opercion a realizar
        fragmento="z=atenuacionAmplificacion(A,"+factor+")\n";
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //inicializarXY()
        inicializarXY(secuencia1.funcion.length);
        //cadenaX
        fragmento=cadenaX;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //cadenaY
        fragmento=cadenaY;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //datosAGraficar()
        datosAGraficar(secuencia1.funcion.length);
        //cadenaStemX
        fragmento=cadenaStemX;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //cadenaStemY
        fragmento=cadenaStemY;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //cadenaGraficacion
        fragmento=cadenaGraficacion;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //complemento
        fragmento=complemento;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        cerrarArchivoSalida();
        return resultado;
    }
    public String concatenarDiezmacion(Secuencia secuencia1,String factor)
    {
        inicializarArchivoSalida("./salidaGUI/","salidaGUI.txt" );
        String fragmento="";
        String resultado=new String("");        
        //encabezado
        fragmento=encabezado;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //funcion1 
        fragmento=funcion1;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //complemento funcion1()
        fragmento=complementarSecuencia(secuencia1);
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //corrimiento
        fragmento=corrimiento;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //inicializar corrimiento con valor de origen
        fragmento=secuencia1.indiceDeOrigen+";\n";
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //SecuenciaDeEntrada
        fragmento=graficaSecuenciaEntrada1;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //factorDiezmacion
        fragmento="factorDiezmacion="+factor;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //definicionDeFunciones
        fragmento=definicionDeFunciones;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //opercion a realizar
        fragmento="z=diezmacion(A,"+secuencia1.indiceDeOrigen+","+factor+")\n";
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //centrar en el origen
/*        fragmento="corrimiento=desplazamiento((int8(corrimiento/factorDiezmacion))-1)\n";
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //OLD*/
        
        fragmento="datosLadoIzqDelOrigen=0\n" +
        "acum=factorDiezmacion\n" +
        "while(corrimiento-acum>=1)\n" +
        "	datosLadoIzqDelOrigen=datosLadoIzqDelOrigen+1\n" +
        "	acum=acum+factorDiezmacion\n" +
        "end\n" +
        "corrimiento=desplazamiento(datosLadoIzqDelOrigen)\n";
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //inicializarXY()-------
        int totalMuestras=0;
        int acumulador=Integer.parseInt(factor);        
        while (secuencia1.indiceDeOrigen-acumulador>=1){
            totalMuestras++;
            acumulador=acumulador+Integer.parseInt(factor);
        }
        System.out.println("despues del primer while");
        acumulador=Integer.parseInt(factor);
        while(secuencia1.indiceDeOrigen+acumulador<=secuencia1.funcion.length){
            totalMuestras ++;
            acumulador+=Integer.parseInt( factor);
        }
        totalMuestras ++;
        
        System.out.println("el total de muestras es"+totalMuestras);
        inicializarXY(totalMuestras);
        
        
        //cadenaX
        fragmento=cadenaX;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //cadenaY
        fragmento=cadenaY;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //datosAGraficar()
        datosAGraficar(totalMuestras);
        //cadenaStemX
        fragmento=cadenaStemX;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //cadenaStemY
        fragmento=cadenaStemY;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //cadenaGraficacion
        fragmento=cadenaGraficacion;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //complemento
        fragmento=complemento;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        cerrarArchivoSalida();
        return resultado;
    }
    
    public String concatenarReflejo(Secuencia secuencia1)
    {
        inicializarArchivoSalida("./salidaGUI/","salidaGUI.txt" );
        String fragmento;
        String resultado=new String("");        
        //encabezado
        fragmento=encabezado;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //funcion1 
        fragmento=funcion1;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //complemento funcion1()
        fragmento=complementarSecuencia(secuencia1);
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //corrimiento
        fragmento=corrimiento;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //inicializar corrimiento con valor de origen
        fragmento=secuencia1.indiceDeOrigen+";\n";
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //SecuenciaDeEntrada
        fragmento=graficaSecuenciaEntrada1;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //definicionDeFunciones
        fragmento=definicionDeFunciones;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //centrar en el origen
        fragmento="corrimiento=desplazamiento(length(A)-corrimiento)\n";
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //opercion a realizar
        fragmento="z=reflejo(A)\n";
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //inicializarXY()
        inicializarXY(secuencia1.funcion.length);
        
        //cadenaX
        fragmento=cadenaX;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //cadenaY
        fragmento=cadenaY;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //datosAGraficar()
        datosAGraficar(secuencia1.funcion.length);
        
        //cadenaStemX
        fragmento=cadenaStemX;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //cadenaStemY
        fragmento=cadenaStemY;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //cadenaGraficacion
        fragmento=cadenaGraficacion;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //complemento
        fragmento=complemento;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        cerrarArchivoSalida();
        return resultado;
    }
    public String concatenarDesplazamiento(Secuencia secuencia1,String factor)
    {
        inicializarArchivoSalida("./salidaGUI/","salidaGUI.txt");
        String fragmento="";
        String resultado=new String("");        
        //encabezado
        fragmento=encabezado;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //funcion1 
        fragmento=funcion1;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //complemento funcion1()
        fragmento=complementarSecuencia(secuencia1);
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //corrimiento
        fragmento=corrimiento;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //inicializar corrimiento con valor de origen
        fragmento=secuencia1.indiceDeOrigen+";\n";
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //SecuenciaDeEntrada
        fragmento=graficaSecuenciaEntrada1;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //definicionDeFunciones
        fragmento=definicionDeFunciones;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //centrar en el origen
        //opercion a realizar
        fragmento="corrimiento=desplazamiento(corrimiento-1+("+factor+"))\n";
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //inicializarXY()
        inicializarXYLeerSecuenciaA(secuencia1.funcion.length);
        //cadenaX
        fragmento=cadenaX;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //cadenaY
        fragmento=cadenaY;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //datosAGraficar()
        datosAGraficar(secuencia1.funcion.length);
        //cadenaStemX
        fragmento=cadenaStemX;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //cadenaStemY
        fragmento=cadenaStemY;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //cadenaGraficacion
        fragmento=cadenaGraficacion;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //complemento
        fragmento=complemento;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        cerrarArchivoSalida();
        return resultado;
    }
    public String concatenarInterpolacion(Secuencia secuencia1,String factor,String tipoInterpolacion)
    {
        inicializarArchivoSalida("./salidaGUI/","salidaGUI.txt");
        String fragmento="";
        String resultado=new String("");        
        //encabezado
        fragmento=encabezado;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //funcion1 
        fragmento=funcion1;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //complemento funcion1()
        fragmento=complementarSecuencia(secuencia1);
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //corrimiento
        fragmento=corrimiento;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //inicializar corrimiento con valor de origen
        fragmento=secuencia1.indiceDeOrigen+";\n";
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //SecuenciaDeEntrada
        fragmento=graficaSecuenciaEntrada1;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //factorInterpolacion
        fragmento="factorInterpolacion="+factor;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        //definicionDeFunciones
        fragmento=definicionDeFunciones;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        if(tipoInterpolacion.equals("A Cero"))
        {
            fragmento="z=interpolacionACero(A,factorInterpolacion)\n";
            resultado+=fragmento;
            escribirArchivoSalida(fragmento.getBytes());
        }
        else if(tipoInterpolacion.equals("A Escalon"))
        {
            fragmento="z=interpolacionAEscalon(A,factorInterpolacion)\n";
            resultado+=fragmento;
            escribirArchivoSalida(fragmento.getBytes());
        }
        else if(tipoInterpolacion.equals("Lineal"))
        {
            fragmento="z=interpolacionLineal(A,factorInterpolacion)\n";
            resultado+=fragmento;
            escribirArchivoSalida(fragmento.getBytes());
        }
        
        //centrar en el origen
        //opercion a realizar
        fragmento="if(mod(length(A),2)==0)\n" +
"	corrimiento=desplazamiento((corrimiento-1)*factorInterpolacion-(mod(corrimiento,2)))\n" +
"	else\n" +
"	corrimiento=desplazamiento((corrimiento-1)*factorInterpolacion-(mod(corrimiento,2))+1)\n" +
"       end\n";
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //inicializarXY()
        inicializarXY(secuencia1.funcion.length*Integer.parseInt( factor) );
        //cadenaX
        fragmento=cadenaX;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //cadenaY
        fragmento=cadenaY;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //datosAGraficar()
        datosAGraficar(secuencia1.funcion.length*Integer.parseInt( factor));
        //cadenaStemX
        fragmento=cadenaStemX;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //cadenaStemY
        fragmento=cadenaStemY;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //cadenaGraficacion
        fragmento=cadenaGraficacion;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        //complemento
        fragmento=complemento;
        resultado+=fragmento;
        escribirArchivoSalida(fragmento.getBytes());
        
        cerrarArchivoSalida();
        return resultado;
    }
    //Encabezado
    String encabezado="figure\n";
    
    //Secuencias. Este dato se obtendrá de los objetos de la clase secuencia
    String funcion1="A=[";
    String funcion2="B=[";
    
    public String complementarSecuencia(Secuencia secuencia)
    {
        String resultado="";
        for (int i = 0; i < secuencia.funcion.length-1; i++) {
            resultado+=secuencia.funcion[i]+",";
        }
        return resultado+=secuencia.funcion[secuencia.funcion.length-1]+"]\n";
    }
    
    //Corrimiento
    String corrimiento="corrimiento=";
    
    //graficaSecuencia1
    String graficaSecuenciaEntrada1="%graficar funcion 1\n" +
"for n=1:1:length(A)\n" +
"	xFunc1(n)=linspace(n-1,n-1,1)\n" +
"end\n" +
"for n=1:1:length(xFunc1)\n" +
"	XFunc1(n)=xFunc1(n)-corrimiento +1\n" +
"end\n" +
"for n=1:1:length(A)\n" +
"	YFunc1(n)=A(n)\n" +
"end\n" +
"stem(XFunc1,YFunc1)\n" +
"title('Funcion 1');\n" +
"xlabel('n');\n" +
"ylabel('x(n)');\n" +
"print(\"C:\\\\Users\\\\Erick1\\\\Documents\\\\NetBeansProjects\\\\PoyectoSenales\\\\salidaGUI\\\\imagen1.png\",\"-dpng\") \n";

    //graficaSecuenciaEntrada2
    String graficaSecuenciaEntrada2="%graficar funcion 2\n" +
"for n=1:1:length(B)\n" +
"	xFunc2(n)=linspace(n-1,n-1,1)\n" +
"end\n" +
"for n=1:1:length(xFunc2)\n" +
"	XFunc2(n)=xFunc2(n)-corrimiento +1\n" +
"end\n" +
"for n=1:1:length(B)\n" +
"	YFunc2(n)=B(n)\n" +
"end\n" +
"stem(XFunc2,YFunc2)\n" +
"title('Funcion 2');\n" +
"xlabel('n');\n" +
"ylabel('x(n)');\n" +
"print(\"C:\\\\Users\\\\Erick1\\\\Documents\\\\NetBeansProjects\\\\PoyectoSenales\\\\salidaGUI\\\\imagen2.png\",\"-dpng\") \n";

    //Variable de definicionDeFunciones
    String definicionDeFunciones="% declaracion de funciones\n" +
"function [m] = suma (A,B)\n" +
"	m=A+B\n" +
"endfunction\n" +
"function [n] = resta(A,B)\n" +
"	n=A-B\n" +
"endfunction\n" +
"% puede que la multiplicacion sea por un escalar y no un vector por otro\n" +
"function [o] = multiplicacion(A,B)\n" +
"	for n=1:1:length(A)\n" +
"		o(n)=A(n)*B(n)\n" +
"	end\n" +
"endfunction\n" +
"function [d]=desplazamiento(corrimiento)\n" +
"	d=corrimiento*(-1)\n" +
"endfunction\n" +
"function [p]=reflejo(A)\n" +
"	for n=1:1:length(A)\n" +
"		p(n)=A(length(A)-n+1)\n" +
"	end\n" +
"endfunction\n" +
"function [x] = atenuacionAmplificacion (A,factor)\n"+
"	x=A*factor\n"+
"endfunction\n"+
"function [p]=diezmacion(A,origen,factordiezmacion)\n" +
"	% valores detras del origen\n" +
"	modulo=mod(origen,factordiezmacion)\n" +
"	if modulo==0\n" +
"		valor=factordiezmacion\n" +
"	else\n" +
"		valor=modulo\n" +
"	end		\n" +
"\n" +
"	i=1\n" +
"	for n= valor:factordiezmacion:length(A)\n" +
"		p(i)=A(n)\n" +
"		i=i+1\n" +
"	end\n" +
"	\n" +
"%	for n=mod(origen,factordiezmacion):factordiezmacion:length(A)/factordiezmacion\n" +
"%		p(n)=A(n)\n" +
"%	end\n" +
"endfunction\n" +
"function [q]=interpolacionACero(A,factorInterpolacion)\n" +
"	%el valor inicial(i) de los datos a interpolar\n" +
"	i=1\n" +
"	for n=1:1:factorInterpolacion*length(A)\n" +
"		if(mod(n,factorInterpolacion)==1)\n" +
"			q(n)=A(i)\n" +
"			%para almacenar el siguiente valor existente en A\n" +
"			i=i+1\n" +
"		else\n" +
"			q(n)=0\n" +
"		end\n" +
"	end\n" +
"endfunction\n" +
"function [q]=interpolacionAEscalon(A,factorInterpolacion)\n" +
"	%el valor inicial(i) de los datos a interpolar(entrada)\n" +
"	i=1\n" +
"	%hacer la salida del doble del tamanio de la entrada\n" +
"	%n sera el indice de la salida resultante\n" +
"	for n=1:1:factorInterpolacion*length(A)\n" +
"		if(mod(n,factorInterpolacion)~=0)\n" +
"			q(n)=A(i)\n" +
"			%para almacenar el siguiente valor existente en A\n" +
"		\n" +
"		else\n" +
"			\n" +
"			q(n)=A(i)\n" +
"			i=i+1\n" +
"		end\n" +
"	end\n" +
"endfunction	\n" +
"function [q]=interpolacionLineal(A,factorInterpolacion)\n" +
"	%numeroiteracion=2\n" +
"	indiceAlterno=1\n" +
"	\n" +
"	for n=1:1:length(A)-1\n" +
"		delta=(A(n+1)-A(n))/factorInterpolacion\n" +
"		q(indiceAlterno)=A(n)\n" +
"		for i=1:1:factorInterpolacion\n" +
"			indiceAlterno=indiceAlterno+1\n" +
"			q(indiceAlterno)=q(indiceAlterno-1)+delta\n" +
"			%numeroiteracion=numeroiteracion+i\n" +
"		end\n" +
"	end\n" +
"	delta=-q(indiceAlterno)/factorInterpolacion\n" +
"	for n=1:1:factorInterpolacion-1\n" +
"		indiceAlterno=indiceAlterno+1\n" +
"		q(indiceAlterno)=q(indiceAlterno-1)+delta\n" +
"	end\n" +
"endfunction	\n"+ 
"%fin de declaracion\n";
    
    //Invocacion de funciones
    String suma="z=suma(A,B)\n";
    
    String resta="z=resta(A,B)\n";
    
    String multiplicacion="z=multiplicacion(A,B)\n";
    
    String reflejo="z=reflejo(A)\n";
    
    String desplazamiento="corrimiento=desplazamiento(corrimiento)\n";
    
    //Ejemplo:z=diezmacion(A,corrimiento,3)"
    String diezmacion="z=diezmacion(A,corrimiento,";
    
    //Ejemplo:z=interpolacionACero(A,2)
    String interpolacionACero="z=interpolacionACero(A,";
    
    //Ejemplo:z=interpolacionAEscalon(A,6)
    String interpolacionAEscalon="z=interpolacionAEscalon(A,";
    
    //Ejemplo:z=interpolacionLineal(A,2)
    String interpolacionLineal="z=interpolacionLineal(A,";
    
    String cadenaX="";
    String cadenaY="";
    
    public void inicializarXYLeerSecuenciaA(int cantidadDatos){
        
        for (int i = 1; i <= cantidadDatos; i++) {
            cadenaX+="x"+i+"= linspace("+(i-1)+","+(i-1)+",1)';\n";
            cadenaY+="y"+i+"=A("+i+");\n";
        }
                
    }
    public void inicializarXY(int cantidadDatos){
        
        for (int i = 1; i <= cantidadDatos; i++) {
            cadenaX+="x"+i+"= linspace("+(i-1)+","+(i-1)+",1)';\n";
            cadenaY+="y"+i+"=z("+i+");\n";
        }
                
    }
    String cadenaStemX="X=[";
    String cadenaStemY="Y=[";
    public void datosAGraficar(int cantidadDatos){
        for (int i = 1; i < cantidadDatos; i++) {
            cadenaStemX+="x"+i+"+corrimiento,";
            cadenaStemY+="y"+i+",";
        }
        cadenaStemX+="x"+cantidadDatos+"+corrimiento];\n";
        cadenaStemY+="y"+cantidadDatos+"];\n";
    }
    String cadenaGraficacion="stem(X,Y)\n";
    String complemento="title('Bolanos Pulido Carlos. Castillo Luna Erick. 3CV7');\n" +
    "\n" +
    "xlabel('n');\n" +
    "\n" +
    "ylabel('x(n)');\n" +
    "print(\"C:\\\\Users\\\\Erick1\\\\Documents\\\\NetBeansProjects\\\\PoyectoSenales\\\\salidaGUI\\\\imagen3.png\",\"-dpng\")";
    
    
    static FileOutputStream fos=null;
    public static void inicializarArchivoSalida(String ruta, String nombre)
    {
        if(fos==null)
        {
            try {
                fos=new FileOutputStream(ruta+"\\"+nombre);
            } catch (Exception e) {
                System.out.println("no se pudo crear el archivo");
            }
        }
    }
    
    public static void escribirArchivoSalida(byte[] cuerpoArchivo)
    {
        try {
            fos.write(cuerpoArchivo);
            System.out.println("-------ESCRIBIOARCHIVO------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void cerrarArchivoSalida()
    {
        try {
            fos.close();
            fos=null;
            System.out.println("ARCHIVO CERRADO");
        } catch (Exception e) {
            System.out.println("no se pudo cerrar archivo");
        }
    }
}
