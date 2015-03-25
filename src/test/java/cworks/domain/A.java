/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Baked with love by corbett
 * Project: logging
 * Package: cworks.logging
 * Class: A
 * Created: 3/23/15 10:17 AM
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package cworks.domain;

import cworks.logging.Level;
import cworks.logging.Log;

public class A {
    public void doA() {
        Log.log("Log something from A and include some tags.", "Java", "Logging", "Test");
        AA aa = new AA();
        aa.doAA();
    }
    public void doA(Level level, String text) {
        Log.log(level, text);
    }
}
