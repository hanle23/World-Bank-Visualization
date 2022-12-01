package test;
import java.util.HashMap;

import javax.swing.JFrame;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

import java.awt.*;
import java.awt.event.*;
import src.*;
import src.controller.concrete.MainUI;

public class TestLogin extends login{
	boolean isAuthenticated;
	public void loginTest(String userName, String password) {
		userName = userName.trim();
		password = password.trim();
		HashMap<String, String> credentials = null;
		try {
			credentials = util.jsonToDict("loginCredentials/credentials.json");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (credentials.containsKey(userName)) {
				if (credentials.get(userName).equals(password)) {
					this.isAuthenticated = true;
					
				} 
				
			} 
		}

	}
	@Test
	public void testLogin1(){
		TestLogin test = new TestLogin();
		test.loginTest("xyz","123");
		assertEquals(test.isAuthenticated, false);
		
	}
	
	@Test
	public void testLogin2(){
		TestLogin test = new TestLogin();
		test.loginTest("admin","adminPassword");
		assertEquals(test.isAuthenticated, true);
		
	}
	
	@Test
	public void testLogin3(){
		TestLogin test = new TestLogin();
		test.loginTest("   admin ","   adminPassword ");
		assertEquals(test.isAuthenticated, true);
		
	}
}
