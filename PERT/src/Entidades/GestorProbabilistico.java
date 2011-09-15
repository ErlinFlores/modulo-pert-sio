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
        double zeta = (tiempo - duracionEsperadaDelProyecto) / desviacionEstandarDelProyecto;
        return tablaZeta.obtenerProbabilidad(zeta);
    }
    
    public double calcularDuracion(double probabilidad){
        double zeta = tablaZeta.obtenerZeta(probabilidad);
        return Math.abs((zeta * desviacionEstandarDelProyecto) + duracionEsperadaDelProyecto);
    }
}
