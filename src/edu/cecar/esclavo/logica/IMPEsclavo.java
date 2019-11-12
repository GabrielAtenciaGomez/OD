/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecar.esclavo.logica;

import edu.cecar.interfaces.IServidorEsclavo;
import edu.cecar.interfaces.IServidorMaestro;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ForkJoinPool;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author 1102883765
 */
public class IMPEsclavo  implements IServidorEsclavo {
     JSONObject respuesta = new JSONObject();
    
     @Override
    public void ordenar(String datos) throws RemoteException {
               int num = Integer.valueOf(datos);
        
        for (int i = 0; i<= num; i++) {
            System.out.println(i);
        }
    }

    public IMPEsclavo() {
    try{
        String url = "rmi://127.0.0.1/MyServer";
        IServidorMaestro maestro = (IServidorMaestro) Naming.lookup(url);
        maestro.recibirObjeto((IServidorEsclavo)UnicastRemoteObject.exportObject(this, 0));
        
    }catch(Exception e){
        e.printStackTrace();
    }
    
    }
    
      public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        
       new IMPEsclavo();
        
     
    }


    @Override
    public String clasificar(String datos) throws RemoteException {
        ForkJoinPool pool = new ForkJoinPool();
        JSONArray numeros = new JSONArray(datos);
        int[] array = new int[numeros.length()];
        for (int i = 0; i < numeros.length();i++) {
            array[i]= numeros.getInt(i);
        }
        
          
        System.out.println("antes"); 
        
        pool.submit(new MergeSort(array, 0, array.length)).join();
        System.out.println("despues");
        
        while (pool.isShutdown()==true) {            
            System.out.println("debtro");
        }
        
        for (int i : array) {
            System.out.println(i);
        }
        return "";
    }

    @Override
    public String getInformacion() throws RemoteException {
      
       
       respuesta.put("cores",Runtime.getRuntime().availableProcessors() );
              
       return  respuesta.toString();
    }


    
}
