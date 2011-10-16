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
        this.comienzoTardio = GestorDeCifrasDecimales.getInstance().acotar(comienzoTardio);
    }

    public double obtenerComienzoTemprano() {
        return comienzoTemprano;
    }

    public void setearComienzoTemprano(double comienzoTemprano) {
        this.comienzoTemprano = GestorDeCifrasDecimales.getInstance().acotar(comienzoTemprano);
    }

    public double obtenerFinTardio() {
        return finTardio;
    }

    public void setearFinTardio(double finTardio) {
        this.finTardio = GestorDeCifrasDecimales.getInstance().acotar(finTardio);
    }

    public double obtenerFinTemprano() {
        return finTemprano;
    }

    public void setearFinTemprano(double finTemprano) {
        this.finTemprano = GestorDeCifrasDecimales.getInstance().acotar(finTemprano);
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