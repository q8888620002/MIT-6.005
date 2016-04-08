package ui;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;


import model.JottoModel;

/**
 * // TODO Write specifications for your JottoGUI class.
 * 
 * 
 */
public class JottoGUI extends JFrame {
	
	
	// views object that display the view
		private JottoModel model;
		private JButton newPuzzleButton;
		private JTextField newPuzzleNumber;
		private JLabel puzzleNumber;
		private JLabel typePuzzleNumber;
		private JTextField guess;
		private JTable guessTable;
		private JLabel guessLabel;
		private JScrollPane guessScrollPane;
		private DefaultTableModel tableModel;
	// start JottoGUI with a random id of puzzle
	
	public JottoGUI() {
		
		// initialized the Jotto model with random number
	     model = new JottoModel();
		
		Container cp = this.getContentPane();
		GroupLayout layout = new GroupLayout(cp);
		
		cp.setLayout(layout);
		
		// set the size of JottoGUI

		setTitle("Jotto Game");
		setSize(550, 500);
		
		newPuzzleButton = new JButton();
		newPuzzleButton.setName("newPuzzleButton");
	    newPuzzleButton.setText("New Puzzle");
	    
	    newPuzzleNumber = new JTextField();
        newPuzzleNumber.setName("newPuzzleNumber");
        puzzleNumber = new JLabel();
        
        puzzleNumber.setName("puzzleNumber");
        puzzleNumber.setText("Puzzle # " + model.getId());
        
        typePuzzleNumber = new JLabel();
        typePuzzleNumber.setText("New Puzzle ID:");
        
		guessLabel = new JLabel("Type a guess here :");
		guess = new JTextField("");
		
		guessTable = new JTable();
		guessTable.setName("guessTable");
		guessTable.setFillsViewportHeight(true);
	        
	    guessScrollPane = new JScrollPane(guessTable);
	    guessScrollPane.setName("guessScrollPane");
		
	    tableModel = new DefaultTableModel(){
	    	// Make cell non-editable  
	    	
	    	@Override
	    	    public boolean isCellEditable(int row, int column) {
	    	       //all cells false
	    	       return false;
	    	    }
	    };
		tableModel.addColumn("guess");
		tableModel.addColumn("in common");
		tableModel.addColumn("correct position");
		
		guessTable.setModel(tableModel);
		
		// set the layout manager
		
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		
		// the top layer of the JottoGUI
		// Puzzle number (JLabel) , New Puzzle(Jbutton),  new Puzzle number(JTextField)
		layout.setHorizontalGroup(
				layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
						.addComponent(puzzleNumber, 100 ,100,100)
						.addComponent(newPuzzleButton,200,200,200)
						.addComponent(typePuzzleNumber,100,100,100)
						.addComponent(newPuzzleNumber)
						)
				.addGroup(layout.createSequentialGroup()
						.addComponent(guessLabel, 150, 150,150)
						.addComponent(guess)
						)
				.addComponent(guessScrollPane)
				);
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(puzzleNumber, 40 ,40 ,40)
						.addComponent(newPuzzleButton,40,40,40)
						.addComponent(typePuzzleNumber,40,40,40)
						.addComponent(newPuzzleNumber,40,40,40)
						)
				.addGroup(layout.createParallelGroup()
						.addComponent(guessLabel,35,35,35)
						.addComponent(guess,35,35,35)
						)
				.addComponent(guessScrollPane)
				);
		
	
		
	/** Add a eventListener to PuzzleButton. */
		
		newPuzzleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 /** Refresh puzzle id to reflect changes in input field. */
				
				String text = newPuzzleNumber.getText();
				
				/** Set the new puzzle id or Create a new random id of puzzle if no input provided.*/
				
				
				if(text.length() != 0){
					
					try {
						int newID = Integer.parseInt(text);
							if(newID > 0){
								model = new JottoModel(newID);
								tableModel.setRowCount(0);
								puzzleNumber.setText("Puzzle #"+text);
								newPuzzleNumber.setText("");
							}
					} catch (NumberFormatException e2) {
						newPuzzleNumber.setText("Invalid puzzle id "+text);
					}
				}else{
					
					/** Create a new random id of puzzle within 1 - 10000  if no input provided. */
					
					model = new JottoModel();
					tableModel.setRowCount(0);
					puzzleNumber.setText("Puzzle #"+model.getId());
					// clear the text box of puzzle id input
					newPuzzleNumber.setText("");
				}
				
			}
		});
		
		// get the client guess input
		
		guess.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("On the event thread? : " +
		                  EventQueue.isDispatchThread());
				
				String userInput = guess.getText();
				
				// update user guess on JTable before receiving the server response.
				
				tableModel.addRow(new Object[]{userInput,"",""}) ;
				try {
					
					// Spawn a new thread for each guess so that the GUI doesn't hang and the user
				    // can still make additional guesses. 
					
					new Thread(new Runnable() {
						@Override
						public void run() {
							
							int currentRow = tableModel.getRowCount()-1;
							
							String serverResponse = model.makeGuess(userInput);
							String results[] = model.handleInput(serverResponse);

							// Add guess result to the table
							
							if(results.length == 2){
								tableModel.setValueAt(results[0], currentRow, 1 );
								tableModel.setValueAt(results[1], currentRow, 2);
							}else{
								
								// win condition 
								tableModel.addRow(new Object[]{userInput,results[0],"secret word is \""+userInput+"\""});
							}
						}
					}).start();
				} catch (RuntimeException e2) {
					e2.printStackTrace();
				}
			
				
				
				
				//scroll to the last add row
				
				int last = tableModel.getRowCount()-1;
				Rectangle rectangle = guessTable.getCellRect(last, 0, true);
				guessTable.scrollRectToVisible(rectangle);
				
				// clear the textbox
				guess.setText("");
			}
		});
	}
	
	
	public static void main(final String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				JottoGUI main = new JottoGUI();
				
				main.setVisible(true);
			}
		});
	}
}
