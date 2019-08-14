package io.github.xeonpowder.fabric.rpg.entity.banana;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;

/**
 * FabricRPGPortalGuardianModel
 */
@Environment(EnvType.CLIENT)
public class BananaModel extends EntityModel<Banana> {
    private final Cuboid bone;
    private final Cuboid bone1;
    private final Cuboid bone2;
    private final Cuboid bone3;

    public BananaModel() {
        this(0.0F);
    }

    public BananaModel(float f1) {
        bone = new Cuboid(this).setTextureSize(16, 16);
        bone.setRotationPoint(0.0F, 24.0F, 0.0F);
        bone.pitch = -0.6981F;
        bone.yaw = 0.0F;
        bone.roll = 0.0F;
        bone.addBox("bone", -2.0F, -2.0F, -4.0F, 1, 1, 1, 0.0F, 0, 0);

        bone1 = new Cuboid(this).setTextureSize(16, 16);
        bone1.setRotationPoint(0.0F, 24.0F, 0.0F);
        bone1.pitch = -0.6981F;
        bone1.yaw = 0.0F;
        bone1.roll = 0.0F;
        bone1.addBox("body", -3.0F, -7.0F, -5.0F, 3, 5, 3, 0.0F, 0, 2);

        bone2 = new Cuboid(this).setTextureSize(16, 16);
        bone2.setRotationPoint(0.0F, 24.0F, 0.0F);
        bone2.addBox("body", -2.0F, -13.0F, 1.0F, 1, 1, 1, 0.0F, 0, 0);

        bone3 = new Cuboid(this).setTextureSize(16, 16);
        bone3.setRotationPoint(0.0F, 24.0F, 0.0F);
        bone3.addBox("body", -3.0F, -12.0F, 0.0F, 3, 5, 3, 0.0F, 0, 2);
    }

    public void render(Banana banana, float float_1, float float_2, float float_3, float float_4, float float_5,
            float float_6) {

        this.setAngles(banana, float_1, float_2, float_3, float_4, float_5, float_6);
        this.bone.render(float_6);
        this.bone1.render(float_6);
        this.bone2.render(float_6);
        this.bone3.render(float_6);
    }

    public void setAngles(Banana banana, float float_1, float float_2, float float_3, float float_4, float float_5,
            float float_6) {
    }
}