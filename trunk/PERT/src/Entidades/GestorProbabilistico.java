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
            double valorParaAcotar = GestorDeCifrasDecimales.getInstance().obtenerValorParaAcotar();
            return Math.round(probabilidadExtendida * valorParaAcotar) / valorParaAcotar;
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
            double probabilidadAcotada = Math.round(probabilidadExtendida * 1000000) / 1000000.0; // Se acota manteniendo la cantidad de cifras de los valores de la tabla Z.
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
            double valorParaAcotar = GestorDeCifrasDecimales.getInstance().obtenerValorParaAcotar();
            return Math.round(duracionExtendida * valorParaAcotar) / valorParaAcotar;
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
