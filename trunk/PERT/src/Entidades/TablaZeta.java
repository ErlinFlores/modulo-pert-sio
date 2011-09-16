/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import EntradaSalida.ManejadorDeArchivos;

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
    
    private double[][] tablaZ;
    
    private TablaZeta(){
        this.cargarTabla();
    }
    
    private void cargarTabla(){
        tablaZ = ManejadorDeArchivos.cargarTablaZ();
    }
    
    public double obtenerProbabilidad(double zeta){
        if ((-3.0 <= zeta) && (zeta <= 3.0)){
            double zetaAbs = Math.abs(zeta);
            int fila = (int)(zetaAbs*10);
            int columna = (int)(Math.round(((zetaAbs*10) - fila)*10));
            if (zeta >= 0){
                return tablaZ[fila][columna];
            }
            return 1 - tablaZ[fila][columna];
        }
        return -1;
    }
    
    public double obtenerZeta(double probabilidad){
        return 1.28;
    }
}
