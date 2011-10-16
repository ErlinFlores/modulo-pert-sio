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
    
    private GestorDeCifrasDecimales(){
        cifrasDecimales = 3;
    }
    
    public double acotar(double valorParaAcotar){
        double aux = Math.pow(10, cifrasDecimales);
        return Math.round(valorParaAcotar * aux) / aux;
    }
    
    public int obtenerCifrasDecimales(){
        return cifrasDecimales;
    }
}
