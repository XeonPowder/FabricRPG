package io.github.xeonpowder.fabric.rpg.resource;

import java.util.ArrayList;
import java.util.HashMap;
import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.resource.FabricRPGResource.ResourceType;
import io.github.xeonpowder.fabric.rpg.resource.resources.Energy;
import io.github.xeonpowder.fabric.rpg.resource.resources.Mana;
import io.github.xeonpowder.fabric.rpg.resource.resources.Rage;
import nerdhub.cardinal.components.api.ComponentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;

/**
 * FabricRPGPlayerResourceComponent
 */
public class FabricRPGPlayerResourceComponent implements FabricRPGResourceComponent {
    PlayerEntity player;
    HashMap<ResourceType, FabricRPGResource> resources;
    private static final int COMPOUND_TAG_TYPE = new CompoundTag().getType();

    public FabricRPGPlayerResourceComponent(PlayerEntity player) {
        this.player = player;
        this.resources = new HashMap<>();
        this.resources.put(ResourceType.MANA, new Mana());
        this.resources.put(ResourceType.RAGE, new Rage());
        this.resources.put(ResourceType.ENERGY, new Energy());
    }

    @Override
    public void fromTag(CompoundTag tag) {
        tag.getList("resources", COMPOUND_TAG_TYPE).forEach(resourceFromTag -> {
            FabricRPGResource resource = new FabricRPGResource();
            resource.fromTag((CompoundTag) resourceFromTag);
            this.resources.put(resource.getType(), resource);
        });

    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        if (tag == null) {
            tag = new CompoundTag();
        }
        ArrayList<FabricRPGResource> resources = new ArrayList<>();
        ListTag resourcesListTag = new ListTag();
        resources.addAll(this.resources.values());
        for (int i = 0; i < resources.size(); i++) {
            resourcesListTag.add(resources.get(i).toTag(null));
        }
        tag.put("resources", resourcesListTag);
        return tag;
    }

    @Override
    public Entity getEntity() {
        return this.player;
    }

    @Override
    public ComponentType<FabricRPGPlayerResourceComponent> getComponentType() {
        return FabricRPG.PlayerResourceComponent;
    }


}
