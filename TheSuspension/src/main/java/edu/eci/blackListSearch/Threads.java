/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.blackListSearch;

import edu.eci.blackListSearch.HostBlacklistsDataSourceFacade;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author 2092692
 */
public class Threads extends Thread{
    private String ip,ipaddres;
    LinkedList<Integer> blackListOcurrences=new LinkedList<>();
    
    private int N,n,inicio,fin, tamaño;
    private HostBlacklistsDataSourceFacade skds=HostBlacklistsDataSourceFacade.getInstance();
    public int checkedListsCount=0;
    public int ocurrencesCount=0;
    private static final int BLACK_LIST_ALARM_COUNT=5;
    
    public Threads(){
    }

    Threads(String a, int y, int z) {
        ip=a;
        n=y;
        N=z;
    }
    
    
    /**PARA EL SEGMENTO
     * INICIO
     * FIN
     * TAMAÑO
     */
    public Threads(int in,int fn,String ip, int lng){
        inicio=in;
        fin=fn;
        ipaddres=ip;
        tamaño=lng;
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
        
        for (int i = inicio; i < fin && HostBlackListsValidator.ocurrencesCount < BLACK_LIST_ALARM_COUNT; i++) {
            synchronized (HostBlackListsValidator.checkedListsCount) {
                HostBlackListsValidator.checkedListsCount++;
            }
            if (skds.isInBlackListServer(i, ipaddres)) {
                synchronized (HostBlackListsValidator.blackListOcurrences) {
                    HostBlackListsValidator.blackListOcurrences.add(i);
                }
                HostBlackListsValidator.ocurrencesCount++;
            }
        }
        if (HostBlackListsValidator.ocurrencesCount >= BLACK_LIST_ALARM_COUNT) {
            skds.reportAsNotTrustworthy(ipaddres);
        } else {
            skds.reportAsTrustworthy(ipaddres);
        }
    }

    public int consultarOcurrences() {
        return ocurrencesCount;
    }
        
        /*
        for (int i=0;i<skds.getRegisteredServersCount() && ocurrencesCount<BLACK_LIST_ALARM_COUNT;i++){
            checkedListsCount++;
            if (skds.isInBlackListServer(i, ip)){
                blackListOcurrences.add(i);
                ocurrencesCount++;
            }
        } */
}
