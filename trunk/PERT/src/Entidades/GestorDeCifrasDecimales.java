/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author Manuel Lorenze
 */
public class GestorDeCifrasDecimales {
    
    //--------------- SINGLETON ------------------------------------------------
    
    private static GestorDeCifrasDecimales instancia = null;     
 
    private synchronized static void createInstance() {
        if (instancia == null) { 
            instancia = new GestorDeCifrasDecimales();
        }
    }
 
    public static GestorDeCifrasDecimales getInstance() {
        if (instancia == null) 
            createInstance();
        return instancia;
    }   
    
    //--------------------------------------------------------------------------
    
    private final int cifrasDecimales = 3;
    private double valorAux;
    
    private GestorDeCifrasDecimales(){
        valorAux = Math.pow(10, cifrasDecimales);
    }
    
    public double acotar(double valorParaAcotar){
        return Math.round(valorParaAcotar * valorAux) / valorAux;
    }
    
    public int obtenerCifrasDecimales(){
        return cifrasDecimales;
    }
}
