package cworks.logging.internal.caller;

public class ReflectionCallerLogic implements CallerLogic {

    @Override
    public Caller getCaller(int stackDepth) {

        final String name = sun.reflect.Reflection.getCallerClass(stackDepth).getName();

        return new Caller() {
            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getMethodName() {
                return null;
            }

            @Override
            public int getLineNumber() {
                return 0;
            }
        };
    }
}
