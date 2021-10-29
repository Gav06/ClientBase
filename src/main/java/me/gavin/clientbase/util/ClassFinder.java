package me.gavin.clientbase.util;

import com.google.common.reflect.ClassPath;
import net.minecraft.launchwrapper.Launch;

import java.util.HashSet;
import java.util.Set;

public class ClassFinder {

    @SuppressWarnings("UnstableApiUsage")
    public static Set<Class<?>> findClassesInPackage(String pkg) {
        try {
            final Set<Class<?>> set = new HashSet<>();
            for (ClassPath.ClassInfo classInfo : ClassPath.from(Launch.classLoader).getAllClasses()) {
                if (classInfo.getName().startsWith(pkg)) {
                    set.add(classInfo.load());
                }
            }
            return set;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
