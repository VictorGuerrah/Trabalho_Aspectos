/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aspectos;

import java.util.Stack;

/**
 *
 * @author reisd
 */
public class Tag {
    String nome;
    String expressao;
    String expressao2;

    
    
    Tag(String nome, String expressao){
        this.nome = nome;
        this.expressao = expressao;
        this.expressao2 = null;
    }
    
    public void exibirTag(){
        System.out.println("Nome: " + nome + "  Expressao: " + expressao);
        System.out.println("Expressao2: " + expressao2 + "\n");
    }
    
    public String getTag(){
        return nome + ": " + expressao;
    }
    
    public boolean validarExpressao(){
        Stack pilha = new Stack();
        int i = 0;
        boolean valida  = true;
        String aux;
        while(i != expressao.length()){
            aux = "";
            aux += expressao.charAt(i);
            if(!"+".equals(aux) && !".".equals(aux) && !"*".equals(aux)){
                pilha.push(aux);
            }
            else if("*".equals(aux)){
                String a = null;
                if(!pilha.empty()){
                    a = (String) pilha.pop();
                }
                else{
                    valida = false;
                    break;
                }
                pilha.push("(" + a + ")*");
            }
            else{
                String a = null;
                String b = null;
                if(!pilha.empty()){
                    a = (String) pilha.pop();
                }
                else{
                    valida = false;
                    break;
                }
                if(!pilha.empty()){
                    b = (String) pilha.pop();
                }
                else{
                    valida = false;
                    break;
                }
                if("+".equals(aux)){
                    pilha.push(b + "+" + a);
                }
                else{
                    pilha.push(b + "" + a);
                }
            }
            i++;
        }
        if(!pilha.empty()){
            aux = (String) pilha.pop();
            this.expressao2 = aux;
        }
        if(!pilha.empty())
            valida = false;
        return valida;
    }
    
    
    
    
}
