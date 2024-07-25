package dontneg.noteblockcharged.sound;

import dontneg.noteblockcharged.NoteBlockCharged;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class NoteSounds{
    static float[] pitches = new float[25];

    public static final SoundEvent ELECTRIC_GUITAR = registerSoundEvent("electric_guitar");

    @SuppressWarnings("SameParameterValue")
    private static SoundEvent registerSoundEvent(String name){
        Identifier id = Identifier.of(NoteBlockCharged.MOD_ID,name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds(){
        for(int i = 0;i<25;i++){
            pitches[i] = (float) Math.pow(2, (double) (i - 12) /12);
        }
        NoteBlockCharged.LOGGER.info("Note Block Charged - Sound Initializing!");
    }

    public static float[] getPitches(){
        return pitches;
    }
}
