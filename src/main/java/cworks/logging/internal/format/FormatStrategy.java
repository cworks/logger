package cworks.logging.internal.format;

import cworks.logging.Level;

public interface FormatStrategy {
    String format(Level level, String something, String...tags);
}
