# Don't make me think about logging

Simple logging...just use.

    Log.debug("Log this to System.out and to File if -Dlog.file=/tmp/my.log property is set.");

    try {
        throw new Exception("This is never gonna work Sponge Bob!");
    } catch(Exception ex) {
        Log.debug("A trace log statement with an Exception", ex);
    }
        
	Log.newLogger().file("/tmp/test_app.log").level("info").tags("TestApp").add();
	
	Log.info("This will now show up on all logger, including the one for test_app.log");

