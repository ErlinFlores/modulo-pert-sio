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
    
    private List<Tarea> tareasDelProyecto;
    
    public GestorDeCalculos(List<Tarea> tareasDelProyecto){
        this.tareasDelProyecto = tareasDelProyecto;
    }
    
    public List<Tarea> realizarCalculos(){        
        boolean error = false;
        double duracionDelProyecto = 0;        
        for (Tarea tarea : tareasDelProyecto){
            if (!tarea.tieneTareasPrecedentes()){
                tarea.setComienzoTemprano(0);
                tarea.setFinTemprano(tarea.getDuracionEsperada());
            }else{
                double finTempranoMasGrandeDeLosPrecedentes = 0;
                for (Tarea tareaPrecedente : tarea.getPrecedencia().getTareas()){
                    double finTemprano = tareaPrecedente.getFinTemprano();
                    if (finTempranoMasGrandeDeLosPrecedentes < finTemprano){
                        finTempranoMasGrandeDeLosPrecedentes = finTemprano;
                    }
                }
                tarea.setComienzoTemprano(finTempranoMasGrandeDeLosPrecedentes);
                tarea.setFinTemprano(finTempranoMasGrandeDeLosPrecedentes + tarea.getDuracionEsperada());                
            }
            if (!tieneSucesores(tarea)){
                if (duracionDelProyecto < tarea.getFinTemprano()){
                    duracionDelProyecto = tarea.getFinTemprano();
                }
            }
        }        
        for (int i = tareasDelProyecto.size()-1; i >= 0; i--){
            Tarea tarea = tareasDelProyecto.get(i);
            if (!tieneSucesores(tarea)){
                tarea.setFinTardio(duracionDelProyecto);               
            }
            double comienzoTardio = tarea.getFinTardio() - tarea.getDuracionEsperada();
            tarea.setComienzoTardio(comienzoTardio);            
            for (Tarea tareaPrecedente : tarea.getPrecedencia().getTareas()){
                double finTardioActual = tareaPrecedente.getFinTardio();
                if ((finTardioActual > comienzoTardio) || (finTardioActual == -1)){
                    tareaPrecedente.setFinTardio(comienzoTardio);
                }
            }
            if (!tarea.tieneTareasPrecedentes()){
                if (tarea.getComienzoTardio() != 0){
                    error = true;
                }
            }
        }
        if (!error)
            return tareasDelProyecto;
        else
            return null;
    }
    
    private boolean tieneSucesores(Tarea tareaOrigen){
        for (Tarea tarea : tareasDelProyecto){
            if (tarea.getPrecedencia().esPrecedente(tareaOrigen.getId())){
                return true;
            }
        }
        return false;
    }
}
