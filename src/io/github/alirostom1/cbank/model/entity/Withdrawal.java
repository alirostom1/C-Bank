package io.github.alirostom1.cbank.model.entity;

import java.util.UUID;
import java.time.LocalDateTime;
import io.github.alirostom1.cbank.model.enums.Destination;

public class Withdrawal extends Operation{
    private Destination destination;

    public Withdrawal(UUID id,LocalDateTime date,double amount,Destination destination){
        super(amount);
        this.destination = destination;
    }
    public Destination getDestination(){
        return this.destination;
    }
    public void setDestination(Destination destination){
        this.destination = destination;
    }
}
