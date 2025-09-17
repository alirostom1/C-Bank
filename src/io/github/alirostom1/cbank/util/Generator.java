package io.github.alirostom1.cbank.util;

import java.util.Random;

public class Generator{
    
    public final static String generateCode(){
        Random rand = new Random();
        int number = rand.nextInt(100000);
        return "CPT-" + String.valueOf(number);   
    }
}
