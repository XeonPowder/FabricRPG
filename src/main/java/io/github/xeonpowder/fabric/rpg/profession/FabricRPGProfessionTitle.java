package io.github.xeonpowder.fabric.rpg.profession;

import io.github.xeonpowder.TUSK__3.panI18n.FormattingEngine;
import nerdhub.cardinal.components.api.component.Component;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.TranslatableText;

/**
 * FabricRPGProfessionTitle
 */
public class FabricRPGProfessionTitle implements FabricRPGProfessionComponent {
    private String title = "<empty>";
    private FabricRPGProfession profession;

    public FabricRPGProfessionTitle(FabricRPGProfession profession) {
        this.profession = profession;

    }

    public FabricRPGProfessionTitle(String title) {
        this.title = title;

    }

    public void setProfession(FabricRPGProfession profession) {
        this.profession = profession;
    }

    public String getTitleKey() {
        return this.title;
    }

    public String getTitle() {
        return FormattingEngine.replaceColorCodeEnumInString((new TranslatableText(this.title)).asString());
    }

    @Override
    public void fromTag(CompoundTag tag) {

        this.title = tag.getString("title");
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        if (tag == null) {
            tag = new CompoundTag();
        }

        tag.putString("professionComponentName", "title");
        tag.putString("title", this.title);
        return tag;
    }

    @Override
    public FabricRPGProfession getProfession() {
        return this.profession;
    }

}