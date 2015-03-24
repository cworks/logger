package cworks.logging.loggers;

import cworks.logging.Level;
import cworks.logging.Logger;
import cworks.logging.io.IO;

import java.io.IOException;
import java.io.Writer;

public class FileLogger extends Logger {

    private static final Writer LOG_FILE = getLogFile();

    static {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                IO.closeQuietly(LOG_FILE);
            }
        });
    }
    
    public FileLogger(Level level) {
        super(level);
    }

    @Override
    protected void write(String something) {
        if(LOG_FILE != null) {
            writeQuietly(something);
        }
    }

    private void writeQuietly(String something) {
        try {
            LOG_FILE.write(something + System.getProperty("line.separator"));
        } catch (IOException ex) {
            System.err.println("IOException in FileLogger.writeQuietly(), text was: " + something);
        }
    }
    
    private static Writer getLogFile() {
        Writer logFile = null;
        try {
            String logProperty = System.getProperty("log");
            if(logProperty != null) {
                logFile = IO.asWriter(System.getProperty("log"), true);
            }
        } catch (IOException ex) {
            return null;
        }
        return logFile;
    }
}
