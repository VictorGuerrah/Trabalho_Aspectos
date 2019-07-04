//Alunos:
//Caio Vincenzo Reis Dima   201776003
//Pedro Cotta Badaro        201776014
//Victor Guerra Horta       201776005
package aspectos;


public class Transicao {
    public char simbolo;
    private boolean consumiuPalavra;
    private Estado destino;
    
    Transicao(char simbolo, Estado destino){
        consumiuPalavra = false;
        this.simbolo = simbolo;
        this.destino = destino;
    }

    @Override
    public String toString() {
        return simbolo + "";
    }
    public Estado getEstadoDestino(){
        return this.destino;
    }
}
