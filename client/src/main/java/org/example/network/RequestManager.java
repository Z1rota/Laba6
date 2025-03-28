package org.example.network;

import org.example.builders.MusicBandsBuilder;
import org.example.commands.*;
import org.example.exceptions.InvalidDataException;
import org.example.mainClasses.MusicBand;
import org.example.managers.CommandManager;

import java.util.Scanner;


public class RequestManager {
    public void execute() throws InvalidDataException, InterruptedException {
        CommandManager commands = new CommandManager();
        commands.putCommand(new Add());
        commands.putCommand(new Clear());
        commands.putCommand(new ExecuteScript());
        commands.putCommand(new GroupCountingByLabel());
        commands.putCommand(new Help());
        commands.putCommand(new Info());
        commands.putCommand(new PrintDescending());
        commands.putCommand(new PrintFieldAscendingLabel());
        commands.putCommand(new RemoveAt());
        commands.putCommand(new RemoveById());
        commands.putCommand(new RemoveFirst());
        commands.putCommand(new Show());
        commands.putCommand(new Shuffle());
        commands.putCommand(new UpdateId());
        String[] input;
        Scanner scanner = new Scanner(System.in);
        Client client = new Client("localhost",1782,5000,3);

        System.out.println("Введите help для получения списка команд: ");
        while (true) {
            String cmd = (scanner.nextLine()+" ").trim();
            input = cmd.split(" ");
            if (input[0].equals("exit")) {
                System.out.println("До связи");
                System.exit(0);
            }
            if (commands.getCommands().get(input[0]) == null) {
                System.err.println("команды нет!");
                continue;
            }

            Command command = commands.getCommands().get(input[0]);
            if (!command.isHasArgs()) {
                if (input.length != 1) {
                    System.err.println("у этой команды не должно быть аргументов");
                    continue;
                }

                if (input[0].equals("add")) {
                    MusicBand band = new MusicBandsBuilder().create();
                    System.out.println(client.sendRequest(new Request(command, band)).getResult());
                } else {
                    try {
                        Request request = new Request(command);
                        System.out.println(client.sendRequest(request).getResult());
                    } catch (NullPointerException e) {
                        System.out.println("Клиент не смог подключиться к серверу");
                        System.exit(505);
                    }
                }
                continue;
            }

            if (input.length != 2) {
                System.err.println("Команде нужен только один аргумент");
                continue;
            }
            if (!(input[0].equals("execute_script"))) {
                long id;
                id = Long.parseLong(input[1]);
                if (input[0].equals("update")) {
                    MusicBand band = new MusicBandsBuilder().create();
                    System.out.println(client.sendRequest(new Request(command,band,id)).getResult());
                    continue;
                }
                if (input[0].equals("remove_at")) {
                    System.out.println(client.sendRequest(new Request(command,id)).getResult());
                }
                if (input[0].equals("remove_by_id")) {
                    System.out.println(client.sendRequest(new Request(command, id)).getResult());
                }


            } else {
                Request request = new Request(command, input[1].toUpperCase());
                System.out.println(client.sendRequest(request).getResult());
            }
        }

    }
}





