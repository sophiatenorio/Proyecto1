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
El nodo de cada lista de adyacencia. 
Guarda el vertice del usuario destino
*/
public class Nodo {
    Nodo sig;
    Vertice user;
    
    public Nodo(Vertice user){
        this.user = user;
        this.sig = null;
    }
    

}
