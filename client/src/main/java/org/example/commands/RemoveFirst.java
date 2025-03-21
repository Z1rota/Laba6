package org.example.commands;



import java.io.Serial;
import java.io.Serializable;


public class RemoveFirst extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 1347L;

    public RemoveFirst() {
        super("remove_first", "remove_first : удалить первый элемент из коллекции", false);
    }
}