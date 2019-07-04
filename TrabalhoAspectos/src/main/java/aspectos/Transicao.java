//Alunos:
//Caio Vincenzo Reis Dima   201776003
//Pedro Cotta Badaro
//Victor Guerra Horta
package aspectos;

/**
 *
 * @author reisd
 */
public class Transicao {
    public char simbolo;
    private boolean consumiuPalavra;
    private Estado destino;
    
    Transicao(char simbolo, Estado destino){
        consumiuPalavra = false;
        this.simbolo = simbolo;
        this.destino = destino;
    }
    
    public void Teste(){
        
        System.out.println("");
        destino.Teste();
    }

    @Override
    public String toString() {
        return simbolo + ""; //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean realizaTransicao(String palavra){
        if(simbolo == '/'){
            consumiuPalavra = false;
            return true;
        }
        else if(simbolo == palavra.charAt(0)){
            consumiuPalavra = true;
            return true;
        }
        return false;
    }
    
    public Estado getEstadoDestino(){
        return this.destino;
    }
}
