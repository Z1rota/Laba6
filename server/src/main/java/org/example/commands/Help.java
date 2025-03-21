package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.managers.CommandManager;
import org.example.network.Request;
import org.example.network.Response;

import java.io.Serial;
import java.io.Serializable;

/**
 * Команда для вывода справки по доступным командам.
 * Реализует абстрактный метод execute() из класса Command.
 */
public class Help extends Command implements Serializable {


    @Serial
    private static final long serialVersionUID = 1341L;
    private final CollectionManager collectionManager; // Менеджер коллекции (не используется в данной команде)
    private final CommandManager commandManager; // Менеджер команд для получения списка доступных команд


    public Help(CollectionManager collectionManager, CommandManager commandManager) {
        super("help", "help : вывести справку по доступным командам");
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
    }

    @Override
    public Response execute(Request request) {
        StringBuilder resp = new StringBuilder().append("Список команд: \n");
        commandManager.getCommands().values().forEach(command -> resp.append(command.getDescription()).append("\n"));
        return new Response(resp.toString());
    }
}