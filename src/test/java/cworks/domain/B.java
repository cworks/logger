/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Baked with love by corbett
 * Project: logging
 * Package: cworks.logging
 * Class: B
 * Created: 3/23/15 10:17 AM
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package cworks.domain;

import cworks.logging.Level;
import cworks.logging.Log;

public class B {
    public void doB() {
        Log.log("Log something from B.");
    }

    public void doB(Level level, String text) {
        Log.log(level, text);
    }
}
