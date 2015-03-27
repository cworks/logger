package cworks.logging.loggers;

import cworks.logging.Level;
import cworks.logging.internal.format.FormatStrategy;
import cworks.logging.internal.format.SimpleFormatStrategy;

import java.io.Closeable;
import java.io.IOException;

public abstract class Logger implements Closeable {

    /**
     * Log Level 
     */
    protected Level activeLevel;

    /**
     * Logger name
     */
    protected String name;

    /**
     * Line format strategy 
     */
    private FormatStrategy formatStrategy;

    /**
     * Slugs, Tags and Bears Oh my 
     */
    private String[] tags;

    public Logger(String name, Level level) {
        this(name, level, new SimpleFormatStrategy());
    }
    
    public Logger(String name, Level level, FormatStrategy strategy) {
        this.name = name;
        this.activeLevel = level;
        this.formatStrategy = strategy;
    }

    public void write(Level level, String something, String...tags) {
        write(level, something, null, tags);
    }

    public void write(Level level, String something, Throwable throwable) {
        write(level, something, throwable, new String[]{});
    }

    public void write(Level level, String something, Throwable throwable, String...tags) {
        if(level.getValue() <= activeLevel.getValue()) {
            String[] _tags = createTags(tags);
            String formattedLine;
            if(throwable != null) {
                formattedLine = formatStrategy.format(level, something, throwable, _tags);
            } else {
                formattedLine = formatStrategy.format(level, something, _tags);
            }
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

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    private String[] createTags(String...tags) {
        String[] _tags;
        if(this.tags != null && tags != null) {
            _tags = concatArray(this.tags, tags);
        } else if(this.tags != null) {
            _tags = this.tags;
        } else if(tags != null) {
            _tags = tags;
        } else {
            _tags = new String[]{};
        }
        return _tags;
    }
    
    private String[] concatArray(String[] a, String[] b) {
        int aLen = a.length;
        int bLen = b.length;
        String[] c = new String[aLen + bLen];
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        return c;
    }



}
