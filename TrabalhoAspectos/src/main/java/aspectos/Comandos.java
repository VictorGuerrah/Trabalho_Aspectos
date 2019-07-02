//Alunos:
//Caio Vincenzo Reis Dima
//Pedro Cotta Badaro
//Victor Guerra Horta
package aspectos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author reisd
 */
public class Comandos {
    
    Comandos(){
        
    }
    
    public void realizarComando(String comando, List<Tag> tags, List<Automato> automatos) throws IOException{
        int i = 0;
        String complemento = "";
        if(comando.length() >= 2){
            if(comando.charAt(i) == ':'){
                i++;
                char tipo = comando.charAt(i);
//                if(tipo == 'q'){
//                    i++;
//                    if(comando.length() == 2 || (comando.length() == 3 && comando.charAt(i) == ' ')){
//                        sair();
//                    }
//                }
//                else if(tipo == 'a'){
//                    System.out.println(tags.size());
//                    for(int j = 0; j < tags.size(); j++){
//                        tags.get(j).exibirTag();
//                    }
//                }
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
                    classificarStringsDeArquivo(complemento, tags, automatos);
                }
                else if(tipo == 'l'){
                    System.out.println("Complemento: " + complemento);
                    carregarTags(complemento, tags, automatos);
                }
                else if(tipo == 'o'){

                }
                else if(tipo == 'p'){
                    classificarString(complemento, automatos);
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
                criarTag(comando, tags, automatos);
                
            }
        }

    }
    
    public void classificarString(String complemento, List<Automato> automatos){
        System.out.println(complemento + ": ");
        char aux2;
        int i = 0;
        Set<Automato> automatosAceitos = new HashSet<>();
        Set<Automato> automatosAceitos2 = new HashSet<>();
        Set<Automato> podeReconhcer = new HashSet<>();
        while(i < complemento.length()){
            aux2 = complemento.charAt(i);
            for(Automato automato : automatos){
                automato.reconhcerPalavra(aux2);
            }
            boolean algumPodeReconhcer = false;
            for(Automato automato : automatos){
                if(automato.podeReconhcer()){
                    algumPodeReconhcer = true;
                    i++;
                    break;
                }
            }
            if(!algumPodeReconhcer){
                if(!identificarTag(automatos)){
                    i++;
                }
                for(Automato automato : automatos){
                    automato.resetarEstadoAtual();
                }
            }
            
        }
        identificarTag(automatos);
        for(Automato automato : automatos){
            automato.resetarEstadoAtual();
        }
        System.out.println("");
    }
    
    public boolean identificarTag(List<Automato> automatos){
        String maiorCadeia = "";
        List<Automato> cadeias = new ArrayList<>();
        for(Automato automato : automatos){
            if(automato.getSubCadeia() != null){
                if(automato.getSubCadeia().length() > maiorCadeia.length()){
                    cadeias.clear();
                    cadeias.add(automato);
                    maiorCadeia = automato.getSubCadeia();
                }
                else if(automato.getSubCadeia().length() == maiorCadeia.length()){
                    cadeias.add(automato);
                }
            }
        }
        if(cadeias.isEmpty()){
            System.out.print("Nenhuma | ");
            return false;
        }
        else{
            if(cadeias.size() > 1){
                System.out.println("Mais de uma tag pode reconhcer a palavra");
            }
            else{
                
            }
            for(Automato automato : cadeias){
                System.out.print(automato.getTag() + " ");
            }
            System.out.print(" | ");
            return true;
        }
    }
    
    public void sair(){
        System.out.println("Voce saiu");
        System.exit(0);
    }
    
    public void carregarTags(String complemento, List<Tag> tags, List<Automato> automatos) throws FileNotFoundException, IOException{
        File file = new File(complemento);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str;
        while((str = br.readLine()) != null){
            criarTag(str, tags, automatos);
        }
    }
    
    public void salvarTags(String complemento, List<Tag> tags) throws FileNotFoundException, IOException{
        FileWriter file =  new FileWriter(complemento);
        for (int i = 0; i < tags.size(); i++) {
            file.write(tags.get(i).getTag() + "\n");
        }
        file.close();
    }
    
    public void classificarStringsDeArquivo(String complemento, List<Tag> tags, List<Automato> automatos) throws FileNotFoundException, IOException{
        File file = new File(complemento);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str;
        while((str = br.readLine()) != null){
            classificarString(str, automatos);
        }
    }
    
    public void salvarClassificacao(){
        
    }
    
    public void criarTag(String input, List<Tag> tags, List<Automato> automatos){
        //System.out.println("Expressao: " + input);
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
                    System.out.println("Expressao aceita");
                    //System.out.println(tag.expressao2);
                    tags.add(tag);
                    Automato automato = tag.criarAutomato();
                    automato.setTag(tag);
                    automatos.add(automato);
                    //automato.Teste();
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
