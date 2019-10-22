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

/**
 *
 * @author 1102883765
 */
public class Main{
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        
        IServidorMaestro esclavo= (IServidorMaestro)  Naming.lookup("//localhost/MyServer");
        
     
    }

   
}
