package com.liyan.test;

import org.junit.Test;

public class SingTest {
    @Test
    public void na(){
        SingleTon singleTon=SingleTon.getInstance();
        SingleTon singleTon1=SingleTon.getInstance();
        System.out.println(singleTon==singleTon1);

    }
}
