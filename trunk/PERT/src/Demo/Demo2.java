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
 * @author mlorenze
 */
public class Demo2 implements IDemo{

    private Accion tipoAccion;
    private String nombre;
    private String unidadDeTiempo;
    private String descripcion;       
    private RedDeTareas redDeTareas;
    
    public Demo2(){}
    
    public void inicializar(){
        FabricaDeTareas.getInstance().reset();
        tipoAccion = Accion.modificar;
        nombre = "Proyecto Demo 2";
        unidadDeTiempo = "horas";
        descripcion = "Ejemplo de un proyecto de 2 tareas.";        
        redDeTareas = new RedDeTareas(new ArrayList<Tarea>());        
        agregarTareas();
    }
    
    private void agregarTareas(){
        TiempoEstimado tiemposEstimados_A = new TiempoEstimado(1, 2, 9);
        List<Tarea> tareasPrecedentes_A = new ArrayList<Tarea>();
        Precedencia precedencia_A = new Precedencia(tareasPrecedentes_A);        
        Tarea tarea_A = FabricaDeTareas.getInstance().crearTarea("TAREA A", tiemposEstimados_A, precedencia_A);
        redDeTareas.agregarTarea(tarea_A);
        
        TiempoEstimado tiemposEstimados_B = new TiempoEstimado(2, 3, 23);
        List<Tarea> tareasPrecedentes_B = new ArrayList<Tarea>();
        tareasPrecedentes_B.add(tarea_A);
        Precedencia precedencia_B = new Precedencia(tareasPrecedentes_B);        
        Tarea tarea_B = FabricaDeTareas.getInstance().crearTarea("TAREA B", tiemposEstimados_B, precedencia_B);
        redDeTareas.agregarTarea(tarea_B);
    }
    
    public Accion obtenerTipoAccion() {
        return tipoAccion;
    }

    public String obtenerNombre() {
        return nombre;
    }

    public String obtenerUnidadDeTiempo() {
        return unidadDeTiempo;
    }

    public String obtenerDescripcion() {
        return descripcion;
    }

    public RedDeTareas obtenerRedDeTareas() {
        return redDeTareas;
    }
}
