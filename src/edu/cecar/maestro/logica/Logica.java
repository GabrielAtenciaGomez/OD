/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecar.maestro.logica;

import com.opencsv.CSVReader;
import edu.cecar.interfaces.IServidorEsclavo;
import edu.cecar.interfaces.IServidorMaestro;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author 1102883765
 */
public class Logica extends UnicastRemoteObject implements IServidorMaestro {

    ArrayList<IServidorEsclavo> esclavos = new ArrayList();
    ArrayList<JSONObject> datos = new ArrayList();
    ArrayList<EsclavoHilo> esclavoHilos = new ArrayList<>();
    ArrayList<ColaNumero> colaNumeros = new ArrayList<>();

    ArrayList<Stack<Long>> paquetesNumero = new ArrayList<>();

    int cantidadCores = 0;
    String[] numeros;
    float paqueteNumero = 0.0f;
    float cantidadNumero = 0.0f;

    public Logica() throws RemoteException {

        super();

    }

    public void cargarArchivo(File file) {

        try {
            CSVReader csvReader;
            csvReader = new CSVReader(new FileReader(file), ',');

            numeros = csvReader.readNext();

            Long[] intNumeros = new Long[numeros.length];

            //for (Long intNumero : intNumeros) {
            // System.out.println(intNumero);
            //}
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Logica.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Logica.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void recibirObjeto(IServidorEsclavo esclavo) throws RemoteException {
        System.out.println("LLegar un esclavo");
        esclavos.add(esclavo);
        datos.add(new JSONObject(esclavo.getInformacion()));
    }

    public void iniciar() throws RemoteException {
        cantidadCores = 0;
        for (JSONObject dato : datos) {
            cantidadCores += dato.getInt("cores");
        }
        cantidadNumero = numeros.length;
        paqueteNumero = cantidadNumero / (float) cantidadCores;

        JSONObject numerosRespartidos = repartirNumeros();

        int i = 1;
        for (IServidorEsclavo esclavo : esclavos) {

            EsclavoHilo esclavoHilo = new EsclavoHilo(esclavo);
            esclavoHilo.setArray(numerosRespartidos.getJSONArray("" + i));
            esclavoHilos.add(esclavoHilo);
            esclavoHilos.get(i - 1).start();
            i++;

        }

        //  for (EsclavoHilo esclavoHilo : esclavoHilos) {
        //  esclavoHilo.start();
        // }
        for (EsclavoHilo esclavo : esclavoHilos) {
            try {
                esclavo.join();
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }

        for (EsclavoHilo esclavo : esclavoHilos) {

            //colaNumeros.add(new ColaNumero(esclavo.getNumeros()));
            int aux = esclavo.getNumeros().length();
            Stack<Long> auxL = new Stack<>();
            for (int j = 0; j < aux; j++) {
                auxL.add(esclavo.getNumeros().getLong(j));
            }
            paquetesNumero.add(auxL);
        }

        System.out.println("tamaño páquete numeros: " + paquetesNumero.size());
        ordenarDatosRecividos();

        colaNumeros.clear();
        esclavoHilos.clear();
        System.out.println("fina app");

    }

    public JSONObject repartirNumeros() {
        int i = 0;
        JSONArray array;
        JSONObject respuesta = new JSONObject();
        int incrementoAnt = 0;
        int incrementoNuevo = 0;
        System.out.println("Numero: " + numeros.length);
        System.out.println("Cantidad paquete numero: " + paqueteNumero);

        //recorrer todos los esclavos para repartir los numeros
        for (int k = 0; k < esclavos.size(); k++) {

            //funcion matematica que permite calcular el numero de 
            System.out.println("incremeto sin redondear: " + (paqueteNumero * (k + 1) * datos.get(k).getInt("cores")));
            incrementoNuevo = (int) (paqueteNumero * (k + 1) * datos.get(k).getInt("cores"));
            System.out.println("incremento: " + incrementoNuevo);

            array = new JSONArray();
            for (i = incrementoAnt; i < incrementoNuevo; i++) {
                //  System.out.println("incremento: " + incrementoNuevo + " i: " + i + " esclavo: " + k + " numero: " + numeros[i]);

                array.put(numeros[i]);

            }
            incrementoAnt = incrementoNuevo;

            respuesta.put("" + (k + 1), array);
        }

        return respuesta;
    }

    public void ordenarDatosRecividos() {
        long aux = 0;
        int pivote = 0;
        int parada = 0;
        do {
            parada = 0;
            for (int i = 0; i < paquetesNumero.size(); i++) {

                if (!paquetesNumero.get(i).isEmpty()) {
                    if (paquetesNumero.get(i).peek() > aux) {
                        aux = paquetesNumero.get(i).peek();
                        pivote = i;
                    } else if (i == paquetesNumero.size() - 1) {
                        System.out.println(aux + " " + paquetesNumero.get(pivote).pop());
                        aux = 0;

                    }

                } else {
                    parada++;
                }
            }

        } while (paquetesNumero.size()  > parada);

    }
}
