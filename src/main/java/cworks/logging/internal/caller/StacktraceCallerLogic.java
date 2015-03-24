package cworks.logging.internal.caller;

import cworks.logging.Log;

public class StacktraceCallerLogic implements CallerLogic {
    @Override
    public Caller getCaller(int stackDepth) {

        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        StackTraceElement caller = elements[0];
        for(int i = stackDepth; i < elements.length; i++) {
            if(elements[i].getClassName().equals(Log.class.getName())) {
                continue;
            }
            caller = elements[i];
            break;
        }

        final StackTraceElement finalCaller = caller;
        return new Caller() {
            @Override
            public String getName() {
                return finalCaller.getClassName();
            }

            @Override
            public String getMethodName() {
                return finalCaller.getMethodName();
            }

            @Override
            public int getLineNumber() {
                return finalCaller.getLineNumber();
            }
        };
    }
}
