package com.joshktan.cypher;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JTextField;
/**
 * 
 * layout help: http://docs.oracle.com/javase/tutorial/uiswing/examples/layout/index.html
 * @author jdot
 *
 */
public class Cypher {


	public static void addComponentsToPane(Container pane) {
		
		JTextField plainField = new JTextField("Insert plaintext here.");
		pane.add(plainField, 0);
		JTextField cypherField = new JTextField("Observe corresponding cyphertext here.");
		pane.add(cypherField, 1);

	}

	private static void createAndShowGUI() {
		//Create and set up the window.
		JFrame cypherFrame = new JFrame("Caesar Cypher");
		cypherFrame.setPreferredSize(new Dimension(500,250));
		cypherFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cypherFrame.setLayout(new GridLayout(2, 1, 5, 0));

		//Set up the content pane.
		addComponentsToPane(cypherFrame.getContentPane());

		//Display the window.
		cypherFrame.pack();
		cypherFrame.setVisible(true);
	}
	
	public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                createAndShowGUI();
//            }
//        });
		
		
		System.out.println("Enter your plaintext: ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			String plaintext = reader.readLine();
			
		} catch (IOException e) {
			System.err.println("Problem reading plaintext.");
		}
		
    }
	
	public static void encipher(String plaintext) {
		// do stuff here
	}

}
