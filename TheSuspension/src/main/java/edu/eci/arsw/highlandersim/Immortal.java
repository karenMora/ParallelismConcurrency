package edu.eci.arsw.highlandersim;

import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Immortal extends Thread {

    private ImmortalUpdateReportCallback updateCallback=null;
    
    private int health;
    
    private int defaultDamageValue;

    private final List<Immortal> immortalsPopulation;

    private final String name;

    private final Random r = new Random(System.currentTimeMillis());

    public boolean pausa=false;
    public boolean pelear=true;
    private final int index;

    public Immortal(String name, List<Immortal> immortalsPopulation, int health, int defaultDamageValue, ImmortalUpdateReportCallback ucb,int id) {
        super(name);
        this.updateCallback=ucb;
        this.name = name;
        this.immortalsPopulation = immortalsPopulation;
        this.health = health;
        this.defaultDamageValue=defaultDamageValue;
        this.index=id;
    }

    public void run() {

        while (true && this.health>0) {
            Immortal im;
            int myIndex = immortalsPopulation.indexOf(this);
            int nextFighterIndex = r.nextInt(immortalsPopulation.size());

            //avoid self-fight
            if (nextFighterIndex == myIndex) {
                nextFighterIndex = ((nextFighterIndex + 1) % immortalsPopulation.size());
            }
            im = immortalsPopulation.get(nextFighterIndex);
            this.fight(im);

            
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    public void fight(Immortal i2) {
        if(pausa){
            synchronized(ControlFrame.lock){
                try{
                    ControlFrame.pauseInm.getAndIncrement();
                    ControlFrame.lock.wait();
                }catch(Exception e){
                    Logger.getLogger(Immortal.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        Immortal inmortalA, inmortalB;
        if (i2.getIndex() < this.getIndex()) {
            inmortalA = this;
            inmortalB = i2;
        } else {
            inmortalA = i2;
            inmortalB = this;
        }
        synchronized (inmortalA) {
            synchronized (inmortalB) {
                if (i2.getHealth() > 0) {
                    i2.changeHealth(i2.getHealth() - defaultDamageValue);
                    this.health += defaultDamageValue;
                    updateCallback.processReport("Fight: " + this + " vs " + i2 + "\n");
                } else {
                    updateCallback.processReport(this + " says:" + i2 + " is already dead!\n");
                }
            }
        }
    }

    public void changeHealth(int v) {
        health = v;
    }

    public int getHealth() {
        return health;
    }

    @Override
    public String toString() {

        return name + "[" + health + "]";
    }
    
    public void continuar(){
        pausa=false;
    }
    public void pause(){
        pausa=true;
    }
    public void stopp(){
        pelear=false;
    }
    public int getIndex(){
        return index;
    }
}
