/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.blackListSearch;

import edu.eci.blackListSearch.HostBlacklistsDataSourceFacade;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hcadavid
 */
public class HostBlackListsValidator extends Thread{

    private static final int BLACK_LIST_ALARM_COUNT=5;
    int ocurrences=0;
    public static LinkedList<Integer> blackListOcurrences=new LinkedList<>();
    public static int ocurrencesCount=0;
    public static Integer checkedListsCount=0;
    
    /**
     * Check the given host's IP address in all the available black lists,
     * and report it as NOT Trustworthy when such IP was reported in at least
     * BLACK_LIST_ALARM_COUNT lists, or as Trustworthy in any other case.
     * The search is not exhaustive: When the number of occurrences is equal to
     * BLACK_LIST_ALARM_COUNT, the search is finished, the host reported as
     * NOT Trustworthy, and the list of the five blacklists returned.
     * @param ipaddress suspicious host's IP address.
     * @return  Blacklists numbers where the given host's IP address was found.
     */
    public List<Integer> checkHost(String ipaddress, int N) throws InterruptedException{
        HostBlacklistsDataSourceFacade skds=HostBlacklistsDataSourceFacade.getInstance();
        
        LinkedList<Threads> hilos =new LinkedList<Threads>();
        int nHilos=0;
        int rango, p=0, totl=0, servidores=0,k=0;
        
        if(N%2==0){
            rango=(skds.getRegisteredServersCount())/N;
            while(p<N){
                Threads hl=new Threads(totl,rango,ipaddress,N);
                hl.start();
                hl.join();
                hilos.add(hl);
                totl+=rango;
                ocurrencesCount+=hl.ocurrencesCount;
                if(ocurrencesCount>=5){
                    checkedListsCount=hl.checkedListsCount;
                }
                p+=1;
            }
            if (ocurrencesCount >= BLACK_LIST_ALARM_COUNT) {
            skds.reportAsNotTrustworthy(ipaddress);
            } else {
                skds.reportAsTrustworthy(ipaddress);
            }
            LOG.log(Level.INFO, "Checked Black Lists:{0} of {1}", new Object[]{checkedListsCount, skds.getRegisteredServersCount()}); 
        }else{
            rango=((skds.getRegisteredServersCount())/N)-1;
            while(k<N){
                Threads hl=new Threads(totl,rango,ipaddress,N);
                hl.start();
                hilos.add(hl);
                totl+=rango;
                ocurrencesCount+=hl.ocurrencesCount;
                if(ocurrencesCount>=5){
                    checkedListsCount=hl.checkedListsCount;
                }
                k+=1;
            }
            if (ocurrencesCount >= BLACK_LIST_ALARM_COUNT) {
            skds.reportAsNotTrustworthy(ipaddress);
            } else {
                skds.reportAsTrustworthy(ipaddress);
            }
            LOG.log(Level.INFO, "Checked Black Lists:{0} of {1}", new Object[]{checkedListsCount, skds.getRegisteredServersCount()}); 
        }
        return blackListOcurrences;
        
        /*
        for (int i=0;i<skds.getRegisteredServersCount() && ocurrencesCount<BLACK_LIST_ALARM_COUNT;i++){
            checkedListsCount++;
            if (skds.isInBlackListServer(i, ipaddress)){     
                blackListOcurrences.add(i);
                ocurrencesCount++;
            }
        }
        
        if (ocurrencesCount>=BLACK_LIST_ALARM_COUNT){
            skds.reportAsNotTrustworthy(ipaddress);
        }
        else{
            skds.reportAsTrustworthy(ipaddress);
        }                
        
        LOG.log(Level.INFO, "Checked Black Lists:{0} of {1}", new Object[]{checkedListsCount, skds.getRegisteredServersCount()});
        return blackListOcurrences;*/
    }
    private static final Logger LOG = Logger.getLogger(HostBlackListsValidator.class.getName());
}
