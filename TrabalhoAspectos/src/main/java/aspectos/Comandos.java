//Alunos:
//Caio Vincenzo Reis Dima   201776003
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
import java.util.List;


public class Comandos {
    
    private List<List<String>> listaClassificacoes;
    
    Comandos(){
        listaClassificacoes = new ArrayList<>();
    }
    
    public void realizarComando(String comando, List<Tag> tags, List<Automato> automatos) throws IOException{
        int i = 0;
        String complemento = "";
        if(comando.length() >= 2){
            if(comando.charAt(i) == ':'){
                i++;
                char tipo = comando.charAt(i);
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
                switch (tipo) {
                    case 'f':
                        classificarStringsDeArquivo(complemento, tags, automatos);
                        break;
                    case 'l':
                        System.out.println("Complemento: " + complemento);
                        carregarTags(complemento, tags, automatos);
                        break;
                    case 'o':
                        salvarClassificacao(complemento);
                        break;
                    case 'p':
                        classificarString(complemento, automatos);
                        break;
                    case 's':
                        System.out.println("Complemento: " + complemento);
                        salvarTags(complemento, tags);
                        break;
                    case 'q':
                        break;
                    default:
                        System.out.println("Comando incorreto");
                        break;
                }
            }
            //Criação de tag
            else{
                criarTag(comando, tags, automatos);
            }
        }

    }
    
    public void classificarString(String complemento, List<Automato> automatos){
        char aux;
        int i = 0;
        List<String> classificacao =  new ArrayList<>();
        classificacao.add(complemento);
        while(i < complemento.length()){
            aux = complemento.charAt(i);
            for(Automato automato : automatos){
                automato.reconhcerPalavra(aux);
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
                if(!identificarTag(automatos, classificacao)){
                    i++;
                }
                for(Automato automato : automatos){
                    automato.resetarEstadoAtual();
                }
            }
            
        }
        identificarTag(automatos, classificacao);
        for(Automato automato : automatos){
            automato.resetarEstadoAtual();
        }
        listaClassificacoes.add(classificacao);
        System.out.println("");
    }
    
    public boolean identificarTag(List<Automato> automatos, List<String> classificacao){
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
            classificacao.add("Nenhum | ");
            System.out.print("Nenhum | ");
            return false;
        }
        else{
            if(cadeias.size() > 1){
                System.out.println("Mais de uma tag pode reconhcer a palavra");
            }
            for(Automato automato : cadeias){
                classificacao.add(automato.getTag() + " ");
                System.out.print(automato.getTag() + " ");
            }
            classificacao.add(" | ");
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
        for (Tag tag : tags) {
            file.write(tag.getTag() + "\n");
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
    
    public void salvarClassificacao(String complemento) throws IOException, FileNotFoundException{
        FileWriter file =  new FileWriter(complemento);
        listaClassificacoes.forEach((lista) -> {
            try {
                int i = 0;
                file.write(lista.get(i) + ":\n");
                System.out.println(lista.get(i));
                i++;
                while(i < lista.size()){
                    System.out.print(lista.get(i) + " ");
                    file.write(lista.get(i));
                    i++;
                }
                file.write("\n\n");
            } catch (IOException ex) {
                //arrumar
            }
        });
        file.close();
    }
    
    public void criarTag(String input, List<Tag> tags, List<Automato> automatos){
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
                    tags.add(tag);
                    Automato automato = tag.criarAutomato();
                    automato.setTag(tag);
                    automatos.add(automato);
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
