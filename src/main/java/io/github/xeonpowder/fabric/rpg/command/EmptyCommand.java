package io.github.xeonpowder.fabric.rpg.command;

public class EmptyCommand<T extends EmptyRegister> {
    public T registerInstance;

    public EmptyCommand(T registerInstance) {
        this.registerInstance = registerInstance;
    }

    public String getCommandName() {
        return "EMPTY";
    }
}