package cworks.logging.loggers;

import cworks.logging.Level;
import cworks.logging.LogContext;
import cworks.logging.internal.format.FormatStrategy;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LoggerBuilder {
    
    private String name;
    private Level  level;
    private String dateTimeFormat;
    private List<String> tags;
    private File file;
    private FormatStrategy formatStrategy;
    
    public LoggerBuilder() {

    }
    
    public LoggerBuilder name(String name) {
        this.name = name;
        return this;
    }
    
    public LoggerBuilder level(String level) {
        this.level = Level.valueOf(level);
        return this;
    }

    public LoggerBuilder dateTimeFormat(String format) {
        this.dateTimeFormat = format;
        return this;
    }
    
    public LoggerBuilder tags(String... tags) {
        this.tags = Arrays.asList(tags);
        return this;
    }

    public LoggerBuilder file(String file) throws IOException {
        this.file = new File(file);
        return this;
    }
    
    public LoggerBuilder formatStrategy(FormatStrategy strategy) {
        this.formatStrategy = strategy;
        return this;
    }

    public void create() {
        Logger logger;
        
        if(file != null) {
            logger = new FileLogger(file);
        } else {
            logger = new SystemOutLogger();
        }
        
        if(name != null) {
            logger.setName(name);
        }
        
        if(level != null) {
            logger.setLevel(level);
        }
        
        if(formatStrategy != null) {
            logger.setFormatStrategy(formatStrategy);
        }
        
        if(tags != null) {
            logger.setTags(tags);
        }
        
        if(dateTimeFormat != null) {
            logger.setDateTimeFormat(dateTimeFormat);
        }

        LogContext.getContext().addLogger(logger);
    }
}
