package io.github.alirostom1.cbank.model.entity;

import java.util.UUID;
import java.time.LocalDateTime;
public abstract class Operation{
    protected UUID id;
    protected LocalDateTime date;
    protected double amount;
    
    public Operation(double amount){
        this.id = UUID.randomUUID();
        this.date = LocalDateTime.now();
        this.amount = amount;
    }
    public UUID getId(){
        return this.id;
    }
    public LocalDateTime getDate(){
        return this.date;
    }
    public double getAmount(){
        return this.amount;
    }
    public void setId(UUID id){
        this.id = id;
    }
    public void setDate(LocalDateTime date){
        this.date = date;
    }
    public void setAmount(double amount){
        this.amount = amount;
    }

}
