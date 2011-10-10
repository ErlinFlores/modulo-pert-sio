/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Demo;

import Entidades.Estados.Accion;
import Entidades.FabricaDeTareas;
import Entidades.Precedencia;
import Entidades.RedDeTareas;
import Entidades.Tarea;
import Entidades.TiempoEstimado;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manuel Lorenze
 */
public class Demo1 implements IDemo{
    
    private Accion tipoAccion;
    private String nombre;
    private String unidadDeTiempo;
    private String descripcion;       
    private RedDeTareas redDeTareas;
    
    public Demo1(){}
    
    public void inicializar(){
        FabricaDeTareas.getInstance().reset();
        tipoAccion = Accion.modificar;
        nombre = "Proyecto Demo 1";
        unidadDeTiempo = "dias";
        descripcion = "Ejemplo de un proyecto de 10 tareas.";        
        redDeTareas = new RedDeTareas(new ArrayList<Tarea>());        
        agregarTareas();
    }
    
    private void agregarTareas(){        
        TiempoEstimado tiemposEstimados_A = new TiempoEstimado(4, 5, 12);
        List<Tarea> tareasPrecedentes_A = new ArrayList<Tarea>();
        Precedencia precedencia_A = new Precedencia(tareasPrecedentes_A);        
        Tarea tarea_A = FabricaDeTareas.getInstance().crearTarea("TAREA A", tiemposEstimados_A, precedencia_A);
        redDeTareas.agregarTarea(tarea_A);
        
        TiempoEstimado tiemposEstimados_B = new TiempoEstimado(1, 1.5, 5);
        List<Tarea> tareasPrecedentes_B = new ArrayList<Tarea>();
        Precedencia precedencia_B = new Precedencia(tareasPrecedentes_B);        
        Tarea tarea_B = FabricaDeTareas.getInstance().crearTarea("TAREA B", tiemposEstimados_B, precedencia_B);
        redDeTareas.agregarTarea(tarea_B);
        
        TiempoEstimado tiemposEstimados_C = new TiempoEstimado(2, 3, 4);
        List<Tarea> tareasPrecedentes_C = new ArrayList<Tarea>();
        tareasPrecedentes_C.add(tarea_A);
        Precedencia precedencia_C = new Precedencia(tareasPrecedentes_C);        
        Tarea tarea_C = FabricaDeTareas.getInstance().crearTarea("TAREA C", tiemposEstimados_C, precedencia_C);
        redDeTareas.agregarTarea(tarea_C);
        
        TiempoEstimado tiemposEstimados_D = new TiempoEstimado(3, 4, 11);
        List<Tarea> tareasPrecedentes_D = new ArrayList<Tarea>();
        tareasPrecedentes_D.add(tarea_A);
        Precedencia precedencia_D = new Precedencia(tareasPrecedentes_D);        
        Tarea tarea_D = FabricaDeTareas.getInstance().crearTarea("TAREA D", tiemposEstimados_D, precedencia_D);
        redDeTareas.agregarTarea(tarea_D);
        
        TiempoEstimado tiemposEstimados_E = new TiempoEstimado(2, 3, 4);
        List<Tarea> tareasPrecedentes_E = new ArrayList<Tarea>();
        tareasPrecedentes_E.add(tarea_A);
        Precedencia precedencia_E = new Precedencia(tareasPrecedentes_E);        
        Tarea tarea_E = FabricaDeTareas.getInstance().crearTarea("TAREA E", tiemposEstimados_E, precedencia_E);
        redDeTareas.agregarTarea(tarea_E);
        
        TiempoEstimado tiemposEstimados_F = new TiempoEstimado(1.5, 2, 2.5);
        List<Tarea> tareasPrecedentes_F = new ArrayList<Tarea>();
        tareasPrecedentes_F.add(tarea_C);
        Precedencia precedencia_F = new Precedencia(tareasPrecedentes_F);       
        Tarea tarea_F = FabricaDeTareas.getInstance().crearTarea("TAREA F", tiemposEstimados_F, precedencia_F);
        redDeTareas.agregarTarea(tarea_F);
        
        TiempoEstimado tiemposEstimados_G = new TiempoEstimado(1.5, 3, 4.5);
        List<Tarea> tareasPrecedentes_G = new ArrayList<Tarea>();
        tareasPrecedentes_G.add(tarea_D);
        Precedencia precedencia_G = new Precedencia(tareasPrecedentes_G); 
        Tarea tarea_G = FabricaDeTareas.getInstance().crearTarea("TAREA G", tiemposEstimados_G, precedencia_G);
        redDeTareas.agregarTarea(tarea_G);
        
        TiempoEstimado tiemposEstimados_H = new TiempoEstimado(2.5, 3.5, 7.5);
        List<Tarea> tareasPrecedentes_H = new ArrayList<Tarea>();
        tareasPrecedentes_H.add(tarea_B);
        tareasPrecedentes_H.add(tarea_E);
        Precedencia precedencia_H = new Precedencia(tareasPrecedentes_H);     
        Tarea tarea_H = FabricaDeTareas.getInstance().crearTarea("TAREA H", tiemposEstimados_H, precedencia_H);
        redDeTareas.agregarTarea(tarea_H);
        
        TiempoEstimado tiemposEstimados_I = new TiempoEstimado(1.5, 2, 2.5);
        List<Tarea> tareasPrecedentes_I = new ArrayList<Tarea>();
        tareasPrecedentes_I.add(tarea_H);
        Precedencia precedencia_I = new Precedencia(tareasPrecedentes_I);      
        Tarea tarea_I = FabricaDeTareas.getInstance().crearTarea("TAREA I", tiemposEstimados_I, precedencia_I);
        redDeTareas.agregarTarea(tarea_I);
        
        TiempoEstimado tiemposEstimados_J = new TiempoEstimado(1, 2, 3);
        List<Tarea> tareasPrecedentes_J = new ArrayList<Tarea>();
        tareasPrecedentes_J.add(tarea_F);
        tareasPrecedentes_J.add(tarea_G);
        tareasPrecedentes_J.add(tarea_I);
        Precedencia precedencia_J = new Precedencia(tareasPrecedentes_J);        
        Tarea tarea_J = FabricaDeTareas.getInstance().crearTarea("TAREA J", tiemposEstimados_J, precedencia_J);
        redDeTareas.agregarTarea(tarea_J);
    }    
   
    public Accion obtenerTipoAccion(){
        return tipoAccion;
    }
    
    public String obtenerNombre(){
        return nombre;
    }
    
    public String obtenerUnidadDeTiempo(){
        return unidadDeTiempo;
    }
    
    public String obtenerDescripcion(){
        return descripcion;
    }   
    
    public RedDeTareas obtenerRedDeTareas(){
        return redDeTareas;
    }    
}
