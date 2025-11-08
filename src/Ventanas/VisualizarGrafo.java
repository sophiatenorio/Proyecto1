/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ventanas;

/**
 *
 * @author leonardo
 */
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;

import proyecto.EDD.Grafo;
import proyecto.EDD.Vertice;
import proyecto.EDD.Nodo;

/**
 * Clase auxiliar para transformar la estructura de datos interna (Grafo) a un
 * objeto GraphStream y mostrarlo.
 */
public class VisualizarGrafo {

    /**
     * Muestra el grafo actual en una ventana de GraphStream, aplicando un layout
     * de fuerza para separar los nodos y mostrando los CFCs por color.
     *
     * @param grafo La instancia del grafo a visualizar.
     */
    public static void mostrarGrafo(Grafo grafo) {
        System.setProperty("org.graphstream.ui", "swing");

        String styleSheet = "node {"
                + "   fill-color: #6A5ACD;"
                + "   text-mode: normal;"
                + "   text-size: 14px;"
                + "   text-color: white;"
                + "   shape: circle;"
                + "   size: 75px, 75px;"
                + "}"
                + "edge {"
                + "   fill-color: #555;"
                + "   arrow-shape: arrow;"
                + "   arrow-size: 8px;"
                + "   size: 1px;"
                + "}";

        Graph graph = new SingleGraph("RedSocial");
        graph.setAttribute("ui.stylesheet", styleSheet);
        graph.setAttribute("ui.quality");
        graph.setAttribute("ui.antialias");

        Vertice auxVertice = grafo.primero;
        while (auxVertice != null) {
            String userId = auxVertice.username;

            try {
                if (graph.getNode(userId) == null) {
                    Node node = graph.addNode(userId);
                    node.setAttribute("ui.label", userId);

                    if (auxVertice.sccColor != null && !auxVertice.sccColor.equals("N/A")) {
                        String color = getColorForCFC(auxVertice.sccColor);
                        node.setAttribute("ui.style", "fill-color: " + color + ";");
                    }
                }
            } catch (Exception e) {
                System.err.println("ERROR al añadir nodo " + userId + ". La iteración continuará.");
            }

            auxVertice = auxVertice.sig;
        }

        auxVertice = grafo.primero;
        while (auxVertice != null) {
            String origenId = auxVertice.username;

            Nodo auxArista = auxVertice.adyacentes.primero;
            while (auxArista != null) {
                String destinoId = auxArista.user.username;
                String edgeId = origenId + "->" + destinoId;

                try {
                    if (graph.getNode(origenId) != null && graph.getNode(destinoId) != null && graph.getEdge(edgeId) == null) {
                        graph.addEdge(edgeId, origenId, destinoId, true);
                    }
                } catch (Exception e) {
                    System.err.println("ERROR al añadir arista " + edgeId + ". La iteración continuará.");
                }

                auxArista = auxArista.sig;
            }
            auxVertice = auxVertice.sig;
        }

        graph.setAttribute("ui.quality");
        graph.setAttribute("ui.antialias");

        graph.setAttribute("ui.layout", "force");
        graph.setAttribute("ui.layout.force.springLength", 100);
        graph.setAttribute("ui.layout.force.repulsion", 100000);


        Viewer viewer = graph.display();

        System.out.println("finish");
    }

    /**
     * Asigna un color distintivo basado en el identificador del Componente
     * Fuertemente Conectado (CFC).
     *
     * @param sccColor El identificador del CFC (ej: "CFC1").
     * @return El código hexadecimal del color.
     */
    private static String getColorForCFC(String sccColor) {
        int hash = sccColor.hashCode();
        String[] colors = {
            "#FF5733", "#33FF57", "#3357FF", "#FF33F6", "#33FFF6", "#FFB833", "#A033FF"
        };
        int index = Math.abs(hash % colors.length);
        return colors[index];
    }
}