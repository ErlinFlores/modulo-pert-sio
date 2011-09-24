/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Demo;

import Entidades.Accion;
import Entidades.FabricaDeTareas;
import Entidades.RedDeTareas;
import Entidades.Tarea;
import java.util.ArrayList;

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
    
    public Demo2(){
        FabricaDeTareas.getInstance().reset();
        tipoAccion = Accion.modificar;
        nombre = "Proyecto Demo 2";
        unidadDeTiempo = "horas";
        descripcion = "Ejemplo de un proyecto de 3 tareas.";        
        redDeTareas = new RedDeTareas(new ArrayList<Tarea>());        
        agregarTareas();
    }
    
    private void agregarTareas(){
        //Agregar tareas!!!
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
