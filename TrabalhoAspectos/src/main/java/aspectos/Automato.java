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

    private String tag;
    private String expressao;
    private List<Character> alfabeto;
    private List<String> conjuntoEstados;
    private String estadoInicial;
    private String nomeTag;
    //private List<Transicao> conjuntoTransicao;

    Automato(String tag, String expressao) {
        this.tag = tag;
        this.expressao = expressao;
        ReconheceAlfabeto();

    }

    void ReconheceAlfabeto() {
        for (int i = 0; i < expressao.length(); i++) {
            if (expressao.charAt(i) != '+' && expressao.charAt(i) != '.' && expressao.charAt(i) != '*' && expressao.charAt(i) != '\\') {
                alfabeto.add(expressao.charAt(i));
            }
        }
        Collections.sort(alfabeto);
        //alfabeto.distinct();
    }

    boolean ValidaExpressao() {
        Stack<String> pilha = new Stack<String>();
        int i = 0;
        boolean valida = true;
        
        while (expressao.charAt(i) != '\0') {
            String aux = "";
            aux += expressao.charAt(i);
            if (aux == "\\") {

            } else if (aux != "+" && aux != "." && aux != "*") {
                pilha.push(aux);
            } else if (aux == "+") {
                String a;
                String b;
                if (!pilha.empty()) {
                    a = pilha.pop();
                } else {
                    valida = false;
                    break;
                }

                if (!pilha.empty()) {
                    b = pilha.pop();
                } else {
                    valida = false;
                    break;
                }
                pilha.push(b + "+" + a);
            } else if (aux == ".") {
                String a;
                String b;
                if (!pilha.empty()) {
                    a = pilha.pop();
                } else {
                    valida = false;
                    break;
                }

                if (!pilha.empty()) {
                    b = pilha.pop();
                } else {
                    valida = false;
                    break;
                }
                pilha.push(b + "." + a);
            } else if (aux == "*") {
                String a;
                if (!pilha.empty()) {
                    a = pilha.pop();
                } else {
                    valida = false;
                    break;
                }
                pilha.push("(" + a + ")*");
            }
            i++;
        }
        String expressao2;
        if (!pilha.empty()) {
            // Teste: cout << expressao2 << endl;
            expressao2 = pilha.pop();
        }
        if (pilha.empty() && valida) {
            //Teste: cout << "Expressao: " << expressao2 << endl;
            return true;
        } else {
            return false;
        }
    }
    
    void ExibirAlfabeto(){
    for(int i = 0; i < alfabeto.size(); i++){
        System.out.println(i + "");
    }
        System.out.println("");
}
    
    
    
    
    
    String GetExpressao() {
        return expressao;
    }

    String GetTag() {
        return tag;
    }
}
