package io.github.xeonpowder.fabric.rpg.command.commands;

import com.google.common.base.CaseFormat;

import io.github.xeonpowder.fabric.rpg.command.EmptyCommand;
import io.github.xeonpowder.fabric.rpg.command.registers.CurrencyRegister;

public class CurrencyCommand extends EmptyCommand<CurrencyRegister> {

    public CurrencyCommand(CurrencyRegister register) {
        super(register);
    }

    @Override
    public String getCommandName() {
        // System.out.println("got command name " + CaseFormat.UPPER_CAMEL
        // .to(CaseFormat.LOWER_UNDERSCORE,
        // this.getClass().getSimpleName()).split("_")[0]);
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, this.getClass().getSimpleName()).split("_")[0];
    }

}