//Alunos:
//Caio Vincenzo Reis Dima   201776003
//Pedro Cotta Badaro
//Victor Guerra Horta
package aspectos;

import java.util.*;
import java.util.Set;

/**
 *
 * @author pedro
 */
public class Automato {
    public Set<Estado> conjuntoEstados;
    public Estado estadoInicial;
    public Estado estadoFinal;
    public Set<Estado> estadosAtuais;
    
    private String subCadeiaReconhecida;
    private String subCadeiaProcessada;
    
    private Tag tag = null;
    private boolean podeReconhcer;

    Automato(Tag tag) {
        this.tag = tag;
    }
    Automato(Estado estadoInicial, Estado estadoFinal){
        this.conjuntoEstados = new HashSet<>();
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
        return !estadosAtuais.isEmpty();
    }
    
    public String getSubCadeia(){
        return subCadeiaReconhecida;
    }

}
