package org.example.commands;



import java.io.Serial;
import java.io.Serializable;


public class Shuffle extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 1349L;

    public Shuffle() {
        super("shuffle", "shuffle : перемешать элементы коллекции в случайном порядке", false);

    }


}