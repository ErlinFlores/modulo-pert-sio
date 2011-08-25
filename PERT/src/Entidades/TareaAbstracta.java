/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
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
        this.comienzoTardio = comienzoTardio;
    }

    public double obtenerComienzoTemprano() {
        return comienzoTemprano;
    }

    public void setearComienzoTemprano(double comienzoTemprano) {
        this.comienzoTemprano = comienzoTemprano;
    }

    public double obtenerFinTardio() {
        return finTardio;
    }

    public void setearFinTardio(double finTardio) {
        this.finTardio = finTardio;
    }

    public double obtenerFinTemprano() {
        return finTemprano;
    }

    public void setearFinTemprano(double finTemprano) {
        this.finTemprano = finTemprano;
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