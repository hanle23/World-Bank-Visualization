package src.controller.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import src.controller.concrete.MainUI;

public class TestMainUI {
	
	@Test
	public void test_mainUI_1() {
		MainUI test1 = MainUI.getInstance();
		MainUI test2 = MainUI.getInstance();
		assertEquals(test1, test2);
	}
}
