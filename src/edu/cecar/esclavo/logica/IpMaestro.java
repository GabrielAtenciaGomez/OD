/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecar.esclavo.logica;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author Gabriel Atencia
 */
public class IpMaestro {
    
    public static String getIP(){
        String resultado="";
        
        try {
            Document document = Jsoup.connect("https://sd-chaki.000webhostapp.com/SistemasDistribuido1.html").get();
            
            Element element = document.selectFirst("h1");
            System.out.println(element.text());
            
        } catch (IOException ex) {
            Logger.getLogger(IpMaestro.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        return resultado;
    
    }
}
