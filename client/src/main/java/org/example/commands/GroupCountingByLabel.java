package org.example.commands;


import java.io.Serial;
import java.io.Serializable;


public class GroupCountingByLabel extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 1340L;



    public GroupCountingByLabel() {
        super("group_counting_by_label", "group_counting_by_label : сгруппировать " +
                "элементы коллекции по значению поля label, вывести количество элементов в каждой группе", false);

    }

}