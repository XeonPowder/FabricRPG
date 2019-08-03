package io.github.xeonpowder.fabric.rpg.block.blocks.plant;

import io.github.xeonpowder.fabric.rpg.block.FabricRPGPlantBlock;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Material;

/**
 * PortalPlantBlock
 */
public class PortalPlantBlock extends FabricRPGPlantBlock {

    public PortalPlantBlock() {
        // Block.Settings, isTransparent
        super(FabricBlockSettings.of(Material.PLANT).collidable(false).build(), true);
        this.registerBlock();
    }

}