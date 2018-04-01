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

public class gui {

	private JFrame frame;
	private JTextField textField;

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
		
		textField = new JTextField();
		textField.setBounds(167, 15, 115, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JComboBox gPersonality = new JComboBox();
		gPersonality.setBounds(167, 41, 115, 27);
		gPersonality.addItem("Rookie");
		gPersonality.addItem("Drunken");
		gPersonality.addItem("Suspicious");
		frame.getContentPane().add(gPersonality);
		
		JButton btnUp = new JButton("Up");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
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
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.setBounds(585, 68, 117, 29);
		frame.getContentPane().add(btnNewGame);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(585, 396, 117, 29);
		frame.getContentPane().add(btnExit);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Courier New", Font.PLAIN, 20));
		textArea.setBounds(23, 108, 487, 306);
		frame.getContentPane().add(textArea);
		
		JLabel lblYouCanStart = new JLabel("You can start a new game.");
		lblYouCanStart.setBounds(24, 445, 400, 16);
		frame.getContentPane().add(lblYouCanStart);
		
	}
}
