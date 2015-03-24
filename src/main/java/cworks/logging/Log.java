/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Baked with love by corbett
 * Project: logging
 * Package: cworks.logging
 * Class: Log
 * Created: 3/23/15 10:18 AM
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package cworks.logging;

import cworks.logging.impl.BasicLogBuilder;
import cworks.logging.io.IO;

import java.io.IOException;
import java.io.Writer;
import java.time.OffsetDateTime;

public class Log {
    private static final Writer LOG_FILE = getLogFile();
    static {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                IO.closeQuietly(LOG_FILE);
            }
        });
    }

    public static LogBuilder setup() {
        return new BasicLogBuilder();
    }
    
    public static void error(String something) {
        log("ERROR", something);
    }
    
    public static void warn(String something) {
        log("WARN", something);
    }
    
    public static void info(String something) {
        log("INFO", something);
    }
    
    public static void debug(String something) {
        log("DEBUG", something);
    }
    
    public static void log(String something) {
        log(level(), something);
    }
    
    public static String level() {
        return "INFO";
    }

    public static String tagsAsString() {
        return "";
    }
    
    static void log(String level, String something) {
        
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        StackTraceElement caller = elements[0];
        for(int i = 1; i < elements.length; i++) {
            if(elements[i].getClassName().equals(Log.class.getName())) {
                continue;
            }
            caller = elements[i];
            break;
        }
        
        String line = String.format("%s|%s|%s|%s|%s|%s:%d%s|%s",
                OffsetDateTime.now(),
                level,
                Thread.currentThread().getName(),
                caller.getClassName(),
                caller.getMethodName(),
                caller.getFileName(),
                caller.getLineNumber(),
                tagsAsString(),
                something);
        
        if(LOG_FILE != null) {
            try {
                LOG_FILE.write(line + System.getProperty("line.separator"));
            } catch(IOException ex) { }
        }
        
        System.out.println(line);
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
