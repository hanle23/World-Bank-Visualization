package src;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

import javax.swing.*;

public class login extends JFrame implements ActionListener {
	private static final long serialVersionUID = 7443430018716222992L;
	JPanel panel;
	JLabel user_label, password_label, message;
	JTextField userName_text;
	JPasswordField password_text;
	JButton submit, cancel;
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
			credentials = util.jsonToDict("loginCredentials/credentials.json");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (credentials.containsKey(userName)) {
				if (credentials.get(userName).equals(password)) {
					message.setText(" Hello " + userName + "");
					this.isAuthenticated = true;
					
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

}