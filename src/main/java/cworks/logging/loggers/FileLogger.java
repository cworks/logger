package cworks.logging.loggers;

import cworks.logging.Level;
import cworks.logging.Logger;
import cworks.logging.io.IO;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

public class FileLogger extends Logger {

    //private static final Writer LOG_FILE = getLogFile();
    private Writer logWriter = null;
    private File   logFile   = null;

    /**
     * Create a FileLogger at a certain log Level and to a certain File 
     * @param level
     * @param logFile
     */
    public FileLogger(Level level, File logFile) {
        super("file-logger", level);
        this.logFile = logFile;
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
                this.logWriter = IO.asWriter(this.logFile, true);
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
