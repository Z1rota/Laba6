package org.example.commands;

import org.example.exceptions.EmptyCollectionException;
import org.example.managers.CollectionManager;
import org.example.network.Request;
import org.example.network.Response;

import java.io.Serial;
import java.io.Serializable;

/**
 * Команда для вывода значений поля label всех элементов коллекции в порядке возрастания.
 * Реализует абстрактный метод execute() из класса Command.
 */
public class PrintFieldAscendingLabel extends Command implements Serializable {


    @Serial
    private static final long serialVersionUID = 1344L;

    public PrintFieldAscendingLabel(CollectionManager collectionManager) {
        super("print_field_ascending_label",
                "print_field_ascending_label : вывести значения поля label всех элементов в порядке возрастания");
        this.collectionManager = collectionManager;
    }

    private final CollectionManager collectionManager; // Менеджер коллекции для доступа к данным

    /**
     * Метод для выполнения команды print_field_ascending_label.
     * Выводит значения поля label всех элементов коллекции в порядке возрастания.
     *
     * @param args аргументы команды (не используются в данной команде)
     * @throws EmptyCollectionException если коллекция пуста
     */
    @Override
    public Response execute(Request request) throws EmptyCollectionException {
        try {
            return new Response(collectionManager.printLabelField()); // Вывод значений поля label в порядке возрастания
        } catch (EmptyCollectionException e) {
            return new Response("Коллекция пуста!"); // Обработка исключения, если коллекция пуста
        }
    }
}