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
    
    private int cifrasDecimales;
    private double valorParaAcotar;
    
    private GestorDeCifrasDecimales(){
        cifrasDecimales = 2;
        valorParaAcotar = Math.pow(10, cifrasDecimales);
    }
    
    public int obtenerCifrasDecimales(){
        return cifrasDecimales;
    }
    
    public double obtenerValorParaAcotar(){
        return valorParaAcotar;
    }
}
