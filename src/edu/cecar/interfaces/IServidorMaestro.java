/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecar.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author 1102883765
 */
public interface IServidorMaestro extends Remote{
     public void recibirObjeto(IServidorEsclavo esclavo) throws RemoteException;
}
