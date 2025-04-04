package net.seentro.prehistoriccraft.util;

import net.minecraft.core.BlockPos;

import java.util.List;

public class BlockPosListEntry {
    private final int id;
    private final List<BlockPos> blockPosList;

    public BlockPosListEntry(int id, List<BlockPos> blockPosList) {
        this.id = id;
        this.blockPosList = blockPosList;
    }

    public int getId() {
        return id;
    }

    public List<BlockPos> getBlockPosList() {
        return blockPosList;
    }
}