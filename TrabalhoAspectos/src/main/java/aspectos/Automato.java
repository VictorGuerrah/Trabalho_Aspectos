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
    private List<Transicao> conjuntoTransicao;

    Automato(String tag, String expressao) {
        this.tag = tag;
        this.expressao = expressao;
        ReconheceAlfabeto();

    }

    void ReconheceAlfabeto() {
        list<Character>::iterator it;
        for (int i = 0; i < expressao.length(); i++) {
            if (expressao[i] != '+' && expressao[i] != '.' && expressao[i] != '*' && expressao[i] != '\\') {
                alfabeto.push_back(expressao[i]);
            }
        }
        alfabeto.sort();
        alfabeto.unique();

    }

    boolean ValidaExpressao() {
        Stack<String> pilha;
        int i = 0;
        boolean valida = true;

        while (expressao[i] != '\0') {
            String aux = "";
            aux += expressao[i];
            if (aux == "\\") {

            } else if (aux != "+" && aux != "." && aux != "*") {
                pilha.push(aux);
            } else if (aux == "+") {
                String a;
                String b;
                if (!pilha.empty()) {
                    a = pilha.top();
                    pilha.pop();
                } else {
                    valida = false;
                    break;
                }

                if (!pilha.empty()) {
                    b = pilha.top();
                    pilha.pop();
                } else {
                    valida = false;
                    break;
                }
                pilha.push(b + "+" + a);
            } else if (aux == ".") {
                String a;
                String b;
                if (!pilha.empty()) {
                    a = pilha.top();
                    pilha.pop();
                } else {
                    valida = false;
                    break;
                }

                if (!pilha.empty()) {
                    b = pilha.top();
                    pilha.pop();
                } else {
                    valida = false;
                    break;
                }
                pilha.push(b + "." + a);
            } else if (aux == "*") {
                String a;
                if (!pilha.empty()) {
                    a = pilha.top();
                    pilha.pop();
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
            expressao2 = pilha.top();
            pilha.pop();
        }
        if (pilha.empty() && valida) {
            //Teste: cout << "Expressao: " << expressao2 << endl;
            return true;
        } else {
            return false;
        }
    }
    
    void ExibirAlfabeto(){
    list<Character>::iterator it;
    for(it = alfabeto.begin(); it != alfabeto.end(); it++){
        System.out.println(it+"");
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
