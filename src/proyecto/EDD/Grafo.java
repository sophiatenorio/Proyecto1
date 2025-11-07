/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.EDD;

/**
 *
 * @author sophia
 */

import proyecto.EDD.Nodo;
import proyecto.EDD.Vertice;


/**
 * Clase principal que gestiona el grafo dirigido de la red social. Utiliza una
 * lista enlazada de {@code Vertice} (usuarios) y listas de adyacencia
 * (relaciones de seguimiento) como estructura de datos. Esta clase implementa
 * el algoritmo de Kosaraju para encontrar Componentes Fuertemente Conectados
 * (CFCs).
 */
public class Grafo {

    /** Puntero al primer vértice de la lista principal de usuarios. */
    Vertice primero;
    /** Contador para etiquetar los Componentes Fuertemente Conectados (CFCs). */
    private int contadorCFC;

    /**
     * Constructor de la clase Grafo. Inicializa la lista de vértices como
     * vacía.
     */
    public Grafo() {
        this.primero = null;
        this.contadorCFC = 0;
    }

    /**
     * Inserta un nuevo usuario (Vértice) en la lista principal del grafo. Si el
     * usuario ya existe, emite un mensaje de error.
     *
     * @param username El nombre de usuario a agregar (ej: "@pepe").
     */
    public void insertarVertice(String username) {
        if (buscarVertice(username) != null) {
            System.out.println("Error: El usuario " + username + " ya existe.");
            return;
        }

        Vertice nuevo = new Vertice(username);
        if (this.primero == null) {
            this.primero = nuevo;
        } else {
            Vertice aux = this.primero;
            while (aux.sig != null) {
                aux = aux.sig;
            }
            aux.sig = nuevo;
        }
    }

    /**
     * Busca un Vértice por nombre de usuario en la lista principal.
     *
     * @param username El nombre de usuario a buscar.
     * @return El objeto Vertice si se encuentra, o {@code null} en caso
     * contrario.
     */
    public Vertice buscarVertice(String username) {
        Vertice aux = this.primero;
        while (aux != null && !aux.username.equals(username)) {
            aux = aux.sig;
        }
        return aux;
    }

    /**
     * Agrega una arista dirigida (relación de seguimiento) desde el origen
     * hacia el destino.
     *
     * @param origen_user El nombre del usuario que sigue.
     * @param destino_user El nombre del usuario seguido.
     */
    public void agregarArista(String origen_user, String destino_user) {
        Vertice origen = buscarVertice(origen_user);
        Vertice destino = buscarVertice(destino_user);

        if (origen != null && destino != null) {
            origen.adyacentes.insert(destino);
        } else {
            System.out.println("Error: Uno o ambos usuarios no existen para crear la arista.");
        }
    }

    /**
     * Elimina un usuario (Vértice) del grafo. La función elimina el vértice de
     * la lista principal y todas las aristas entrantes (donde otros usuarios lo
     * siguen).
     *
     * @param username El nombre de usuario a eliminar.
     */
    public void eliminarVertice(String username) {
        Vertice aux = this.primero;
        while (aux != null) {
            aux.adyacentes.eliminar(username);
            aux = aux.sig;
        }

        if (this.primero == null) {
            return;
        }

        if (this.primero.username.equals(username)) {
            this.primero = this.primero.sig;
            return;
        }

        Vertice prev = this.primero;
        while (prev.sig != null && !prev.sig.username.equals(username)) {
            prev = prev.sig;
        }

        if (prev.sig != null) {
            prev.sig = prev.sig.sig;
        }
    }

    
}