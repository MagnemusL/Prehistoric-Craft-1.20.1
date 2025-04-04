package net.seentro.prehistoriccraft.item.custom;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.gameevent.GameEvent;
import net.seentro.prehistoriccraft.block.ModBlocks;

public class FieldHorsetailBlockItem extends BlockItem {
    public FieldHorsetailBlockItem(Properties pProperties) {
        super(ModBlocks.FIELD_HORSETAIL.get(), pProperties);
    }

    @Override
    public InteractionResult place(BlockPlaceContext pContext) {
        super.place(pContext);
        Player player = pContext.getPlayer();
        Level level = pContext.getLevel();
        BlockPos pos = pContext.getClickedPos();
        SoundType soundtype = level.getBlockState(pos).getSoundType(level, pos, player);
        level.playSound(player, pos, SoundEvents.GRASS_PLACE, SoundSource.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
        level.gameEvent(GameEvent.BLOCK_PLACE, pos, GameEvent.Context.of(player, level.getBlockState(pos)));
        if(player instanceof ServerPlayer) {
            CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)player, pos, new ItemStack(this));
        }
        return InteractionResult.SUCCESS;
    }
}
