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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author 1102883765
 */
public class IMPEsclavo implements IServidorEsclavo {

    IServidorMaestro maestro;
    JSONObject respuesta = new JSONObject();

    @Override
    public void ordenar(String datos) throws RemoteException {
        int num = Integer.valueOf(datos);

        for (int i = 0; i <= num; i++) {
            System.out.println(i);
        }
    }

    public IMPEsclavo() {

        conectar();

    }

    public static void main(String[] args) {
        
        
        new IMPEsclavo();

    }

    @Override
    public String clasificar(String datos){
        JSONArray respuesta = new JSONArray();

        ForkJoinPool pool = new ForkJoinPool();
        JSONArray numeros = new JSONArray(datos);

        long[] array = new long[numeros.length()];

        for (int i = 0; i < array.length; i++) {

            array[i] = numeros.getLong(i);

        }

        System.out.println("antes");

        for (long i : array) {
            System.out.println(i);
        }
        pool.submit(new MergeSort(array, 0, array.length)).join();
        System.out.println("despues");

        for (long i : array) {
            System.out.println(i);

            respuesta.put(i);
        }
        return respuesta.toString();
    }

    @Override
    public String getInformacion(){

        respuesta.put("cores", Runtime.getRuntime().availableProcessors());

        return respuesta.toString();
    }

    private void conectar() {
        while (true) {

            try {
                
                

                String url = "rmi://" + IpMaestro.getIP() + "/MyServer";
                maestro = (IServidorMaestro) Naming.lookup(url);
                maestro.recibirObjeto((IServidorEsclavo) UnicastRemoteObject.exportObject(this, 0));               
                System.out.println(maestro.toString());
                System.out.println("Conectado al maestro");
                break;
            } catch (Exception e) {

                System.out.println("intentado conectar.....");
                
                
                
                try {

                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(IMPEsclavo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

}
