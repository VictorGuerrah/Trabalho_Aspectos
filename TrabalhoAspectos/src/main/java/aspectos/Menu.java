/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aspectos;

import java.util.Scanner;

/**
 *
 * @author reisd
 */
public class Menu {
    
    Menu(){
        
    }
    
    public int EscolheComando(String comando){
        int i = 0;
        if(comando.charAt(i) == ':'){
            i++;
            if(comando.charAt(i) == 'f'){
                i++;
            }
            if(comando.charAt(i) == 'l'){
            
            }
            if(comando.charAt(i) == 'o'){
            
            }
            if(comando.charAt(i) == 'p'){
            
            }
            if(comando.charAt(i) == 'q'){
            
            }
            if(comando.charAt(i) == 's'){
            
            }
        }
        
        String a;
        return 0;
    }
    
    public void Start(){
        String comando = "";
        Scanner input = new Scanner(System.in);
        comando = input.nextLine();
        int comandoEscolhido;
    }
}
