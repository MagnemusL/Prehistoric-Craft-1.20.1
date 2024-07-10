package net.smazeee.prehistoriccraft.item.custom.satchets;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.fml.common.Mod;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.block.ModBlocks;
import net.smazeee.prehistoriccraft.plants.FieldHorsetail;

import java.util.Objects;

import static net.smazeee.prehistoriccraft.plants.FieldHorsetail.TYPE;

public class FieldHorsetailSatchet extends Item {
    public FieldHorsetailSatchet(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Player player = pContext.getPlayer();
        BlockPos pos = pContext.getClickedPos();
        Level level = pContext.getLevel();
        RandomSource ran = player.getRandom();
        int type = ran.nextInt(0, 4);
        SoundType soundtype = level.getBlockState(pos).getSoundType(level, pos, pContext.getPlayer());
        level.setBlockAndUpdate(pos.above(), ModBlocks.FIELD_HORSETAIL.get().defaultBlockState().setValue(TYPE, type));
        level.playSound(player, pos, SoundEvents.GRASS_PLACE, SoundSource.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
        return InteractionResult.SUCCESS;
    }
}