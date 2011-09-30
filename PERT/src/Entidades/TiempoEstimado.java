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
    
    private double duracionEsperada;
    private double varianza;
    private double desviacionEstandar;
    
    public TiempoEstimado(double to, double tm, double tp){
        double valorParaAcotar = GestorDeCifrasDecimales.getInstance().obtenerValorParaAcotar();
        tiempoOptimista = Math.round(to*valorParaAcotar)/valorParaAcotar;
        tiempoMasProbable = Math.round(tm*valorParaAcotar)/valorParaAcotar;
        tiempoPesimista = Math.round(tp*valorParaAcotar)/valorParaAcotar;
        duracionEsperada = Math.round(calcularDuracionEsperada()*valorParaAcotar)/valorParaAcotar;
        desviacionEstandar = Math.round(calcularDesviacionEstandar()*valorParaAcotar)/valorParaAcotar;
        varianza = Math.round(calcularVarianza()*valorParaAcotar)/valorParaAcotar;        
    }    
    
    private double calcularDuracionEsperada(){
        return (tiempoOptimista+(4*tiempoMasProbable)+tiempoPesimista)/6;
    }
    
    private double calcularDesviacionEstandar(){
        return (tiempoPesimista-tiempoOptimista)/6;
    }
    
    private double calcularVarianza(){
        return desviacionEstandar * desviacionEstandar;
    }

    public double obtenerDuracionEsperada() {
        return duracionEsperada;
    }

    public double obtenerDesviacionEstandar() {
        return desviacionEstandar;
    }

    public double obtenerVarianza() {            
        return varianza;
    }

    public void setearTiempoEstimado(double to, double tm, double tp){
        double valorParaAcotar = GestorDeCifrasDecimales.getInstance().obtenerValorParaAcotar();
        tiempoOptimista = Math.round(to*valorParaAcotar)/valorParaAcotar;
        tiempoMasProbable = Math.round(tm*valorParaAcotar)/valorParaAcotar;
        tiempoPesimista = Math.round(tp*valorParaAcotar)/valorParaAcotar;
        duracionEsperada = Math.round(calcularDuracionEsperada()*valorParaAcotar)/valorParaAcotar;
        desviacionEstandar = Math.round(calcularDesviacionEstandar()*valorParaAcotar)/valorParaAcotar;
        varianza = Math.round(calcularVarianza()*valorParaAcotar)/valorParaAcotar;      
    }
    
    public void setearTiempoOptimista(double to){
        double valorParaAcotar = GestorDeCifrasDecimales.getInstance().obtenerValorParaAcotar();
        tiempoOptimista = Math.round(to*valorParaAcotar)/valorParaAcotar;
        duracionEsperada = Math.round(calcularDuracionEsperada()*valorParaAcotar)/valorParaAcotar;
        desviacionEstandar = Math.round(calcularDesviacionEstandar()*valorParaAcotar)/valorParaAcotar;
        varianza = Math.round(calcularVarianza()*valorParaAcotar)/valorParaAcotar;      
    }
    
    public double obtenerTiempoOptimista() {
        return tiempoOptimista;
    }

    public void setearTiempoMasProbable(double tm){
        double valorParaAcotar = GestorDeCifrasDecimales.getInstance().obtenerValorParaAcotar();
        tiempoMasProbable = Math.round(tm*valorParaAcotar)/valorParaAcotar;
        duracionEsperada = Math.round(calcularDuracionEsperada()*valorParaAcotar)/valorParaAcotar;
    }
    
    public double obtenerTiempoMasProbable() {
        return tiempoMasProbable;
    }

    public void setearTiempoPesimista(double tp){
        double valorParaAcotar = GestorDeCifrasDecimales.getInstance().obtenerValorParaAcotar();
        tiempoPesimista = Math.round(tp*valorParaAcotar)/valorParaAcotar;
        duracionEsperada = Math.round(calcularDuracionEsperada()*valorParaAcotar)/valorParaAcotar;
        desviacionEstandar = Math.round(calcularDesviacionEstandar()*valorParaAcotar)/valorParaAcotar;
        varianza = Math.round(calcularVarianza()*valorParaAcotar)/valorParaAcotar;      
    }
    
    public double obtenerTiempoPesimista() {
        return tiempoPesimista;
    }    
}