/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aspectos;

import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author pedro
 */
public class Automato {

    //private String tag;
    //private String expressao;
    public List<Estado> conjuntoEstados;
    public Estado estadoInicial;
    public Estado estadoFinal;
    public Set<Estado> estadosAtuais;
    
    private Tag tag = null;
    //private List<Transicao> conjuntoTransicao;
    private boolean podeReconhcer;

    Automato(Tag tag) {
        this.tag = tag;
    }
    Automato(Estado estadoInicial, Estado estadoFinal){
        this.conjuntoEstados = new ArrayList<>();
        conjuntoEstados.add(estadoInicial);
        conjuntoEstados.add(estadoFinal);
        this.estadoInicial = estadoInicial;
        this.estadoFinal = estadoFinal;
        this.estadosAtuais = new HashSet<>();
        
        this.podeReconhcer = true;
    }
    
    void Teste(){
//        System.out.println("Tamanho estadosIniciais: " + estadosIniciais.size());
//        System.out.println("Tamanho estadosFinais: " + estadosFinais.size());
        System.out.println("Tamanho conjuntoEstados: " + this.conjuntoEstados.size());
        
        System.out.println("outros testes");
//        for (int i = 0; i < estadosIniciais.size(); i++) {
//            //estadosIniciais.get(i).Teste();
//        }
        
    }
    
    void setTag(Tag tag){
        this.tag = tag;
    }
    
    
    public void reconhcerPalavra(char simbolo){
        if(estadosAtuais.isEmpty()){
            if(podeReconhcer){
                Estado aux = estadoInicial;
                estadosAtuais.addAll(buscaEmLargura(aux, simbolo));
                if(estadosAtuais.isEmpty()){
                    podeReconhcer = false;
                }
            }
        }
        else{
            Iterator<Estado> it = estadosAtuais.iterator();
            Set<Estado> setAux = new HashSet<>();
            for(Estado estado : estadosAtuais){
                setAux.addAll(buscaEmLargura(estado, simbolo));
            }
            estadosAtuais.clear();
            estadosAtuais.addAll(setAux);
        }
    }
    
    private Set<Estado> buscaEmLargura(Estado estado, char simbolo){
        Set <Estado>set =  new HashSet<>();
        Set <Estado>setAux =  new HashSet<>();
        for(Transicao transicao : estado.transicoes){
            if(transicao.simbolo == simbolo){
                setAux.add(transicao.getEstadoDestino());
                setAux.addAll(buscaEmLargura(transicao.getEstadoDestino(), '\\'));
            }
            else if(transicao.simbolo == '\\'){
                setAux.addAll(buscaEmLargura(transicao.getEstadoDestino(), simbolo));
            }
        }
        set.addAll(setAux);
        return set;
    }
    
    public String getTag(){
        return this.tag.getTag();
    }
    
    public void resetarEstadoAtual(){
        podeReconhcer = true;
        estadosAtuais.clear();
    }
    
    public boolean reconhce(){
        for (Estado estado : estadosAtuais) {
            if(estado.estadoFinal)
                return true;
        }
        return false;
    }

}
