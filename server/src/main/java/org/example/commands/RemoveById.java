package org.example.commands;

import org.example.exceptions.NoElementException;
import org.example.managers.CollectionManager;
import org.example.network.Request;
import org.example.network.Response;

import java.io.Serial;
import java.io.Serializable;

/**
 * Команда для удаления элемента из коллекции по его идентификатору.
 */
public class RemoveById extends Command implements Serializable {


    @Serial
    private static final long serialVersionUID = 1346L;

    private final CollectionManager collectionManager;

    /**
     * Конструктор команды.
     *
     * @param collectionManager менеджер коллекции, который будет использоваться для удаления элемента
     */
    public RemoveById(CollectionManager collectionManager) {
        super("remove_by_id", "remove_by_id id : удалить элемент из коллекции по его id");
        this.collectionManager = collectionManager;
    }


    @Override
    public Response execute(Request request) {
        long id = (long) request.getArgs();
        try {
            collectionManager.removeById(id);
            return new Response("Элемент с id " + id + " был успешно удален.");
        } catch (NoElementException e) {
            return new Response("Элемента с id " + id + " нет!");
        } catch (NumberFormatException e) {
            return new Response("Введите корректное число для id!");
        }
    }
}