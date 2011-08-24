/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;


/**
 *
 * @author Manuel Lorenze
 */
public class GestorDeCalculos {
    
    private RedDeTareas tareasDelProyecto;
    
    public GestorDeCalculos(RedDeTareas tareasDelProyecto){
        this.tareasDelProyecto = tareasDelProyecto;
    }
    
    public boolean realizarCalculosPERT(){
        boolean error = false;

        double duracionDelProyecto = 0;        
        for (Tarea tarea : tareasDelProyecto.obtenerTareas()){
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
            if (!tareasDelProyecto.existeAlgunaTareaSucesora(tarea)){
                if (duracionDelProyecto < tarea.obtenerFinTemprano()){
                    duracionDelProyecto = tarea.obtenerFinTemprano();
                }
            }
        }        
        for (int i = tareasDelProyecto.obtenerCantidadDeTareas() - 1; i >= 0; i--){
            Tarea tarea = tareasDelProyecto.obtenerTareas().get(i);
            if (!tareasDelProyecto.existeAlgunaTareaSucesora(tarea)){
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