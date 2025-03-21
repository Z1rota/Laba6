package org.example.commands;



import java.io.Serial;
import java.io.Serializable;


public class Show extends Command implements Serializable {

    @Serial
    private static final long serialVersionUID = 1348L;


    public Show() {
        super("show", "show : вывести в стандартный поток вывода все элементы " +
                "коллекции в строковом представлении", false);
    }


}