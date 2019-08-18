package io.github.xeonpowder.fabric.rpg.profession;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.profession.professions.mining.Mining;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.World;

/**
 * FabricRPGProfression
 */
public class FabricRPGProfession implements FabricRPGProfessionComponent {
    private static final int COMPOUND_TAG_TYPE = new CompoundTag().getType();
    protected Profession.ID professionID;
    protected FabricRPGProfessionName professionName;
    protected FabricRPGProfessionTitle professionTitle;
    protected FabricRPGProfessionLevel professionLevel;
    protected FabricRPGProfessionAction professionAction;
    protected FabricRPGProfession nextProfession;

    public FabricRPGProfession() {
        professionName = new FabricRPGProfessionName(this);
        professionTitle = new FabricRPGProfessionTitle(this);
        professionLevel = new FabricRPGProfessionLevel(this);
        professionAction = new FabricRPGProfessionAction(this);
        nextProfession = null;
        professionID = Profession.ID.EMPTY;
    }

    public FabricRPGProfession(FabricRPGProfessionName professionName,
            FabricRPGProfessionTitle professionTitle, FabricRPGProfessionLevel professionLevel,
            FabricRPGProfessionAction professionAction, Profession.ID professionID,
            FabricRPGProfession nextProfession) {
        this.professionName = professionName;
        this.professionTitle = professionTitle;
        this.professionLevel = professionLevel;
        this.professionAction = professionAction;
        this.professionID = professionID;
        this.nextProfession = nextProfession;
    }

    public static FabricRPGProfession FabricRPGProfessionFromTag(CompoundTag tag) {
        FabricRPGProfession profession = new FabricRPGProfession();
        profession.fromTag(tag);
        return profession;
    }

    public FabricRPGProfession getProfession() {
        return this;
    }

    public Profession.ID getProfessionID() {
        return this.professionID;
    }

    public String getName() {
        return this.professionName.getName();
    }

    public String getNameKey() {
        return this.professionName.getNameKey();
    }

    public String getTitle() {
        return this.professionTitle.getTitle();
    }

    public String getTitleKey() {
        return this.professionTitle.getTitleKey();
    }

    public int getLevel() {
        return this.professionLevel.getLevel();
    }

    public float getExperience() {
        return this.professionLevel.getExperience();
    }

    public FabricRPGProfessionAction getAction() {
        return this.professionAction;
    }

    public String getActionName() {
        return this.professionAction.getActionName();
    }

    public void toggleAction() {
        this.professionAction.toggle();

    }

    public void consume(World world, PlayerEntity player) {
        this.professionAction.consume(world, player);
    }

    public boolean isToggled() {
        return this.professionAction.isToggled();
    }

    @Override
    public void fromTag(CompoundTag tag) {
        tag.getList("data", COMPOUND_TAG_TYPE).forEach(dataObject -> {
            if (dataObject instanceof CompoundTag) {
                switch (((CompoundTag) dataObject).getString("professionComponentName")) {
                    case "level":
                        this.professionLevel.fromTag(((CompoundTag) dataObject));
                        break;
                    case "name":
                        this.professionName.fromTag(((CompoundTag) dataObject));
                        break;
                    case "title":
                        this.professionTitle.fromTag(((CompoundTag) dataObject));
                        break;
                    case "action":
                        this.professionAction.fromTag(((CompoundTag) dataObject));
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        tag.putInt("ProfessionID", this.professionID.ordinal());
        ListTag data = new ListTag();
        data.add(professionName.toTag(null));
        data.add(professionTitle.toTag(null));
        data.add(professionLevel.toTag(null));
        data.add(professionAction.toTag(null));
        tag.put("data", data);
        return tag;
    }

    public static class Profession {
        public static enum ID {
            EMPTY, MINING, LEATHERWORKING, BLACKSMITHING, HERBALISM, ALCHEMY, ENCHANTING, TAILORING
        }

    }

    public void updateProfessionLink(FabricRPGProfession profession) {
        this.professionAction.setProfession(profession);
        this.professionName.setProfession(profession);
        this.professionTitle.setProfession(profession);
        this.professionLevel.setProfession(profession);
    }

    public boolean hasNextProfession() {
        return this.nextProfession != null;
    }

    public FabricRPGProfession getNextProfession() {
        return this.nextProfession;
    }

}
