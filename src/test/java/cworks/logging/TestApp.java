package cworks.logging;

import org.junit.Test;

public class TestApp {

	@Test
	public void testBasicLogger() throws Exception {

		/*
		 * Setup global log properties per JVM instance
		 */
		//Log.setup().file("test_app.log") // test_app.log will be created in user.dir
		//	.level("info")
		//	.dtFormat("YYYY-MM-DDThh:mm:ss.sssTZD")
		//	.tags("TestApp");
		
		System.setProperty("log", "/tmp/test_basic_logger.log");
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