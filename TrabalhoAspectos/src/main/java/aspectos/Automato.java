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
    
    private String subCadeiaReconhecida;
    private String subCadeiaProcessada;
    
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
        estadosAtuais.add(estadoInicial);
        
        this.podeReconhcer = true;
        this.subCadeiaReconhecida = null;
        this.subCadeiaProcessada = null;
    }
    
    void setTag(Tag tag){
        this.tag = tag;
    }
    
    
    public void reconhcerPalavra(char simbolo){
        if(podeReconhcer()){
            if(subCadeiaProcessada == null)
                subCadeiaProcessada = "";
            subCadeiaProcessada += simbolo;
            Set<Estado> setAux = new HashSet<>();
            for(Estado estado : estadosAtuais){
                setAux.addAll(buscaEmLargura(estado, simbolo));
            }
            estadosAtuais.clear();
            estadosAtuais.addAll(setAux);
            for(Estado estado : estadosAtuais){
                if(estado.estadoFinal){
                    subCadeiaReconhecida = subCadeiaProcessada;
                }
            }
        }
        
        
        
        
        /*
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
        }*/
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
        estadosAtuais.add(estadoInicial);
        subCadeiaReconhecida = null;
        subCadeiaProcessada = null;
    }
    
    public boolean reconhce(){
        for (Estado estado : estadosAtuais) {
            if(estado.estadoFinal)
                return true;
        }
        return false;
    }
    
    public boolean podeReconhcer(){
        if(estadosAtuais.isEmpty())
            return false;
        return true;
    }
    
    public String getSubCadeia(){
        return subCadeiaReconhecida;
    }

}
