package com.API.masiv.TestApi.Repository;
import com.API.masiv.TestApi.Documents.Bet;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BetRepositoryImplement implements BetRepository {
    
    private RedisTemplate <String,Bet> redisTemplate;
    private HashOperations hashOperation;
    private static final String key = "Bet";
    
    public BetRepositoryImplement(RedisTemplate<String,Bet> redisTemplate){
        this.redisTemplate=redisTemplate;
    }
    
    @PostConstruct
    private void init(){
        hashOperation = redisTemplate.opsForHash();
    }

    @Override
    public Map<String, Bet> findAll() {
        return hashOperation.entries(key);
    }

    @Override
    public Bet findById(String id) {
        return (Bet)hashOperation.get(key,id);
    }

    @Override
    public void save(Bet bet) {        
        hashOperation.put(key,bet.getId(),bet);
    }

    @Override
    public void delete(String Id) {
        hashOperation.delete(key, Id);
    }
}
