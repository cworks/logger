package cworks.logging;

import cworks.domain.A;
import cworks.domain.AA;
import cworks.domain.AAA;
import cworks.domain.B;
import cworks.domain.C;
import cworks.logging.io.IO;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TestApp {

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
		String logFile = "build/logs/test_file_logger.log";
		Log.newLogger().file(logFile).level("info").tags("TestApp").add();
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
		Assert.assertTrue(lines.get(0).contains("You're a pretty smart ole chap, you get an A!"));
		Assert.assertTrue(lines.get(1).contains("You're not the brightest bulb but you're not the dimmest either, you get a C!"));
		Assert.assertTrue(lines.get(2).contains("A++ your parents will give you money!"));
		Assert.assertTrue(lines.get(3).contains("A+++, That's only possible if you're Einstein smart!"));
		Assert.assertEquals(4, lines.size());
		Files.delete(Paths.get(logFile));
	}
	
	@Test
	public void testMultipleLogs() throws IOException {
		Log.newLogger().file("build/logs/a.log").level("debug").tags("A_LOG").add();
		Log.newLogger().file("build/logs/b.log").level("info").tags("B_LOG").add();
		Log.newLogger().file("build/logs/c.log").level("warn").tags("C_LOG").add();
		A a = new A();
		a.doA(Level.DEBUG, "a-hello-debug");
		a.doA(Level.INFO, "a-hello-info");
		a.doA(Level.WARN, "a-hello-warn");
		AA aa = new AA();
		aa.doAA(Level.DEBUG, "aa-hello-debug");
		aa.doAA(Level.INFO, "aa-hello-info");
		aa.doAA(Level.WARN, "aa-hello-warn");
		AAA aaa = new AAA();
		aaa.doAAA(Level.DEBUG, "aaa-hello-debug");
		aaa.doAAA(Level.INFO, "aaa-hello-info");
		aaa.doAAA(Level.WARN, "aaa-hello-warn");
	}

}