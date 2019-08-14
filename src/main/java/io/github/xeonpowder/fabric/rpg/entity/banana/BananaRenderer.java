package io.github.xeonpowder.fabric.rpg.entity.banana;

import io.github.xeonpowder.fabric.rpg.FabricRPG;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.util.Identifier;

/**
 * FabricRPGPortalGuardianRenderer
 */
@Environment(EnvType.CLIENT)
public class BananaRenderer extends LivingEntityRenderer<Banana, BananaModel> {

    public BananaRenderer(EntityRenderDispatcher entityRenderDispatcher_1) {
        super(entityRenderDispatcher_1, new BananaModel(), 1);
    }

    @Override
    protected Identifier getTexture(Banana var1) {
        return new Identifier(FabricRPG.MODID, "textures/entity/banana/banana.png");
    }

}