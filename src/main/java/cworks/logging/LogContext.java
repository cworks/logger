package cworks.logging;

import cworks.logging.loggers.FileLogger;
import cworks.logging.loggers.Logger;
import cworks.logging.loggers.SystemOutLogger;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public final class LogContext {

    /**
     * The one and only logging context
     */
    private static final LogContext INSTANCE = new LogContext();
    
    /**
     * If we have file loggers they are captured here so that we can close them when JVM exits 
     */
    final private List<Logger> chain;

    /**
     * If the log system property is set (-Dlog=/tmp/my.log) then this will be true
     */
    private boolean toFile;

    /**
     * The default logging level
     */
    private Level logLevel;

    /**
     * The default dateTime format
     */
    private String format;

    /**
     * Tags to use on every log statement 
     */
    private String[] tags;
    
    /**
     * Private construction because LogContext is a singleton
     */
    private LogContext() {
        this.logLevel = Level.DEBUG;
        this.format = "YYYY-MM-DDThh:mm:ss.sssTZD";
        this.toFile = System.getProperty("log") != null;
        this.chain = createChain();
    }

    public static LogContext getContext() {
        return INSTANCE;
    }
    
    /**
     * A slightly important factory method which is intended to always return
     * at least one functional Logger but could return more than one based
     * on user configuration.  Each logger is created to log at or above
     * a certain level and with a specific line format.
     *
     * We should follow the pattern of getting properties from the system first
     * so that they can override environmental properties.  If system properties
     * don't exist then look for environmental properties.  If environmental
     * properties are absent then use sensible defaults.
     *
     * @return top of the Logger chain
     */
    List<Logger> createChain() {
        LinkedList<Logger> chain = new LinkedList<>();
        // always add a System.out logger
        chain.add(new SystemOutLogger(logLevel));
        // if log to file is set then add a FileLogger
        if(toFile) {
            chain.add(new FileLogger(logLevel, new File(System.getProperty("log"))));
        }

        return chain;
    }

    /**
     * Changes log level for all Loggers in the chain 
     * @param level
     */
    void level(Level level) {

        this.logLevel = level;
    }

    void dtFormat(String format) {
        this.format = format;
    }

    void tags(String[] tags) {
        this.tags = tags;
    }

    /**
     * User has specified a dedicated file to use in code, so this one shall
     * override the existing one 
     * @param file
     */
    void file(String file) {
        toFile = true;
    }

    public Level level() {
        return this.logLevel;
    }

    public String dtFormat() {
        return this.format;
    }

    public String[] tags() {
        return this.tags;
    }
    
    public List<Logger> chain() {
        return this.chain;
    }

    public void addLogger(Logger logger) {
        chain.add(logger);
    }
}
