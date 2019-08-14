package io.github.xeonpowder.fabric.rpg.profession;

import io.github.xeonpowder.TUSK__3.panI18n.FormattingEngine;
import nerdhub.cardinal.components.api.component.Component;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.TranslatableText;

/**
 * FabricRPGProfessionName
 */
public class FabricRPGProfessionName implements FabricRPGProfessionComponent {
    private String name = "<empty>";
    private FabricRPGProfession profession;

    public FabricRPGProfessionName(FabricRPGProfession profession) {
        this.profession = profession;
    }

    public FabricRPGProfessionName(FabricRPGProfession profession, String name) {
        this.profession = profession;
        this.name = name;
    }

    public FabricRPGProfessionName(String name) {
        this.name = name;
    }

    public void setProfession(FabricRPGProfession profession) {
        this.profession = profession;
    }

    public String getNameKey() {
        return this.name;
    }

    public String getName() {
        return FormattingEngine.replaceColorCodeEnumInString((new TranslatableText(this.name)).asString());
    }

    @Override
    public void fromTag(CompoundTag tag) {
        if (tag != null) {
            this.name = tag.getString("name");
        }
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        if (tag == null) {
            tag = new CompoundTag();
        }
        tag.putString("professionComponentName", "name");
        tag.putString("name", this.name);
        return tag;
    }

    @Override
    public FabricRPGProfession getProfession() {
        return this.profession;
    }

}