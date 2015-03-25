package cworks.logging.loggers;

import cworks.logging.Level;
import cworks.logging.internal.format.FormatStrategy;
import cworks.logging.internal.format.SimpleFormatStrategy;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

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
    private FormatStrategy formatStrategy;
    private List<String> tags;
    private String dateTimeFormat;

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

    void setName(String name) {
        this.name = name;
    }

    void setLevel(Level level) {
        this.activeLevel = level;
    }

    public void setFormatStrategy(FormatStrategy formatStrategy) {
        this.formatStrategy = formatStrategy;
    }

    public FormatStrategy getFormatStrategy() {
        return formatStrategy;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setDateTimeFormat(String dateTimeFormat) {
        this.dateTimeFormat = dateTimeFormat;
    }
}
