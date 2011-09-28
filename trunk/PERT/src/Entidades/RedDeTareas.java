/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidades;

import Entidades.Estados.EstrategiaDeSeleccionDeDesvEst;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa el grafo de tareas de un proyecto.
 * @author mlorenze
 */
public class RedDeTareas {

    private List<Tarea> tareas;
    private double duracionDelProyecto;
    private List<CaminoCritico> caminosCriticos;
    private boolean ultimoCalculoPERTesCorrecto;
    private int cifrasDecimales;

    public RedDeTareas(List<Tarea> tareas){
        this.tareas = tareas;
        this.duracionDelProyecto = -1;
        this.caminosCriticos = new ArrayList<CaminoCritico>();
        this.ultimoCalculoPERTesCorrecto = false;
        this.cifrasDecimales = GestorDeCifrasDecimales.getInstance().obtenerCifrasDecimales();
    }

    public void agregarTarea(Tarea tarea){
        this.tareas.add(tarea);
        this.ultimoCalculoPERTesCorrecto = false;
    }
    
    public Tarea modificarTarea(int idTarea, String descripcion){
        Tarea tarea = obtenerTareaPorID(idTarea);
        tarea.setearDescripcion(descripcion);
        this.ultimoCalculoPERTesCorrecto = false;
        return tarea;
    }
    
    public void borrarTarea(int idTarea){
        Tarea tarea = obtenerTareaPorID(idTarea);
        borrarTareaDePrecedencias(tarea);
        this.tareas.remove(tarea);
        this.ultimoCalculoPERTesCorrecto = false;
    }
    
    private void borrarTareaDePrecedencias(Tarea tarea){
        for (Tarea tareaDelProyecto : tareas){
            Precedencia precedenciaDeTareaDelProyecto = tareaDelProyecto.obtenerPrecedencia();
            if (precedenciaDeTareaDelProyecto.esPrecedente(tarea.obtenerId())){
                precedenciaDeTareaDelProyecto.borrarTarea(tarea);
            }
        }
    }
    
    public int obtenerCantidadDeCaminosCriticos(){
        return caminosCriticos.size();
    }
    
    public Tarea obtenerTareaPorID(int id){
        for (Tarea tarea : tareas){
            if (tarea.obtenerId() == id){
                return tarea;
            }
        }
        return null;
    }
    
    public boolean existeAlgunaTareaSucesora(Tarea tareaOrigen){
        for (Tarea tarea : tareas){
            if (tarea.obtenerPrecedencia().esPrecedente(tareaOrigen.obtenerId())){
                return true;
            }
        }
        return false;
    }

    /**
     * Se obtiene una lista de tareas candidatas a ser precedentes de una tarea determinada, teniendo
     * en cuenta que dichas tareas candidatas no sean sucesoras de dicha tarea (evitando asi un ciclo en la red,
     * los cuales no estan permitidos).
     * @param tarea
     * @return 
     */
    public List<Tarea> obtenerPosiblesTareasPrecedentes(Tarea tarea){
        Precedencia tareasPrecedentes = tarea.obtenerPrecedencia();
        List<Tarea> tareasHabilitadasParaSerPrecedentes = new ArrayList<Tarea>();
        for (Tarea posibleTareaPrecedente : tareas){
            int idPosibleTareaPrecedente = posibleTareaPrecedente.obtenerId();
            if ((!tareasPrecedentes.esPrecedente(idPosibleTareaPrecedente)) && (idPosibleTareaPrecedente != tarea.obtenerId())){
                if (!hayCamino(tarea, posibleTareaPrecedente)){
                    tareasHabilitadasParaSerPrecedentes.add(posibleTareaPrecedente);
                }
            }
        }       
        return tareasHabilitadasParaSerPrecedentes;
    }
    
     /**
     * Método que determina si existe un camino entre dos tareas específicas.
     * @param tareaInicio
     * @param tareaDestino
     * @return 
     */
    private boolean hayCamino(Tarea tareaInicio, Tarea tareaDestino){
        Precedencia tareasPrecedentesDeTareaDestino = tareaDestino.obtenerPrecedencia();
        boolean existeCamino = false;
        for (Tarea tarea : tareasPrecedentesDeTareaDestino.obtenerTareas()){
            if (!(tarea.obtenerId() == tareaInicio.obtenerId())){
                existeCamino = hayCamino(tareaInicio, tarea);
            }else{
                return true;
            }
            if (existeCamino){
                break;
            }
        }
        return existeCamino;
    }  
    
    public int obtenerCantidadDeTareas(){
        return tareas.size();
    }
    
    public List<Tarea> obtenerTareas(){
        return tareas;
    }
    
    public double obtenerDuracionDelProyecto(){
        return duracionDelProyecto;
    }
    
    public double obtenerDesviacionEstandarDelProyecto(EstrategiaDeSeleccionDeDesvEst estrategia){
        switch (estrategia){
            case suma:{
                double sumaDesvEst = 0;
                for (CaminoCritico caminoCritico : caminosCriticos){
                    sumaDesvEst += caminoCritico.obtenerDesviacionEstandar();
                }
                return sumaDesvEst;
            }
            case promedio:{
                double sumaDesvEst = 0;
                for (CaminoCritico caminoCritico : caminosCriticos){
                    sumaDesvEst += caminoCritico.obtenerDesviacionEstandar();
                }
                return sumaDesvEst/caminosCriticos.size();
            }    
            case mayor:{
                double mayorDesvEst = 0;
                for (CaminoCritico caminoCritico : caminosCriticos){
                    if (caminoCritico.obtenerDesviacionEstandar() > mayorDesvEst){
                        mayorDesvEst = caminoCritico.obtenerDesviacionEstandar();
                    }
                }
                return mayorDesvEst;
            }
            case ninguna:{
                if (caminosCriticos.size() == 1){
                    return caminosCriticos.get(0).obtenerDesviacionEstandar();
                }
                break;
            }
        }        
        return -1;
    }
    
