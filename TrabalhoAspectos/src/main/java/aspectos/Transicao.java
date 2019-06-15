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
    private List<Character> simbolos;
    private Estado destino;
    
    Transicao(String simbolos, Estado destino){
        this.simbolos = new ArrayList<Character>();
        for (int i = 0; i < simbolos.length(); i++) {
            
            this.simbolos.add(simbolos.charAt(i));
        }
        this.destino = destino;
    }
    
    public void Teste(){
        System.out.println("Tamanho conjunto de simbolos: " + simbolos.size());
        for (int i = 0; i < simbolos.size(); i++) {
            System.out.print(simbolos.get(i) + " ");
            
        }
        System.out.println("");
        destino.Teste();
    }
}
