package com.API.masiv.TestApi.Documents;
import java.io.Serializable;

public class Bet implements Serializable {
    
    private static final long serialVersionUID=6543799434508676095L; 
    private String Id;
    private String owner;
    private int number;
    private String color;
    private int amount;    

    public Bet() {
    }
    
    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }
    
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
