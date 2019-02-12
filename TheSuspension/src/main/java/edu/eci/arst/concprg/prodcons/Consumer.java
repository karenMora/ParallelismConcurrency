/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arst.concprg.prodcons;

import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hcadavid
 */
public class Consumer extends Thread{
    
    private Queue<Integer> queue;
    private long stockLimit;
    private long continuar;
    
    
    public Consumer(Queue<Integer> queue, long stokLimit){
        this.queue=queue;
        this.stockLimit=stokLimit;
        continuar=(stockLimit/2);
    }
    
    
    /**
     * 
     */
    
    @Override
    public void run() {
        while (true) {
            if (queue.size() > 0) {
                int elem=queue.poll();
                System.out.println("Consumer consumes "+elem);
            }
            try {
                Thread.sleep(1000);
            }catch (InterruptedException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(queue.size()==continuar){
                synchronized(queue){
                    queue.notifyAll();
                }
            }
            
            if(queue.isEmpty() || queue.size()==1){
                synchronized(queue){
                    try {
                        queue.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
}
