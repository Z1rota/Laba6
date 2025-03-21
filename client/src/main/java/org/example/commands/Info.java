package org.example.commands;


import java.io.Serial;
import java.io.Serializable;


public class Info extends Command implements Serializable {

    @Serial
    private static final long serialVersionUID = 1342L;

    public Info() {
        super("info", "info : вывести в стандартный поток вывода информацию о коллекции " +
                "(тип, дата инициализации, количество элементов и т.д.)", false);

    }



}

