/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aspectos;

import java.util.List;
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
    
    public String getExpressao(){
        return this.expressao;
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
    
    public Automato criarAutomato(){
        if(expressao != null){
            Stack pilha = new Stack();
            int i = 0;
            String aux;
            while(i != expressao.length()){
                aux = "";
                aux += expressao.charAt(i);
                if(!"+".equals(aux) && !".".equals(aux) && !"*".equals(aux)){
                    pilha.push(adicionarAutomatoPilha(aux));
                }
                else{
                    if("*".equals(aux)){
                        if(pilha.size() >= 1){
                            Automato automato = (Automato) pilha.pop();
                            Automato novoAutomato = criarAutomatoFechoKleene(automato);
                            pilha.push(automato);
                        }
                    }
                    else{
                        if(pilha.size() >= 2){
                            Automato aux2 = (Automato) pilha.pop();
                            Automato aux1 = (Automato) pilha.pop();
                            if("+".equals(aux)){
                                Automato novoAutomato = criarAutomatoUniao(aux1, aux2);
                                pilha.push(novoAutomato);
                            }
                            else if(".".equals(aux)){
                                Automato novoAutomato = criaAutomatoConcatenacao(aux1, aux2);
                                pilha.push(novoAutomato);
                            }
                        }
                    }
                }
                i++;
                /*
                else if("+".equals(aux)){
                    Automato aux1;
                    Automato aux2;
                    if(pilha.size() >= 2){
                        aux2 = (Automato) pilha.pop();
                        aux1 = (Automato) pilha.pop();
                        Automato novoAutomato = criarAutomatoUniao(aux1, aux2);
                        pilha.push(novoAutomato);
                    }
                }
                else if(".".equals(aux)){
                    Automato aux1;
                    Automato aux2;
                    if(pilha.size()>=2){
                        aux1 = (Automato) pilha.pop();
                        aux2 = (Automato) pilha.pop();
                        Automato novoAutomato = criaAutomatoConcatenacao(aux1, aux2);
                        pilha.push(novoAutomato);
                    }
                }
                else if("*".equals(aux)){
                    if(pilha.size() >= 1){
                        Automato automato = (Automato) pilha.pop();
                        Automato novoAutomato = criarAutomatoFechoKleene(automato);
                        pilha.push(automato);
                    }
                }
                i++;*/
            }
            return (Automato) pilha.pop();
        }
        return null;
    }
    
    public Automato adicionarAutomatoPilha(String simbolo){
        Estado estadoInicial = new Estado();
        Estado estadoFinal = new Estado();
        estadoInicial.estadoInicial = true;
        estadoFinal.estadoFinal = true;
        estadoInicial.criarTransicao(simbolo, estadoFinal);
        Automato automato = new Automato(estadoInicial, estadoFinal);
        return automato;
    }
    
    public Automato criarAutomatoUniao(Automato automato1, Automato automato2){
        Estado estadoInicial = new Estado();
        Estado estadoFinal = new Estado();
        estadoInicial.estadoInicial = true;
        estadoFinal.estadoFinal = true;
        
        estadoInicial.criarTransicao("\\", automato1.estadoInicial);
        estadoInicial.criarTransicao("\\", automato2.estadoInicial);
        automato1.estadoFinal.criarTransicao("\\", estadoFinal);
        automato2.estadoFinal.criarTransicao("\\", estadoFinal);
        
        automato1.estadoInicial.estadoInicial = false;
        automato2.estadoInicial.estadoInicial = false;
        automato1.estadoFinal.estadoFinal = false;
        automato2.estadoFinal.estadoFinal = false;
        
        automato1.estadoInicial = estadoInicial;
        automato1.estadoFinal = estadoFinal;
        automato1.conjuntoEstados.addAll(automato2.conjuntoEstados);
        return automato1;
    }
    
    public Automato criaAutomatoConcatenacao(Automato automato1, Automato automato2){
        automato1.estadoFinal.criarTransicao("\\", automato2.estadoInicial);
        automato1.estadoFinal.estadoFinal = false;
        automato1.estadoFinal = automato2.estadoFinal;
        automato1.conjuntoEstados.addAll(automato2.conjuntoEstados);
        return automato1;
    }
    
    
    public Automato criarAutomatoFechoKleene(Automato automato){
        automato.estadoFinal.criarTransicao("//", automato.estadoInicial);
        automato.estadoFinal.estadoFinal = false;
        automato.estadoFinal = automato.estadoInicial;
        automato.estadoInicial.estadoFinal = true;
        return automato;
    }
    
    
}