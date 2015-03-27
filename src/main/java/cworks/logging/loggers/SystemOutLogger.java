package cworks.logging.loggers;

import cworks.logging.Level;

public class SystemOutLogger extends Logger {

    public SystemOutLogger() {
        this(Level.DEBUG);
    }
    
    public SystemOutLogger(Level level) {
        super("system-out-logger", level);
    }

    public SystemOutLogger(String name, Level level) {
        super(name, level);
    }
    
    public SystemOutLogger(String name, Level level, String...tags) {
        super(name, level);
        setTags(tags);
    }

    @Override
    protected void write(String something) {
        System.out.println(something);
    }
}
