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
    
    private RedDeTareas redDeTareas;
    
    public GestorDeCalculos(RedDeTareas redDeTareas){
        this.redDeTareas = redDeTareas;
        redDeTareas.resetearTiemposCalculables();
    }
    
    public boolean realizarCalculosPERT(){
        if (redDeTareas.obtenerCantidadDeTareas() == 0){
            return true;
        }
        
        boolean alMenosUnaTareaTieneComienzoTardioCero = false;
        double duracionDelProyecto = 0;        
        for (Tarea tarea : redDeTareas.obtenerTareas()){
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
            if (!redDeTareas.existeAlgunaTareaSucesora(tarea)){
                if (duracionDelProyecto < tarea.obtenerFinTemprano()){
                    duracionDelProyecto = tarea.obtenerFinTemprano();
                }
            }
        }        
        for (int i = redDeTareas.obtenerCantidadDeTareas() - 1; i >= 0; i--){
            Tarea tarea = redDeTareas.obtenerTareas().get(i);
            if (!redDeTareas.existeAlgunaTareaSucesora(tarea)){
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
                if (tarea.obtenerComienzoTardio() == 0){
                    alMenosUnaTareaTieneComienzoTardioCero = true;
                }
            }
        }        
        return alMenosUnaTareaTieneComienzoTardioCero;
    }
}