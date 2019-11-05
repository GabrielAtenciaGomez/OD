/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecar.maestro.logica;

import edu.cecar.interfaces.IServidorEsclavo;
import org.json.JSONArray;

/**
 *
 * @author Gabriel Atencia
 */
public class EsclavoHilo extends Thread{

    IServidorEsclavo esclavo;
    JSONArray array;
    

    public EsclavoHilo(IServidorEsclavo esclavo) {
    this.esclavo = esclavo;    
    
    
    
    }
    
    
    
    @Override
    public void run() {
       
        for (int i = 0; i < 1000; i++) {
            System.out.println(""+array.get(0)+": "+i);
        }
        
        
    }

    public JSONArray getArray() {
        return array;
    }

    public void setArray(JSONArray array) {
        this.array = array;
    }
    
    
    
    
}
