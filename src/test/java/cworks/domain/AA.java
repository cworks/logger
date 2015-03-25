/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Baked with love by corbett
 * Project: logging
 * Package: cworks.logging
 * Class: AA
 * Created: 3/23/15 11:42 AM
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package cworks.domain;

import cworks.logging.Level;
import cworks.logging.Log;

public class AA {
    public void doAA() {
        Log.log("Log something from AA.");
        AAA aaa = new AAA();
        aaa.doAAA();
    }

    public void doAA(Level level, String text) {
        Log.log(level, text);
    }
}
