package org.example.utility;

import org.example.commands.*;
import org.example.managers.CollectionManager;
import org.example.managers.CommandManager;
import org.example.managers.FileManager;
import org.example.managers.RunManager;
import org.example.network.Server;

import java.io.IOException;
import java.util.logging.Logger;

public class Facade {
    private String[] args;

    public Facade(String[] args) {
        this.args = args;

    }

    public void start() {
        final Logger logger = Logger.getLogger("logger");
        CollectionManager collectionManager = new CollectionManager();
        CommandManager commandManager = new CommandManager();
        FileManager fileManager = new FileManager(collectionManager);
        RunManager runManager = new RunManager(commandManager);
        commandManager.init(commandManager,collectionManager);
        Server server = new Server("localhost",runManager, 1782, fileManager);
        if (args.length != 0) {
            try {
                fileManager.readFromCollection(args[0]);
            } catch (IOException e) {
                logger.warning("Ошибка чтения");
            }
            logger.info("коллекция загружена");
        } else {
            args =  new String[] {" "};
            logger.info("Файл не найден");

        }
        server.run(args[0]);
    }
}
