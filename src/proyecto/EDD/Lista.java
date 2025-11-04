/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.EDD;

/**
 *
 * @author sophia
 */
public class Lista {
    Nodo primero;
    
    public Lista(){
        primero = null;
    }
    
    public void insert(Vertice user){
        Nodo VN = new Nodo(user);
        // VN = vertice nuevo
        if(this.primero == null){
        this.primero = VN;
            
        
        } else{
            Nodo aux = this.primero;
                    //aux es auxiliar
                    while (aux.sig != null){
                        aux = aux.sig; 
                    }
                    aux.sig = VN;
        }
    }
    
    public void eliminar(String username){
        Nodo aux = this.primero;
        if (this.primero.user.username.equals(username)){
            this.primero = aux.sig;
        // en this.primero = puede ser aux.sig o this.primero.sig
        
    }else{
           while (aux.sig != null && !aux.sig.user.username.equals(username)) {
               aux = aux.sig;
           }
           if(aux.sig != null){
           aux.sig = aux.sig.sig;
           }
        }
        
    }
    
    public Nodo buscar(String username){
        Nodo aux = this.primero;
                while (aux != null && !aux.user.username.equals(username)){
                 aux = aux.sig;
                }
                return aux;
    }
    
}
