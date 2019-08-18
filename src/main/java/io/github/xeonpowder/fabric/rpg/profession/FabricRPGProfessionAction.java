package io.github.xeonpowder.fabric.rpg.profession;

import java.util.Arrays;
import java.util.HashMap;

import io.github.xeonpowder.TUSK__3.panI18n.FormattingEngine;
import io.github.xeonpowder.fabric.rpg.profession.FabricRPGProfession.Profession;
import nerdhub.cardinal.components.api.component.Component;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;

/**
 * FabricRPGProfessionAction
 */
public class FabricRPGProfessionAction implements FabricRPGProfessionComponent {

    protected boolean actionPrimed = false;
    protected boolean togglable = false;
    protected String descriptionKey = "<undefined>";
    protected String titleKey = "<undefined>";
    public String actionKey = "<undefined>";
    protected String actionNameKey = "<undefined>";
    protected boolean isActive = false;
    private FabricRPGProfession profession;
    protected FabricRPGProfessionActionRank actionRank;
    public static HashMap<Profession.ID, HashMap<String, FabricRPGProfessionAction>> ACTION_MAP;
    static {
        ACTION_MAP = new HashMap<>();
        Arrays.asList(Profession.ID.values()).forEach(id -> {
            System.out.println(id);
            ACTION_MAP.put(id, new HashMap<>());
        });
    }

    public FabricRPGProfessionAction(FabricRPGProfession profession) {
        this.setProfession(profession);
    }

    public FabricRPGProfessionAction(Profession.ID professionID, String actionKey, String actionNameKey,
            String titleKey, String descriptionKey) {
        this.actionPrimed = false;
        this.togglable = false;
        this.isActive = false;
        this.actionKey = actionKey;
        this.descriptionKey = descriptionKey;
        this.titleKey = titleKey;
        this.actionNameKey = actionNameKey;
    }

    public void setProfession(FabricRPGProfession profession) {
        this.profession = profession;
        this.actionRank = new FabricRPGProfessionActionRank(this.profession);
    }

    public void setTogglable(boolean togglable) {
        this.togglable = togglable;
    }

    public void setActionPrimed(boolean actionPrimed) {
        this.actionPrimed = actionPrimed;
    }

    public void toggle() {
        this.actionPrimed = !this.actionPrimed;
    }

    public void toggleIfTogglable() {
        if (togglable)
            this.toggle();
        else
            System.out.println("action not togglable");
    }

    public boolean isToggled() {
        return this.actionPrimed;
    }

    public String getActionKey() {
        return this.actionKey;
    }

    public String getActionNameKey() {
        return this.actionNameKey;
    }

    public String getActionName() {
        return "";
    }

    public FabricRPGProfessionActionRank getActionRank() {
        return this.actionRank;
    }

    public boolean isActionActive() {
        return this.isActive;
    }

    public String getTitleKey() {
        return this.titleKey;
    }

    public String getTitle() {
        return FormattingEngine.replaceColorCodeEnumInString(new TranslatableText(this.getTitleKey()).asString());
    }

    public String getDescriptionKey() {
        return this.descriptionKey;
    }

    public String getDescription() {
        return FormattingEngine.replaceColorCodeEnumInString(new TranslatableText(this.getDescriptionKey()).asString());
    }

    public void consume(World world, PlayerEntity player) {
        this.isActive = true;
        System.out.println(player.getStackInHand(player.getActiveHand()).getTag());
        FabricRPGProfessionLevel actionRankLevelManager = this.actionRank.getLevelManager();
        actionRankLevelManager
                .addExperience(actionRankLevelManager.getExperience() + (actionRankLevelManager.getLevel() + 1));
        if (actionRankLevelManager.getExperience() > (actionRankLevelManager.getLevel() == 0 ? 10
                : actionRankLevelManager.getLevel() * 10)) {
            actionRankLevelManager.setExperience(0);
            actionRankLevelManager.addLevel();
        }
    }

    @Override
    public void fromTag(CompoundTag tag) {
        this.actionPrimed = tag.getBoolean("actionPrimed");
        this.togglable = tag.getBoolean(("togglable"));
        this.descriptionKey = tag.getString("actionDescriptionKey");
        this.actionKey = tag.getString("actionKey");
        this.titleKey = tag.getString("actionTitleKey");
        this.isActive = tag.getBoolean("isActive");
        this.actionNameKey = tag.getString("actionNameKey");
        this.actionRank.fromTag(tag.getCompound("actionRank"));
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        if (tag == null) {
            tag = new CompoundTag();
        }
        tag.putString("professionComponentName", "action");
        tag.putBoolean("actionPrimed", this.actionPrimed);
        tag.putBoolean("togglable", this.togglable);
        tag.putBoolean("isActive", this.isActive);
        tag.putString("actionDescriptionKey", this.descriptionKey);
        tag.putString("actionKey", this.actionKey);
        tag.putString("actionNameKey", this.actionNameKey);
        tag.putString("actionTitleKey", this.titleKey);
        tag.put("actionRank", this.actionRank.toTag(null));
        return tag;
    }

    @Override
    public FabricRPGProfession getProfession() {
        return this.profession;
    }

}