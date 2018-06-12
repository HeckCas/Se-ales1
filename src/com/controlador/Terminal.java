/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controlador;


import java.io.File;
import java.io.InputStream;


/**
 *
 * @author Erick1
 */
public class Terminal {
    String SalidaNormal;
    String SalidaError;
    //static String []comandoCD={"cd","C:\\Octave\\Octave-4.2.1\\bin\\","\n"};
    /*static String []comandos={"cmd","/C","start", "octave"
            ,"C:\\Users\\Erick1\\Documents\\NetBeansProjects\\PoyectoSenales\\salidaGUI\\salidaGUI.txt",
            "\n"};
    */
    
    static String []comandos={"octave"
            //carpeta donde esta alojado el texto generado 
            ,"C:\\Users\\Erick1\\Documents\\NetBeansProjects\\PoyectoSenales\\salidaGUI\\salidaGUI.txt",
            "\n"};
    
    public static void ejecutarComando(/*String ...com*/)
    {
        try {
            
            System.out.println("-----");
            ProcessBuilder pb=new ProcessBuilder(comandos);
            
            pb.command(comandos);
            Process proceso=pb.start();
            
            InputStream is=proceso.getInputStream();
            byte[] bytes=new byte[1000];
            String cadenaSalida="";
            int tamanioLeido=0;
            StringBuilder sb=new StringBuilder();
             while((tamanioLeido=is.read(bytes))!=-1)
            {
                cadenaSalida=new String(bytes,0, tamanioLeido);
                sb.append(cadenaSalida);
                //System.out.println("...\n");
            }
             System.out.println(sb.toString());
            
             //System.out.println("posibles errores");
            is=proceso.getErrorStream();
            bytes=new byte[1000];
            cadenaSalida="";
            tamanioLeido=0;
            sb=new StringBuilder();
             while((tamanioLeido=is.read(bytes))!=-1)
            {
                cadenaSalida=new String(bytes,0, tamanioLeido);
                sb.append(cadenaSalida);
                //System.out.println("...\n");
            }
             System.out.println(sb.toString());
             
            //System.out.println("ProcesoInputStream"+proceso.getInputStream());
            //System.out.println("ProcesoOutputStream"+proceso.getOutputStream());
            
            
        } catch (Exception e) {
            System.out.println("NO se pudo iniciar el proceso");
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        //ejecutarComando(comandoCD);
        ejecutarComando(/*comandos*/);
    }
}
