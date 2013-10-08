package com.joshktan.cypher;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
/**
 * 
 * layout help: http://docs.oracle.com/javase/tutorial/uiswing/examples/layout/index.html
 * @author jdot
 *
 */
public class Cypher {
	
	public static final int ALPHABET_SIZE = 26;
	public static final int ALPHABET_OFFSET = 97;
	
	private int shift;

	public Cypher(int shift) {
		this.shift = shift;
	}
	
	public Cypher(){
		this(13); // default shift is 13 characters
	}
	
	/**
	 * 
	 * @param plaintext
	 * @return ciphertext
	 */
	public String encipher(String plaintext) {
		
		// convert all characters to lower case
		plaintext = plaintext.toLowerCase();
		
		String[] plainArray = plaintext.split("\\s+");
		String[] cipherArray = new String[plainArray.length];
		
		for (int i = 0; i < plainArray.length; i++) {
			String word = plainArray[i];
			StringBuilder wordBuilder = new StringBuilder();
			for (int j = 0; j < word.length(); j++) {
				
				int charAsInt = (int) word.charAt(j);
				
				if (charAsInt > 96 && charAsInt < 123) {
					int actualShift = ((charAsInt - ALPHABET_OFFSET) + shift) % ALPHABET_SIZE;
					wordBuilder.append((char)(actualShift + ALPHABET_OFFSET));
				} else {
					wordBuilder.append(word.charAt(j));
				}
			}
			
			cipherArray[i] = wordBuilder.toString();
		}
		
		return join(cipherArray);
	}
	
	private String join(String[] wordArray) {
		StringBuilder gluer = new StringBuilder();
		for (int i = 0; i < wordArray.length; i++) {
			if (i < wordArray.length - 1) {
				gluer.append(wordArray[i]).append(' ');
			} else {
				gluer.append(wordArray[i]);
			}
		}
		return gluer.toString();
	}
	
	

	public static void addComponentsToPane(Container pane) {
		


	}

	private static void createAndShowGUI(final Cypher cypher) {
		//Create and set up the window.
		JFrame cypherFrame = new JFrame("Caesar Cypher");
		cypherFrame.setPreferredSize(new Dimension(500,250));
		cypherFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cypherFrame.setLayout(new GridBagLayout());

		//Set up the content pane.
		Container cypherPane = cypherFrame.getContentPane();
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.weightx = 0.5;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 0;
		cypherPane.add(button, c);

		final JTextField plainField = new JTextField("Insert plaintext here.");
		cypherPane.add(plainField, 0);
		final JTextField cypherField = new JTextField("Observe corresponding cyphertext here.");
		cypherPane.add(cypherField, 1);
		
		plainField.getDocument().addDocumentListener(new DocumentListener() {

	        @Override
	        public void removeUpdate(DocumentEvent e) {
	        	cypherField.setText(cypher.encipher(plainField.getText()));
	        }

	        @Override
	        public void insertUpdate(DocumentEvent e) {
	        	cypherField.setText(cypher.encipher(plainField.getText()));
	        }

	        @Override
	        public void changedUpdate(DocumentEvent arg0) {
	        	cypherField.setText(cypher.encipher(plainField.getText()));
	        }
	    });
		
		
		
		addComponentsToPane(cypherFrame.getContentPane());

		//Display the window.
		cypherFrame.pack();
		cypherFrame.setVisible(true);
	}
	
	public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	Cypher caesarCipher = new Cypher();
                createAndShowGUI(caesarCipher);
            }
        });
//		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//		
//		try {
//			System.out.println("Enter cipher shift size: ");
//			int shift = Integer.parseInt(reader.readLine());
//			Cypher caesarCipher = new Cypher(shift);
//			
//			System.out.println("Enter your plaintext: ");
//			String plaintext = reader.readLine();
//			
//			String ciphertext = caesarCipher.encipher(plaintext);
//			System.out.println("Your ciphertext:\n" + ciphertext);
//			
//		} catch (IOException e) {
//			System.err.println("Problem reading plaintext.");
//		}
		
    }
	

}
