package test;
import java.util.HashMap;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import src.*;
import src.controller.concrete.MainUI;

public class loginTester extends login{
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
}
