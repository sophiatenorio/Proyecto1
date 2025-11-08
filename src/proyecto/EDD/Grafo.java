
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

    /**
     * Puntero al primer vértice de la lista principal de usuarios.
     */
    public Vertice primero;
    /**
     * Contador para etiquetar los Componentes Fuertemente Conectados (CFCs).
     */
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
        if(origen_user.equals(destino_user)){
            return;
        }
        Vertice origen = buscarVertice(origen_user);
        Vertice destino = buscarVertice(destino_user);

        if (origen != null && destino != null) {
            if (origen.adyacentes.buscar(destino_user) == null) {
                
                origen.adyacentes.insert(destino);
                System.out.println("Arista creada exitosamente de " + origen_user + " a " + destino_user);
            }
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

    /**
     * Resetea el estado de visita (campo {@code visitado}) de todos los
     * vértices del grafo. Necesario antes de cada ejecución del DFS.
     */
    public void resetearVisitas() {
        Vertice aux = this.primero;
        while (aux != null) {
            aux.visitado = false;
            aux = aux.sig;
        }
    }

    /**
     * Método principal que invoca el Algoritmo de Kosaraju para encontrar CFCs.
     * Etiqueta cada Vértice con su Componente Fuertemente Conectado (CFC).
     */
    public void encontrarCFCs() {
        Pila pilaOrdenada = new Pila();
        this.contadorCFC = 0;

        resetearVisitas();
        Vertice aux = this.primero;
        while (aux != null) {
            if (!aux.visitado) {
                dfsPrimerPaso(aux, pilaOrdenada);
            }
            aux = aux.sig;
        }

        Grafo grafoTranspuesto = transponerGrafo();

        grafoTranspuesto.resetearVisitas();

        while (!pilaOrdenada.esVacia()) {
            Vertice verticeOriginal = pilaOrdenada.pop();

            Vertice verticeTranspuesto = grafoTranspuesto.buscarVertice(verticeOriginal.username);

            if (!verticeTranspuesto.visitado) {
                this.contadorCFC++;
                dfsSegundoPaso(verticeTranspuesto, "CFC" + this.contadorCFC);
            }
        }
    }

    /**
     * Primer paso del DFS para Kosaraju. Visita los vértices y los apila al
     * terminar de recorrer todos sus descendientes.
     *
     * @param u Vértice actual.
     * @param pila Pila para almacenar el orden inverso de finalización.
     */
    // Se cambia el tipo de la pila a la clase Pila propia
    private void dfsPrimerPaso(Vertice u, Pila pila) {
        u.visitado = true;

        Nodo aux = u.adyacentes.primero;
        while (aux != null) {
            Vertice v = aux.user;
            if (!v.visitado) {
                dfsPrimerPaso(v, pila);
            }
            aux = aux.sig;
        }
        pila.push(u);
    }

    /**
     * Crea y retorna una nueva instancia de {@code Grafo} con las aristas
     * invertidas.
     *
     * @return El Grafo Traspuesto (G^T).
     */
    private Grafo transponerGrafo() {
        Grafo Gt = new Grafo();

        Vertice u = this.primero;
        while (u != null) {
            Gt.insertarVertice(u.username);
            u = u.sig;
        }

        u = this.primero;
        while (u != null) {
            Nodo arista = u.adyacentes.primero;
            while (arista != null) {
                String origen_Gt = arista.user.username;
                String destino_Gt = u.username;

                Vertice origenGt = Gt.buscarVertice(origen_Gt);
                Vertice destinoGt = Gt.buscarVertice(destino_Gt);

                origenGt.adyacentes.insert(destinoGt);

                arista = arista.sig;
            }
            u = u.sig;
        }

        return Gt;
    }

    /**
     * Segundo paso del DFS para Kosaraju, ejecutado sobre el grafo traspuesto.
     * Etiqueta todos los vértices alcanzables con la misma etiqueta de
     * Componente Fuertemente Conectado.
     *
     * @param u Vértice actual (del grafo traspuesto).
     * @param sccLabel Etiqueta/color del CFC (ej: "CFC1").
     */
    private void dfsSegundoPaso(Vertice u, String sccLabel) {
        u.visitado = true;

        Vertice verticeOriginal = this.buscarVertice(u.username);
        if (verticeOriginal != null) {
            verticeOriginal.sccColor = sccLabel;
        } else {
            System.out.println("Error grave: Vértice no encontrado en grafo original: " + u.username);
        }

        Nodo aux = u.adyacentes.primero;
        while (aux != null) {
            Vertice v = aux.user;
            if (!v.visitado) {
                dfsSegundoPaso(v, sccLabel);
            }
            aux = aux.sig;
        }
    }

    /**
     * Muestra en la consola los Componentes Fuertemente Conectados (CFCs)
     * encontrados, agrupando los usuarios por su etiqueta de color (CFC1, CFC2,
     * etc.).
     */
    public void mostrarCFCs() {
        System.out.println("\n--- Resultados del Análisis de CFCs ---");
        System.out.println("Total de CFCs encontrados: " + this.contadorCFC);

        for (int i = 1; i <= this.contadorCFC; i++) {
            System.out.print("  " + "CFC" + i + ": {");
            Vertice aux = this.primero;
            StringBuilder sccMembers = new StringBuilder();
            while (aux != null) {
                if (aux.sccColor.equals("CFC" + i)) {
                    sccMembers.append(aux.username).append(", ");
                }
                aux = aux.sig;
            }
            if (sccMembers.length() > 0) {
                sccMembers.setLength(sccMembers.length() - 2);
            }
            System.out.println(sccMembers.toString() + "}");
        }
    }
}

