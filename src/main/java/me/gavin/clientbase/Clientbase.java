package me.gavin.clientbase;

import me.gavin.clientbase.module.api.Module;
import me.gavin.clientbase.module.api.ModuleManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

@Mod(
        modid = Clientbase.MOD_ID,
        name = Clientbase.MOD_NAME,
        version = Clientbase.VERSION
)
public class Clientbase {

    public static final String MOD_ID = "clientbase";
    public static final String MOD_NAME = "ClientBase";
    public static final String VERSION = "1.0";

    @Mod.Instance(MOD_ID)
    public static Clientbase INSTANCE;

    private ModuleManager moduleManager;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        this.moduleManager = new ModuleManager();

        MinecraftForge.EVENT_BUS.register(this);
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    // listen for key presses, for toggling modules
    @SubscribeEvent
    public void onEvent(InputEvent.KeyInputEvent event) {
        if (Keyboard.getEventKeyState()) {
            final int key = Keyboard.getEventKey();

            if (key != Keyboard.KEY_NONE) {
                for (Module module : moduleManager.getModules()) {
                    if (module.getBind() == key) {
                        module.toggle();
                    }
                }
            }
        }
    }
}
