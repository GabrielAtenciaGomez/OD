/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecar.maestro.logica;

import java.util.Stack;
import org.json.JSONArray;

/**
 *
 * @author Gabriel Atencia
 */
public class ColaNumero implements Comparable<ColaNumero> {

    JSONArray numeros;
    int contador = 0;

    public ColaNumero(JSONArray numeros) {
        this.numeros = numeros;              
        contador = (numeros.length()-1);
    }

    public long numeroActual() {
        if(contador>0)
        return numeros.getLong(contador);
        else return numeros.getLong(0);
    }

    public long nextNumero() {
        
        Stack<Integer> a = new Stack();
        
       
        
        long respuesta=0;
        //System.out.println(contador+" numero actual: "+numeroActual());
        if(contador>-1){
            
                respuesta= numeros.getLong(contador);
        contador--;
        }

        return respuesta;
    }
    
    public int cantidad(){
        return contador;
    }
    
    

    @Override
    public int compareTo(ColaNumero n) {
        int estado = -1;

        if (numeroActual() == n.numeroActual()) {
            estado = 0;

        } else {
            estado = 1;
        }

        return estado;
    }

}
