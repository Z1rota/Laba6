package org.example.commands;



import java.io.Serial;
import java.io.Serializable;

/**
 * Команда для очистки коллекции.
 * Реализует абстрактный метод execute() из класса Command.
 */
public class Clear extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 1338L;


    public Clear() {
        super("clear", "clear : очистить коллекцию", false);
    }


}