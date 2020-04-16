package org.leung.ttsprj.controller;

public class mycontroller {
    public void main(String[] args) {
        int a = 15, b  = 199;
        System.out.println("交换前的值：a = " + a + "; b = " + b);
        a = a^b;
        b = b^a;
        a = a^b;
        System.out.println("交换后的值：a = " + a + "; b = " + b);
    }
}
