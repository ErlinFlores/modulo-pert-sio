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

    private int tiempoOptimista;
    private int tiempoMasProbable;
    private int tiempoPesimista;
    
    public TiempoEstimado(int to, int tm, int tp){
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

    public void setearTiempoEstimado(int to, int tm, int tp){
        tiempoOptimista = to;
        tiempoMasProbable = tm;
        tiempoPesimista = tp;
    }
    
    public void setearTiempoOptimista(int to){
        tiempoOptimista = to;
    }
    
    public int obtenerTiempoOptimista() {
        return tiempoOptimista;
    }

    public void setearTiempoMasProbable(int tm){
        tiempoMasProbable = tm;
    }
    
    public int obtenerTiempoMasProbable() {
        return tiempoMasProbable;
    }

    public void setearTiempoPesimista(int tp){
        tiempoPesimista = tp;
    }
    
    public int obtenerTiempoPesimista() {
        return tiempoPesimista;
    }    
}