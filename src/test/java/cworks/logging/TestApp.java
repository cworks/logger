package cworks.logging;

import cworks.domain.*;
import cworks.logging.io.IO;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class TestApp {
	
	private static final String SLASH = File.separator;
	
	private static final String TMP_DIR = System.getProperty("java.io.tmpdir");
	
	@Test
	public void testBasicLogger() throws Exception {

		Log.debug("Starting testBasicLogger");
		
		A a = new A();
		a.doA();

		B b = new B();
		b.doB();
		
		C c = new C();
		c.doC();
		
		AA aa = new AA();
		aa.doAA();
		
		AAA aaa = new AAA();
		aaa.doAAA();
		
		Log.debug("Stopping testBasicLogger");
		
	}
	
	@Test
	public void testFileLogger() throws Exception {

		String logFile = TMP_DIR + SLASH + "test_file_logger.log";

		Log.newSetup().file(logFile)
			.level("info")
			.dtFormat("YYYY-MM-DDThh:mm:ss.sssTZD")
			.tags("TestApp").create();

		Log.debug("Starting testFileLogger");

		A a = new A();
		a.doA(Level.INFO, "You're a pretty smart ole chap, you get an A!");

		B b = new B();
		b.doB(Level.DEBUG, "Much improved ole boy, soon you will make an A!");

		C c = new C();
		c.doC(Level.WARN, "You're not the brightest bulb but you're not the dimmest either, you get a C!");

		AA aa = new AA();
		aa.doAA(Level.ERROR, "A++ your parents will give you money!");

		AAA aaa = new AAA();
		aaa.doAAA(Level.INFO, "A+++, That's only possible if you're Einstein smart!");

		Log.debug("Stopping testFileLogger");
		
		// Now read back file and check for each line of text
		List<String> lines = IO.asLines(new File(logFile));

	}

}