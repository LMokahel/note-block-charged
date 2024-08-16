package dontneg.noteblockcharged.mixin;

import dontneg.noteblockcharged.block.cnoteblock.ChargedNoteBlock;
import dontneg.noteblockcharged.block.NoteBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LightningEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("unused")
@Mixin(LightningEntity.class)
public class ChargedNoteBlockMixin {

    @Inject(method = "cleanOxidation", at = @At(value = "HEAD"))
    private static void injectCleanOxidation(World world, BlockPos pos, CallbackInfo ci){
        BlockState state = world.getBlockState(pos);
        if (state.isOf(NoteBlocks.CHARGED_NOTE_BLOCK)) {
            world.setBlockState(pos, state.with(ChargedNoteBlock.CHARGED, true));
        }
    }
}
