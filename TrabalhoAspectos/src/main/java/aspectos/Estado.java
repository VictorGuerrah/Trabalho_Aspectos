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
    public List<Transicao> transicoes;
    public int id;
    public static int idAtual = 0;

    @Override
    public String toString() {
        return this.id + "";//To change body of generated methods, choose Tools | Templates.
    }
    
    Estado(){
        id = idAtual;
        idAtual++;
        estadoFinal = false;
        estadoInicial = false;
        transicoes = new ArrayList<>();
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
