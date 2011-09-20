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
            return tablaZeta.obtenerProbabilidad(Math.round(zetaExtendido*100)/100.0);
        }
        return -1;
    }
    
    public double calcularDuracion(double probabilidadExtendida){ 
        if ((0 <= probabilidadExtendida) && (probabilidadExtendida <= 1)){ // la probabilidad representada en decimal
            double zetaAcotado = tablaZeta.obtenerZeta(Math.round(probabilidadExtendida*10000)/10000.0);
            return Math.abs((zetaAcotado * desviacionEstandarDelProyecto) + duracionEsperadaDelProyecto);
        }
        return -1;
    }
}
