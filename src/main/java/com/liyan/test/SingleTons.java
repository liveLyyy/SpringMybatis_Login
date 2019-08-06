package com.liyan.test;

public class SingleTons {
    //在类加载时进行实例化
    private static SingleTons singleTon=new SingleTons();
    private SingleTons() {
    }
    public static SingleTons getInstance() {
        return singleTon;
    }
}
