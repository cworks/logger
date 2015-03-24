package cworks.logging.loggers;

import cworks.logging.LogBuilder;
import cworks.logging.io.IO;

import java.io.IOException;
import java.io.Writer;

public class BasicLogBuilder implements LogBuilder {
    
    private String level;
    private String dtFormat;
    private String[] tags;
    private String file;
    
    @Override
    public LogBuilder level(String level) {
        return null;
    }

    @Override
    public LogBuilder dtFormat(String format) {
        return null;
    }

    @Override
    public LogBuilder tags(String... tags) {
        return null;
    }

    @Override
    public LogBuilder file(String file) throws IOException {
        Writer logFile = IO.asWriter(file, true);


        return null;
    }
}
