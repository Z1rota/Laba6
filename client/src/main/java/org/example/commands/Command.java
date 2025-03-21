package org.example.commands;

import java.io.Serial;
import java.io.Serializable;

/**
 * Абстрактный класс, представляющий команду.
 * Каждая команда имеет имя, описание и метод для выполнения.
 */
public abstract class Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 666L;
    private final String name; // Название команды
    private final String description; // Описание команды
    private final boolean hasArgs;


    public Command(String name, String description, boolean hasArgs) {
        this.name = name;
        this.description = description;
        this.hasArgs = hasArgs;
    }


    public boolean isHasArgs() {
        return this.hasArgs;
    }
    public String getDescription() {
        return description;
    }


    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command command = (Command) o;
        if (hasArgs != command.hasArgs) return false;
        if (name != null ? !name.equals(command.name) : command.name != null) return false;
        return description != null ? description.equals(command.description) : command.description == null;

    }
}