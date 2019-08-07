package io.github.xeonpowder.fabric.rpg.timber;

import io.github.xeonpowder.TUSK__3.panI18n.FormattingEngine;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

/**
 * Timber
 */
public class Timber implements TimberComponent {
    private boolean active;
    private PlayerEntity player;

    public Timber(PlayerEntity player) {
        this.player = player;
        this.active = false;
    }

    public boolean isActive() {
        return this.active;
    }

    public PlayerEntity getPlayer() {
        return this.player;
    }

    public boolean toggle() {
        this.setActive(!this.active);
        if (this.player.world.isClient) {
            this.player.sendMessage(new LiteralText(FormattingEngine
                    .replaceColorCodeEnumInString(new TranslatableText("timber.toggled." + this.active).asString())));
        }
        return this.isActive();
    }

    public void setActive(boolean bool) {
        this.active = bool;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        this.active = tag.getBoolean("active");
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        if (tag == null) {
            tag = new CompoundTag();
        }
        tag.putBoolean("active", this.isActive());
        return tag;
    }

    @Override
    public Timber getTimber() {
        return this;
    }

}