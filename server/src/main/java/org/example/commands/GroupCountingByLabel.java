package org.example.commands;

import org.example.exceptions.EmptyCollectionException;
import org.example.managers.CollectionManager;
import org.example.network.Request;
import org.example.network.Response;

import java.io.Serial;
import java.io.Serializable;

/**
 * Команда для группировки элементов коллекции по значению поля label.
 * Реализует абстрактный метод execute() из класса Command.
 * Выводит количество элементов в каждой группе.
 */
public class GroupCountingByLabel extends Command implements Serializable {


    @Serial
    private static final long serialVersionUID = 1340L;
    private final CollectionManager collectionManager; // Менеджер коллекции для выполнения группировки

    public GroupCountingByLabel(CollectionManager collectionManager) {
        super("group_counting_by_label", "group_counting_by_label : сгруппировать " +
                "элементы коллекции по значению поля label, вывести количество элементов в каждой группе");
        this.collectionManager = collectionManager;
    }


    @Override
    public Response execute(Request request) {
        try {
            return new Response(collectionManager.groupByLabel()); // Выполнение группировки
        } catch (EmptyCollectionException e) {
            return new Response("Коллекция пуста!"); // Обработка исключения, если коллекция пуста
        }
    }
}