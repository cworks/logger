package cworks.logging.internal.format;

import cworks.logging.Level;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static java.util.stream.Collectors.joining;

/**
 * SimpleFormatStrategy is designed to be...well simple.  This is
 * the FormatStrategy you want to use for apps where performance is key.
 * However you sacrifice some information when using this strategy, because
 * its simple by design, it does not include the caller's class name, method
 * or line number.
 *
 * This format is as follows:
 * Without Tags: UTC datetime with offset | Log level | Thread | logged text
 * With Tags:    UTC datetime with offset | Log level | Thread | tag1, tag2, tag3...tagN | logged text
 * 
 * Tags can be any string and will always be rendered as comma separated values.
 */
public class SimpleFormatStrategy extends FormatStrategy {

    public SimpleFormatStrategy() {
        super(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }
    
    @Override
    public String format(Level level, String something, String...tags) {
        String formattedLine;
        
        // no tags
        if(tags.length <= 0) {
            formattedLine = String.format("%s|%s|%s|%s",
                getDateTimeFormatter().format(OffsetDateTime.now()),
                level,
                Thread.currentThread().getName(),
                something);
        } else { // tags
            String tagString = Arrays.asList(tags).stream().collect(joining(","));
            formattedLine = String.format("%s|%s|%s|%s|%s", 
                getDateTimeFormatter().format(OffsetDateTime.now()),
                level,
                Thread.currentThread().getName(),
                tagString,
                something);
        }

        return formattedLine;
    }
}
