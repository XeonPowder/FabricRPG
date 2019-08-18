package io.github.xeonpowder.fabric.rpg.profession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.profession.FabricRPGProfession.Profession;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.util.sync.EntitySyncedComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;

/**
 * FabricRPGPlayerProfessions
 */
public class FabricRPGPlayerProfessions
        implements FabricRPGPlayerProfessionsComponent, EntitySyncedComponent {
    private static final int COMPOUND_TAG_TYPE = new CompoundTag().getType();
    PlayerEntity player;
    HashMap<Profession.ID, FabricRPGProfession> professions;

    public FabricRPGPlayerProfessions(PlayerEntity player) {
        this.player = player;
        this.professions = new HashMap<>();
    }

    public static FabricRPGProfession registerDefaultProfessionGlobally(Profession.ID id,
            FabricRPGProfession profession) {
        FabricRPG.DEFAULT_PROFESSION_LIST.put(id, profession);
        FabricRPG.PROFESSION_LIST.get(id).put(profession.getNameKey(), profession);
        return profession;
    }

    public static FabricRPGProfession registerProfessionGlobally(Profession.ID id,
            FabricRPGProfession profession) {
        FabricRPG.PROFESSION_LIST.get(id).put(profession.getNameKey(), profession);
        return profession;
    }

    public void addProfession(FabricRPGProfession profession) {
        this.professions.put(profession.professionID, profession);
    }

    public void removeProfessionsByID(Profession.ID id) {
        this.professions.remove(id);
    }

    public void removeProfession(FabricRPGProfession professionToRemove) {
        this.professions.remove(professionToRemove.professionID);
    }

    @Override
    public ComponentType<?> getComponentType() {
        return FabricRPG.PlayerProfessionsComponent;
    }

    @Override
    public Entity getEntity() {
        return this.player;
    }

    public HashMap<Profession.ID, FabricRPGProfession> getProfessions() {
        return this.professions;
    }

    public FabricRPGProfession getProfession(Profession.ID professionID) {
        return this.professions.get(professionID);
    }

    public HashMap<Profession.ID, FabricRPGProfession> getAvailableProfessions() {
        HashMap<Profession.ID, FabricRPGProfession> availableProfessions = new HashMap<>();
        availableProfessions.putAll(FabricRPG.DEFAULT_PROFESSION_LIST);
        if (professions.size() > 0) {
            professions.forEach((professionID, profession) -> {
                if (availableProfessions.get(professionID).getName().equals(profession.getName())) {
                    availableProfessions.remove(professionID);
                    if (profession.hasNextProfession()) {
                        availableProfessions.put(professionID, profession.getNextProfession());
                    }
                }
            });
        }
        return availableProfessions;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        if (tag != null) {
            this.professions = new HashMap<>();
            if (tag.containsKey("professions")) {
                tag.getList("professions", COMPOUND_TAG_TYPE).forEach(professionTag -> {
                    if (professionTag instanceof CompoundTag) {
                        FabricRPGProfession profession =
                                FabricRPGProfession.FabricRPGProfessionFromTag(tag);
                        this.professions.put(profession.professionID, profession);
                    }
                });
            }
        }
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        if (tag == null) {
            tag = new CompoundTag();
        }
        ListTag data = new ListTag();
        this.professions.values().forEach(profession -> {
            data.add(profession.toTag(null));
        });
        tag.put("professions", data);
        return tag;
    }

    @Override
    public FabricRPGPlayerProfessions getPlayerProfessions() {
        return this;
    }

}
