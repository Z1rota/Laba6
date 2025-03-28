package org.example.network;


import org.example.commands.ExecuteScript;
import org.example.managers.FileManager;
import org.example.managers.RunManager;

import java.io.*;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.NotYetBoundException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.logging.Logger;

public class Server {

    private int port;
    private String host;
    private int timeout;
    private int maxReconnectionAttempts;
    private int reconnectionAttempts;
    FileManager fileManager;
    public RunManager runManager;
    ServerSocketChannel serverSocket;
    private static final Logger logger = Logger.getLogger("logger");


    BufferedInputStream input = new BufferedInputStream(System.in);
    BufferedReader scanner = new BufferedReader(new InputStreamReader(input));

    public Server(String host, RunManager runManager, int port, FileManager fileManager) {
        this.host = host;
        this.port = port;
        this.fileManager = fileManager;
        this.runManager = runManager;
    }


    public void openServer() {
        try {
            serverSocket = ServerSocketChannel.open();
            serverSocket.bind(new InetSocketAddress(host,port));
            serverSocket.configureBlocking(false);
        } catch (IOException e) {
            logger.warning("Ошибка при попытке подключиться к порту");
        }

    }

    private void processClientRequest(SocketChannel clientSocket) {
        Request userRequest;
        Response responseToUser;
        try (ObjectInputStream clientReader = new ObjectInputStream(clientSocket.socket().getInputStream());
             ObjectOutputStream clientWriter = new ObjectOutputStream(clientSocket.socket().getOutputStream())) {
            userRequest = (Request) clientReader.readObject();
            logger.info("запрос с командой " + userRequest.getCommand().getName());
            responseToUser = runManager.run(userRequest);
            clientWriter.writeObject(responseToUser);
            logger.info("Отправлен ответ " + responseToUser.getResult());
            clientWriter.flush();

        } catch (ClassNotFoundException | InvalidClassException | NotSerializableException exception) {
            exception.printStackTrace();
            logger.warning("Ошибка при взаимодействии с клиентом!");
        } catch (IOException exception) {
            exception.printStackTrace();
            logger.warning("Ошибка ввода вывода");
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                logger.warning("Ошибка при закрытии клиентского сокета");
            }
        }
    }


    public void run(String path) {
        try {
            openServer();
            logger.info("Соединение с сервером найдено");

            Thread inputThread = new Thread(() -> {
                try {
                    while (true) {
                        String line = scanner.readLine();
                        if (line.equals("save")) {
                            fileManager.saveObj(path);
                            logger.info("Объекты сохранены");
                        }
                    }
                } catch (IOException e) {
                    logger.warning("Ошибка ввода");
                }
            });
            inputThread.setDaemon(true);
            inputThread.start();

            while (true) {
                SocketChannel client = serverSocket.accept();
                if (client != null) {
                    processClientRequest(client);
                }
            }


        }catch (IOException exception) {
                logger.warning("Ошибка при запуске");
            }catch(NotYetBoundException exception) {
                logger.warning("Порт уже занят");
            }

    }

}
