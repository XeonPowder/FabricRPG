package io.github.xeonpowder.fabric.rpg.block.blocks.plant;

import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;

/**
 * PortalHaloBlockEntityRenderer
 */
public class PortalPlantBlockEntityRenderer extends BlockEntityRenderer<PortalPlantBlockEntity> {
        private static ItemStack stack = new ItemStack(
                        Registry.ITEM.get(new Identifier(FabricRPG.MODID, "portal_halo")), 1);

        public void renderNewStack(PortalPlantBlockEntity blockEntity, double translatedX, double translatedY,
                        double translatedZ, float rotationDegrees, float rotationX, float rotationY, float rotationZ,
                        int light) {
                GlStateManager.pushMatrix(); // which is mandatory when doing GL calls
                GlStateManager.translated(translatedX, translatedY, translatedZ);
                GlStateManager.rotatef(rotationDegrees, rotationX, rotationY, rotationZ);
                GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, (float) (light & 0xFFFF), (float) ((light >> 16) & 0xFFFF));
                MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformation.Type.GROUND);
                GlStateManager.popMatrix(); // Mandatory call after GL calls

        }

        @Override
        public void render(PortalPlantBlockEntity blockEntity, double x, double y, double z, float partialTicks,
                        int destroyStage) {
                double offset = MathHelper.sin((float) ((blockEntity.getWorld().getTime() + partialTicks) / 8.0)) / 4.0;
                float degrees = ((blockEntity.getWorld().getTime() + partialTicks) * 4);
                int light = blockEntity.getWorld().getLightmapIndex(blockEntity.getPos().up(), 0);
                renderNewStack(blockEntity, x + 0.5, y + (offset / 3), z + 0.5, degrees, (float) (-1 * offset),
                                (float) 1, (float) (offset), light);
                renderNewStack(blockEntity, x + 0.5, y + (offset / 3), z + 0.5, degrees, (float) (1 * offset),
                                (float) 1, (float) (-1 * offset), light);
                renderNewStack(blockEntity, x + 0.5, y + (offset / 3), z + 0.5, degrees, (float) (-1 * offset / 2),
                                (float) 1, (float) (offset / 2), light);
                renderNewStack(blockEntity, x + 0.5, y + (offset / 3), z + 0.5, degrees, (float) (1 * offset / 2),
                                (float) 1, (float) (offset / 2), light);
        }

}