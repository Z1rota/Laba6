package org.example.commands;

import org.example.builders.MusicBandsBuilder;
import org.example.managers.CollectionManager;
import org.example.exceptions.InvalidDataException;
import org.example.network.Request;
import org.example.network.Response;

import java.io.Serial;
import java.io.Serializable;


public class Add extends Command implements Serializable {

    @Serial
    private static final long serialVersionUID = 1337L;

    private final CollectionManager collectionManager;


    public Add(CollectionManager collectionManager) {
        super("add", "add {element} : добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        try {
            collectionManager.add(request.getMusicBand()); // Создание и добавление элемента
            return new Response("Объект создан успешно!");
        } catch (InvalidDataException e) {
            return new Response("Объект не создан. проверьте правильность данных"); // Обработка исключения при некорректных данных
        }
    }
}