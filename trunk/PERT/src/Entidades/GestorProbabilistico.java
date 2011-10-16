/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Entidades.Estados.ResultadoDeCargaDeTablaZeta;

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
    
    /**
     * Método que calcula la probabilidad de culminar el proyecto en un
     * tiempo determinado.
     * @param duracion
     * @return probabilidad de culminación del proyecto
     */
    public Double calcularProbabilidad(double duracionExtendida){
        if (!(tablaZeta.obtenerResultadoDeUltimaCarga().equals(ResultadoDeCargaDeTablaZeta.cargaExitosa))){
            return null;
        }
        if (duracionExtendida > 0){
            double zetaExtendido = (duracionExtendida - duracionEsperadaDelProyecto) / desviacionEstandarDelProyecto;
            double probabilidadExtendida = tablaZeta.obtenerProbabilidad(Math.round(zetaExtendido * 100) / 100.0);
            return GestorDeCifrasDecimales.getInstance().acotar(probabilidadExtendida);
        }
        return null;
    }
    
    /**
     * Método que calcula la posible duración de culminación de un proyecto
     * dada una probabilidad determinada.
     * @param probabilidadExtendida
     * @return posible duración del proyecto
     */
    public Double calcularDuracion(double probabilidadExtendida){ 
        if (!(tablaZeta.obtenerResultadoDeUltimaCarga().equals(ResultadoDeCargaDeTablaZeta.cargaExitosa))){
            return null;
        }
        if ((0 <= probabilidadExtendida) && (probabilidadExtendida <= 1)){ // la probabilidad representada en decimal            
            double zetaAcotado;
            if (probabilidadExtendida < 0.5){
                zetaAcotado = -1 * tablaZeta.obtenerZeta(1 - probabilidadExtendida);
            }else{
                zetaAcotado = tablaZeta.obtenerZeta(probabilidadExtendida);
            }       
            double duracionExtendida = (zetaAcotado * desviacionEstandarDelProyecto) + duracionEsperadaDelProyecto;
            return GestorDeCifrasDecimales.getInstance().acotar(duracionExtendida);
        }
        return null;
    }
    
    /**
     * Se setea la desviación estándar del proyecto.
     * Esto puede darse en el caso de que el proyecto tenga más de un camino
     * crítico, por lo que el usuario puede querer modificar la estrategia
     * de selección de la desviación estándar y por lo tanto la desviación
     * estandar misma.
     * @param desviacionEstandarDelProyecto 
     */
    public void setearDesviacionEstandarDelProyecto(double desviacionEstandarDelProyecto){
        this.desviacionEstandarDelProyecto = desviacionEstandarDelProyecto;
    }
}
