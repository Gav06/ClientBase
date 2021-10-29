package me.gavin.clientbase.module.api;

import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.input.Keyboard;

public abstract class Module {

    private final String name;
    private final String description;
    private final Category category;

    private int bind;
    private boolean enabled;

    public Module(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.bind = Keyboard.KEY_NONE;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        if (enabled) {
            enable();
        } else {
            disable();
        }
    }

    public void toggle() {
        if (enabled) {
            disable();
        } else {
            enable();
        }
    }

    public void enable() {
        if (!enabled) {
            enabled = true;
            MinecraftForge.EVENT_BUS.register(this);
            onEnable();
        }
    }

    public void disable() {
        if (enabled) {
            enabled = false;
            MinecraftForge.EVENT_BUS.unregister(this);
            onDisable();
        }
    }

    protected void onEnable() { }

    protected void onDisable() { }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public int getBind() {
        return bind;
    }

    public void setBind(int bind) {
        this.bind = bind;
    }

    public enum Category {
        Combat,
        Movement,
        Render,
        World,
        Other,
        Client
    }
}
