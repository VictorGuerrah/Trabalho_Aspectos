/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aspectos;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 *
 * @author pedro
 */
public class Main {
    
    List <Integer>list;
    
    Stack pilha = new Stack(); // Classe Pilha
    
    
    
    
    public static void main(String[] args) throws IOException 
    {
        
        Manager manager = new Manager();
        manager.Start();
        /*
        System.out.println("TESTE");
        Scanner teclado = new Scanner(System.in);
        FileWriter arquivo = new FileWriter("arquivo11.txt");
        PrintWriter escreve = new PrintWriter(arquivo);
        
        escreve.println("DADOS DENTRO DO TXT");
        escreve.println(99);
        escreve.println("32323232");
        
        escreve.close();*/
    }
    
    
}
