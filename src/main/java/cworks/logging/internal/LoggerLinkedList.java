package cworks.logging.internal;

import cworks.logging.loggers.Logger;

import java.util.LinkedList;

public class LoggerLinkedList extends LinkedList<Logger> {
    
    /**
     * Capacity of this list 
     */
    private int capacity;

    /**
     * Create a list of a specific capacity
     * @param capacity
     */
    public LoggerLinkedList(int capacity) {
        this.capacity = (capacity < 1) ? 100 : capacity;
    }

    /**
     * add a Logger into this list
     * @param logger
     * @return
     */
    public boolean add(Logger logger) {
        if(size() > capacity) {
            return false;
        }
        return super.add(logger);
    }
    
}
