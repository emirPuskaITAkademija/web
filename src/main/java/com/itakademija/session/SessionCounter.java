package com.itakademija.session;


//SessionCounter sessionCounter -> Jozo ima svoj sessionCounter
//SessionCounter sessionCounter -> Tarik ima svoj sessionCounter
//SessionCounter sessionCounter -> Jovan ima svoj sessionCounter
//SessionCounter sessionCounter -> Dženita ima svoj sessionCounter
public class SessionCounter {
    private int counter;

    public synchronized void increment() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }
}
