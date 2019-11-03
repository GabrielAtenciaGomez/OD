/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecar.esclavo.logica;

import edu.cecar.interfaces.IServidorEsclavo;
import edu.cecar.interfaces.IServidorMaestro;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import netscape.javascript.JSObject;
import org.json.JSONObject;

/**
 *
 * @author 1102883765
 */
public class IMPEsclavo  implements IServidorEsclavo {
    
    
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
    public String getIP() throws RemoteException {
       String ip="no hay";
         try {
            ip=  InetAddress.getLocalHost().getHostAddress();
         } catch (UnknownHostException ex) {
             Logger.getLogger(IMPEsclavo.class.getName()).log(Level.SEVERE, null, ex);
         }
         return ip;
    }

    @Override
    public void clasificar(JSONObject jSObject) throws RemoteException {
       
        
        
        
    }


    
}
