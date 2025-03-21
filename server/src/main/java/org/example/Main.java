package org.example;

import org.example.managers.*;
import org.example.commands.*;
import org.example.network.Server;
import org.example.utility.Facade;

import java.io.IOException;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Facade facade = new Facade(args);
        facade.start();
    }
}