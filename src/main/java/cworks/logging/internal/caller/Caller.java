package cworks.logging.internal.caller;

public interface Caller {
    public String getName();
    public String getMethodName();
    public int getLineNumber();
}
