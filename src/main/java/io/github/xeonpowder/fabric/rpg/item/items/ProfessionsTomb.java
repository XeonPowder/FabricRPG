package io.github.xeonpowder.fabric.rpg.item.items;

import com.google.common.base.CaseFormat;

import io.github.xeonpowder.fabric.rpg.item.FabricRPGItem;
import io.github.xeonpowder.fabric.rpg.profession.gui.ProfessionClientScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

/**
 * ProfessionsTomb
 */
public class ProfessionsTomb extends FabricRPGItem {
    public ProfessionsTomb() {
        super();
        this.setItemName(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, this.getClass().getSimpleName()));
        this.registerItem();
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        // PacketByteBuf packet = new PacketByteBuf(Unpooled.buffer());
        // packet.writeBoolean(true);
        // CustomPayloadS2CPacket openProfessionsGUI = new CustomPayloadS2CPacket(
        // new Identifier(FabricRPG.MODID, "open_professions_gui"), packet);
        // ServerSidePacketRegistry.INSTANCE.sendToPlayer(playerEntity,
        // openProfessionsGUI, (buf) -> {

        // });
        if (world.isClient)
            this.openProfessionsGUI(world, playerEntity);
        // if (!world.isClient) {
        // this.openProfessionsGUI(world, playerEntity);
        // }
        return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, playerEntity.getStackInHand(hand));
    }

    private void openProfessionsGUI(World world, PlayerEntity player) {
        net.minecraft.client.MinecraftClient.getInstance().openScreen(new ProfessionClientScreen(world, player));
    }
}