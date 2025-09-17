package io.github.alirostom1.cbank.model.entity;

import io.github.alirostom1.cbank.model.enums.Source;

public class Deposit extends Operation{
    private Source source;
    
    public Deposit(double amount,Source source){
        super(amount);
        this.source = source;
    }
    public Source getSource(){
        return this.source;
    }
    public void setSource(Source source){
        this.source = source;
    }
}
