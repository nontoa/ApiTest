package com.API.masiv.TestApi.Controller;
import com.API.masiv.TestApi.Documents.Bet;
import com.API.masiv.TestApi.Documents.Roulette;
import com.API.masiv.TestApi.Repository.BetRepositoryImplement;
import com.API.masiv.TestApi.Repository.RouletteRepositoryImplement;
import java.util.Map;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BetController {
    
    private static final int idLength=6;
    private BetRepositoryImplement betRepositoryimplement;
    private RouletteRepositoryImplement rouletteRepositoryImplement;

    public BetController(BetRepositoryImplement betRepositoryimplement, RouletteRepositoryImplement rouletteRepositoryImplement ) {
        this.betRepositoryimplement = betRepositoryimplement;
        this.rouletteRepositoryImplement = rouletteRepositoryImplement;
    } 
    
    @GetMapping("/Bets")
    public Map<String,Bet> getAllBets(){
        return betRepositoryimplement.findAll();
    }
    
    @GetMapping("/Bet/{Id}")
    public Bet getBetById(@PathVariable String Id){
        return betRepositoryimplement.findById(Id);
    }
    
    @PostMapping("/Bet/number/{idRoulette}")
    public ResponseEntity<?>  createNumberBet (@RequestHeader("Owner") String idUser, @PathVariable String idRoulette, @RequestBody Bet bet){       
        Roulette roulette = rouletteRepositoryImplement.findById(idRoulette);
        if(roulette.getStatusRoulette().equals("opened") && bet.getAmount()<=10000 && bet.getNumber()>=0 && bet.getNumber()<=36){
            bet.setId(RandomStringUtils.randomAlphanumeric(idLength));
            bet.setOwner(idUser);                   
            betRepositoryimplement.save(bet); 
            rouletteRepositoryImplement.updateBets(getBetById(bet.getId()), roulette);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>("The roulette is closed or the parameters are invalid",HttpStatus.FORBIDDEN);
        }                 
    }
    
    @PostMapping("/Bet/color/{idRoulette}")
    public ResponseEntity<?>  createColorBet (@RequestHeader("Owner") String idUser, @PathVariable String idRoulette, @RequestBody Bet bet){       
        Roulette roulette = rouletteRepositoryImplement.findById(idRoulette);
        if(roulette.getStatusRoulette().equals("opened") && bet.getAmount()<=10000 && (bet.getColor().equals("red") || bet.getColor().equals("black"))){
            bet.setId(RandomStringUtils.randomAlphanumeric(idLength));
            bet.setOwner(idUser);                   
            betRepositoryimplement.save(bet); 
            rouletteRepositoryImplement.updateBets(getBetById(bet.getId()), roulette);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>("The roulette is closed or the parameters are invalid",HttpStatus.FORBIDDEN);
        }                 
    }
    
    @DeleteMapping("/Bet/{id}")
    public void deleteBet (@PathVariable String id){
        betRepositoryimplement.delete(id);
    }
}
