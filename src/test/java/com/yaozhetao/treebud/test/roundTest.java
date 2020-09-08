package com.yaozhetao.treebud.test;


import org.junit.jupiter.api.Test;

public class roundTest {

    @Test
    public void roundTest(){
        boolean state = false;
        for(int i = 0;i<10;i++){
            state = true;
            state = i>5 || state;
            System.out.println(state);
        }
    }
}
