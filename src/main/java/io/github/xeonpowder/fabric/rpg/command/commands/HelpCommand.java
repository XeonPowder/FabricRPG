package io.github.xeonpowder.fabric.rpg.command.commands;

import com.google.common.base.CaseFormat;

import io.github.xeonpowder.fabric.rpg.command.EmptyCommand;
import io.github.xeonpowder.fabric.rpg.command.registers.HelpRegister;

public class HelpCommand extends EmptyCommand<HelpRegister> {

    public HelpCommand(HelpRegister register) {
        super(register);
    }

    @Override
    public String getCommandName() {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, this.getClass().getSimpleName()).split("_")[0];
    }

}