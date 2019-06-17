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
//                System.out.println("testeeeee");
                aux = "";
                aux += expressao.charAt(i);
                if(!"+".equals(aux) && !".".equals(aux) && !"*".equals(aux)){
//                    System.out.println("teste3");
                    Estado estadoInicial = new Estado();
                    Estado estadoFinal = new Estado();
                    estadoInicial.estadoInicial = true;
                    estadoFinal.estadoFinal = true;
                    
                    estadoInicial.criarTransicao(aux, estadoFinal);
                    Automato automato = new Automato(estadoInicial, estadoFinal);
                    pilha.push(automato);
                }
                else if("+".equals(aux)){
                    Automato aux1;
                    Automato aux2;
                    if(pilha.size() >= 2){
                        aux2 = (Automato) pilha.pop();
                        aux1 = (Automato) pilha.pop();
                        Estado estadoInicial = new Estado();
                        Estado estadoFinal = new Estado();
                        estadoInicial.estadoInicial = true;
                        estadoFinal.estadoFinal = true;
                        List<Estado> estadosIniciais1 = aux1.estadosIniciais;
                        List<Estado> estadosIniciais2 = aux2.estadosIniciais;
                        List<Estado> estadosFinais1 = aux1.estadosFinais;
                        List<Estado> estadosFinais2 = aux2.estadosFinais;
//                        aux1.estadosIniciais.clear();
//                        aux2.estadosIniciais.clear();
//                        aux1.estadosFinais.clear();
//                        aux2.estadosFinais.clear();
                        for (int j = 0; j < estadosIniciais1.size(); j++) {
                            estadosIniciais1.get(j).estadoInicial = false;
                            estadoInicial.criarTransicao("\\", estadosIniciais1.get(j));
                        }
                        for (int j = 0; j < estadosIniciais2.size(); j++) {
                            estadosIniciais2.get(j).estadoInicial = false;
                            estadoInicial.criarTransicao("\\", estadosIniciais2.get(j));
                        }
                        for (int j = 0; j < estadosFinais1.size(); j++) {
                            estadosFinais1.get(j).estadoFinal = false;
                            estadosFinais1.get(j).criarTransicao("\\", estadoFinal);
                        }
                        for (int j = 0; j < estadosFinais2.size(); j++) {
                            estadosFinais2.get(j).estadoFinal = false;
                            estadosFinais2.get(j).criarTransicao("\\", estadoFinal);
                        }
                        Automato novoAutomato = new Automato(estadoInicial, estadoFinal);
                        pilha.push(novoAutomato);
                    }
                }
                else if(".".equals(aux)){
                    System.out.println("teste");
                    Automato aux1;
                    Automato aux2;
                    if(pilha.size()>=2){
                        aux1 = (Automato) pilha.pop();
                        aux2 = (Automato) pilha.pop();
                        Estado estadoInicial = new Estado();
                        Estado estadoFinal = new Estado();
                        estadoInicial.estadoInicial = true;
                        estadoFinal.estadoFinal = true;
                        List<Estado> estadosIniciais1 = aux2.estadosIniciais;
                        List<Estado> estadosIniciais2 = aux1.estadosIniciais;
                        List<Estado> estadosFinais1 = aux2.estadosFinais;
                        List<Estado> estadosFinais2 = aux1.estadosFinais;
                        for (int j = 0; j < estadosFinais1.size(); j++) {
                            for (int k = 0; k < estadosIniciais2.size(); k++) {
                                estadosFinais1.get(j).criarTransicao("\\", estadosIniciais2.get(k));
                            }
                        }
                        aux2.estadosFinais = aux1.estadosFinais;
                        pilha.push(aux2);
                    }
                }
                i++;
            }
            return (Automato) pilha.pop();
        }
        return null;
        }
}