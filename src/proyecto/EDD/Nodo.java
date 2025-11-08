
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.EDD;

/**
 *
 * @author sophia
 */

/**
 * Representa un nodo dentro de una Lista, apuntando a un Vértice.
 * Se usa para formar las listas de adyacencia.
 */
public class Nodo {
    public Nodo sig; // Puntero al siguiente nodo en la lista.
    public Vertice user; // Referencia al Vértice al que apunta la arista.
    
    /**
     * Constructor del Nodo.
     * @param user El Vértice al que este nodo apunta.
     */
    public Nodo(Vertice user){
        this.user = user;
        this.sig = null;
    }
}