package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.Font;

import dkeep.*;
import dkeep.logic.GameState;

public class gui {

	private JFrame frame;
	private JTextField ogresNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui window = new gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		//frame.setBounds(100, 100, 450, 300);
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
		lblNumberOfOgres.setBounds(23, 20, 152, 16);
		frame.getContentPane().add(lblNumberOfOgres);

		JLabel lblGuardPersonality = new JLabel("Guard Personality");
		lblGuardPersonality.setBounds(23, 45, 115, 16);
		frame.getContentPane().add(lblGuardPersonality);

		ogresNumber = new JTextField();
		ogresNumber.setBounds(167, 15, 115, 26);
		frame.getContentPane().add(ogresNumber);
		ogresNumber.setColumns(10);

		JComboBox gPersonality = new JComboBox();
		gPersonality.setBounds(167, 41, 115, 27);
		gPersonality.addItem("Rookie");
		gPersonality.addItem("Drunken");
		gPersonality.addItem("Suspicious");
		frame.getContentPane().add(gPersonality);


		// Movement buttons
		JButton btnUp = new JButton("Up");
		btnUp.setBounds(585, 165, 117, 29);
		frame.getContentPane().add(btnUp);

		JButton btnDown = new JButton("Down");
		btnDown.setBounds(585, 247, 117, 29);
		frame.getContentPane().add(btnDown);

		JButton btnLeft = new JButton("Left");
		btnLeft.setBounds(530, 206, 117, 29);
		frame.getContentPane().add(btnLeft);

		JButton btnRight = new JButton("Right");
		btnRight.setBounds(642, 206, 117, 29);
		frame.getContentPane().add(btnRight);

		// Exit Button
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(585, 396, 117, 29);
		frame.getContentPane().add(btnExit);

		//show game
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Courier New", Font.PLAIN, 20));
		textArea.setBounds(23, 108, 487, 306);
		frame.getContentPane().add(textArea);

		// variable message
		JLabel message = new JLabel("You can start a new game. (max. 4 ogres) ");
		message.setBounds(24, 445, 400, 16);
		frame.getContentPane().add(message);

		// New game Button
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean inputIsValid = false;
				char guardPer;
				int ogreNum = 0;
				
				try {
					ogreNum = Integer.parseInt(ogresNumber.getText());
				} catch (NumberFormatException e1){
					message.setText("The input is not valid.");
				}catch (NullPointerException e1) {
					message.setText("The input is not valid.");
				}
				
				String guardP = String.valueOf(gPersonality.getSelectedItem());
				
				switch (guardP) {
				case "Rookie":
					guardPer = 'r';
					inputIsValid = true;
					break;
				case "Drunken":
					guardPer = 'd';
					inputIsValid = true;
					break;
				case "Suspicious":
					guardPer = 's';
					inputIsValid = true;
					break;
				default:
					System.out.println(inputIsValid);
					inputIsValid = false;
					break;
				}
				
				if(ogreNum < 1 || ogreNum > 4) {
					inputIsValid = false;
				}
				
				if(!inputIsValid) {
					message.setText("The input is not valid.");
				}else {
					message.setText("Level 1");
					
					
				}

				
			}
		});
		btnNewGame.setBounds(585, 68, 117, 29);
		frame.getContentPane().add(btnNewGame);




	}
}
