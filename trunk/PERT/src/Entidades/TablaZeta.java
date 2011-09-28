/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Entidades.Estados.ResultadoDeCargaDeTablaZeta;
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
    private ResultadoDeCargaDeTablaZeta resultadoDeCargaDeTablaZeta;
    
    private TablaZeta(){
        tablaZ = ManejadorDeArchivos.cargarTablaZ();
        resultadoDeCargaDeTablaZeta = ManejadorDeArchivos.resultadoDeCargaDeTablaZeta;
    }
    
    public ResultadoDeCargaDeTablaZeta obtenerResultadoDeUltimaCarga(){
        return resultadoDeCargaDeTablaZeta;
    }
    
    /**
     * Método que retorna una probabilidad en base a un valor "zeta" determinado.
     * Hace uso de la tabla Zeta (Probabilidades de una Normal Estandar).
     * @param zetaAcotado
     * @return 
     */
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
            return probabilidadExtendida;
        }else if(zetaAcotado > 3.09){
            return 1;
        }else{
            return 0;
        }        
    }
    
    /**
     * Método que retorna un valor "zeta" en base a una probabilidad determinada.
     * Hace uso de la tabla Zeta (Probabilidades de una Normal Estandar).
     * @param probabilidad
     * @return 
     */
    public double obtenerZeta(double probabilidad){
        double diferenciaAnterior = 0;
        int filaAnterior = -1;
        int columnaAnterior = -1;
        for(int filaActual = 0; filaActual <= 30; filaActual++){
            for(int columnaActual = 0; columnaActual <= 9; columnaActual++){
                double diferenciaActual = tablaZ[filaActual][columnaActual] - probabilidad;
                if ((0 <= diferenciaActual) && !((filaActual == 0) && (columnaActual == 0))){
                    diferenciaAnterior = Math.abs(diferenciaAnterior);
                    if (diferenciaAnterior >= diferenciaActual){
                        return (filaActual/10.0)+(columnaActual/100.0);
                    }else{
                        if (!(columnaActual == 0)){
                            return (filaActual/10.0)+(columnaAnterior/100.0);
                        }else{
                            return (filaAnterior/10.0)+(columnaAnterior/100.0);
                        }
                    }                        
                }                
                diferenciaAnterior = diferenciaActual;
                columnaAnterior = columnaActual;
            }
            filaAnterior = filaActual;
        }
        return (filaAnterior/10.0)+(columnaAnterior/100.0);
    }
}
