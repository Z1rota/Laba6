package org.example.network;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Objects;

public class Client {

    private int port;
    private String host;
    private int timeout;
    private SocketChannel socket;
    private ObjectOutputStream writer;
    private ObjectInputStream reader;
    private int reconnectionAttempts;
    private int maxReconnectionAttempts;

    public Client(String host, int port, int timeout, int maxReconnectionAttempts) {
        this.host = host;
        this.port = port;
        this.timeout = timeout;
        this.maxReconnectionAttempts = maxReconnectionAttempts;
    }


    public void connect() {
        try {
            socket = SocketChannel.open();
            socket.connect(new InetSocketAddress(host,port));
            writer = new ObjectOutputStream(socket.socket().getOutputStream());
            reader = new ObjectInputStream(socket.socket().getInputStream());
        }catch (IOException e) {
            System.err.println("Ошибка подключения к серверу");
        }
    }


    public void disconnect() {
        try {
            socket.close();
            reader.close();
            writer.close();
        }catch (IOException e) {
            System.err.println("Не подключено к серверу");
        }
    }


    public Response sendRequest(Request request) throws InterruptedException {
        this.connect();
        for (int reconnectionAttempts = 0; reconnectionAttempts < maxReconnectionAttempts; reconnectionAttempts++) {
            try {
                if (Objects.isNull(writer) || Objects.isNull(reader)) throw new IOException();
                if (request.getCommand() == null & !(request.getArgs() == ("exit"))) System.err.println("Запрос пуст, введите команду");
                writer.writeObject(request);
                writer.flush();
                Response response = (Response) reader.readObject();
                this.disconnect();
                return response;

            }catch (IOException e) {
                if (reconnectionAttempts >= maxReconnectionAttempts) {
                    break;
                }
                System.err.println("Рекконект через: " +timeout/1000 + " секунд");
                Thread.sleep(timeout);
                this.connect();

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (NullPointerException e) {
                System.out.println("Не получилось подключиться, завершение работы...");
                System.exit(0);
            }

        }

        return null;
    }




}
