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
        double valorParaAcotar = GestorDeCifrasDecimales.getInstance().obtenerValorParaAcotar();
        tiempoOptimista = Math.round(to*valorParaAcotar)/valorParaAcotar;
        tiempoMasProbable = Math.round(tm*valorParaAcotar)/valorParaAcotar;
        tiempoPesimista = Math.round(tp*valorParaAcotar)/valorParaAcotar;
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
        double valorParaAcotar = GestorDeCifrasDecimales.getInstance().obtenerValorParaAcotar();
        tiempoOptimista = Math.round(to*valorParaAcotar)/valorParaAcotar;
        tiempoMasProbable = Math.round(tm*valorParaAcotar)/valorParaAcotar;
        tiempoPesimista = Math.round(tp*valorParaAcotar)/valorParaAcotar;
    }
    
    public void setearTiempoOptimista(double to){
        double valorParaAcotar = GestorDeCifrasDecimales.getInstance().obtenerValorParaAcotar();
        tiempoOptimista = Math.round(to*valorParaAcotar)/valorParaAcotar;
    }
    
    public double obtenerTiempoOptimista() {
        return tiempoOptimista;
    }

    public void setearTiempoMasProbable(double tm){
        double valorParaAcotar = GestorDeCifrasDecimales.getInstance().obtenerValorParaAcotar();
        tiempoMasProbable = Math.round(tm*valorParaAcotar)/valorParaAcotar;
    }
    
    public double obtenerTiempoMasProbable() {
        return tiempoMasProbable;
    }

    public void setearTiempoPesimista(double tp){
        double valorParaAcotar = GestorDeCifrasDecimales.getInstance().obtenerValorParaAcotar();
        tiempoPesimista = Math.round(tp*valorParaAcotar)/valorParaAcotar;
    }
    
    public double obtenerTiempoPesimista() {
        return tiempoPesimista;
    }    
}