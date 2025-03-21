package org.example.commands;


import java.io.Serial;
import java.io.Serializable;


public class RemoveAt extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 1345L;

    public RemoveAt() {
        super("remove_at", "remove_at index : удалить элемент, находящийся в " +
                "заданной позиции коллекции (index)", true);

    }

}