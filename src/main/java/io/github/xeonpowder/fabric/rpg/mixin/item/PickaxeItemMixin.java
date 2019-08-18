package io.github.xeonpowder.fabric.rpg.mixin.item;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.xeonpowder.TUSK__3.panI18n.FormattingEngine;
import io.github.xeonpowder.fabric.rpg.FabricRPG;
import io.github.xeonpowder.fabric.rpg.profession.FabricRPGProfession;
import io.github.xeonpowder.fabric.rpg.profession.FabricRPGProfession.Profession;
import io.github.xeonpowder.fabric.rpg.profession.professions.mining.Mining;
import net.minecraft.block.BedBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

/**
 * AxeItemMixin
 */
@Mixin(Item.class)
public class PickaxeItemMixin {
    @Inject(at = @At(value = "RETURN"), method = "use")
    public void rightClickWithPickaxe(World world, PlayerEntity playerEntity, Hand hand,
            CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        if (world.isClient) {
            System.out.println("pickaxe use is client");
            if (playerEntity.getMainHandStack().getItem() instanceof PickaxeItem) {
                System.out.println("item in hand is instance of PickaxeItem");
                FabricRPGProfession profession = FabricRPG.PlayerProfessionsComponent.get(playerEntity)
                        .getPlayerProfessions().getProfession(Profession.ID.MINING);
                System.out.println("player's current mining profession");
                System.out.println(profession);
                if (profession != null) {
                    System.out.println(profession.getName());
                    profession.toggleAction();
                    String actionName = profession.getActionName();
                    System.out.println(actionName);
                    String translatedText = (new TranslatableText(
                            "professions.mining.action.toggled." + profession.isToggled(), actionName).asString());
                    String formattedTranslatedText = FormattingEngine.replaceColorCodeEnumInString(translatedText);
                    playerEntity.addChatMessage(new LiteralText(formattedTranslatedText), true);
                } else {
                    String translatedText = new TranslatableText("professions.playerhasno.mining").asString();
                    String formattedTranslatedText = FormattingEngine.replaceColorCodeEnumInString(translatedText);
                    playerEntity.addChatMessage(new LiteralText(formattedTranslatedText), true);
                }
            }
        }
    }
}