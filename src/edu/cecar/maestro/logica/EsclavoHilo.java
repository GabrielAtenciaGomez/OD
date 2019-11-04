/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecar.maestro.logica;

import edu.cecar.interfaces.IServidorEsclavo;

/**
 *
 * @author Gabriel Atencia
 */
public class EsclavoHilo implements Runnable{

    IServidorEsclavo esclavo;

    public EsclavoHilo(IServidorEsclavo esclavo) {
    this.esclavo = esclavo;    
    }
    
    
    
    @Override
    public void run() {
       
        
        
        
    }
    
}
