package src;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import src.concrete.systemFacade;
import src.concrete.Adapter;

public class MainUI extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel west;
	private JButton recalculate, addView, removeView;
	private JComboBox<String> fromList, toList, countriesList, viewsList, methodsList;
	private int startYear, endYear;
	private systemFacade facade;
	private String country, analysis;
	private LinkedHashMap<String, String> countries;
	private String[] excludedCountries;
	
	private static Properties props;

	private static MainUI instance;

	public static MainUI getInstance() {
		if (instance == null)
			instance = new MainUI();

		return instance;
	}

	private MainUI() {
		// Set window title
		super("Country Statistics");
		
		// Get properties
		try {
			props = new Properties();
			props.load(new FileInputStream("src/src/config.properties"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("src/src/excludedList.json"));
			Gson gson = new Gson();
	        JsonArray js = (gson.fromJson(bufferedReader, JsonObject.class)).get("Countries").getAsJsonArray();
	        this.excludedCountries = new String[js.size()];
	        for (int i = 0; i < js.size(); i++) {
	        	this.excludedCountries[i] = js.get(i).toString().strip().trim().replaceAll("\"", "");
	        }
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Get list of countries
		Adapter fetcher = new Adapter();
		this.countries = fetcher.getCountriesCode();
		
		facade = new systemFacade();

		// Set top bar
		JLabel chooseCountryLabel = new JLabel(props.getProperty("CountriesLabel"));
		Vector<String> countriesNames = new Vector<String>();
		countriesNames.addAll(Arrays.asList(props.getProperty("Countries").split(",")));
		countriesNames.sort(null);
		countriesList = new JComboBox<String>(countriesNames);
		countriesList.addActionListener(this);

		JLabel from = new JLabel(props.getProperty("fromLabel"));
		JLabel to = new JLabel(props.getProperty("toLabel"));
		
		Vector<String> years = new Vector<String>();
		for (int i = 2021; i >= 2000; i--) {
			years.add("" + i);
		}
		
		fromList = new JComboBox<String>(years);
		fromList.addActionListener(this);
		toList = new JComboBox<String>(years);
		toList.addActionListener(this);

		JPanel north = new JPanel();
		north.add(chooseCountryLabel);
		north.add(countriesList);
		north.add(from);
		north.add(fromList);
		north.add(to);
		north.add(toList);

		// Set bottom bar
		recalculate = new JButton(props.getProperty("recalculateLabel"));
		recalculate.addActionListener(this);

		JLabel viewsLabel = new JLabel(props.getProperty("viewsLabel"));

		Vector<String> viewsNames = new Vector<String>();
		viewsNames.addAll(Arrays.asList(props.getProperty("Charts").split(",")));
		viewsList = new JComboBox<String>(viewsNames);
		//viewsList.addActionListener(this);
		
		addView = new JButton("+");
		addView.addActionListener(this);
		removeView = new JButton("-");
		removeView.addActionListener(this);

		JLabel methodLabel = new JLabel(props.getProperty("methodLabel"));

		Vector<String> methodsNames = new Vector<String>();
		methodsNames.addAll(Arrays.asList(props.getProperty("Methods").split(",")));
		methodsList = new JComboBox<String>(methodsNames);
		methodsList.addActionListener(this);

		JPanel south = new JPanel();
		south.add(viewsLabel);
		south.add(viewsList);
		south.add(addView);
		south.add(removeView);

		south.add(methodLabel);
		south.add(methodsList);
		south.add(recalculate);
		
		//setting default values to what is currently selected if user never changes options
		//user will have to select '+" to add a graph. if they don't click plus on any graph but clicks recalculate we need a popup message that they didn't select a graph
		this.country = this.countries.get((String) countriesList.getSelectedItem());//need to convert to a country code. Will facade do that?
		this.startYear = Integer.parseInt((String)fromList.getSelectedItem());
		this.endYear = Integer.parseInt((String)toList.getSelectedItem());
		this.analysis = (String) methodsList.getSelectedItem(); //analysis attribute is only a string. Need a factory to create the object
		System.out.println(country);
		System.out.println(startYear);
		System.out.println(endYear);
		System.out.println(analysis);

		JPanel east = new JPanel();

		// Set charts region
		west = new JPanel();
		west.setLayout(new GridLayout(2, 0));
		//createCharts(west);

		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(south, BorderLayout.SOUTH);
		getContentPane().add(west, BorderLayout.WEST);
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() == recalculate) {		
			this.remove(this.west);
			this.west = new JPanel();
			this.west.setLayout(new GridLayout(2, 0));
			
			//this listener should call facade --> facade can interact with analysis factory and subject to update the graphs	
			//createCharts(west);
			facade.recalculate(this.startYear, this.endYear, this.country, this.analysis, this.west);
	
	
			getContentPane().add(west, BorderLayout.WEST);
			this.repaint();
			this.revalidate();
			//clear subject once done since user will have to reset the graphs
		}
		else if(evt.getSource() == addView) {
			facade.addGraph((String) viewsList.getSelectedItem());
		}
		else if(evt.getSource() == removeView) {
			facade.removeGraph((String) viewsList.getSelectedItem());
		}
		else if(evt.getSource() == fromList) {
			this.startYear = Integer.parseInt((String)fromList.getSelectedItem());
			System.out.println(this.startYear);
		}
		else if(evt.getSource() == toList) {
			this.endYear = Integer.parseInt((String)toList.getSelectedItem());
			System.out.println(this.endYear);
		}
		else if(evt.getSource() == countriesList) {
			this.country = this.countries.get((String) countriesList.getSelectedItem()); //need to convert country to country code.
			if (this.country == null || Arrays.stream(this.excludedCountries).anyMatch(this.country::equals)) { //checking util to see if country is excluded
				JOptionPane.showMessageDialog(null, "Country is Excluded From Data Fetching", "Country Selction", JOptionPane.INFORMATION_MESSAGE);
				this.country = null;
			}
			System.out.println(this.country);
		}
		else if(evt.getSource() == methodsList) {
			this.analysis = (String) methodsList.getSelectedItem(); //need to convert analysis object
			System.out.println(this.analysis);
		}
	}




	public static void main(String[] args) {
    	new JFrame();
		JFrame frame = MainUI.getInstance();
		frame.setSize(900, 600);
		frame.pack();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setVisible(true);

        
	}
	// TODO Auto-generated method stub

}