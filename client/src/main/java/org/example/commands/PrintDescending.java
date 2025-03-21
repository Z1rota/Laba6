package org.example.commands;



import java.io.Serial;
import java.io.Serializable;


public class PrintDescending extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 1343L;

    public PrintDescending() {
        super("print_descending", "print_descending : вывести элементы коллекции в порядке убывания",false);

    }



}