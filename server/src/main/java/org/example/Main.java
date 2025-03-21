package org.example;
import org.example.utility.Facade;

public class Main {
    public static void main(String[] args) {
        Facade facade = new Facade(args);
        facade.start();
    }
}