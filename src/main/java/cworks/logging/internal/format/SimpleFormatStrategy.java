package cworks.logging.internal.format;

import cworks.logging.Level;

import java.time.OffsetDateTime;
import java.util.Arrays;

import static java.util.stream.Collectors.joining;

public class SimpleFormatStrategy implements FormatStrategy {

    @Override
    public String format(Level level, String something, String...tags) {
        String tagString = null;
        if(tags.length > 0) {
            tagString = Arrays.asList(tags).stream().collect(joining(", "));
        }
        
        String formattedLine;
        if(tagString == null) {
            formattedLine = String.format("%s|%s|%s|%s",
                OffsetDateTime.now(),
                level,
                Thread.currentThread().getName(),
                something);
        } else {
            formattedLine = String.format("%s|%s|%s|%s|%s",
                OffsetDateTime.now(),
                level,
                Thread.currentThread().getName(),
                tagString,
                something);
        }

        return formattedLine;
    }
}
