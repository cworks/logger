/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Baked with love by corbett
 * Project: logging
 * Package: cworks.logging
 * Class: C
 * Created: 3/23/15 10:18 AM
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package cworks.domain;

import cworks.logging.Level;
import cworks.logging.Log;

public class C {
    public void doC() {
        Log.log("Log something from C.");
    }

    public void doC(Level level, String text) {
        Log.log(level, text);
    }
}
