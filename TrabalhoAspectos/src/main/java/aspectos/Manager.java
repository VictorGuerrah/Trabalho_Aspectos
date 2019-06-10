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
    
    Manager(){
        tags = new ArrayList<Tag>();
    }
    
    public void Start() throws IOException{
        String comando = "";
        Scanner input = new Scanner(System.in);
        Comandos comandos = new Comandos();
        while(true){
            System.out.println("Digite o comando:");
            comando = input.nextLine();
            comandos.realizarComando(comando, tags);
            System.out.println("Tamanho de tags: " + tags.size());
        }
    }
}
