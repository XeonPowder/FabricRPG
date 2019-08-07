package io.github.xeonpowder.fabric.rpg.gui.panel;

import java.awt.Color;
import java.util.List;
import java.util.stream.Collectors;

import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.xeonpowder.fabric.rpg.FabricRPG;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;

/**
 * LocationPanel
 */
public class LocationPanel extends WPlainPanel {
    private Color targetColor = getDefaultErrorColor();
    private Color currentColor = getDefaultColor();
    private Color defaultColor = getDefaultColor();
    private boolean error, redReached, blueReached, greenReached = false;

    private Color getDefaultErrorColor() {
        return new Color(255, 0, 0);
    }

    private Color getDefaultColor() {
        return new Color(0xFF2F2F2F);
    }

    public LocationPanel() {
        super();
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void paintBackground(int x, int y) {
        if (error) {
            int targetRed = targetColor.getRed();
            int targetBlue = targetColor.getBlue();
            int targetGreen = targetColor.getGreen();
            int currentRed = currentColor.getRed();
            int currentBlue = currentColor.getBlue();
            int currentGreen = currentColor.getGreen();
            if (!redReached) {
                if (targetRed > currentRed) {
                    currentRed++;
                } else if (targetRed < currentRed) {
                    currentRed--;
                } else {
                    redReached = true;
                }
            }
            if (!blueReached) {
                if (targetBlue > currentBlue) {
                    currentBlue++;
                } else if (currentBlue > targetBlue) {
                    currentBlue--;
                } else {
                    blueReached = true;
                }
            }
            if (!greenReached) {
                if (targetGreen > currentGreen) {
                    currentGreen++;
                } else if (currentGreen > targetGreen) {
                    currentGreen--;
                } else {
                    greenReached = true;
                }
            }
            if (redReached && blueReached && greenReached) {
                redReached = blueReached = greenReached = false;
                if (targetColor.getRed() != defaultColor.getRed() && targetColor.getBlue() != defaultColor.getBlue()
                        && targetColor.getGreen() != defaultColor.getGreen()) {
                    targetColor = getDefaultColor();
                } else {
                    error = false;
                    targetColor = getDefaultErrorColor();
                    this.setBackgroundPainter((left, top, panel) -> {
                        ScreenDrawing.drawGuiPanel(left - 8, top - 8, panel.getWidth() + 14, panel.getHeight() + 14,
                                this.defaultColor.getRGB());
                    });
                }
            } else {
                this.currentColor = new Color(currentRed, currentBlue, currentGreen);
                this.setBackgroundPainter((left, top, panel) -> {
                    ScreenDrawing.drawGuiPanel(left - 8, top - 8, panel.getWidth() + 14, panel.getHeight() + 14,
                            this.currentColor.getRGB());
                });
            }
        }
        super.paintBackground(x, y);
    }

    public void onClick(PlayerEntity player, Position nodePosition) {
        int requiredCurrencyAmount = calculateRequiredCurrency(player.getPos(), nodePosition);
        if (hasEnoughCurrencyToTeleport(player.inventory, requiredCurrencyAmount)) {
            removeCurrencyFromPlayerInventory(player.inventory, requiredCurrencyAmount);
            player.teleport(nodePosition.getX(), nodePosition.getY(), nodePosition.getZ());
        } else {
            this.error = true;
        }
    }

    public int calculateRequiredCurrency(Vec3d pos, Position nodePosition) {
        return ((int) (pos.distanceTo(new Vec3d(nodePosition.getX(), nodePosition.getY(), nodePosition.getZ())) / 100));
    }

    @Override
    public void onClick(int x, int y, int button) {
        super.onClick(x, y, button);
    }

    public void removeCurrencyFromPlayerInventory(PlayerInventory inventory, int countToRemove) {
        List<ItemStack> portalPlantBlockItemStacks = inventory.main.stream().filter(itemStack -> {
            return itemStack.getTranslationKey().equals("portal_plant_block");
        }).collect(Collectors.toList());
        int countRemoved = 0;
        for (ItemStack itemStack : portalPlantBlockItemStacks) {
            if (countRemoved < countToRemove) {
                int countInItemStack = itemStack.getCount();
                // System.out.println(countInItemStack);
                if (countInItemStack >= (countToRemove - countRemoved)) {
                    inventory.takeInvStack(inventory.getSlotWithStack(itemStack), countToRemove);
                    countRemoved = countToRemove;
                } else {
                    inventory.takeInvStack(inventory.getSlotWithStack(itemStack), countInItemStack);
                    countRemoved += countInItemStack;
                }
            }
        }
    }

    public boolean hasEnoughCurrencyToTeleport(PlayerInventory inventory, int requiredCount) {
        Item x = Registry.ITEM.get(new Identifier(FabricRPG.MODID, "portal_plant_block"));
        int numberOfPortalFlowers = inventory.countInInv(x);
        // System.out.println(numberOfPortalFlowers);
        return numberOfPortalFlowers >= requiredCount;
    }
}