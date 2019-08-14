package io.github.xeonpowder.fabric.rpg.entity.portalguardian;

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
public class FabricRPGPortalGuardianRenderer
        extends LivingEntityRenderer<FabricRPGPortalGuardian, FabricRPGPortalGuardianModel> {

    public FabricRPGPortalGuardianRenderer(EntityRenderDispatcher entityRenderDispatcher_1) {
        super(entityRenderDispatcher_1, new FabricRPGPortalGuardianModel(), 1);
    }

    @Override
    protected Identifier getTexture(FabricRPGPortalGuardian var1) {
        return new Identifier(FabricRPG.MODID, "textures/entity/portal_guardian/portal_guardian.png");
    }

}