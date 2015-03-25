package cworks.logging;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class LogBuilder {
    
    private String name;
    private Level  level;
    private String format;
    private List<String> tags;
    private File file;
    
    public LogBuilder() {

    }
    
    public LogBuilder level(String level) {
        LogContext.getContext().level(Level.valueOf(level.trim().toUpperCase()));
        return this;
    }

    public LogBuilder dtFormat(String format) {
        LogContext.getContext().dtFormat(format);
        return this;
    }
    
    public LogBuilder tags(String... tags) {
        LogContext.getContext().tags(tags);
        return this;
    }

    public LogBuilder file(String file) throws IOException {
        LogContext.getContext().file(file);
        return this;
    }

    /**
     * Create a Logger, each Logger must have
     * 1. Level
     * 2. FormatStrategy
     * 3.
     */
    public void create() {
        

        
    }
}
