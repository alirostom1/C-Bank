package io.github.alirostom1.cbank.model.entity;


import java.time.LocalDateTime;
import io.github.alirostom1.cbank.model.enums.Destination;

public class Withdrawal extends Operation{
    private Destination destination;

    public Withdrawal(String id,LocalDateTime date,double amount,String code,Destination destination){
        super(id,date,amount,code);
        this.destination = destination;
    }
    public Withdrawal(double amount,String code,Destination destination){
        super(amount,code);
        this.destination = destination;
    }
    public Destination getDestination(){
        return this.destination;
    }
    public void setDestination(Destination destination){
        this.destination = destination;
    }
}
