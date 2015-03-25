package cworks.logging;

import cworks.logging.internal.format.FormatStrategy;
import cworks.logging.internal.format.SimpleFormatStrategy;

import java.io.Closeable;
import java.io.IOException;
import java.util.Iterator;

public abstract class Logger implements Closeable {

    private FormatStrategy strategy;
    
    private Level activeLevel;

    public Logger(Level level) {
        this(level, new SimpleFormatStrategy());
    }
    
    public Logger(Level level, FormatStrategy strategy) {
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
    public void close() throws IOException {

    }
}
