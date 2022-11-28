package src.interfaces;

import javax.swing.JPanel;

import src.concrete.linkedList;

public interface Graph {
	public void update(linkedList dataSets, JPanel west, String graphTitle);
}
