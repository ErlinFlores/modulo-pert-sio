/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidades;

/**
 * Clase que representa los tiempos estimados de una tarea determinada.
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

    public double obtenerDuracionEsperada(int cifrasDecimales) {
        double valor = Math.pow(10, cifrasDecimales);
        return Math.round(((tiempoOptimista+(4*tiempoMasProbable)+tiempoPesimista)/6)*valor)/valor;
    }

    public double obtenerDesviacionEstandar(int cifrasDecimales) {
        double valor = Math.pow(10, cifrasDecimales);
        return Math.round(((tiempoPesimista-tiempoOptimista)/6)*valor)/valor;
    }

    public double obtenerVarianza(int cifrasDecimales) {
        double de = obtenerDesviacionEstandar(cifrasDecimales);
        double valor = Math.pow(10, cifrasDecimales);
        return Math.round((de*de)*valor)/valor;
    }

    public void setearTiempoEstimado(double to, double tm, double tp){
        tiempoOptimista = to;
        tiempoMasProbable = tm;
        tiempoPesimista = tp;
    }
    
    public void setearTiempoOptimista(double to){
        tiempoOptimista = to;
    }
    
    public double obtenerTiempoOptimista(int cifrasDecimales) {
        double valor = Math.pow(10, cifrasDecimales);
        return Math.round(tiempoOptimista*valor)/valor;
    }

    public void setearTiempoMasProbable(double tm){
        tiempoMasProbable = tm;
    }
    
    public double obtenerTiempoMasProbable(int cifrasDecimales) {
        double valor = Math.pow(10, cifrasDecimales);
        return Math.round(tiempoMasProbable*valor)/valor;
    }

    public void setearTiempoPesimista(double tp){
        tiempoPesimista = tp;
    }
    
    public double obtenerTiempoPesimista(int cifrasDecimales) {
        double valor = Math.pow(10, cifrasDecimales);
        return Math.round(tiempoPesimista*valor)/valor;
    }    
}