/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Demo;

import Entidades.Estados.Accion;
import Entidades.RedDeTareas;

/**
 *
 * @author Manuel Lorenze
 */
public interface IDemo {
    
    public String obtenerNombre();
    
    public String obtenerUnidadDeTiempo();
    
    public String obtenerDescripcion();    
    
    public RedDeTareas obtenerRedDeTareas();
}
