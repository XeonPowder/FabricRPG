package io.github.xeonpowder.fabric.rpg.command.registers;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.github.xeonpowder.TUSK__3.panI18n.FormattingEngine;
import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.command.EmptyRegister;
import io.github.xeonpowder.fabric.rpg.currency.FabricRPGPlayerCurrency;
import net.minecraft.command.arguments.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

public class CurrencyRegister implements EmptyRegister {

    @Override
    public LiteralCommandNode<ServerCommandSource> regsiterMain(
            CommandDispatcher<ServerCommandSource> dispatcher, String commandName) {
        // System.out.println("help register registerMain");
        return dispatcher
                .register(
                        CommandManager.literal(commandName)
                                .then(CommandManager.literal(this.getRegisterName())
                                        .then(CommandManager.literal("add")
                                                .then(CommandManager
                                                        .argument("target",
                                                                EntityArgumentType.player())
                                                        .then(CommandManager
                                                                .argument("amount",
                                                                        StringArgumentType
                                                                                .greedyString())
                                                                .executes(ctx -> {
                                                                    FabricRPGPlayerCurrency playerCurrency =
                                                                            FabricRPG.PlayerCurrencyComponent
                                                                                    .get(EntityArgumentType
                                                                                            .getPlayer(
                                                                                                    ctx,
                                                                                                    "target"))
                                                                                    .getCurrency();
                                                                    String amount =
                                                                            StringArgumentType
                                                                                    .getString(ctx,
                                                                                            "amount");
                                                                    if (playerCurrency
                                                                            .isValidCurrencyString(
                                                                                    amount)) {
                                                                        Double emeraldsToAdd =
                                                                                playerCurrency
                                                                                        .getEmeraldAmountFromString(
                                                                                                amount);
                                                                        Double goldToAdd =
                                                                                playerCurrency
                                                                                        .getGoldAmountFromString(
                                                                                                amount);
                                                                        Double silverToAdd =
                                                                                playerCurrency
                                                                                        .getSilverAmountFromString(
                                                                                                amount);
                                                                        playerCurrency.setSilver(
                                                                                playerCurrency
                                                                                        .getSilver()
                                                                                        + silverToAdd);
                                                                        playerCurrency.setGold(
                                                                                playerCurrency
                                                                                        .getGold()
                                                                                        + goldToAdd);
                                                                        playerCurrency.setEmerald(
                                                                                playerCurrency
                                                                                        .getEmerald()
                                                                                        + emeraldsToAdd);
                                                                        while (playerCurrency
                                                                                .getSilver() > 100) {
                                                                            playerCurrency
                                                                                    .setSilver(
                                                                                            playerCurrency
                                                                                                    .getSilver()
                                                                                                    - 100);
                                                                            playerCurrency.setGold(
                                                                                    playerCurrency
                                                                                            .getGold()
                                                                                            + 1);
                                                                        }
                                                                        while (playerCurrency
                                                                                .getGold() > 100) {
                                                                            playerCurrency.setGold(
                                                                                    playerCurrency
                                                                                            .getGold()
                                                                                            - 100);
                                                                            playerCurrency
                                                                                    .setEmerald(
                                                                                            playerCurrency
                                                                                                    .getEmerald()
                                                                                                    + 1);
                                                                        }
                                                                        ctx.getSource().getPlayer()
                                                                                .sendMessage(
                                                                                        new LiteralText(
                                                                                                FormattingEngine
                                                                                                        .replaceColorCodeEnumInString(
                                                                                                                new TranslatableText(
                                                                                                                        this.getTranslationKey()
                                                                                                                                + ".add.completed",
                                                                                                                        emeraldsToAdd,
                                                                                                                        goldToAdd,
                                                                                                                        silverToAdd)
                                                                                                                                .asString())));
                                                                    } else {
                                                                        ctx.getSource().getPlayer()
                                                                                .sendMessage(
                                                                                        new LiteralText(
                                                                                                FormattingEngine
                                                                                                        .replaceColorCodeEnumInString(
                                                                                                                new TranslatableText(
                                                                                                                        this.getTranslationKey()
                                                                                                                                + ".add.invalidAmountString")
                                                                                                                                        .asString())));
                                                                    }
                                                                    return Command.SINGLE_SUCCESS;

                                                                }))
                                                        .executes(ctx -> {
                                                            ctx.getSource().getPlayer().sendMessage(
                                                                    new LiteralText(FormattingEngine
                                                                            .replaceColorCodeEnumInString(
                                                                                    new TranslatableText(
                                                                                            this.getTranslationKey()
                                                                                                    + ".add.target")
                                                                                                            .asString())));
                                                            return Command.SINGLE_SUCCESS;
                                                        }))
                                                .executes(ctx -> {
                                                    ctx.getSource().getPlayer().sendMessage(
                                                            new LiteralText(FormattingEngine
                                                                    .replaceColorCodeEnumInString(
                                                                            new TranslatableText(
                                                                                    this.getTranslationKey()
                                                                                            + ".add")
                                                                                                    .asString())));
                                                    return Command.SINGLE_SUCCESS;
                                                }))
                                        .then(CommandManager.literal("set")
                                                .then(CommandManager
                                                        .argument("target",
                                                                EntityArgumentType.player())
                                                        .then(CommandManager
                                                                .argument("amount",
                                                                        StringArgumentType
                                                                                .greedyString())
                                                                .executes(ctx -> {
                                                                    /// /frpg currency set target
                                                                    /// amount
                                                                    return Command.SINGLE_SUCCESS;
                                                                }))
                                                        .executes(ctx -> {
                                                            // /frpg currency set target
                                                            ctx.getSource().getPlayer().sendMessage(
                                                                    new LiteralText(FormattingEngine
                                                                            .replaceColorCodeEnumInString(
                                                                                    new TranslatableText(
                                                                                            this.getTranslationKey()
                                                                                                    + ".set.target")
                                                                                                            .asString())));
                                                            return Command.SINGLE_SUCCESS;
                                                        }))
                                                .executes(ctx -> {
                                                    // /frpg currency set
                                                    ctx.getSource().getPlayer().sendMessage(
                                                            new LiteralText(FormattingEngine
                                                                    .replaceColorCodeEnumInString(
                                                                            new TranslatableText(
                                                                                    this.getTranslationKey()
                                                                                            + ".set")
                                                                                                    .asString())));
                                                    return Command.SINGLE_SUCCESS;
                                                }))
                                        .then(CommandManager.literal("remove").executes(ctx -> {
                                            ctx.getSource().getPlayer().sendMessage(new LiteralText(
                                                    FormattingEngine.replaceColorCodeEnumInString(
                                                            new TranslatableText(
                                                                    this.getTranslationKey()
                                                                            + ".remove")
                                                                                    .asString())));
                                            return Command.SINGLE_SUCCESS;
                                        })).then(CommandManager.literal("give").executes(ctx -> {
                                            ctx.getSource().getPlayer().sendMessage(new LiteralText(
                                                    FormattingEngine.replaceColorCodeEnumInString(
                                                            new TranslatableText(
                                                                    this.getTranslationKey()
                                                                            + ".give")
                                                                                    .asString())));
                                            return Command.SINGLE_SUCCESS;
                                        })).then(CommandManager.literal("request").executes(ctx -> {
                                            ctx.getSource().getPlayer().sendMessage(new LiteralText(
                                                    FormattingEngine.replaceColorCodeEnumInString(
                                                            new TranslatableText(
                                                                    this.getTranslationKey()
                                                                            + ".request")
                                                                                    .asString())));
                                            return Command.SINGLE_SUCCESS;
                                        })).then(CommandManager.literal("convert")
                                                .then(CommandManager.literal("to").executes(ctx -> {
                                                    ctx.getSource().getPlayer().sendMessage(
                                                            new LiteralText(FormattingEngine
                                                                    .replaceColorCodeEnumInString(
                                                                            new TranslatableText(
                                                                                    this.getTranslationKey()
                                                                                            + ".convert.to")
                                                                                                    .asString())));
                                                    return Command.SINGLE_SUCCESS;
                                                })).then(CommandManager.literal("from")
                                                        .executes(ctx -> {
                                                            ctx.getSource().getPlayer().sendMessage(
                                                                    new LiteralText(FormattingEngine
                                                                            .replaceColorCodeEnumInString(
                                                                                    new TranslatableText(
                                                                                            this.getTranslationKey()
                                                                                                    + ".convert.from")
                                                                                                            .asString())));
                                                            return Command.SINGLE_SUCCESS;
                                                        }))
                                                .executes(ctx -> {
                                                    ctx.getSource().getPlayer().sendMessage(
                                                            new LiteralText(FormattingEngine
                                                                    .replaceColorCodeEnumInString(
                                                                            new TranslatableText(
                                                                                    this.getTranslationKey()
                                                                                            + ".convert")
                                                                                                    .asString())));
                                                    return Command.SINGLE_SUCCESS;
                                                }))
                                        .executes(ctx -> {
                                            ctx.getSource().getPlayer().sendMessage(new LiteralText(
                                                    FormattingEngine.replaceColorCodeEnumInString(
                                                            new TranslatableText(
                                                                    this.getTranslationKey())
                                                                            .asString())));
                                            return Command.SINGLE_SUCCESS;
                                        })));
    }

    public CurrencyRegister() {

    }

    @Override
    public String getRegisterName() {
        return "currency";
    }

    @Override
    public String getTranslationKey() {
        return "command.fabric_rpg.currency";
    }

}
