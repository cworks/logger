package cworks.logging;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Level {
    ERROR(10),
    WARN(20),
    INFO(30),
    DEBUG(40);

    private static final Map<Integer,Level> lookup = new HashMap<Integer,Level>();

    static {
        for(Level level : EnumSet.allOf(Level.class)) {
            lookup.put(level.getValue(), level);
        }
    }

    private int level;

    private Level(int level) {
        this.level = level;
    }

    public int getValue() { return level; }

    public static Level fromValue(int value) {
        return lookup.get(value);
    }
}
