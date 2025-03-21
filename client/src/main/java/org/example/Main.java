package org.example;

import org.example.exceptions.InvalidDataException;
import org.example.network.RequestManager;

public class Main {
    public static void main(String[] args) throws InvalidDataException, InterruptedException {
        RequestManager program = new RequestManager();
        program.execute();



    }
}