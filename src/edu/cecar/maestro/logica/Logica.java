/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecar.maestro.logica;

import edu.cecar.interfaces.IServidorEsclavo;
import edu.cecar.interfaces.IServidorMaestro;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author 1102883765
 */
public class Logica extends UnicastRemoteObject implements IServidorMaestro{

    public Logica() throws RemoteException {

        super();

    }
    
    ArrayList<IServidorEsclavo> esclavos = new ArrayList();
    
    @Override
    public void recibirObjeto(IServidorEsclavo esclavo) throws RemoteException {
        System.out.println("LLegar un esclavo");
        esclavos.add(esclavo);
    }
    
    public void iniciar() throws RemoteException{
        for (IServidorEsclavo esclavo : esclavos) {
            esclavo.ordenar("5");
        }
    }
    
}
