package its_meow.soundsystemreloader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = SoundSystemReloader.MOD_ID, name = SoundSystemReloader.NAME, version = SoundSystemReloader.VERSION, acceptedMinecraftVersions = SoundSystemReloader.acceptedMCV, clientSideOnly = true)
@Mod.EventBusSubscriber(modid = SoundSystemReloader.MOD_ID, value = Side.CLIENT)
public class SoundSystemReloader {

    public static final String MOD_ID = "soundsystemreloader";
    public static final String NAME = "Sound System Reloader";
    public static final String VERSION = "@VERSION@";
    public static final String acceptedMCV = "[1.12,1.12.2]";

    public static final Logger LOGGER = LogManager.getLogger(SoundSystemReloader.MOD_ID);

    @SubscribeEvent
    public static void keyPress(KeyInputEvent event) {
        if(Keyboard.isKeyDown(Keyboard.KEY_F3) && Keyboard.isKeyDown(Keyboard.KEY_S)) {
            LOGGER.info("Captured F3+S: Reloading Sound System...");
            Minecraft.getMinecraft().getSoundHandler().sndManager.reloadSoundSystem();
        }
    }

}
