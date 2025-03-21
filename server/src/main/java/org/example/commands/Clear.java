package org.example.commands;

import org.example.exceptions.AlreadyEmptyException;
import org.example.managers.CollectionManager;
import org.example.network.Request;
import org.example.network.Response;

import java.io.Serial;
import java.io.Serializable;

/**
 * Команда для очистки коллекции.
 * Реализует абстрактный метод execute() из класса Command.
 */
public class Clear extends Command implements Serializable {

    @Serial
    private static final long serialVersionUID = 1338L;
    private final CollectionManager collectionManager; // Менеджер коллекции, которую необходимо очистить

    public Clear(CollectionManager collectionManager) {
        super("clear", "clear : очистить коллекцию");
        this.collectionManager = collectionManager;
    }


    @Override
    public Response execute(Request request) {
        try {
            collectionManager.clear(); // Очистка коллекции
            return new Response("Очистка выполнена успешно");
        } catch (AlreadyEmptyException e) {
            return new Response("Коллекция уже пуста!"); // Обработка исключения, если коллекция уже пуста
        }
    }
}