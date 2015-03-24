package cworks.logging;

import java.io.IOException;

public interface LogBuilder {
    LogBuilder level(String level);
    LogBuilder dtFormat(String format);
    LogBuilder tags(String...tags);
    LogBuilder file(String s) throws IOException;
}
