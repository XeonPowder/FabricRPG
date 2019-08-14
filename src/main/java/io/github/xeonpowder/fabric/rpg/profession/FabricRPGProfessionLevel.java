package io.github.xeonpowder.fabric.rpg.profession;

import io.github.xeonpowder.fabric.rpg.profession.FabricRPGProfession.Profession;
import nerdhub.cardinal.components.api.component.Component;
import net.minecraft.nbt.CompoundTag;

/**
 * FabricRPGProfessionLevel
 */
public class FabricRPGProfessionLevel implements FabricRPGProfessionComponent {
    private int level;
    private float experience;
    private FabricRPGProfession profession;

    public FabricRPGProfessionLevel() {
        this.level = 0;
        this.experience = 0;
    }

    public FabricRPGProfessionLevel(FabricRPGProfession profession) {
        this.profession = profession;
        this.level = 0;
        this.experience = 0;
    }

    public void setProfession(FabricRPGProfession profession) {
        this.profession = profession;
    }

    public float getExperience() {
        return this.experience;
    }

    public int getLevel() {
        return this.level;
    }

    public void setExperience(float experience) {
        this.experience = experience;
    }

    public void addExperience(float experience) {
        this.experience += experience;
    }

    public void removeExperience(float experience) {
        this.experience -= experience;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void addLevels(int levels) {
        this.level += levels;
    }

    public void addLevel() {
        this.level++;
    }

    public void removeLevels(int levels) {
        this.level -= levels;
    }

    public void removeLevel() {
        this.level--;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        if (tag != null) {
            this.level = tag.getInt("level");
            this.experience = tag.getFloat("experience");
        }
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        if (tag == null) {
            tag = new CompoundTag();
        }
        tag.putString("professionComponentName", "level");
        tag.putInt("level", this.level);
        tag.putFloat("experience", this.experience);
        return tag;
    }

    @Override
    public FabricRPGProfession getProfession() {
        return this.profession;
    }

}