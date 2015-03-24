package cworks.logging;

import org.junit.Test;

import java.io.File;

public class TestApp {
	
	private static final String SLASH = File.separator;
	
	private static final String TMP_DIR = System.getProperty("java.io.tmpdir");
	
	@Test
	public void testBasicLogger() throws Exception {

		/*
		 * Setup global log properties per JVM instance
		 */
		//Log.setup().file("test_app.log") // test_app.log will be created in user.dir
		//	.level("info")
		//	.dtFormat("YYYY-MM-DDThh:mm:ss.sssTZD")
		//	.tags("TestApp");
		
		//System.setProperty("log", TMP_DIR + SLASH + "test_basic_logger.log");
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

}