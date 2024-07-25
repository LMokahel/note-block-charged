package dontneg.noteblockcharged.creativetab;

import dontneg.noteblockcharged.NoteBlockCharged;
import dontneg.noteblockcharged.block.NoteBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class NoteBlockCreativeClass {

    @SuppressWarnings("unused")
    public static final ItemGroup NOTEBLOCK_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(NoteBlockCharged.MOD_ID, "note_block"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.noteblock"))
                    .icon(() -> new ItemStack(NoteBlocks.CHARGED_NOTE_BLOCK)).entries((displayContext, entries) ->
                            entries.add(NoteBlocks.CHARGED_NOTE_BLOCK)).build());


    public static void registerCreativeTabs() {
        NoteBlockCharged.LOGGER.info("Note Block Charged - Creative Tab Initializing!");
    }
}
