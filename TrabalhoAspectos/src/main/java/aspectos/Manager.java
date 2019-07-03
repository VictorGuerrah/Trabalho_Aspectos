/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aspectos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author reisd
 */
public class Manager {
    List<Tag> tags;
    List<Automato> automatos;
    Manager(){
        tags = new ArrayList<>();
        automatos = new ArrayList<>();
    }
    
    public void Start() throws IOException{
        String comando = "";
        Scanner input = new Scanner(System.in);
        Comandos comandos = new Comandos();
        while(!":q".equals(comando)){
            System.out.println("Digite o comando:");
            comando = input.nextLine();
            comandos.realizarComando(comando, tags, automatos);
            System.out.println("Tamanho de tags: " + tags.size() + "\n");
        }
    }
}
