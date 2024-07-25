package dontneg.noteblockcharged.block;

import dontneg.noteblockcharged.NoteBlockCharged;
import dontneg.noteblockcharged.block.cnoteblock.ChargedNoteBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class NoteBlocks {
    public static final Block CHARGED_NOTE_BLOCK = registerBlock("charged_note_block",
            new ChargedNoteBlock(AbstractBlock.Settings.copy(Blocks.NOTE_BLOCK)
                    .luminance(state -> state.get(ChargedNoteBlock.CHARGED) ? 7 : 0)));


    @SuppressWarnings("SameParameterValue")
    private static Block registerBlock(String key, Block block){
        registerBlockItem(key, block);
        return Registry.register(Registries.BLOCK, Identifier.of(NoteBlockCharged.MOD_ID, key), block);
    }

    private static void registerBlockItem(String key, Block block){
        Registry.register(Registries.ITEM, Identifier.of(NoteBlockCharged.MOD_ID, key),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerBlocks(){
        NoteBlockCharged.LOGGER.info("Note Block Charged - Block Initializing!");
    }
}
