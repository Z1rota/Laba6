package org.example.commands;

import org.example.exceptions.EmptyCollectionException;
import org.example.managers.CollectionManager;
import org.example.network.Request;
import org.example.network.Response;

import java.io.Serial;
import java.io.Serializable;

/**
 * Команда для вывода элементов коллекции в порядке убывания.
 * Реализует абстрактный метод execute() из класса Command.
 */
public class PrintDescending extends Command implements Serializable {


    @Serial
    private static final long serialVersionUID = 1343L;
    private final CollectionManager collectionManager; // Менеджер коллекции для вывода элементов

    /**
     * Конструктор команды PrintDescending.
     *
     * @param collectionManager менеджер коллекции, который предоставляет доступ к данным коллекции
     */
    public PrintDescending(CollectionManager collectionManager) {
        super("print_descending", "print_descending : вывести элементы коллекции в порядке убывания");
        this.collectionManager = collectionManager;
    }


    @Override
    public Response execute(Request request) {
        try {
            return new Response(collectionManager.printAscend()); // Вывод элементов коллекции в порядке убывания
        } catch (EmptyCollectionException e) {
           return new Response("Коллекция пуста!"); // Обработка исключения, если коллекция пуста
        }
    }
}