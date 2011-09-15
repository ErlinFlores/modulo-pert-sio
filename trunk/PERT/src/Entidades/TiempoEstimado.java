/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidades;

/**
 *
 * @author Manuel Lorenze
 */
public class TiempoEstimado {

    private double tiempoOptimista;
    private double tiempoMasProbable;
    private double tiempoPesimista;
    
    public TiempoEstimado(double to, double tm, double tp){
        tiempoOptimista = to;
        tiempoMasProbable = tm;
        tiempoPesimista = tp;
    }    

    public double obtenerDuracionEsperada() {
        return (tiempoOptimista+(4*tiempoMasProbable)+tiempoPesimista)/6;
    }

    public double obtenerDesviacionEstandar() {
        return (tiempoPesimista-tiempoOptimista)/6;
    }

    public double obtenerVarianza() {
        double de = obtenerDesviacionEstandar();
        return de*de;
    }

    public void setearTiempoEstimado(double to, double tm, double tp){
        tiempoOptimista = to;
        tiempoMasProbable = tm;
        tiempoPesimista = tp;
    }
    
    public void setearTiempoOptimista(double to){
        tiempoOptimista = to;
    }
    
    public double obtenerTiempoOptimista() {
        return tiempoOptimista;
    }

    public void setearTiempoMasProbable(double tm){
        tiempoMasProbable = tm;
    }
    
    public double obtenerTiempoMasProbable() {
        return tiempoMasProbable;
    }

    public void setearTiempoPesimista(double tp){
        tiempoPesimista = tp;
    }
    
    public double obtenerTiempoPesimista() {
        return tiempoPesimista;
    }    
}