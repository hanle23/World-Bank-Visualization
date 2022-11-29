package src.view.interfaces;

import javax.swing.JPanel;

import src.backend.concrete.linkedList;

public interface Graph {
	public void update(linkedList dataSets, JPanel west, String graphTitle);
}
