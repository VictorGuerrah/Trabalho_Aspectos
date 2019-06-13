/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aspectos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author reisd
 */
public class Comandos {
    
    Comandos(){
        
    }
    
    public void realizarComando(String comando, List<Tag> tags) throws IOException{
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
                else if(tipo == 'a'){
                    System.out.println(tags.size());
                    for(int j = 0; j < tags.size(); j++){
                        tags.get(j).exibirTag();
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
                        i++;
                    }
                }
                else{
                    System.out.println("Comando inexistente");
                }
                
                
                //
                if(tipo == 'f'){
                    
                }
                else if(tipo == 'l'){
                    System.out.println("Complemento: " + complemento);
                    carregarTags(complemento, tags);
                }
                else if(tipo == 'o'){

                }
                else if(tipo == 'p'){

                }
                else if(tipo == 's'){
                    System.out.println("Complemento: " + complemento);
                    salvarTags(complemento, tags);
                }
                else{
                    System.out.println("Comando incorreto");
                }
            }
            //Criação de tag
            else{
                criarTag(comando, tags);
                
            }
        }

    }
    
    public void classificarString(){
        
    }
    
    public void sair(){
        System.out.println("Voce saiu");
        System.exit(0);
    }
    
    public void carregarTags(String complemento, List<Tag> tags) throws FileNotFoundException, IOException{
        
        File file = new File(complemento);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str;
        while((str = br.readLine()) != null){
            System.out.println(str);
            criarTag(str, tags);
        }
    }
    
    public void salvarTags(String complemento, List<Tag> tags) throws FileNotFoundException, IOException{
        FileWriter file =  new FileWriter(complemento);
        for (int i = 0; i < tags.size(); i++) {
            file.write(tags.get(i).getTag() + "\n");
        }
        file.close();
    }
    
    public void classificarStringsDeArquivo(){
        
    }
    
    public void salvarClassificacao(){
        
    }
    
    public void criarTag(String input, List<Tag> tags){
        System.out.println("Expressao: " + input);
        String array[] = input.split(": ");
        String nome;
        String expressao;
        if(array.length == 2){
            nome = array[0];
            expressao = array[1];
            expressao = expressao.replace(" ", "");
            if(!expressao.isEmpty()){
                System.out.println("Nome da tag: " + nome); 
                System.out.println("Exepressao: " + expressao);
                 Tag tag = new Tag(nome, expressao);
                if(tag.validarExpressao()){
                    System.out.println("Expressao aceita\n");
                    tags.add(tag);
                    Automato automato = new Automato(tag);
                }
                else{
                    System.out.println("Expressao rejeitada\n");
                }
            }
            else
            {
                System.out.println("Expressao rejeitada\n");
            }
        }
        else{
            System.out.println("Expressao rejeitada\n");
        }
        
    }    
}
