package dontneg.noteblockcharged.block.cnoteblock;

import dontneg.noteblockcharged.sound.NoteSounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NoteBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ChargedNoteBlock extends NoteBlock {
    public static final BooleanProperty CHARGED = BooleanProperty.of("charged");

    public ChargedNoteBlock(Settings settings) {
        super(settings);
        this.setDefaultState(getDefaultState().with(ChargedNoteBlock.CHARGED, false));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        float[] pitches = NoteSounds.getPitches();
        if(!world.getBlockState(pos).get(CHARGED)){
            super.onUse(state,world,pos,player,hit);
            return ActionResult.SUCCESS;
        }
        state = state.cycle(NOTE);
        world.setBlockState(pos, state, Block.NOTIFY_ALL);
        world.playSound(player, pos, NoteSounds.ELECTRIC_GUITAR, SoundCategory.BLOCKS, 0.3f ,
                pitches[world.getBlockState(pos).get(NOTE)]);
        return ActionResult.SUCCESS;
    }

    @Override
    public void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        float[] pitches = NoteSounds.getPitches();
        if(!world.getBlockState(pos).get(CHARGED)){
            super.onBlockBreakStart(state, world, pos, player);
            return;
        }
        world.playSound(player, pos, NoteSounds.ELECTRIC_GUITAR, SoundCategory.BLOCKS, 0.3f ,
                pitches[world.getBlockState(pos).get(NOTE)]);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if(!world.getBlockState(pos).get(CHARGED)){
            super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
            if(world.getBlockState(pos.up()).isOf(Blocks.LIGHTNING_ROD)){
                if(world.getBlockState(pos.up()).get(POWERED)){
                    world.setBlockState(pos, state.with(ChargedNoteBlock.CHARGED, true));
                }
            }
            return;
        }
        float[] pitches = NoteSounds.getPitches();
        boolean bl = world.isReceivingRedstonePower(pos);
        if (bl != state.get(POWERED)) {
            if (bl) {
                world.playSound((Entity)null, pos, NoteSounds.ELECTRIC_GUITAR, SoundCategory.BLOCKS, 0.3f ,
                        pitches[world.getBlockState(pos).get(NOTE)]);
            }

            world.setBlockState(pos, state.with(POWERED, bl), Block.NOTIFY_ALL);
        }


    }


    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(CHARGED);
    }
}
