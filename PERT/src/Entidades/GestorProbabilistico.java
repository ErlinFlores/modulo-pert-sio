/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author Manuel Lorenze
 */
public class GestorProbabilistico {
        
    private TablaZeta tablaZeta;
    private double duracionEsperadaDelProyecto;
    private double desviacionEstandarDelProyecto;
    
    public GestorProbabilistico(double duracionEsperadaDelProyecto, double desviacionEstandarDelProyecto){
        tablaZeta = TablaZeta.getInstance();
        this.duracionEsperadaDelProyecto = duracionEsperadaDelProyecto;
        this.desviacionEstandarDelProyecto = desviacionEstandarDelProyecto;
    }
    
    public double calcularProbabilidad(double tiempo){
        if (tiempo > 0){
            double zetaExtendido = (tiempo - duracionEsperadaDelProyecto) / desviacionEstandarDelProyecto;
            return tablaZeta.obtenerProbabilidad(Math.round(zetaExtendido * 100) / 100.0);
        }
        return -1;
    }
    
    public double calcularDuracion(double probabilidadExtendida){ 
        if ((0 <= probabilidadExtendida) && (probabilidadExtendida <= 1)){ // la probabilidad representada en decimal
            double probabilidadAcotada = Math.round(probabilidadExtendida * 10000) / 10000.0;
            double zetaAcotadoAbs;
            boolean zetaNegativo;
            if (probabilidadAcotada < 0.5){
                zetaAcotadoAbs = tablaZeta.obtenerZeta(1 - probabilidadAcotada);
                zetaNegativo = true;
            }else{
                zetaAcotadoAbs = tablaZeta.obtenerZeta(probabilidadAcotada);
                zetaNegativo = false;
            }   
            double zetaAcotado;
            if (zetaNegativo){
                zetaAcotado = -1 * zetaAcotadoAbs;
            }else{
                zetaAcotado = zetaAcotadoAbs;
            }     
            double duracionExtendida = (zetaAcotado * desviacionEstandarDelProyecto) + duracionEsperadaDelProyecto;
            return Math.round(duracionExtendida * 10000) / 10000.0;
        }
        return -1;
    }
}
