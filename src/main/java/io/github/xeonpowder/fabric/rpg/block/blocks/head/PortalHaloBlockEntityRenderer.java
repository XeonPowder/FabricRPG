package io.github.xeonpowder.fabric.rpg.block.blocks.head;

import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * PortalHaloBlockEntityRenderer
 */
public class PortalHaloBlockEntityRenderer extends BlockEntityRenderer<PortalHaloBlockEntity> {
    private static ItemStack stack = new ItemStack(
            Registry.ITEM.get(new Identifier(FabricRPG.MODID, "portal_plant_block")), 1);

    @Override
    public void render(PortalHaloBlockEntity blockEntity, double x, double y, double z, float partialTicks,
            int destroyStage) {

        GlStateManager.pushMatrix(); // which is mandatory when doing GL calls

        double offset = Math.sin((blockEntity.getWorld().getTime() + partialTicks) / 8.0) / 4.0;
        GlStateManager.translated(x + 0.5, y + 1.25 + offset, z + 0.5);
        GlStateManager.rotatef((blockEntity.getWorld().getTime() + partialTicks) * 4, 0, 1, 0);

        int light = blockEntity.getWorld().getLightmapIndex(blockEntity.getPos().up(), 0);
        GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, (float) (light & 0xFFFF), (float) ((light >> 16) & 0xFFFF));

        MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformation.Type.GROUND);

        GlStateManager.popMatrix(); // Mandatory call after GL calls

    }

}