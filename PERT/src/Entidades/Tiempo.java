/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidades;

/**
 *
 * @author Usuario
 */
public class Tiempo {

    public Tiempo(int to, int tm, int tp){
        tiempoOptimista = to;
        tiempoMasProbable = tm;
        tiempoPesimista = tp;
        duracion = (to+(4*tm)+tp)/6;
        desviacionEstandar = (tp-to)/6;
        varianza = desviacionEstandar*desviacionEstandar;
    }

    private int tiempoOptimista;
    private int tiempoMasProbable;
    private int tiempoPesimista;
    private int duracion;
    private double desviacionEstandar;
    private double varianza;

    public int getDuracion() {
        return duracion;
    }

    public double getDesviacionEstandar() {
        return desviacionEstandar;
    }

    public double getVarianza() {
        return varianza;
    }

    public int getTiempoOptimista() {
        return tiempoOptimista;
    }

    public int getTiempoMasProbable() {
        return tiempoMasProbable;
    }

    public int getTiempoPesimista() {
        return tiempoPesimista;
    }

    public void setTiemposEstimados(Integer to, Integer tm, Integer tp) {
        if (to != null) {
            tiempoOptimista = to;
        }
        if (tm != null) {
            tiempoMasProbable = tm;
        }
        if (tp != null) {
            tiempoPesimista = tp;
        }
        duracion = (tiempoOptimista+(4*tiempoMasProbable)+tiempoPesimista)/6;
        desviacionEstandar = (tiempoPesimista-tiempoOptimista)/6;
        varianza = desviacionEstandar*desviacionEstandar;
    }
}
