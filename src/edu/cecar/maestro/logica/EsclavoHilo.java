/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecar.maestro.logica;

import edu.cecar.interfaces.IServidorEsclavo;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;

/**
 *
 * @author Gabriel Atencia
 */
public class EsclavoHilo extends Thread{

    IServidorEsclavo esclavo;
     JSONArray array;
    
    private JSONArray numeros;
    

    public EsclavoHilo(IServidorEsclavo esclavo) {
    this.esclavo = esclavo;    
    
    
    
    }
    
    
    
    @Override
    public void run() {
       
        try {
        numeros = new JSONArray(    esclavo.clasificar(array.toString()));
        } catch (RemoteException ex) {
            Logger.getLogger(EsclavoHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    public JSONArray getArray() {
        return array;
    }

    public void setArray(JSONArray array) {
        this.array = array;
    }

    public JSONArray getNumeros() {
        return numeros;
    }

    public void setNumeros(JSONArray numeros) {
        this.numeros = numeros;
    }
    
    
    
    
}
