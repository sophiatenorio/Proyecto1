/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.EDD;

/**
 *
 * @author sophia
 */

/*
Clase del Vertice del Grafo

Guarda el nombre de usuario y la lista de adyacentes de cada vertice :)
*/
public class Vertice {
    String username;
    Vertice sig; //sig = siguiente
    Lista adyacentes;
    
    public Vertice(String user){
        this.username =user;
        sig = null;
        adyacentes = new Lista();
    }
}
