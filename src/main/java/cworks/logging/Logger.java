package cworks.logging;

import cworks.logging.internal.format.FormatStrategy;
import cworks.logging.internal.format.SimpleFormatStrategy;

import java.io.Closeable;
import java.io.IOException;

public abstract class Logger implements Closeable {

    /**
     * Line FormatStrategy 
     */
    protected FormatStrategy strategy;

    /**
     * Log Level 
     */
    protected Level activeLevel;

    /**
     * Logger name
     */
    protected String name;
    
    public Logger(String name, Level level) {
        this(name, level, new SimpleFormatStrategy());
    }
    
    public Logger(String name, Level level, FormatStrategy strategy) {
        this.name = name;
        this.activeLevel = level;
        this.strategy = strategy;
    }

    public void write(Level level, String something, String...tags) {
        if(level.getValue() <= activeLevel.getValue()) {
            String formattedLine = strategy.format(level, something, tags);
            write(formattedLine);
        }
    }

    protected abstract void write(String something);

    @Override
    public void close() throws IOException { }
}
