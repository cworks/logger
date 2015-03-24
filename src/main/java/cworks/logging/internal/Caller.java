package cworks.logging.internal;

public interface Caller {
    public String getName();
    public String getMethodName();
    public int getLineNumber();
}
