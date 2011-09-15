/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author Manuel Lorenze
 */
public class TablaZeta {
    
    //--------------- SINGLETON ------------------------------------------------
    
    private static TablaZeta instancia = null;     
 
    private synchronized static void createInstance() {
        if (instancia == null) { 
            instancia = new TablaZeta();
        }
    }
 
    public static TablaZeta getInstance() {
        if (instancia == null) 
            createInstance();
        return instancia;
    }
    
    //-------------------------------------------------------------------------- 
    
    private TablaZeta(){
        this.cargarTabla();
    }
    
    private void cargarTabla(){
        //Levantar de un archivo la tabla Zeta.
    }
    
    public double obtenerProbabilidad(double zeta){
        return 0.6;
    }
    
    public double obtenerZeta(double probabilidad){
        return 1.28;
    }
}
