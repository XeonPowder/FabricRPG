package io.github.xeonpowder.fabric.rpg.portalnetwork;

import java.util.ArrayList;
import java.util.List;

import io.github.xeonpowder.fabric.rpg.portalnetwork.node.PortalNetworkNode;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.level.LevelProperties;

public class PortalNetwork implements PortalNetworkComponent {

    private static final int COMPOUND_TAG_TYPE = new CompoundTag().getType();
    private PlayerEntity player;
    private List<PortalNetworkNode> portalNetworkNodes;
    private World world;
    private LevelProperties levelProperties;
    private Dimension dimension;

    public PortalNetwork(PlayerEntity player) {
        this.player = player;
        this.world = this.player.world;
        this.levelProperties = this.world.getLevelProperties();
        this.portalNetworkNodes = new ArrayList<>();
    }

    public PortalNetwork(World world) {
        this.world = world;
        this.levelProperties = world.getLevelProperties();
        this.portalNetworkNodes = new ArrayList<>();
    }

    public PortalNetwork(LevelProperties levelProperties) {
        this.levelProperties = levelProperties;
        this.portalNetworkNodes = new ArrayList<>();
    }

    public PortalNetwork(Dimension dimension) {
        this.dimension = dimension;
        this.portalNetworkNodes = new ArrayList<>();
    }

    public List<PortalNetworkNode> getNodes() {
        return this.portalNetworkNodes;
    }

    public Dimension getDimension() {
        return this.dimension;
    }

    public LevelProperties getLevelProperties() {
        return this.levelProperties;
    }

    public World getWorld() {
        return this.world;
    }

    public PlayerEntity getPlayer() {
        return this.player;
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        if (tag == null) {
            tag = new CompoundTag();
        }
        ListTag portalNetworkNodesAsTagList = new ListTag();
        portalNetworkNodes.forEach(node -> {
            portalNetworkNodesAsTagList.add(node.toTag(null));
        });
        tag.put("nodes", portalNetworkNodesAsTagList);
        return tag;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        tag.getList("nodes", COMPOUND_TAG_TYPE).forEach((nodeCompoundTag) -> {
            portalNetworkNodes.add(new PortalNetworkNode(new Position() {

                @Override
                public double getZ() {
                    return ((CompoundTag) nodeCompoundTag).getDouble("z");
                }

                @Override
                public double getY() {
                    return ((CompoundTag) nodeCompoundTag).getDouble("y");
                }

                @Override
                public double getX() {
                    return ((CompoundTag) nodeCompoundTag).getDouble("x");
                }
            }, ((CompoundTag) nodeCompoundTag).getString("translationKey")));
        });
    }

    @Override
    public PortalNetwork getPortalNetwork() {
        return this;
    }

}