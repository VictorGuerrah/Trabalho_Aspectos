/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aspectos;

/**
 *
 * @author reisd
 */
public class Comandos {
    
    Comandos(){
        
    }
    
    public void escolherComando(String comando){
        int i = 0;
        String complemento = "";
        if(comando.length() >= 2){
            if(comando.charAt(i) == ':'){
                i++;
                char tipo = comando.charAt(i);
                if(tipo == 'q'){
                    i++;
                    if(comando.length() == 2 || (comando.length() == 3 && comando.charAt(i) == ' ')){
                        sair();
                    }
                }
                i++;
                //Guarda na variável 'complemento' a segunda parte do comando que nao seja ':q'
                //Verifica se o comando tem tamanho maior que 2 e verifica se o terceiro
                //caractere é ' ' (espaço em brando)
                if(comando.length() > 2 && comando.charAt(i) == ' '){
                    i++;
                    while(i < comando.length()){
                        complemento += comando.charAt(i);
                    }
                }
                else{
                    System.out.println("Comando inexistente");
                }
                //
                if(tipo == 'f'){
                    
                }
                if(tipo == 'l'){

                }
                if(tipo == 'o'){

                }
                if(tipo == 'p'){

                }

                if(tipo == 's'){

                }
                else{
                    System.out.println("Comando incorreto");
                }
            }
            //Criação de tag
            else{
                String array[] = comando.split(": ");
                if(array.length == 2){
                    String tag = array[0];
                    String expressaoRegular = array[1];
                    System.out.println("Nome da tag: " + tag); 
                    System.out.println("Exepressao: " + expressaoRegular);
                }
            }
        }

    }
    
    public void classificarString(){
        
    }
    
    public void sair(){
        System.out.println("Voce saiu");
        System.exit(0);
    }
    
    public void carregarTags(){
        
    }
    
    public void salvarTags(){
        
    }
    
    public void classificarStringsDeArquivo(){
        
    }
    
    public void salvarClassificacao(){
        
    }
    
    public void criarTag(String tag, String expressao){
        
    }    
}
