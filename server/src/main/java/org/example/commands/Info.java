package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.network.Request;
import org.example.network.Response;

import java.io.Serial;
import java.io.Serializable;

public class Info extends Command implements Serializable {


    @Serial
    private static final long serialVersionUID = 1342L;
    private final CollectionManager collectionManager;
    public Info(CollectionManager collectionManager) {
        super("info", "info : вывести в стандартный поток вывода информацию о коллекции " +
                "(тип, дата инициализации, количество элементов и т.д.)");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        StringBuilder response = new StringBuilder().append("Информация о коллекции: \n");
        response.append(collectionManager.info());
        return new Response(response.toString());

    }

}

