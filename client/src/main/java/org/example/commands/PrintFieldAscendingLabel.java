package org.example.commands;



import java.io.Serial;
import java.io.Serializable;


public class PrintFieldAscendingLabel extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 1344L;



    public PrintFieldAscendingLabel() {
        super("print_field_ascending_label",
                "print_field_ascending_label : вывести значения поля label всех элементов в " +
                        "порядке возрастания",false);

    }

}