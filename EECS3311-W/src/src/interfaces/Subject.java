package src.interfaces;

import javax.swing.JPanel;

import src.concrete.linkedList;

public interface Subject {
	//Should we add attach/detach methods??
	public void notifyGraphs(linkedList dataSets, JPanel west);
}
