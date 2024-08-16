package dontneg.noteblockcharged;

import net.fabricmc.api.ClientModInitializer;

@SuppressWarnings("unused")
public class NoteBlockChargedClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        NoteBlockCharged.LOGGER.info("Note Block Charged - Client Initializing!");    }
}
