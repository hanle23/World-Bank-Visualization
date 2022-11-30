package src;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

import javax.swing.*;

import src.controller.concrete.MainUI;

public class login extends JFrame implements ActionListener {
	private static final long serialVersionUID = 7443430018716222992L;
	JPanel panel;
	JLabel user_label, password_label, message;
	JTextField userName_text;
	JPasswordField password_text;
	JButton submit, cancel;
	private MainUI userInterface;
	private boolean isAuthenticated;
	public login() {
		this.isAuthenticated = false;
      // Username Label
		user_label = new JLabel();
		user_label.setText("User Name :");
		userName_text = new JTextField();
		// Password Label
		password_label = new JLabel();
		password_label.setText("Password :");
		password_text = new JPasswordField();
		// Submit
		submit = new JButton("SUBMIT");
		panel = new JPanel(new GridLayout(3, 1));
		panel.add(user_label);
		panel.add(userName_text);
		panel.add(password_label);
		panel.add(password_text);
		message = new JLabel();
		panel.add(message);
		panel.add(submit);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Adding the listeners to components..
		submit.addActionListener(this);
		add(panel, BorderLayout.CENTER);
		setTitle("Please Login Here !");
		setSize(450,350);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		String userName = userName_text.getText().trim();
		String password = password_text.getText().trim();
		HashMap<String, String> credentials = null;
		try {
			credentials = util.jsonToDict("loginCredentials/credentials.json"); //pulling usernames/passwords from json file that stores them
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (credentials.containsKey(userName)) { //checking if username is valid
				if (credentials.get(userName).equals(password)) { //checking if password matches the username
					this.isAuthenticated = true;
					this.setVisible(false);
					userInterface = MainUI.getInstance(); //if username and password is correct we get an instance of the mainUI, giving the user access to it
					userInterface.setSize(900, 600);
					userInterface.pack();
					userInterface.setExtendedState(JFrame.MAXIMIZED_BOTH);
					userInterface.setVisible(true);
					
				} else {
					message.setText("Invalid username/password");
				}
			} else {
				message.setText("Invalid username/password");
			}
		}
	}
	
	public boolean isAuthenticated() {
		return this.isAuthenticated;
	}
	
	public static void main(String[] args) {
		login test = new login();

        
	}

}