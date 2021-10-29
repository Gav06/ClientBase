package me.gavin.clientbase.module.mods;

import me.gavin.clientbase.module.api.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ExampleModule extends Module {

    public ExampleModule() {
        super("ExampleModuleName", "Example description", Category.Other);
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        // listen for forge events
    }

    @Override
    protected void onEnable() {
        System.out.println("module enabled");
    }

    @Override
    protected void onDisable() {
        System.out.println("module disabled");
    }
}
