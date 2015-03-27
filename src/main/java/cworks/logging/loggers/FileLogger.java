package cworks.logging.loggers;

import cworks.logging.Level;
import cworks.logging.io.IO;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

public class FileLogger extends Logger {

    private Writer logWriter = null;
    private File   logFile   = null;

    public FileLogger(File logFile) {
        this(Level.DEBUG, logFile);
    }
    
    /**
     * Create a FileLogger at a certain log Level and to a certain File 
     * @param level
     * @param logFile
     */
    public FileLogger(Level level, File logFile) {
        super("file-logger", level);
        this.logFile = logFile;
    }

    public FileLogger(String name, Level level, File logFile) {
        super(name, level);
        this.logFile = logFile;
    }

    public FileLogger(String name, Level level, File logFile, String...tags) {
        this(name, level, logFile);
        setTags(tags);
    }

        @Override
    protected void write(String something) {
        if(getLogWriter() != null) {
            writeQuietly(something);
        }
    }

    private void writeQuietly(String something) {
        try {
            logWriter.write(something + System.getProperty("line.separator"));
            logWriter.flush();
        } catch (IOException ex) {
            System.err.println("IOException in FileLogger.writeQuietly(), text was: " + something);
        }
    }
    
    private Writer getLogWriter() {
        try {
            // this code has ran before so return log writer
            if(this.logWriter != null) {
                return this.logWriter;
            }
            // first time running
            if(this.logFile != null) {
                if(!this.logFile.exists()) {
                    this.logFile.getParentFile().mkdirs();
                    this.logFile.createNewFile();
                }
                this.logWriter = IO.asWriter(this.logFile, true);
                if(this.logWriter != null) {
                    final FileLogger me = this;
                    Runtime.getRuntime().addShutdownHook(new Thread() {
                        public void run() {
                            IO.closeQuietly(me);
                        }
                    });
                }
            }
        } catch (IOException ex) {
            return null;
        }
        return this.logWriter;
    }

    @Override
    public void close() throws IOException {
        if(this.logWriter != null) {
            this.logWriter.flush();
            this.logWriter.close();
        }
    }
}
