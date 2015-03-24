package cworks.logging;

import java.time.OffsetDateTime;

public abstract class Logger {
    
    private Level activeLevel;
    
    private Logger next;
    
    public Logger(Level level) {
        this.activeLevel = level;
    }
    
    public void setNext(Logger logger) {
        this.next = logger;
    }
    
    public void write(Level level, String something) {
        if(level.getValue() <= activeLevel.getValue()) {

            StackTraceElement[] elements = Thread.currentThread().getStackTrace();
            StackTraceElement caller = elements[0];
            for(int i = 1; i < elements.length; i++) {
                if(elements[i].getClassName().equals(Log.class.getName())) {
                    continue;
                }
                caller = elements[i];
                break;
            }

            String formattedLine = String.format("%s|%s|%s|%s|%s|%s:%d%s|%s",
                    OffsetDateTime.now(),
                    level,
                    Thread.currentThread().getName(),
                    caller.getClassName(),
                    caller.getMethodName(),
                    caller.getFileName(),
                    caller.getLineNumber(),
                    tagsAsString(),
                    something);
            
            write(formattedLine);
        }
        
        if(next != null) {
            next.write(level, something);
        }
    }

    public static String tagsAsString() {
        return "";
    }

    protected abstract void write(String something);
    
}
