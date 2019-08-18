package io.github.xeonpowder.fabric.rpg.currency;

import java.util.regex.Pattern;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.util.sync.EntitySyncedComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;

/**
 * FabricRPGPlayerCurrency
 */
public class FabricRPGPlayerCurrency implements FabricRPGPlayerCurrencyComponent {
    double emerald;
    double gold;
    double silver;
    PlayerEntity player;

    public FabricRPGPlayerCurrency() {
        this.gold = 0;
        this.silver = 0;
        this.emerald = 0;
    }

    public FabricRPGPlayerCurrency(PlayerEntity player) {
        this.player = player;
    }

    public String getLongString() {
        return "Emerald: " + this.emerald + ", Gold: " + this.gold + ", Silver: " + this.silver;
    }

    public String getShortString() {
        return this.emerald + "e " + this.gold + "g " + this.silver + "s";
    }

    public Double getEmerald() {
        return this.emerald;
    }

    public Double getSilver() {
        return this.silver;
    }

    public Double getGold() {
        return this.gold;
    }

    public void setGold(double gold) {
        this.gold = gold;
    }

    public void setSilver(double silver) {
        this.silver = silver;
    }

    public void setEmerald(double emerald) {
        this.emerald = emerald;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        this.gold = tag.getDouble("gold");
        this.silver = tag.getDouble("silver");
        this.emerald = tag.getDouble("emerald");
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        if (tag == null) {
            tag = new CompoundTag();
        }
        tag.putDouble("gold", this.gold);
        tag.putDouble("silver", this.silver);
        tag.putDouble("emerald", this.emerald);
        return tag;
    }

    @Override
    public FabricRPGPlayerCurrency getCurrency() {
        return this;
    }

    @Override
    public ComponentType<?> getComponentType() {
        return FabricRPG.PlayerCurrencyComponent;
    }

    @Override
    public Entity getEntity() {
        return player;
    }

    public boolean isValidCurrencyString(String amount) {
        return Pattern.matches("/([0-9].*)s|([0-9].*)g|([0-9].*)e/g", amount);
    }

    public Double getEmeraldAmountFromString(String amount) {
        return null;
    }

    public Double getGoldAmountFromString(String amount) {
        return null;
    }

    public Double getSilverAmountFromString(String amount) {
        return null;
    }

}