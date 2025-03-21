package org.example.commands;

import org.example.exceptions.EmptyCollectionException;
import org.example.managers.CollectionManager;
import org.example.network.Request;
import org.example.network.Response;

import java.io.Serial;
import java.io.Serializable;

/**
 * Команда для удаления первого элемента из коллекции.
 */
public class RemoveFirst extends Command implements Serializable {


    @Serial
    private static final long serialVersionUID = 1347L;
    /**
     * Менеджер коллекции, используемый для управления элементами.
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор команды.
     *
     * @param collectionManager менеджер коллекции, который будет использоваться для удаления первого элемента
     */
    public RemoveFirst(CollectionManager collectionManager) {
        super("remove_first", "remove_first : удалить первый элемент из коллекции");
        this.collectionManager = collectionManager;
    }


    @Override
    public Response execute(Request request) {
        try {

            collectionManager.removeFirst();
            return new Response("Элемент успешно удален!");
        } catch (EmptyCollectionException e) {
            return new Response("Коллекция пуста!");
        }
    }
}