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

    public int getDuracionEsperada() {
        return (tiempoOptimista+(4*tiempoMasProbable)+tiempoPesimista)/6;
    }

    public double getDesviacionEstandar() {
        return (tiempoPesimista-tiempoOptimista)/6;
    }

    public double getVarianza() {
        double de = getDesviacionEstandar();
        return de*de;
    }

    public void setTiempoEstimado(int to, int tm, int tp){
        tiempoOptimista = to;
        tiempoMasProbable = tm;
        tiempoPesimista = tp;
    }
    
    public void setTiempoOptimista(int to){
        tiempoOptimista = to;
    }
    
    public int getTiempoOptimista() {
        return tiempoOptimista;
    }

    public void setTiempoMasProbable(int tm){
        tiempoMasProbable = tm;
    }
    
    public int getTiempoMasProbable() {
        return tiempoMasProbable;
    }

    public void setTiempoPesimista(int tp){
        tiempoPesimista = tp;
    }
    
    public int getTiempoPesimista() {
        return tiempoPesimista;
    }    
}
