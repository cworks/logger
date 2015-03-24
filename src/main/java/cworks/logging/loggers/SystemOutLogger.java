package cworks.logging.loggers;

import cworks.logging.Level;
import cworks.logging.Logger;

public class SystemOutLogger extends Logger {

    public SystemOutLogger(Level level) {
        super(level);
    }

    @Override
    protected void write(String something) {
        System.out.println(something);
    }
}
