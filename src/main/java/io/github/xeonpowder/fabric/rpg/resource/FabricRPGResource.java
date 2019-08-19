package io.github.xeonpowder.fabric.rpg.resource;

import nerdhub.cardinal.components.api.component.Component;
import net.minecraft.nbt.CompoundTag;

/**
 * FabricRPGResource
 */
public class FabricRPGResource implements Component {
    public String resourceName;
    public int maxResourceValue;
    public int minResourceValue;
    public int resourceValue;
    public float resourceRegenValue;
    public int resourceRegenRate;
    public ResourceType resourceType;

    public FabricRPGResource() {
        this.resourceName = "<undefined>";
        this.maxResourceValue = 100;
        this.minResourceValue = 0;
        this.resourceValue = 0;
        this.resourceRegenValue = 0.5f;
        this.resourceRegenRate = 1;
        this.resourceType = ResourceType.NONE;
    }

    public FabricRPGResource(String resourceName, int maxResourceValue, int minResourceValue,
            int resourceValue, float resourceRegenValue, int resourceRegenRate,
            ResourceType resourceType) {
        this.resourceName = resourceName;
        this.maxResourceValue = maxResourceValue;
        this.minResourceValue = minResourceValue;
        this.resourceValue = resourceValue;
        this.resourceRegenValue = resourceRegenValue;
        this.resourceRegenRate = resourceRegenRate;
        this.resourceType = resourceType;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        this.resourceName = tag.getString("resourceName");
        this.maxResourceValue = tag.getInt("maxResourceValue");
        this.minResourceValue = tag.getInt("minResourceValue");
        this.resourceValue = tag.getInt("resourceValue");
        this.resourceRegenValue = tag.getFloat("resourceRegenValue");
        this.resourceRegenRate = tag.getInt("resourceRegenRate");
        this.resourceType = ResourceType.values()[tag.getInt("resourceType")];
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        if (tag == null) {
            tag = new CompoundTag();
        }
        tag.putString("resourceName", this.resourceName);
        tag.putInt("maxResourceValue", this.maxResourceValue);
        tag.putInt("minResourceValue", this.minResourceValue);
        tag.putInt("resourceValue", this.resourceValue);
        tag.putFloat("resourceRegenValue", this.resourceRegenValue);
        tag.putInt("resourceRegenRate", this.resourceRegenRate);
        tag.putInt("resourceType", this.resourceType.ordinal());
        return tag;
    }

    public static enum ResourceType {
        NONE, MANA, RAGE, ENERGY
    }

    public ResourceType getType() {
        return this.resourceType;
    }

}
