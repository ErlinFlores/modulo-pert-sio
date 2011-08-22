/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.ModeloDeRed;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manuel Lorenze
 */
public class Red {
    
    private List<Nodo> listaDeNodos;
    private List<Arco> listaDeArcos;
    
    public Red(Nodo inicio, Nodo fin){
        listaDeNodos = new ArrayList<Nodo>();
        listaDeArcos = new ArrayList<Arco>();
        listaDeNodos.add(inicio);
        listaDeNodos.add(fin);        
    }
    
    public void agregarNodo(Nodo nodo){
        listaDeNodos.add(nodo);
    }
    
    public void agregarArco(Arco arco){
        listaDeArcos.add(arco);
    }

    public void eliminarNodo(Nodo nodo){
        listaDeNodos.remove(nodo);
    }
}
