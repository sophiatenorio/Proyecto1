/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.EDD;

/**
 *
 * @author sophia
 *
 * /**
 * Implementación de una lista enlazada simple, usada como Lista de Adyacencia
 * para almacenar las aristas de un Vértice.
 */
public class Lista {

    public Nodo primero;

    /**
     * Constructor de la Lista.
     */
    public Lista() {
        primero = null;
    }

    /**
     * Inserta un nuevo Vértice al final de la lista de adyacencia.
     *
     * @param user El Vértice a insertar.
     */
    public void insert(Vertice user) {
        Nodo VN = new Nodo(user);

        if (this.primero == null) {
            this.primero = VN;
        } else {
            Nodo aux = this.primero;
            while (aux.sig != null) {
                aux = aux.sig;
            }
            aux.sig = VN;
        }
    }

    /**
     * Elimina una arista de la lista de adyacencia buscando por nombre de
     * usuario.
     *
     * @param username El nombre de usuario del Vértice cuya arista se quiere
     * eliminar.
     */
    public void eliminar(String username) {
        if (this.primero == null) {
            return;
        }

        if (this.primero.user.username.equals(username)) {
            this.primero = this.primero.sig;
            return;
        }

        Nodo aux = this.primero;
        while (aux.sig != null && !aux.sig.user.username.equals(username)) {
            aux = aux.sig;
        }

        if (aux.sig != null) {
            aux.sig = aux.sig.sig;
        }
    }

    /**
     * Busca un Vértice dentro de la lista de adyacencia por nombre de usuario.
     *
     * @param username El nombre de usuario a buscar.
     * @return El Nodo si es encontrado, o null si no existe la arista.
     */
    public Nodo buscar(String username) {
        Nodo aux = this.primero;
        while (aux != null && !aux.user.username.equals(username)) {
            aux = aux.sig;
        }
        return aux;
    }

}