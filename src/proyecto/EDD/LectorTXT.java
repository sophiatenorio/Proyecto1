/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.EDD;

/**
 *
 * @author Andres
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Clase encargada de manejar la persistencia del grafo, incluyendo la lectura
 * y escritura de la información de usuarios y relaciones en archivos de texto (.txt).
 */
public class LectorTXT {

    /**
     * Carga el grafo leyendo la información de usuarios y relaciones desde un archivo de texto.
     *
     * @param rutaArchivo La ruta completa del archivo de texto a cargar.
     * @param grafo La instancia de Grafo donde se cargará la información.
     * @throws FileNotFoundException Si la ruta del archivo es inválida o no existe.
     */
    public static void cargarDesdeArchivo(String rutaArchivo, Grafo grafo) throws FileNotFoundException {
        grafo.primero = null;

        File archivo = new File(rutaArchivo);
        Scanner scanner = new Scanner(archivo);
        String linea;
        boolean leyendoUsuarios = false;
        boolean leyendoRelaciones = false;

        while (scanner.hasNextLine()) {
            linea = scanner.nextLine().trim();

            if (linea.isEmpty()) {
                continue;
            }

            if (linea.equals("usuarios")) {
                leyendoUsuarios = true;
                leyendoRelaciones = false;
                continue;
            }

            if (linea.equals("relaciones")) {
                leyendoUsuarios = false;
                leyendoRelaciones = true;
                continue;
            }

            if (leyendoUsuarios) {
                if (linea.startsWith("@")) {
                    grafo.insertarVertice(linea);
                }
            } else if (leyendoRelaciones) {
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    String origen = partes[0].trim();
                    String destino = partes[1].trim();
                    grafo.agregarArista(origen, destino);
                }
            }
        }
        scanner.close();
    }

    /**
     * Guarda el estado actual del grafo (usuarios y relaciones) en el archivo de texto.
     *
     * @param rutaArchivo La ruta completa del archivo de texto donde se guardará la información.
     * @param grafo La instancia de Grafo que contiene la información a guardar.
     * @throws IOException Si ocurre un error durante la escritura del archivo.
     */
    public static void guardarEnArchivo(String rutaArchivo, Grafo grafo) throws IOException {
        PrintWriter escritor = new PrintWriter(new FileWriter(rutaArchivo));

       escritor.println("usuarios");
        Vertice auxVertice = grafo.primero;
        while (auxVertice != null) {
            escritor.println(auxVertice.username);
            auxVertice = auxVertice.sig;
        }

        escritor.println("relaciones");
        auxVertice = grafo.primero;
        while (auxVertice != null) {
            Nodo auxArista = auxVertice.adyacentes.primero;
            while (auxArista != null) {
                escritor.println(auxVertice.username + ", " + auxArista.user.username);
                auxArista = auxArista.sig;
            }
            auxVertice = auxVertice.sig;
        }

        escritor.close();
    }
}
