package org.example.commands;


import java.io.Serial;
import java.io.Serializable;

/**
 * Команда для вывода справки по доступным командам.
 * Реализует абстрактный метод execute() из класса Command.
 */
public class Help extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 1341L;


    public Help() {
        super("help", "help : вывести справку по доступным командам",false);

    }


}