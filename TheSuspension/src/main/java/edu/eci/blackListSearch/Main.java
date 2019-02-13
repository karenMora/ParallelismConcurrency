/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.blackListSearch;

import java.util.List;

/**
 *
 * @author hcadavid
 */
public class Main {
    
    public static void main(String a[]) throws InterruptedException{
        HostBlackListsValidator hblv=new HostBlackListsValidator();
        List<Integer> blackListOcurrences=hblv.checkHost("200.24.34.55",10);
        System.out.println("The host was found in the following blacklists:"+blackListOcurrences);
        
        //int cantHilos=(Runtime.getRuntime().availableProcessors());
        /*
        int cantHilos=50;
        Thread[] arrayHilos=new Thread[cantHilos];
        for(int i=0; i<cantHilos;i++){
            arrayHilos[i]= new Threads("200.24.34.55");
        }
        
        for(int i=0; i<cantHilos;i++){
            arrayHilos[i].start();
        }
        System.out.println("The host was found in the following blacklists:"+blackListOcurrences);
        */
        
        /*
        Threads hilos=new Threads();
        hilos.start();
        System.out.println("\n");
        System.out.println("La cantidad de servidores maliciosos encontrados es: "+ hilos.getInstanciasMalas());
        */
    }
    
}
