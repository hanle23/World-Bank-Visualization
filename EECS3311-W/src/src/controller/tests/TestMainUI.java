package src.controller.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import src.controller.concrete.MainUI;

public class TestMainUI {
	
	@Test
	public void test_mainUI_1() {
		//testing singleton principal on mainUI
		MainUI test1 = MainUI.getInstance();
		MainUI test2 = MainUI.getInstance();
		assertEquals(test1, test2);
	}
	
	@Test
	public void test_mainUI_countrySelection_0() {
		MainUI test1 = MainUI.getInstance();
		//testing when user makes no changes and chooses the country that is already selected when mainUI is first initialized
		assertEquals(0, test1.getCountry().compareToIgnoreCase("AGO"));
	}
	
	@Test
	public void test_mainUI_countrySelection_1() {
		//testing when the user would select "Canada" from the drop down menu, the string returned in getCountry() should be equal to the code for Canada
		MainUI test1 = MainUI.getInstance();
		test1.setCountry("Canada");
		assertEquals(0, test1.getCountry().compareToIgnoreCase("can"));
	}
	
	@Test
	public void test_mainUI_countrySelection_2() {
		MainUI test1 = MainUI.getInstance();
		//testing when the user would select "Canada" from the drop down menu then change to Armenia, the string returned in getCountry() should be equal to the code for Armenia
		test1.setCountry("Canada");
		test1.setCountry("Armenia");
		assertEquals(0, test1.getCountry().compareToIgnoreCase("ARM"));
	}
	
	@Test
	public void test_mainUI_countrySelection_3() {
		//testing when user selects a country excluded from data fetching, listed in excludedList.json, selecting a excluded country will result in String stored in this.country equal to null
		//NOTE: Popup window will appear notifying you that you selected a country that is excluded. Please close this window so that the unit tests can continue
		MainUI test1 = MainUI.getInstance();
		test1.setCountry("Australia");
		assertEquals(null, test1.getCountry());
	}
	
	@Test
	public void test_mainUI_countrySelection_4() {
		MainUI test1 = MainUI.getInstance();
		//testing when user selects a country not included in the World Bank API, selecting a country not included in the World Bank AP will result in String stored in this.country equal to null
		//NOTE: Popup window will appear notifying you that you selected a country that is excluded. Please close this window so that the unit tests can continue
		test1.setCountry("China");
		assertEquals(null, test1.getCountry());
	}
	
	@Test
	public void test_mainUI_countrySelection_5() {
		MainUI test1 = MainUI.getInstance();
		//testing when user selects a country not included in the World Bank API then changes to a country the is included and not excluded from data fetching
		//NOTE: Popup window will appear notifying you that you selected a country that is excluded. Please close this window so that the unit tests can continue
		test1.setCountry("China");
		test1.setCountry("Canada");
		assertEquals(0, test1.getCountry().compareToIgnoreCase("CAN"));
	}
}
