/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Baked with love by corbett
 * Project: logging
 * Package: cworks.logging
 * Class: AAA
 * Created: 3/23/15 11:43 AM
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package cworks.domain;

import cworks.logging.Level;
import cworks.logging.Log;

public class AAA {
    public void doAAA() {
        Log.debug("Log something from AAA.");
    }

    public void doAAA(Level level, String text) {
        Log.log(level, text);
    }
}
