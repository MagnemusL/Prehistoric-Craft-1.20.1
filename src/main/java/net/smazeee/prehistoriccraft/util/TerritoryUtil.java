package net.smazeee.prehistoriccraft.util;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TerritoryUtil extends SavedData {
    private static final String DATA_NAME = "prehistoriccraft_territory_data";

    private static List<BlockPosListEntry> entries = new ArrayList<>();

    public TerritoryUtil() {
    }

    public TerritoryUtil(List<BlockPosListEntry> entries) {
        TerritoryUtil.entries = entries;
    }

    @Override
    public CompoundTag save(CompoundTag compound) {
        ListTag entriesTag = new ListTag();
        for (BlockPosListEntry entry : entries) {
            CompoundTag entryTag = new CompoundTag();
            entryTag.putInt("id", entry.getId());

            ListTag blockPosListTag = new ListTag();
            for (BlockPos blockPos : entry.getBlockPosList()) {
                blockPosListTag.add(BlockPos.CODEC.encodeStart(NbtOps.INSTANCE, blockPos).result().orElseThrow());
            }
            entryTag.put("blockPosList", blockPosListTag);

            entriesTag.add(entryTag);
        }
        compound.put("entries", entriesTag);

        return compound;
    }

    public static TerritoryUtil load(CompoundTag compound) {
        List<BlockPosListEntry> entries = new ArrayList<>();

        ListTag entriesTag = compound.getList("entries", Tag.TAG_COMPOUND);
        for (Tag entryTag : entriesTag) {
            CompoundTag compoundEntryTag = (CompoundTag) entryTag;
            int id = compoundEntryTag.getInt("id");

            List<BlockPos> blockPosList = new ArrayList<>();
            ListTag blockPosListTag = compoundEntryTag.getList("blockPosList", Tag.TAG_COMPOUND);
            for (Tag blockPosTag : blockPosListTag) {
                blockPosList.add(BlockPos.CODEC.parse(NbtOps.INSTANCE, blockPosTag).result().orElseThrow());
            }

            entries.add(new BlockPosListEntry(id, blockPosList));
        }

        return new TerritoryUtil(entries);
    }

    public static TerritoryUtil get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(TerritoryUtil::load, TerritoryUtil::new, DATA_NAME);
    }

    public List<BlockPosListEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<BlockPosListEntry> entries) {
        TerritoryUtil.entries = entries;
        setDirty();
    }

    public static int getNextId() {
        return entries.size() + 1;
    }

    public void addBlockPosListEntry(int id, List<BlockPos> blockPosList) {
        entries.add(new BlockPosListEntry(id, blockPosList));
        setDirty();
    }

    public void removeBlockPosListEntryById(int id) {
        entries.removeIf(entry -> entry.getId() == id);
        setDirty();
    }

    public void addBlockPosToEntry(int id, BlockPos blockPos) {
        Optional<BlockPosListEntry> optionalEntry = entries.stream()
                .filter(entry -> entry.getId() == id)
                .findFirst();
        if (optionalEntry.isPresent()) {
            BlockPosListEntry entry = optionalEntry.get();
            entry.getBlockPosList().add(blockPos);
            setDirty();
        }
    }

    public void addBlockPosListToEntry(int id, List<BlockPos> blockPosList) {
        Optional<BlockPosListEntry> optionalEntry = entries.stream()
                .filter(entry -> entry.getId() == id)
                .findFirst();
        if (optionalEntry.isPresent()) {
            BlockPosListEntry entry = optionalEntry.get();
            entry.getBlockPosList().addAll(blockPosList);
            setDirty();
        }
    }
}
