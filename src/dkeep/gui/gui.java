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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

public class gui {

	private JFrame frame;
	private JTextField ogresNumber;
	
	private GameState g;
	//private JTextArea textArea;
	private Pannel panel;
	private int no;
	private JLabel message;
	private Integer unlocked;
	
	JButton btnUp;
	JButton btnDown;
	JButton btnLeft;
	JButton btnRight;
	
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
		frame.setBounds(100, 100, 813, 696);
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
		btnUp = new JButton("Up");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				directionAction('w');
			}
		});
		btnUp.setBounds(585, 165, 117, 29);
		frame.getContentPane().add(btnUp);

		btnDown = new JButton("Down");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				directionAction('s');
				
			}
		});
		btnDown.setBounds(585, 247, 117, 29);
		frame.getContentPane().add(btnDown);

		btnLeft = new JButton("Left");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				directionAction('a');
			}
		});
		btnLeft.setBounds(530, 206, 117, 29);
		frame.getContentPane().add(btnLeft);

		btnRight = new JButton("Right");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				directionAction('d');
				
			}
		});
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
		/*
		textArea = new JTextArea();
		textArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			    int keyCode = e.getKeyCode();
			    switch( keyCode ) { 
			        case KeyEvent.VK_UP:
			        		directionAction('w');
			            break;
			        case KeyEvent.VK_DOWN:
			        		directionAction('s');
			            break;
			        case KeyEvent.VK_LEFT:
			        		directionAction('a');
			            break;
			        case KeyEvent.VK_RIGHT :
			        		directionAction('d');
			            break;
			     }
			} 
		});
		textArea.setFont(new Font("Courier New", Font.PLAIN, 20));
		textArea.setBounds(23, 108, 487, 306);
		frame.getContentPane().add(textArea);*/

		// variable message
		message = new JLabel("You can start a new game. (max. 5 ogres) ");
		message.setBounds(24, 445, 400, 16);
		frame.getContentPane().add(message);

		// New game Button
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//textArea.requestFocusInWindow();
				//frame.requestFocusInWindow();
				
				
				boolean inputIsValid = false;
				char guardPer = ' ';
				no = 0; // number of ogres
				unlocked = 0;
				
				try {
					no = Integer.parseInt(ogresNumber.getText());
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
				
				if(no < 1 || no > 5 || guardPer == ' ') {
					inputIsValid = false;
				}
				
				if(!inputIsValid) {
					message.setText("The input is not valid.");
				}else {
					g = new GameState(1, guardPer, no);
					//g.printMatrixGUI(g.getBoard(), textArea);
					panel.setBoard(g.getBoard());
					panel.repaint();
					btnUp.setEnabled(true);
					btnDown.setEnabled(true);
					btnLeft.setEnabled(true);
					btnRight.setEnabled(true);
				}

			}
		});
		btnNewGame.setBounds(585, 68, 117, 29);
		frame.getContentPane().add(btnNewGame);
		
		panel = new Pannel();
		panel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
			    switch( keyCode ) { 
			        case KeyEvent.VK_UP:
			        		directionAction('w');
			            break;
			        case KeyEvent.VK_DOWN:
			        		directionAction('s');
			            break;
			        case KeyEvent.VK_LEFT:
			        		directionAction('a');
			            break;
			        case KeyEvent.VK_RIGHT :
			        		directionAction('d');
			            break;
			     }
			}
		});
		panel.setBounds(42, 124, 500, 500);
		frame.getContentPane().add(panel);

	}
	
	public void playLvl1GUI(char key, int no) {
		
		message.setText("Level 1");
		g.getHero().moveHero(key, g.getBoard());
		g.updateBoard();
		g.moveGuards();
		g.updateBoard();
		
		g.unlockAll();
		if(g.exit()) {
			g.lvl2(no);
			message.setText("Level 2");
		}
		g.updateBoard();
		if(g.getHero().guardScan(g.getBoard())) {
			g.getHero().killHero();
			death();
			
		}
		if (g != null) {
			panel.setBoard(g.getBoard());
			panel.repaint();
		}
	}
	
	public void playLvl2GUI(char key) {
		
		message.setText("Level 2");
		
		if (g.getHero().getRep() == 'K') {
			if(g.checkANUnlock(unlocked)) unlocked++;
		}
		g.wakeOgres();
		g.updateBoard();
		g.getHero().moveHero(key, g.getBoard());
		g.stunOgres();
		g.updateBoard();
		g.catchKey();
		g.updateBoard();
		g.moveOgres();
		g.updateBoard();
		g.attackOgres();
		g.updateBoard();
		if(g.exit()) {
			g.gg();
			win();
		}
		if (g != null) {
			if (g.isPlayerHit()) {
				g.getHero().killHero();
				g.updateBoard();
				panel.setBoard(g.getBoard());
				panel.repaint();
				death();
			}
			if (g != null ) {
				panel.setBoard(g.getBoard());
				panel.repaint();
			}
		}
	}
	
	public void death () {
		message.setText("Game Over (ya ded, boi) ");
		btnUp.setEnabled(false);
		btnDown.setEnabled(false);
		btnLeft.setEnabled(false);
		btnRight.setEnabled(false);
		
	}
	
	public void win() {
		g = null;
		message.setText("gud game, son");
		//textArea.setText("");
	}
	
	public void directionAction(char key) {
		if(g != null) {
			if(g.getLvl() == 1) {
				playLvl1GUI(key, no);
			}else if (g.getLvl() == 2) {
				playLvl2GUI(key);
			}
		}
		
	}
}
