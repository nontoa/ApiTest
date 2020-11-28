package com.API.masiv.TestApi.Repository;
import com.API.masiv.TestApi.Documents.Bet;
import java.util.Map;

public interface BetRepository {
    
    Map<String, Bet> findAll();
    
    Bet findById (String id);
    
    void save(Bet bet);
    
    void delete(String Id);  
}
