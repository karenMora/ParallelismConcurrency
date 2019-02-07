/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.blackListSearch;

import edu.eci.blackListSearch.HostBlacklistsDataSourceFacade;
import static edu.eci.blackListSearch.HostBlackListsValidator.BLACK_LIST_ALARM_COUNT;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author 2092692
 */
public class Threads extends Thread{
    String ip;
    LinkedList<Integer> blackListOcurrences=new LinkedList<>();
    
    public Threads(){
    }

    Threads(String a) {
        ip=a;
    }
    
    /**
     * preguntar a las instancias (los hilos) cuántas
     * ocurrencias de servidores maliciosos ha encontrado
     * 
     * @return el numero de servidores maliciosos
     */
    public int getInstanciasMalas(){
        return 0;
    }
    
    /**
     * busca un segmento del grupo de servidores disponibles.
     */
    public void run(){
        HostBlacklistsDataSourceFacade skds=HostBlacklistsDataSourceFacade.getInstance();
        int checkedListsCount=0;
        int ocurrencesCount=0;
        
        for (int i=0;i<skds.getRegisteredServersCount() && ocurrencesCount<BLACK_LIST_ALARM_COUNT;i++){
            checkedListsCount++;
            if (skds.isInBlackListServer(i, ip)){
                blackListOcurrences.add(i);
                ocurrencesCount++;
            }
        }    
    }
}
