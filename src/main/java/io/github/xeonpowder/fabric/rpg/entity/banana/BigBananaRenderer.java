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
public class BigBananaRenderer extends LivingEntityRenderer<BigBanana, BigBananaModel> {

    public BigBananaRenderer(EntityRenderDispatcher entityRenderDispatcher_1) {
        super(entityRenderDispatcher_1, new BigBananaModel(), 1);
    }

    @Override
    protected Identifier getTexture(BigBanana var1) {
        return new Identifier(FabricRPG.MODID, "textures/entity/banana/banana.png");
    }

}