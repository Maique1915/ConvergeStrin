/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convergestring;

import java.util.Scanner;

/**
 *
 * @author maique
 */
public class ConvergeString extends SelecaoNatural{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Populacao p = new Populacao();
        inicia();
    }
    
    public static void inicia(){
        System.out.println("digite a palavra!(letras minusculas e sem acentos)");
        Scanner s = new Scanner(System.in);
        //s.hasNextLine();
        //new ConvergeString(s.nextLine());
        new ConvergeString("maique pereira");
        
    }

    public ConvergeString(String STR) {
        super(STR);
    }
    
}
