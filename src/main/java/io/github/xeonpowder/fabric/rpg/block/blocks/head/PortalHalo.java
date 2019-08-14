package io.github.xeonpowder.fabric.rpg.block.blocks.head;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.block.FabricRPGHeadBlock;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.BlockView;

/**
 * PortalHalo
 */
public class PortalHalo extends FabricRPGHeadBlock implements BlockEntityProvider {
    public PortalHalo() {
        super(FabricBlockSettings.of(Material.METAL).collidable(false).build(), true);
        this.id = new Identifier(FabricRPG.MODID, this.getTranslationKey());
        this.registerBlock();
    }

    @Override
    public BlockEntity createBlockEntity(BlockView var1) {
        return new PortalHaloBlockEntity();
    }

}