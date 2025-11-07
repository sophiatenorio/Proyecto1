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
 * Representa un nodo (usuario) en el grafo de la red social.
 * Utiliza un puntero 'sig' para formar una lista enlazada de todos los vértices.
 */
public class Vertice {
    String username;
    Vertice sig; // Puntero al siguiente vértice en la lista principal de usuarios.
    Lista adyacentes; // Lista de adyacencia (usuarios que sigue).
    boolean visitado; // Campo auxiliar para algoritmos de recorrido (DFS, Kosaraju).
    String sccColor; // Color/etiqueta para identificar el Componente Fuertemente Conectado (CFC).
    
    /**
     * Constructor del Vértice.
     * @param user Nombre de usuario (ej: @pepe).
     */
    public Vertice(String user){
        this.username = user;
        this.sig = null;
        this.adyacentes = new Lista();
        this.visitado = false;
        this.sccColor = "N/A";
    }
}
