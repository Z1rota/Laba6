package org.example.commands;

import org.example.builders.MusicBandsBuilder;
import org.example.exceptions.InvalidDataException;


import java.io.Serial;
import java.io.Serializable;


public class UpdateId extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 1350L;


    public UpdateId() {
        super("update", "update id {element} : обновить значение элемента коллекции, " +
                "id которого равен заданному", true);

    }


}