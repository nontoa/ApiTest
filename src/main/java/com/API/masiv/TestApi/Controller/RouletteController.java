package com.API.masiv.TestApi.Controller;
import com.API.masiv.TestApi.Documents.Bet;
import com.API.masiv.TestApi.Documents.Roulette;
import com.API.masiv.TestApi.Repository.RouletteRepositoryImplement;
import java.util.ArrayList;
import java.util.Map;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouletteController {
    
    private static final int idLength=5;    
    private RouletteRepositoryImplement rouletteRepositoryImplement;
    
    public RouletteController (RouletteRepositoryImplement rouletteRepositoryImplement){
        this.rouletteRepositoryImplement = rouletteRepositoryImplement;
    }
    
    @GetMapping("/Roulettes")
    public Map<String,Roulette> getAllRoulettes(){
        return rouletteRepositoryImplement.findAll();
    }
    
    @GetMapping("/Roulette/{Id}")
    public Roulette getRouletteById(@PathVariable String Id){
        return rouletteRepositoryImplement.findById(Id);
    }
    
    @PostMapping("/Roulette")
    public ResponseEntity<?> createRoulette (@RequestBody Roulette roulette){
        try{
            roulette.setIdRoulette(RandomStringUtils.randomAlphanumeric(idLength));
            rouletteRepositoryImplement.save(roulette);        
            return new ResponseEntity<>("The roulette was created successfully with this id: "+roulette.getIdRoulette(), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
    
    @DeleteMapping("/Roulette/{id}")
    public void deleteRoulette (@PathVariable String id){
        rouletteRepositoryImplement.delete(id);
    }
    
    @PutMapping("/Roulette/{id}")
    public ResponseEntity<?> updateRoulette (@PathVariable String id){
        try{
            rouletteRepositoryImplement.updateStatus(id);
            return new ResponseEntity<>("The update was successful", HttpStatus.OK);
        }catch(Exception e){
             return new ResponseEntity<>("The update was not successful",HttpStatus.FORBIDDEN);
        }
    }
    
    @PutMapping("/EndRoulette/{id}")
    public ResponseEntity<?> endRoulette (@PathVariable String id){        
        ArrayList<Bet> winners = rouletteRepositoryImplement.endRoulette(id);  
        String query = "";
        for(Bet winner: winners){
            if(winner.getColor()!=null){
                double earning = winner.getAmount()*1.8;
                query=query+" The User with id: "+winner.getOwner()+" won " + earning;
            }else{
                double earning = winner.getAmount()*5;
                query=query+" The User with id: "+winner.getOwner()+" won " + earning;
            }         
        }
        return new ResponseEntity<>(query, HttpStatus.OK);
    }
}
