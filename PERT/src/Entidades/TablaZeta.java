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
    
    public double obtenerProbabilidad(double zetaAcotado){
        if ((-3.09 <= zetaAcotado) && (zetaAcotado <= 3.09)){
            double zetaAcotadoAbs = Math.abs(zetaAcotado);
            int fila = (int)(zetaAcotadoAbs*10);
            int columna = (int)(((zetaAcotadoAbs*10)-fila)*10);
            double probabilidadExtendida;
            if (zetaAcotado >= 0){
                probabilidadExtendida = tablaZ[fila][columna];
            }else{
                probabilidadExtendida = 1 - tablaZ[fila][columna];
            }            
            return Math.round(probabilidadExtendida*10000)/10000.0;
        }else if(zetaAcotado > 3.09){
            return 1;
        }else{
            return 0;
        }        
    }
    
    public double obtenerZeta(double probabilidad){
        double diferenciaAnterior = Math.abs(probabilidad - tablaZ[0][0]);
        int filaAnterior = 0;
        int columnaAnterior = 0;
        for(int filaActual = 0; filaActual <= 30; filaActual++){
            for(int columnaActual = 0; columnaActual <= 9; columnaActual++){
                double diferenciaActual = Math.abs(probabilidad - tablaZ[filaActual][columnaActual]);
                if ((diferenciaAnterior <= diferenciaActual) && !((filaActual == 0) && (columnaActual == 0))){
                    return (filaAnterior/10.0)+(columnaAnterior/100.0);
                }                
                diferenciaAnterior = diferenciaActual;
                columnaAnterior = columnaActual;
            }
            filaAnterior = filaActual;
        }
        return (30/10.0)+(9/100.0);
    }
}
