package com.API.masiv.TestApi.Repository;
import com.API.masiv.TestApi.Documents.Bet;
import com.API.masiv.TestApi.Documents.Roulette;
import java.util.ArrayList;
import java.util.Map;

public interface RouletteRepository {
    
    Map<String, Roulette> findAll();
    
    Roulette findById (String id);
    
    void save(Roulette roulette);
    
    void delete(String Id);
    
    void updateStatus(String id);
    
    void updateBets(Bet bet, Roulette roulette);
    
    ArrayList<Bet> endRoulette(String id);
}
