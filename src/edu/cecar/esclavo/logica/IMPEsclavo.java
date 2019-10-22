/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecar.esclavo.logica;

import edu.cecar.interfaces.IServidorEsclavo;
import java.rmi.RemoteException;

/**
 *
 * @author 1102883765
 */
public class IMPEsclavo  implements IServidorEsclavo {
    
    
     @Override
    public void ordenar(String datos) throws RemoteException {
               int num = Integer.valueOf(datos);
        
        for (int i = 0; i < num; i++) {
            System.out.println(i);
        }
    }
}
