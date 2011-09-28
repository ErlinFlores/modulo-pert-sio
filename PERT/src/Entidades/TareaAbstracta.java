/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 * Clase que representa información relativa a una tarea por los cálculos PERT.
 * @author Manuel Lorenze
 */
public abstract class TareaAbstracta {
    
    protected double comienzoTemprano;
    protected double comienzoTardio;
    protected double finTemprano;
    protected double finTardio;
    
    public double obtenerComienzoTardio() {
        return comienzoTardio;
    }

    public void setearComienzoTardio(double comienzoTardio) {        
        double valorParaAcotar = GestorDeCifrasDecimales.getInstance().obtenerValorParaAcotar();
        this.comienzoTardio = Math.round(comienzoTardio*valorParaAcotar)/valorParaAcotar;
    }

    public double obtenerComienzoTemprano() {
        return comienzoTemprano;
    }

    public void setearComienzoTemprano(double comienzoTemprano) {
        double valorParaAcotar = GestorDeCifrasDecimales.getInstance().obtenerValorParaAcotar();
        this.comienzoTemprano = Math.round(comienzoTemprano*valorParaAcotar)/valorParaAcotar;
    }

    public double obtenerFinTardio() {
        return finTardio;
    }

    public void setearFinTardio(double finTardio) {
        double valorParaAcotar = GestorDeCifrasDecimales.getInstance().obtenerValorParaAcotar();
        this.finTardio = Math.round(finTardio*valorParaAcotar)/valorParaAcotar;
    }

    public double obtenerFinTemprano() {
        return finTemprano;
    }

    public void setearFinTemprano(double finTemprano) {
        double valorParaAcotar = GestorDeCifrasDecimales.getInstance().obtenerValorParaAcotar();
        this.finTemprano = Math.round(finTemprano*valorParaAcotar)/valorParaAcotar;
    }    
    
    public double obtenerHolgura(){
        return comienzoTardio - comienzoTemprano;
    }
    
    public boolean esTareaCritica(){
        return obtenerHolgura() == 0;
    }
    
    public void resetearTiemposCalculables(){
        this.comienzoTemprano = -1;
        this.finTemprano = -1;
        this.comienzoTardio = -1;
        this.finTardio = -1;     
    }
}