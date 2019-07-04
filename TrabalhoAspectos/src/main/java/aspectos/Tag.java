//Alunos:
//Caio Vincenzo Reis Dima   201776003
//Pedro Cotta Badaro        201776014
//Victor Guerra Horta       201776005
package aspectos;

import java.util.Stack;


public class Tag {
    String nome;
    String expressao;
    String expressaoAlterada;

    
    
    Tag(String nome, String expressao){
        this.nome = nome;
        this.expressao = expressao;
        this.expressaoAlterada = null;
    }
    
    public void exibirTag(){
        System.out.println("[Info] Nome: " + nome + "  Expressao: " + expressao);
        System.out.println("[Info] Expressao alterada: " + expressaoAlterada + "\n");
    }
    
    public String getTag(){
        return nome;
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
                if(pilha.empty()){
                    valida = false;
                    break;
                }
                String a = (String) pilha.pop();
                pilha.push("(" + a + ")*");
            }
            else{
                if(pilha.isEmpty()){
                    valida = false;
                    break;
                }
                String a = (String) pilha.pop();
                if(pilha.isEmpty()){
                    valida = false;
                    break;
                }
                String b = (String) pilha.pop();
                if(!"+".equals(aux)){
                    pilha.push(b + "+" + a);
                }
                else{
                    pilha.push(b + "" + a);
                }
            }
            i++;
        }
        if(pilha.size() == 1){
            this.expressaoAlterada = (String) pilha.pop();
        }
        else
            valida = false;
        return valida;
    }
    
    public Automato criarAutomato(){
        if(expressao != null){
            Stack pilha = new Stack();
            int i = 0;
            char aux;
            while(i != expressao.length()){
                aux = expressao.charAt(i);
                if(aux != '+' && aux != '.' && aux != '*'){
                    pilha.push(adicionarAutomatoPilha(aux));
                }
                else{
                    if(aux == '*'){
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
                            if(aux == '+'){
                                Automato novoAutomato = criarAutomatoUniao(aux1, aux2);
                                pilha.push(novoAutomato);
                            }
                            else if(aux == '.'){
                                Automato novoAutomato = criaAutomatoConcatenacao(aux1, aux2);
                                pilha.push(novoAutomato);
                            }
                        }
                    }
                }
                i++;
            }
            return (Automato) pilha.pop();
        }
        return null;
    }
    
    public Automato adicionarAutomatoPilha(char simbolo){
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
        
        estadoInicial.criarTransicao('\\', automato1.estadoInicial);
        estadoInicial.criarTransicao('\\', automato2.estadoInicial);
        automato1.estadoFinal.criarTransicao('\\', estadoFinal);
        automato2.estadoFinal.criarTransicao('\\', estadoFinal);
        
        automato1.estadoInicial.estadoInicial = false;
        automato2.estadoInicial.estadoInicial = false;
        automato1.estadoFinal.estadoFinal = false;
        automato2.estadoFinal.estadoFinal = false;
        
        automato1.estadoInicial = estadoInicial;
        automato1.estadoFinal = estadoFinal;
        automato1.conjuntoEstados.addAll(automato2.conjuntoEstados);
        automato1.resetarEstadoAtual();
        return automato1;
    }
    
    public Automato criaAutomatoConcatenacao(Automato automato1, Automato automato2){
        automato1.estadoFinal.criarTransicao('\\', automato2.estadoInicial);
        automato1.estadoFinal.estadoFinal = false;
        automato1.estadoFinal = automato2.estadoFinal;
        automato1.conjuntoEstados.addAll(automato2.conjuntoEstados);
        automato1.resetarEstadoAtual();
        return automato1;
    }
    
    
    public Automato criarAutomatoFechoKleene(Automato automato){
        automato.estadoFinal.criarTransicao('\\', automato.estadoInicial);
        automato.estadoFinal.estadoFinal = false;
        automato.estadoFinal = automato.estadoInicial;
        automato.estadoInicial.estadoFinal = true;
        automato.resetarEstadoAtual();
        return automato;
    }
    
    
}
