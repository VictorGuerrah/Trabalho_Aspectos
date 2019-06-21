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
public class Estado {
    public boolean estadoFinal;
    public boolean estadoInicial;
    List<Transicao> transicoes;
    
    Estado(){
        estadoFinal = false;
        estadoInicial = false;
        transicoes = new ArrayList<Transicao>();
    }
    
    public void criarTransicao(String simbolo, Estado destino){
        Transicao novaTransicao = new Transicao(simbolo, destino);
        this.transicoes.add(novaTransicao);
    }
    public void Teste(){
        System.out.println("Tamanho transicoes: " + transicoes.size());
        for(int i = 0; i < transicoes.size(); i++){
            System.out.println("Transicao " + i);
            transicoes.get(i).Teste();
        }
    }
    
}
