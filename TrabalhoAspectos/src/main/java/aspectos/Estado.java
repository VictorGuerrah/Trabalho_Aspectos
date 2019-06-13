/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aspectos;

import java.util.List;

/**
 *
 * @author reisd
 */
public class Estado {
    public boolean estadoFinal;
    public boolean estadoInicial;
    List<Transicao> transicoes;
    
    void Estado(){
        estadoFinal = false;
        estadoInicial = false;
    }
    
    public void criarTransicao(String simbolo, Estado destino){
        
    }
    
    
}