    public List<CaminoCritico> obtenerCaminosCriticos(){
        return caminosCriticos;
    }
    
    public boolean elUltimoCalculoPERTesCorrecto(){
        return ultimoCalculoPERTesCorrecto;
    }
    
    /**
     * Se resetean todos los valores relacionados al analisis PERT para poder realizar nuevamente los cálculos.
     */
    private void reset(){
        for (Tarea tarea : tareas){
            tarea.resetearTiemposCalculables();
        }
        this.duracionDelProyecto = -1;
        this.caminosCriticos = new ArrayList<CaminoCritico>();
        this.ultimoCalculoPERTesCorrecto = false;
    }
    
    /**
     * Método que realiza los cálculos de PERT relacionados con el 
     * análisis de los tiempos de cada tarea y además determina duración del
     * proyecto.
     * @return que el cálculo es correcto
     */
    public boolean realizarCalculosPERT(){
        reset();
        caminosCriticos = new ArrayList<CaminoCritico>();
        if (obtenerCantidadDeTareas() == 0){
            return true;
        }        
        boolean alMenosUnaTareaTieneComienzoTardioCero = false;
        duracionDelProyecto = 0;        
        for (Tarea tarea : obtenerTareas()){
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
            if (!existeAlgunaTareaSucesora(tarea)){
                if (duracionDelProyecto < tarea.obtenerFinTemprano()){
                    duracionDelProyecto = tarea.obtenerFinTemprano();
                }
            }
        }        
        for (int i = obtenerCantidadDeTareas() - 1; i >= 0; i--){
            Tarea tarea = obtenerTareas().get(i);
            if (!existeAlgunaTareaSucesora(tarea)){
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
        ultimoCalculoPERTesCorrecto = alMenosUnaTareaTieneComienzoTardioCero;
        determinarCaminosCriticos();
        return ultimoCalculoPERTesCorrecto;
    }
    
    /**
     * Método que determina los caminos críticos del proyecto.
     */
    private void determinarCaminosCriticos(){
        List<Tarea> tareasCriticasSinPrecedentes = new ArrayList<Tarea>();
        List<Tarea> tareasCriticasSinSucesores = new ArrayList<Tarea>();
        boolean noTienePrecedentes, noTieneSucesores;
        for (Tarea tarea : obtenerTareas()){
            noTienePrecedentes= false; noTieneSucesores = false;
            if (tarea.esTareaCritica()){
                if (!tarea.tieneTareasPrecedentes()){                    
                    noTienePrecedentes = true;
                }
                if (!existeAlgunaTareaSucesora(tarea)){                    
                    noTieneSucesores = true;
                }                
                if (noTienePrecedentes && noTieneSucesores){
                    List<Tarea> listaDeTareasDeCaminoCritico = new ArrayList<Tarea>();
                    listaDeTareasDeCaminoCritico.add(tarea);
                    caminosCriticos.add(new CaminoCritico(listaDeTareasDeCaminoCritico));
                }else if(noTienePrecedentes){
                    tareasCriticasSinPrecedentes.add(tarea);
                }else if(noTieneSucesores){
                    tareasCriticasSinSucesores.add(tarea);
                }
            }
        }
        for (Tarea tareaOrigen : tareasCriticasSinPrecedentes){
            for (Tarea tareaDestino : tareasCriticasSinSucesores){
                List<Tarea> camino = new ArrayList<Tarea>();
                camino.add(tareaDestino);
                obtenerCaminosCriticos(tareaOrigen, tareaDestino, camino);
            }
        }
    }   
    
    /**
     * Método que ayuda a "determinarCaminosCriticos()" en la búsqueda de los
     * caminos críticos.
     * @param tareaInicio
     * @param tareaDestino
     * @param camino 
     */
    private void obtenerCaminosCriticos(Tarea tareaInicio, Tarea tareaDestino, List<Tarea> camino){        
        Precedencia tareasPrecedentesDeTareaDestino = tareaDestino.obtenerPrecedencia();
        for (Tarea tarea : tareasPrecedentesDeTareaDestino.obtenerTareas()){
            if (tarea.esTareaCritica()){                
                List<Tarea> caminoActual = new ArrayList<Tarea>(camino);
                caminoActual.add(0,tarea);
                if (!(tarea.obtenerId() == tareaInicio.obtenerId())){
                    obtenerCaminosCriticos(tareaInicio, tarea, caminoActual);
                }else{      
                    if (esCaminoCritico(caminoActual)){
                        caminosCriticos.add(new CaminoCritico(caminoActual));
                    }
                }
            }           
        }
    }  
    
    /**
     * Método que retorna si un camino del grafo de tareas es camino crítico o no.
     * @param camino
     * @return si es camino crítico
     */
    private boolean esCaminoCritico(List<Tarea> camino){
        double tiempoDelCamino = 0;
        for (Tarea tarea : camino){           
            double d = tarea.obtenerDuracionEsperada();
            tiempoDelCamino = tiempoDelCamino + d;
        }
        double valorParaAcotar = Math.pow(10, cifrasDecimales);
        return Math.round(tiempoDelCamino*valorParaAcotar)/valorParaAcotar == duracionDelProyecto;
    }
}
