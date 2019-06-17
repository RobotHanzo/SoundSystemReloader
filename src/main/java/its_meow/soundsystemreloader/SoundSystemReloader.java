package its_meow.soundsystemreloader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
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
    
    public static final Style GREEN_STYLE = new Style().setColor(TextFormatting.GREEN);
    public static final Style RED_STYLE = new Style().setColor(TextFormatting.RED);

    @SubscribeEvent
    public static void keyPress(KeyInputEvent event) {
        if(Keyboard.isKeyDown(Keyboard.KEY_F3) && Keyboard.isKeyDown(Keyboard.KEY_S)) {
            if(Minecraft.getMinecraft().getSoundHandler().sndManager.loaded) {
                LOGGER.info("Captured F3+S: Reloading Sound System...");
                Minecraft.getMinecraft().player.sendStatusMessage(new TextComponentString("Reloading sound system").setStyle(GREEN_STYLE), true);
                Minecraft.getMinecraft().getSoundHandler().sndManager.reloadSoundSystem();
            } else {
                Minecraft.getMinecraft().player.sendStatusMessage(new TextComponentString("Unable to reload sound system, it is not loaded").setStyle(RED_STYLE), true);
            }
            // Reset F3 menu state
            Minecraft.getMinecraft().gameSettings.showDebugInfo = !Minecraft.getMinecraft().gameSettings.showDebugInfo;
            Minecraft.getMinecraft().gameSettings.showDebugProfilerChart = Minecraft.getMinecraft().gameSettings.showDebugInfo && GuiScreen.isShiftKeyDown();
            Minecraft.getMinecraft().gameSettings.showLagometer = Minecraft.getMinecraft().gameSettings.showDebugInfo && GuiScreen.isAltKeyDown();
        }
    }

}
