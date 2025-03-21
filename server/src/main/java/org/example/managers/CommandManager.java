package org.example.managers;

import org.example.commands.*;
import org.example.network.Request;
import org.example.network.Response;

import java.util.HashMap;

public class CommandManager {

    private HashMap<String, Command> commands = new HashMap<>();

    public void addCommand(Command command) {
        this.commands.put(command.getName(), command);
    }
    public HashMap<String, Command> getCommands() {
        return commands;
    }

    public void init(CommandManager commandManager, CollectionManager collectionManager) {
        this.addCommand(new Add(collectionManager));
        commandManager.addCommand(new Clear(collectionManager));
        commandManager.addCommand(new ExecuteScript(commandManager));
        commandManager.addCommand(new GroupCountingByLabel(collectionManager));
        commandManager.addCommand(new Help(collectionManager,commandManager));
        commandManager.addCommand(new Info(collectionManager));
        commandManager.addCommand(new PrintDescending(collectionManager));
        commandManager.addCommand(new PrintFieldAscendingLabel(collectionManager));
        commandManager.addCommand(new RemoveAt(collectionManager));
        commandManager.addCommand(new RemoveById(collectionManager));
        commandManager.addCommand(new RemoveFirst(collectionManager));
        commandManager.addCommand(new Show(collectionManager));
        commandManager.addCommand(new Shuffle(collectionManager));
        commandManager.addCommand(new UpdateId(collectionManager));
    }

    public Response execute(Request request) {
        Command command = this.commands.get(request.getCommand().getName());
        if (command != null) {
            return command.execute(request);
        } else {
            return new Response("Команды нет");
        }
    }
}