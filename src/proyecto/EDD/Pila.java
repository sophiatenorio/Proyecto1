/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.EDD;

/**
 *
 * @author Andres
 */
/**
 * Implementación de una Pila (Stack) usando Nodos, para almacenar Vértices.
 * Reemplaza a java.util.Stack para cumplir con la restricción de no usar librerías de TDA.
 */
class Pila {
    private Nodo cima;

    public Pila() {
        this.cima = null;
    }

    public boolean esVacia() {
        return cima == null;
    }

    /**
     * Agrega un Vértice a la cima de la Pila (push).
     * @param usuario El Vértice a agregar.
     */
    public void push(Vertice usuario) {
        Nodo nuevoNodo = new Nodo(usuario);
        nuevoNodo.sig = cima; 
        cima = nuevoNodo;     
    }

    /**
     * Remueve y retorna el Vértice de la cima de la Pila (pop).
     * @return El Vértice de la cima, o null si la pila está vacía.
     */
    public Vertice pop() {
        if (esVacia()) {
            return null;
        }
        Vertice user = cima.user;
        cima = cima.sig;
        return user;
    }
}