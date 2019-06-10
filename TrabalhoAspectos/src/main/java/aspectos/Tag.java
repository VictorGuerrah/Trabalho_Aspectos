/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aspectos;

/**
 *
 * @author reisd
 */
public class Tag {
    String nome;
    String expressao;

    
    
    Tag(String nome, String expressao){
        this.nome = nome;
        this.expressao = expressao;
    }
    
    public void exibirTag(){
        System.out.println("Nome: " + nome + "  Expressao: " + expressao);
    }
}
