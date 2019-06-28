/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aspectos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author reisd
 */
public class Transicao {
    private char simbolo;
    //private char simbolo;
    private boolean consumiuPalavra;
    private Estado destino;
    
    Transicao(String simbolos, Estado destino){
        consumiuPalavra = false;
        //this.simbolo = new ArrayList<>();
//        for (int i = 0; i < simbolos.length(); i++) {
//            
////            this.simbolo.add(simbolos.charAt(i));
//        }
        this.destino = destino;
    }
    
    public void Teste(){
        
        System.out.println("");
        destino.Teste();
    }

    @Override
    public String toString() {
        return "a"; //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean realizaTransicao(String palavra){
        if(simbolo == '/'){
            consumiuPalavra = false;
            return true;
        }
        else if(simbolo == palavra.charAt(0)){
            consumiuPalavra = true;
            return true;
        }
        return false;
    }
    
    public Estado getEstadoDestino(){
        return this.destino;
    }
}
