package io.github.xeonpowder.fabric.rpg.profession.professions.mining;

import java.util.HashMap;

import io.github.xeonpowder.TUSK__3.panI18n.FormattingEngine;
import io.github.xeonpowder.fabric.rpg.profession.FabricRPGProfession;
import io.github.xeonpowder.fabric.rpg.profession.FabricRPGProfessionAction;
import io.github.xeonpowder.fabric.rpg.profession.FabricRPGProfessionActionRank;
import io.github.xeonpowder.fabric.rpg.profession.FabricRPGProfessionLevel;
import io.github.xeonpowder.fabric.rpg.profession.FabricRPGProfessionName;
import io.github.xeonpowder.fabric.rpg.profession.FabricRPGProfessionTitle;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;

/**
 * Mining
 */
public class Mining extends FabricRPGProfession {
    public static Profession.ID ProfessionID = Profession.ID.MINING;

    public static enum MiningActions {
        RapidStrike, DualWeild, Deconstruction, BloodScent, NONE, ALL
    }

    public static abstract class MiningAction extends FabricRPGProfessionAction {
        public static BloodScent BLOOD_SCENT;
        public static RapidStrike RAPID_STRIKE;
        public static DualWeild DUAL_WEILD;
        public static Deconstruction DECONSTRUCTION;
        public static HashMap<String, FabricRPGProfessionAction> MiningActionMap;
        static {
            MiningActionMap = FabricRPGProfessionAction.ACTION_MAP.get(Mining.ProfessionID);
            BLOOD_SCENT = new BloodScent();
            RAPID_STRIKE = new RapidStrike();
            DUAL_WEILD = new DualWeild();
            DECONSTRUCTION = new Deconstruction();
            MiningActionMap.put(BLOOD_SCENT.actionKey, BLOOD_SCENT);
            MiningActionMap.put(RAPID_STRIKE.actionKey, RAPID_STRIKE);
            MiningActionMap.put(DUAL_WEILD.actionKey, DUAL_WEILD);
            MiningActionMap.put(DECONSTRUCTION.actionKey, DECONSTRUCTION);
        }

        public MiningAction(String actionKey, String actionNameKey, String actionTitleKey,
                String actionDescriptionKey) {
            super(Mining.ProfessionID, actionKey, actionNameKey, actionTitleKey, actionDescriptionKey);
        }

        @Override
        public String getActionKey() {
            return this.actionKey;
        };

        public String getActionName() {
            return FormattingEngine.replaceColorCodeEnumInString(new TranslatableText(this.actionKey).asString());
        }

        @Override
        public void fromTag(CompoundTag tag) {
            super.fromTag(tag);
        }

        @Override
        public CompoundTag toTag(CompoundTag tag) {
            return super.toTag(tag);
        }

        @Override
        public void consume(World world, PlayerEntity player) {
            super.consume(world, player);
        }
    }

    public static class BloodScent extends MiningAction {

        public BloodScent() {
            super("professions.mining.action.bloodscent", "professions.mining.diamond_hound.action",
                    "professions.mining.diamond_hound.action.title",
                    "professions.mining.diamond_hound.action.description");
        }

        @Override
        public void consume(World world, PlayerEntity player) {
            super.consume(world, player);
        }
    }

    public static class RapidStrike extends MiningAction {

        public RapidStrike() {
            super("professions.mining.action.rapidstrike", "professions.mining.apprentice.action",
                    "professions.mining.apprentice.action.title", "professions.mining.apprentice.action.description");
        }

        @Override
        public void consume(World world, PlayerEntity player) {
            super.consume(world, player);
        }
    }

    public static class DualWeild extends MiningAction {

        public DualWeild() {
            super("professions.mining.action.dualweild", "professions.mining.novice.action",
                    "professions.mining.novice.action.title", "professions.mining.novice.action.description");
        }

        @Override
        public void consume(World world, PlayerEntity player) {
            super.consume(world, player);
        }
    }

    public static class Deconstruction extends MiningAction {

        public Deconstruction() {
            super("professions.mining.action.deconstruction", "professions.mining.exscavator.action",
                    "professions.mining.exscavator.action.title", "professions.mining.exscavator.action.description");
        }

        @Override
        public void consume(World world, PlayerEntity player) {
            super.consume(world, player);
        }

    }

    public Mining(String name, String title, FabricRPGProfessionAction fabricRPGProfessionAction) {
        super(new FabricRPGProfessionName(name), new FabricRPGProfessionTitle(title), new FabricRPGProfessionLevel(),
                fabricRPGProfessionAction, FabricRPGProfession.Profession.ID.MINING);
        super.updateProfessionLink(this);
    }

    @Override
    public FabricRPGProfession getProfession() {
        return this;
    }

}