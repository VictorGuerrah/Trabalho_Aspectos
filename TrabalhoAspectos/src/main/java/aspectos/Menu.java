/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aspectos;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public class Menu {
    
    List <Integer>list;
    
    Stack pilha = new Stack(); // Classe Pilha
    
    
    
    public static void main(String[] args) throws IOException 
    {
        
        System.out.println("TESTE");
        Scanner teclado=new Scanner(System.in);
        FileWriter arquivo=new FileWriter("arquivo11.txt");
        PrintWriter escreve=new PrintWriter(arquivo);
        
        escreve.println("DADOS DENTRO DO TXT");
        escreve.println(99);
        escreve.println("32323232");
        
        escreve.close();
    }
}
