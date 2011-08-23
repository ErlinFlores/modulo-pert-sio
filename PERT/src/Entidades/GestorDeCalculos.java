/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.List;

/**
 *
 * @author Manuel Lorenze
 */
public class GestorDeCalculos {
    
    private Proyecto proyecto;
    
    public GestorDeCalculos(Proyecto proyecto){
        this.proyecto = proyecto;
    }
    
    public boolean realizarCalculos(){
        boolean error = false;

        double duracionDelProyecto = 0;        
        for (Tarea tarea : proyecto.obtenerTareas()){
            if (!tarea.tieneTareasPrecedentes()){
                tarea.setearComienzoTemprano(0);
                tarea.setearFinTemprano(tarea.obtenerDuracionEsperada());
            }else{
                double finTempranoMasGrandeDeLosPrecedentes = 0;
                for (Tarea tareaPrecedente : tarea.obtenerPrecedencia().obtenerTareas()){
                    double finTemprano = tareaPrecedente.obtenerFinTemprano();
                    if (finTempranoMasGrandeDeLosPrecedentes < finTemprano){
                        finTempranoMasGrandeDeLosPrecedentes = finTemprano;
                    }
                }
                tarea.setearComienzoTemprano(finTempranoMasGrandeDeLosPrecedentes);
                tarea.setearFinTemprano(finTempranoMasGrandeDeLosPrecedentes + tarea.obtenerDuracionEsperada());
            }
            if (!proyecto.existeAlgunSucesor(tarea)){
                if (duracionDelProyecto < tarea.obtenerFinTemprano()){
                    duracionDelProyecto = tarea.obtenerFinTemprano();
                }
            }
        }        
        for (int i = proyecto.obtenerCantidadDeTareas() - 1; i >= 0; i--){
            Tarea tarea = proyecto.obtenerTareas().get(i);
            if (!proyecto.existeAlgunSucesor(tarea)){
                tarea.setearFinTardio(duracionDelProyecto);
            }
            double comienzoTardio = tarea.obtenerFinTardio() - tarea.obtenerDuracionEsperada();
            tarea.setearComienzoTardio(comienzoTardio);
            for (Tarea tareaPrecedente : tarea.obtenerPrecedencia().obtenerTareas()){
                double finTardioActual = tareaPrecedente.obtenerFinTardio();
                if ((finTardioActual > comienzoTardio) || (finTardioActual == -1)){
                    tareaPrecedente.setearFinTardio(comienzoTardio);
                }
            }
            if (!tarea.tieneTareasPrecedentes()){
                if (tarea.obtenerComienzoTardio() != 0){
                    error = true;
                }
            }
        }
        return !error;
    }
}