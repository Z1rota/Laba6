package org.example.network;

import org.example.commands.Command;
import org.example.mainClasses.MusicBand;

import java.io.Serial;
import java.io.Serializable;

public class Request implements Serializable {

    @Serial
    private static final long serialVersionUID = 21L;

    public MusicBand musicBand;
    Command command;
    Object args;

    public Request(Command command) {
        this.command = command;
    }

    public Request (String string) {
        this.args = string;
    }

    public Request(Command command, Object args) {
        this.command = command;
        this.args = args;
    }

    public Request(Command command, MusicBand band) {
        this.command = command;
        this.musicBand = band;
    }

    public Request(MusicBand musicBand) {
        this.musicBand = musicBand;
    }

    public Request(Command command, MusicBand band, Object args) {
        this.command = command;
        this.musicBand = band;
        this.args = args;
    }

    public Command getCommand() {
        return command;
    }

    public MusicBand getMusicBand() {
        return musicBand;
    }

    public Object getArgs() {
        return args;
    }
}
