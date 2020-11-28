package com.API.masiv.TestApi.Repository;
import com.API.masiv.TestApi.Documents.Bet;
import com.API.masiv.TestApi.Documents.Roulette;
import java.util.ArrayList;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RouletteRepositoryImplement implements RouletteRepository {
    
    private RedisTemplate <String,Roulette> redisTemplate;
    private HashOperations hashOperation;
    private static final String key = "Roulette";
    
    public RouletteRepositoryImplement(RedisTemplate<String,Roulette> redisTemplate){
        this.redisTemplate=redisTemplate;
    }
    
    @PostConstruct
    private void init(){
        hashOperation = redisTemplate.opsForHash();
    }
    
    @Override
    public Map<String, Roulette> findAll() {
        return hashOperation.entries(key);
    }

    @Override
    public Roulette findById(String id) {
        return (Roulette)hashOperation.get(key,id);
    }

    @Override
    public void save(Roulette roulette) {
        hashOperation.put(key,roulette.getIdRoulette(),roulette);
    }

    @Override
    public void delete(String id) {
        hashOperation.delete(key, id);
    }

    @Override
    public void updateStatus(String id) {
        Roulette roulette = findById(id);
        if (roulette.getStatusRoulette().equals("closed")){
            roulette.setStatusRoulette("opened");
        }else{
            roulette.setStatusRoulette("closed");
        }
        save(roulette);
    }

    @Override
    public void updateBets(Bet bet, Roulette roulette) {
        if(roulette.getBets() == null){
            ArrayList<Bet> bets = new ArrayList<Bet>();
            bets.add(bet);
            roulette.setBets(bets);
        }else{
            roulette.updateBet(bet);
        }        
        save(roulette);
    }

    @Override
    public ArrayList<Bet> endRoulette(String id) {
        Roulette roulette = findById(id);
        roulette.setStatusRoulette("closed");
        ArrayList<Bet> bets = roulette.getBets();
        int number = (int)(Math.random()*37);
        ArrayList<Bet> winners = new ArrayList<Bet>();
        for(Bet bet:bets){
            if(bet.getColor()!=null){
                if(number % 2 == 0){
                    if(bet.getColor().equals("red")){
                        winners.add(bet);
                    }
                }else{
                    if(bet.getColor().equals("black")){
                        winners.add(bet);
                    }
                }               
            }else{
                if(number==bet.getNumber()){
                    winners.add(bet);                
                }
            }            
        }
        roulette.resetBets();
        save(roulette);
        return winners;
    }
}
