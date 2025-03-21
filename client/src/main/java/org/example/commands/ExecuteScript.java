package org.example.commands;

import org.example.managers.CommandManager;
import org.example.utility.FileMode;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.NoSuchElementException;


public class ExecuteScript extends Command implements Serializable {

    @Serial
    private static final long serialVersionUID = 1339L;

    public ExecuteScript() {
        super("execute_script", "execute_script file_name : " +
                "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, " +
                "в котором их вводит пользователь в интерактивном режиме.", true);
    }


}