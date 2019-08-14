package io.github.xeonpowder.fabric.rpg.profession;

import nerdhub.cardinal.components.api.component.Component;
import net.minecraft.nbt.CompoundTag;

/**
 * FabricRPGProfessionActionRank
 */
public class FabricRPGProfessionActionRank implements FabricRPGProfessionComponent {
    public static enum RANK {
        I, II, III, IV, V, VI, VII, VIII, IX, X, XI, XII, XIII, XIV, XV, XVI, XVII, XVIII, XIX, XX, XXI, XXII, XXIII,
        XXIV, XXV, XXVI, XXVII, XXVIII, XXIX, XXX
    }

    private RANK rank = RANK.I;
    private FabricRPGProfessionLevel rankLevelManager;
    private FabricRPGProfession profession;

    public FabricRPGProfessionActionRank(FabricRPGProfession profession) {
        this.rankLevelManager = new FabricRPGProfessionLevel(profession);
        this.profession = profession;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        this.rank = RANK.values()[tag.getInt("rank")];
        this.rankLevelManager.fromTag(tag.getCompound("levelManager"));
    }

    public FabricRPGProfessionLevel getLevelManager() {
        return this.rankLevelManager;
    }

    public String rankToString() {
        return this.rank.toString();
    }

    public RANK getRank() {
        return this.rank;
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        if (tag == null) {
            tag = new CompoundTag();
        }
        tag.putInt("rank", this.rank.ordinal());
        tag.put("levelManager", this.rankLevelManager.toTag(null));
        return tag;
    }

    @Override
    public FabricRPGProfession getProfession() {
        return this.profession;
    }

}