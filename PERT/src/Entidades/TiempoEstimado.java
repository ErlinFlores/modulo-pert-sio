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
        this.tiempoOptimista = GestorDeCifrasDecimales.getInstance().acotar(to);
        this.tiempoMasProbable = GestorDeCifrasDecimales.getInstance().acotar(tm);
        this.tiempoPesimista = GestorDeCifrasDecimales.getInstance().acotar(tp);
        this.duracionEsperada = GestorDeCifrasDecimales.getInstance().acotar(calcularDuracionEsperada());
        this.desviacionEstandar = GestorDeCifrasDecimales.getInstance().acotar(calcularDesviacionEstandar());
        this.varianza = GestorDeCifrasDecimales.getInstance().acotar(calcularVarianza());      
    }    
    
    private double calcularDuracionEsperada(){
        return (this.tiempoOptimista+(4*this.tiempoMasProbable)+this.tiempoPesimista)/6;
    }
    
    private double calcularDesviacionEstandar(){
        return (this.tiempoPesimista-this.tiempoOptimista)/6;
    }
    
    private double calcularVarianza(){
        return this.desviacionEstandar * this.desviacionEstandar;
    }

    public double obtenerDuracionEsperada() {
        return this.duracionEsperada;
    }

    public double obtenerDesviacionEstandar() {
        return this.desviacionEstandar;
    }

    public double obtenerVarianza() {            
        return this.varianza;
    }

    public void setearTiempoEstimado(double to, double tm, double tp){
        this.tiempoOptimista = GestorDeCifrasDecimales.getInstance().acotar(to);
        this.tiempoMasProbable = GestorDeCifrasDecimales.getInstance().acotar(tm);
        this.tiempoPesimista = GestorDeCifrasDecimales.getInstance().acotar(tp);
        this.duracionEsperada = GestorDeCifrasDecimales.getInstance().acotar(calcularDuracionEsperada());
        this.desviacionEstandar = GestorDeCifrasDecimales.getInstance().acotar(calcularDesviacionEstandar());
        this.varianza = GestorDeCifrasDecimales.getInstance().acotar(calcularVarianza());      
    }
    
    public void setearTiempoOptimista(double to){
        this.tiempoOptimista = GestorDeCifrasDecimales.getInstance().acotar(to);
        this.duracionEsperada = GestorDeCifrasDecimales.getInstance().acotar(calcularDuracionEsperada());
        this.desviacionEstandar = GestorDeCifrasDecimales.getInstance().acotar(calcularDesviacionEstandar());
        this.varianza = GestorDeCifrasDecimales.getInstance().acotar(calcularVarianza());     
    }
    
    public double obtenerTiempoOptimista() {
        return this.tiempoOptimista;
    }

    public void setearTiempoMasProbable(double tm){
        this.tiempoMasProbable = GestorDeCifrasDecimales.getInstance().acotar(tm);
        this.duracionEsperada = GestorDeCifrasDecimales.getInstance().acotar(calcularDuracionEsperada());
    }
    
    public double obtenerTiempoMasProbable() {
        return this.tiempoMasProbable;
    }

    public void setearTiempoPesimista(double tp){
        this.tiempoPesimista = GestorDeCifrasDecimales.getInstance().acotar(tp);
        this.duracionEsperada = GestorDeCifrasDecimales.getInstance().acotar(calcularDuracionEsperada());
        this.desviacionEstandar = GestorDeCifrasDecimales.getInstance().acotar(calcularDesviacionEstandar());
        this.varianza = GestorDeCifrasDecimales.getInstance().acotar(calcularVarianza());      
    }
    
    public double obtenerTiempoPesimista() {
        return this.tiempoPesimista;
    }    
}