package org.example.commands;

import org.example.exceptions.NoElementException;
import org.example.managers.CollectionManager;
import org.example.network.Request;
import org.example.network.Response;

import java.io.Serial;
import java.io.Serializable;

/**
 * Команда для удаления элемента, находящегося в заданной позиции коллекции.
 * Реализует абстрактный метод execute() из класса Command.
 */
public class RemoveAt extends Command implements Serializable {

    @Serial
    private static final long serialVersionUID = 1345L;
    private final CollectionManager collectionManager; // Менеджер коллекции для удаления элемента

    /**
     * Конструктор команды RemoveAt.
     *
     * @param collectionManager менеджер коллекции, который предоставляет доступ к данным
     */
    public RemoveAt(CollectionManager collectionManager) {
        super("remove_at", "remove_at index : удалить элемент, находящийся в заданной позиции коллекции (index)");
        this.collectionManager = collectionManager;
    }


    @Override
    public Response execute(Request request) {
        try {
            long id = (long) request.getArgs(); // Преобразуем аргумент в число (индекс)
            collectionManager.removeAt((int) id); // Удаление элемента по индексу
            return new Response("Объект успешно удален!");
        } catch (NoElementException e) {
            return new Response("Элемента под этим индексом нет!"); // Обработка исключения, если элемента с таким индексом нет
        } catch (NumberFormatException e) {
            return new Response("Введите число!"); // Обработка исключения, если аргумент не является числом
        }
    }
}