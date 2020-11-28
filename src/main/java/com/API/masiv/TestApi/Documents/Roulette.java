package com.API.masiv.TestApi.Documents;
import java.io.Serializable;
import java.util.ArrayList;

public class Roulette implements Serializable {
    
    private static final long serialVersionUID=1113799434508676095L;   
    private String idRoulette;
    private String statusRoulette;
    private ArrayList<Bet> bets;

    public Roulette(){       
    }
    
    public String getIdRoulette() {
        return idRoulette;
    }

    public void setIdRoulette(String idRoulette) {
        this.idRoulette = idRoulette;
    }

    public String getStatusRoulette() {
        return statusRoulette;
    }

    public void setStatusRoulette(String statusRoulette) {
        this.statusRoulette = statusRoulette;
    }    
    
    public ArrayList<Bet> getBets() {
        return bets;
    }

    public void setBets(ArrayList<Bet> bets) {
        this.bets = bets;
    }
    
    public void updateBet(Bet bet){
        bets.add(bet);
    }
    
    public void resetBets(){
        this.bets.clear();
    }
}
