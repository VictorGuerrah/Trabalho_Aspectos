/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aspectos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public class Automato {

    //private String tag;
    //private String expressao;
    private List<Character> alfabeto;
    private List<Estado> conjuntoEstados;
    public List<Estado> estadosIniciais;
    public List<Estado> estadosFinais;
    private String nomeTag;
    private Tag tag = null;
    //private List<Transicao> conjuntoTransicao;

    Automato(Tag tag) {
        this.tag = tag;

    }
    Automato(Estado estadoInicial, Estado estadoFinal){
        this.alfabeto = new ArrayList<Character>();
        this.conjuntoEstados = new ArrayList<Estado>();
        this.estadosIniciais = new ArrayList<Estado>();
        this.estadosFinais =  new ArrayList<Estado>();
        
        this.estadosIniciais.add(estadoInicial);
        this.estadosFinais.add(estadoFinal);
    }
    
    private void criarAtuomatoParaTag(){
        if(tag != null){
            String expressao = tag.getExpressao();
            Stack pilha = new Stack();
            int i = 0;
            String aux;
            while(i != expressao.length()){
                aux = "";
                aux += expressao.charAt(i);
                if(!"+".equals(aux) && !".".equals(aux) && !"*".equals(aux)){
                    Estado estadoInicial = new Estado();
                    Estado estadoFinal = new Estado();
                    estadoInicial.estadoInicial = true;
                    estadoFinal.estadoFinal = true;
                    estadoInicial.criarTransicao(aux, estadoFinal);
                }
                i++;
            }
        }
    }
    
    void Teste(){
        System.out.println("Tamanho estadosIniciais: " + estadosIniciais.size());
        System.out.println("Tamanho estadosFinais: " + estadosFinais.size());
        System.out.println("Tamanho conjuntoEstados: " + this.conjuntoEstados.size());
        
        System.out.println("outros testes");
        for (int i = 0; i < estadosIniciais.size(); i++) {
            estadosIniciais.get(i).Teste();
        }
        
    }
    void ReconheceAlfabeto() {
//        for (int i = 0; i < expressao.length(); i++) {
//            if (expressao.charAt(i) != '+' && expressao.charAt(i) != '.' && expressao.charAt(i) != '*' && expressao.charAt(i) != '\\') {
//                alfabeto.add(expressao.charAt(i));
//            }
//        }
//        Collections.sort(alfabeto);
//        //alfabeto.distinct();
    }

}
