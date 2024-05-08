package net.smazeee.prehistoriccraft.block.custom.cables;

import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.smazeee.prehistoriccraft.block.entity.FacadeBlockEntity;
import org.jetbrains.annotations.Nullable;

public class FacadeBlockColor implements BlockColor {
    @Override
    public int getColor(BlockState state, @Nullable BlockAndTintGetter level, @Nullable BlockPos pos, int tint) {
        if (level != null) {
            BlockEntity te = level.getBlockEntity(pos);
            if (te instanceof FacadeBlockEntity facade) {
                BlockState mimic = facade.getMimicBlock();
                if (mimic != null) {
                    return Minecraft.getInstance().getBlockColors().getColor(mimic, level, pos, tint);
                }
            }
        }
        return -1;
    }
}