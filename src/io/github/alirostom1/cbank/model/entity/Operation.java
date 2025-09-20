package io.github.alirostom1.cbank.model.entity;

import java.util.UUID;
import java.time.LocalDateTime;
public abstract class Operation{
    protected UUID id;
    protected LocalDateTime date;
    protected double amount;
    protected String code;

    public Operation(String id,LocalDateTime date,double amount,String code){
        this.id = UUID.fromString(id);
        this.date = date;
        this.amount = amount;
        this.code = code;
    }
    public Operation(double amount,String code){
        this.id = UUID.randomUUID();
        this.date = LocalDateTime.now();
        this.amount = amount;
        this.code = code;
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
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }

}
