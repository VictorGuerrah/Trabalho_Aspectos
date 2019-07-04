//Alunos:
//Caio Vincenzo Reis Dima   201776003
//Pedro Cotta Badaro        201776014    
//Victor Guerra Horta       201776005
package aspectos;

import java.util.ArrayList;
import java.util.List;


public class Estado {
    public boolean estadoFinal;
    public boolean estadoInicial;
    public List<Transicao> transicoes;
    public int id;
    public static int idAtual = 0;

    @Override
    public String toString() {
        return this.id + "";//To change body of generated methods, choose Tools | Templates.
    }
    
    Estado(){
        id = idAtual;
        idAtual++;
        estadoFinal = false;
        estadoInicial = false;
        transicoes = new ArrayList<>();
    }
    
    public void criarTransicao(char simbolo, Estado destino){
        Transicao novaTransicao = new Transicao(simbolo, destino);
        this.transicoes.add(novaTransicao);
    }
    
}
