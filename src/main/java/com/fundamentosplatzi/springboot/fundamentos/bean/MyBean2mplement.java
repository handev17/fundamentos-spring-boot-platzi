package com.fundamentosplatzi.springboot.fundamentos.bean;

public class MyBean2mplement implements MyBean{

    @Override
    public void print() {
        System.out.println("Hola desde segunda Implementacion propia de bean");
    }
}
