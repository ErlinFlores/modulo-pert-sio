/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.ModeloDeRed;

/**
 *
 * @author Manuel Lorenze
 */
public abstract class Arco {
    
    private Nodo nodoOrigen;
    private Nodo nodoDestino;

    public Nodo getNodoDestino() {
        return nodoDestino;
    }

    public void setNodoDestino(Nodo nodoDestino) {
        this.nodoDestino = nodoDestino;
    }

    public Nodo getNodoOrigen() {
        return nodoOrigen;
    }

    public void setNodoOrigen(Nodo nodoOrigen) {
        this.nodoOrigen = nodoOrigen;
    }    
}
