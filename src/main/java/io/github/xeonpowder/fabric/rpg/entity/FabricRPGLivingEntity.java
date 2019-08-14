package io.github.xeonpowder.fabric.rpg.entity;

import java.util.ArrayList;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class FabricRPGLivingEntity extends LivingEntity {

    protected FabricRPGLivingEntity(EntityType<? extends LivingEntity> entityType_1, World world_1) {
        super(entityType_1, world_1);
    }

    @Override
    public Iterable<ItemStack> getArmorItems() {
        return new ArrayList<ItemStack>();
    }

    @Override
    public ItemStack getEquippedStack(EquipmentSlot var1) {
        return new ItemStack(Registry.ITEM.get(new Identifier("fabric_rpg:portal_plant_block")));
    }

    @Override
    public void setEquippedStack(EquipmentSlot var1, ItemStack var2) {
    }

    @Override
    public Arm getMainArm() {
        return Arm.RIGHT;
    }
}