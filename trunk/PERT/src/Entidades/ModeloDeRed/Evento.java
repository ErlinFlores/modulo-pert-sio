/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.ModeloDeRed;

/**
 *
 * @author Manuel Lorenze
 */
public class Evento extends Nodo{
    
    private TipoEvento tipo;
    
    private int tiempoMasTempranoDeInicio; // equivale al tiempo temprano de fin mas chico de las tareas que desembocan en este evento.
    private int tiempoMasTardioDeInicio; // equivale al tiempo tardio de fin mas grande de las tareas que desembocan en este evento.
    
    private int tiempoMasTempranoDeFin; // equivale al tiempo temprano de fin mas chico de las tareas que desembocan en este evento.
    private int tiempoMasTardioDeFin;
    
    public Evento(int id, TipoEvento tipo){
        this.id = id;
        this.tipo = tipo;
    }
}
