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
    
    private double comienzoTemprano;
    private double comienzoTardio;
    private double finTemprano;
    private double finTardio;
    
    public double getComienzoTardio() {
        return comienzoTardio;
    }

    public void setComienzoTardio(double comienzoTardio) {
        this.comienzoTardio = comienzoTardio;
    }

    public double getComienzoTemprano() {
        return comienzoTemprano;
    }

    public void setComienzoTemprano(double comienzoTemprano) {
        this.comienzoTemprano = comienzoTemprano;
    }

    public double getFinTardio() {
        return finTardio;
    }

    public void setFinTardio(double finTardio) {
        this.finTardio = finTardio;
    }

    public double getFinTemprano() {
        return finTemprano;
    }

    public void setFinTemprano(double finTemprano) {
        this.finTemprano = finTemprano;
    }    
    
    public double getHolgura(){
        return comienzoTardio - comienzoTemprano;
    }
    
    public boolean isTareaCritica(){
        return getHolgura() == 0;
    }
}
