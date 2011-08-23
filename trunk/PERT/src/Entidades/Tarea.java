/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidades;


/**
 *
 * @author Manuel Lorenze
 */
public class Tarea extends TareaAbstracta{

    private int id;
    private String nombre;
    private String descripcion;
    private TiempoEstimado tiempoEstimado;
    private Precedencia precedencia;
    
    public Tarea(int id, String nombre, String descripcion, TiempoEstimado tiempoEstimado, Precedencia precedencia){
        super.setComienzoTardio(-1);
        super.setComienzoTemprano(-1);
        super.setFinTardio(-1);
        super.setFinTemprano(-1);
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tiempoEstimado = tiempoEstimado;
        this.precedencia = precedencia;
    }    
    
    public double getDuracionEsperada() {
        return tiempoEstimado.getDuracionEsperada();
    }
    
    public boolean tieneTareasPrecedentes(){
        return precedencia.getCantidadDeTareas() > 0;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    public TiempoEstimado getTiempoEstimado() {
        return tiempoEstimado;
    }      

    public Precedencia getPrecedencia() {
        return precedencia;
    }  
}
