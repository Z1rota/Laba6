package org.example.commands;

import org.example.builders.MusicBandsBuilder;
import org.example.mainClasses.MusicBand;
import org.example.managers.CommandManager;
import org.example.managers.ScriptExecuteManager;
import org.example.network.Request;
import org.example.network.Response;
import org.example.utility.FileMode;

import java.io.*;
import java.util.NoSuchElementException;

/**
 * Команда для выполнения скрипта из указанного файла.
 * Реализует абстрактный метод execute() из класса Command.
 * Скрипт содержит команды в том же формате, что и ввод пользователя в интерактивном режиме.
 */
public class ExecuteScript extends Command implements Serializable {

    @Serial
    private static final long serialVersionUID = 1339L;
    private final CommandManager commandManager; // Менеджер команд для выполнения команд из скрипта

    /**
     * Конструктор команды ExecuteScript.
     *
     * @param commandManager менеджер команд, который будет выполнять команды из скрипта
     */
    public ExecuteScript(CommandManager commandManager) {
        super("execute_script", "execute_script file_name : " +
                "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, " +
                "в котором их вводит пользователь в интерактивном режиме.");
        this.commandManager = commandManager;
    }


    @Override
    public Response execute(Request request) {
        String path = (String) (request.getArgs());
        path = path.trim();
        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileMode.setFileMode(true);
            ScriptExecuteManager.pushFile(path);

            for (String line = ScriptExecuteManager.readfile(); line != null; line = ScriptExecuteManager.readfile()) {
                String[] cmd = (line.trim() + " ").split(" ", 2);
                if (cmd[0].equals("execute_script")) {
                    if (ScriptExecuteManager.IsRepeat(cmd[1])) {
                        stringBuilder.append(("Обнаружена рекурсия")).append("\n\n");
                        continue;
                    }
                }
                if (commandManager.getCommands().get(cmd[0]) != null) {
                    if(cmd[0].equals("remove_by_id")){
                        stringBuilder.append(commandManager.execute(
                                new Request(commandManager.getCommands().get(cmd[0]),
                                        Integer.parseInt(cmd[1].trim()))).getResult()).append("\n\n");
                    }
                    if(cmd[0].equals("add") || cmd[0].equals("add_if_min")){
                        MusicBand band = new MusicBandsBuilder().create();
                        stringBuilder.append(commandManager.execute(new Request(
                                commandManager.getCommands().get(cmd[0]), band)).getResult()).append("\n\n");
                    }
                    if(cmd[0].equals("insert_at")  || cmd[0].equals("update")){
                        MusicBand band = new MusicBandsBuilder().create();
                        stringBuilder.append(commandManager.execute(new Request(
                                commandManager.getCommands().get(cmd[0]), band,
                                Integer.parseInt(cmd[1].trim()))).getResult()).append("\n\n");

                    }
                    else {
                        stringBuilder.append(commandManager.execute(new Request(
                                commandManager.getCommands().get(cmd[0]),cmd[1])).getResult()).append("\n\n");
                    }
                }
                if(cmd[0].equals("execute_script")){
                    ScriptExecuteManager.popfile();
                }


            }
            FileMode.setFileMode(false);
            return new Response(stringBuilder.toString());

        } catch (FileNotFoundException e) {
            return new Response("Файл не найден");
        } catch (IOException e) {
            return new Response("Ошибка чтения");
        }


    }

    }