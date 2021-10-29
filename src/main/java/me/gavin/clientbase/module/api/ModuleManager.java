package me.gavin.clientbase.module.api;

import me.gavin.clientbase.util.ClassFinder;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ModuleManager {

    private final List<Module> modules;
    private final HashMap<Class<? extends Module>, Module> moduleMap;

    public ModuleManager() {
        this.moduleMap = new HashMap<>();
        this.modules = new ArrayList<>();

        // finding module classes
        Set<Class<?>> classes = ClassFinder.findClassesInPackage("me.gavin.clientbase.module.mods");

        if (classes == null) {
            throw new RuntimeException("Failed to load modules! Is the package being searched correct?");
        }
        for (Class<?> clazz : classes) {
            if (Module.class.isAssignableFrom(clazz) && !Modifier.isAbstract(clazz.getModifiers())) {
                for (Constructor<?> constructor : clazz.getConstructors()) {
                    if (constructor.getParameterCount() == 0) {
                        try {
                            addModule((Module) constructor.newInstance());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private void addModule(Module module) {
        this.moduleMap.put(module.getClass(), module);
        this.modules.add(module);
    }

    public List<Module> getModules() {
        return modules;
    }

    @SuppressWarnings("unchecked")
    public <T extends Module> T getModule(Class<T> clazz) {
        return (T) moduleMap.get(clazz);
    }
}