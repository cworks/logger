package cworks.logging.internal.format;

import cworks.logging.Level;

import java.time.format.DateTimeFormatter;

public abstract class FormatStrategy {

    private DateTimeFormatter formatter;

    public abstract String format(Level level, String something, String...tags);
    public abstract String format(Level level, String something, Throwable throwable, String...tags);

    protected FormatStrategy(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }
    
    protected DateTimeFormatter getDateTimeFormatter() {
        return formatter;
    }

}