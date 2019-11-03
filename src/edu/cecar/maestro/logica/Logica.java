/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecar.maestro.logica;

import com.opencsv.CSVReader;
import edu.cecar.interfaces.IServidorEsclavo;
import edu.cecar.interfaces.IServidorMaestro;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1102883765
 */
public class Logica extends UnicastRemoteObject implements IServidorMaestro {
    ArrayList<IServidorEsclavo> esclavos = new ArrayList();
 String[] nextRecord;
    public Logica() throws RemoteException {

        super();

    }

    public void cargarArchivo(File file)  {

        
       
        try {
            CSVReader csvReader;
            csvReader = new CSVReader(new FileReader(file),',');
            
            nextRecord=csvReader.readNext();
            
           
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
    }

    public void iniciar() throws RemoteException {
        for (IServidorEsclavo esclavo : esclavos) {
            esclavo.ordenar("5");
           

        }
    }
    
    
    

}
