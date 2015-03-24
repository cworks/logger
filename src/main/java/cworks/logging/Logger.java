package cworks.logging;

import cworks.logging.internal.format.FormatStrategy;
import cworks.logging.internal.format.SimpleFormatStrategy;

public abstract class Logger {

    private FormatStrategy strategy;
    
    private Level activeLevel;
    
    private Logger next;
    
    public Logger(Level level) {
        this(level, new SimpleFormatStrategy());
    }
    
    public Logger(Level level, FormatStrategy strategy) {
        this.activeLevel = level;
        this.strategy = strategy;
    }
    
    public void setNext(Logger logger) {
        this.next = logger;
    }
    
    public void write(Level level, String something, String...tags) {
        if(level.getValue() <= activeLevel.getValue()) {
            String formattedLine = strategy.format(level, something, tags);
            write(formattedLine);
        }
        
        if(next != null) {
            next.write(level, something, tags);
        }
    }

    protected abstract void write(String something);
    
    
}
