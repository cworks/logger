package cworks.logging.loggers;

import cworks.logging.Level;

public class SystemOutLogger extends Logger {

    public SystemOutLogger() {
        this(Level.DEBUG);
    }
    
    public SystemOutLogger(Level level) {
        super("system-out-logger", level);
    }

    @Override
    protected void write(String something) {
        System.out.println(something);
    }
}
