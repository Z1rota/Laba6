package org.example.commands;

import org.example.network.Request;
import org.example.network.Response;

import java.io.Serial;
import java.io.Serializable;


public abstract class Command implements Serializable {

    @Serial
    private static final long serialVersionUID = 666L;
    private final String name; // Название команды
    private final String description; // Описание команды


    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public  abstract Response execute(Request request);
}