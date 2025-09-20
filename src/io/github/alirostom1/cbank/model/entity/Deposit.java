package io.github.alirostom1.cbank.model.entity;

import io.github.alirostom1.cbank.model.enums.Source;
import java.time.LocalDateTime;

public class Deposit extends Operation{
    private Source source;
    
    public Deposit(String id,LocalDateTime date,double amount,String code,Source source){
        super(id,date,amount,code);
        this.source = source;
    }
    public Deposit(double amount,String code,Source source){
        super(amount,code);
        this.source = source;
    }
    public Source getSource(){
        return this.source;
    }
    public void setSource(Source source){
        this.source = source;
    }
}
