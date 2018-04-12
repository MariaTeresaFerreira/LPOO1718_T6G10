package src.dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.Font;

import src.dkeep.*;
import src.dkeep.logic.GameState;
import src.dkeep.logic.Coords;
import src.dkeep.logic.Ogre;
import src.dkeep.logic.Guard;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class gui {

	private JFrame frame;
	private JTextField ogresNumber;
	
	private GameState g;
	//private JTextArea textArea;
	private Pannel panel;
	private int no;
	private JLabel message;
	private Integer unlocked;
	private JLabel lblSelectItem;
	private JLabel lblX;
	private JLabel lblY;
	private JComboBox<String> charSelect;
	private JButton btnCustomKeep;
	private char[][] customMatrix;
	private Integer x, y;
	private LinkedList<Coords> customEx;
	private LinkedList<Ogre> customOGs;
	JButton btnUp;
	JButton btnDown;
	JButton btnLeft;
	JButton btnRight;
	private JTextField xInput;
	private JTextField yInput;
	private JButton btnStart;
	private JButton btnAdd;
	private JButton btnRemove;
	
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
		
		customMatrix = customMatrix();
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
		customEx  = new LinkedList<Coords>();
		customOGs = new LinkedList<Ogre>();
		JComboBox<String> gPersonality = new JComboBox();
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
		btnUp.setBounds(620, 166, 117, 29);
		frame.getContentPane().add(btnUp);

		btnDown = new JButton("Down");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				directionAction('s');
				
			}
		});
		btnDown.setBounds(620, 248, 117, 29);
		frame.getContentPane().add(btnDown);

		btnLeft = new JButton("Left");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				directionAction('a');
			}
		});
		btnLeft.setBounds(566, 207, 117, 29);
		frame.getContentPane().add(btnLeft);

		btnRight = new JButton("Right");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				directionAction('d');
				
			}
		});
		btnRight.setBounds(677, 207, 117, 29);
		frame.getContentPane().add(btnRight);

		// Exit Button
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(620, 595, 117, 29);
		frame.getContentPane().add(btnExit);

		// variable message
		message = new JLabel("You can start a new game. (max. 5 ogres) ");
		message.setBounds(24, 640, 400, 16);
		frame.getContentPane().add(message);

		// New game Button
		JButton btnNewGame = new JButton("Default Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//textArea.requestFocusInWindow();
				//frame.requestFocusInWindow();
				panel.setFocusable(true);
				panel.requestFocusInWindow();
				
				btnUp.setVisible(true);
				btnDown.setVisible(true);
				btnLeft.setVisible(true);
				btnRight.setVisible(true);
				btnCustomKeep.setVisible(true);
				lblSelectItem.setVisible(false);
				lblX.setVisible(false);
				lblY.setVisible(false);
				xInput.setVisible(false);
				yInput.setVisible(false);
				charSelect.setVisible(false);


				
				
				
				
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
		btnNewGame.setBounds(620, 67, 117, 29);
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
		
		btnCustomKeep = new JButton("Custom Keep");
		btnCustomKeep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				btnUp.setVisible(false);
				btnDown.setVisible(false);
				btnLeft.setVisible(false);
				btnRight.setVisible(false);
				btnCustomKeep.setVisible(false);
				lblSelectItem.setVisible(true);
				lblX.setVisible(true);
				lblY.setVisible(true);
				xInput.setVisible(true);
				yInput.setVisible(true);
				charSelect.setVisible(true);
				btnStart.setVisible(true);
				btnAdd.setVisible(true);
				btnRemove.setVisible(true);
				panel.repaint();
				
				
				
			}
		});
		btnCustomKeep.setBounds(620, 108, 117, 29);
		frame.getContentPane().add(btnCustomKeep);
		
		charSelect = new JComboBox<String>();
		charSelect.setBounds(566, 324, 228, 26);
		frame.getContentPane().add(charSelect);
		charSelect.setVisible(false);
		charSelect.addItem("Hero");
		charSelect.addItem("Ogre");
		charSelect.addItem("Wall");
		charSelect.addItem("Exit");
		charSelect.addItem("Key");
		
		
		lblSelectItem = new JLabel("select item");
		lblSelectItem.setVisible(false);
		lblSelectItem.setBounds(576, 299, 90, 26);
		frame.getContentPane().add(lblSelectItem);
		
		xInput = new JTextField();
		xInput.setBounds(576, 402, 90, 26);
		frame.getContentPane().add(xInput);
		xInput.setColumns(10);
		xInput.setVisible(false);
		
		yInput = new JTextField();
		yInput.setBounds(702, 402, 90, 26);
		frame.getContentPane().add(yInput);
		yInput.setColumns(10);
		yInput.setVisible(false);
		
		lblX = new JLabel("x");
		lblX.setVisible(false);
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setBounds(576, 389, 90, 16);
		frame.getContentPane().add(lblX);
		
		lblY = new JLabel("y");
		lblY.setVisible(false);
		lblY.setHorizontalAlignment(SwingConstants.CENTER);
		lblY.setBounds(702, 389, 92, 16);
		frame.getContentPane().add(lblY);
		
		btnStart = new JButton("Start");
		btnStart.setVisible(false);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g = new GameState(customMatrix, customEx, customOGs, new LinkedList<Guard> ());
				xInput.setVisible(false);
				yInput.setVisible(false);
				lblX.setVisible(false);
				lblY.setVisible(false);
				charSelect.setVisible(false);
				btnAdd.setVisible(false);
				btnRemove.setVisible(false);
				btnStart.setVisible(false);
				
				btnUp.setVisible(true);
				btnDown.setVisible(true);
				btnLeft.setVisible(true);
				btnRight.setVisible(true);
				
				panel.setFocusable(true);
				panel.requestFocusInWindow();
				unlocked = 0;
				panel.setBoard(g.getBoard());
				panel.repaint();
			}
		});
		btnStart.setBounds(620, 534, 117, 29);
		frame.getContentPane().add(btnStart);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				char s = comboboxToChar(charSelect);
				boolean validad = false;
				try {
					x = Integer.parseInt(xInput.getText());
				} catch (NumberFormatException e1){
					message.setText("The input is not valid.");
				}catch (NullPointerException e1) {
					message.setText("The input is not valid.");
				}
				try {
					y = Integer.parseInt(yInput.getText());
				} catch (NumberFormatException e1){
					message.setText("The input is not valid.");
				}catch (NullPointerException e1) {
					message.setText("The input is not valid.");
				}
				if (s == 'I' && x < customMatrix.length && x > -1 && y < customMatrix[0].length && y > -1) {
					Coords cx = new Coords(x, y);
					customEx.add(cx);
					customMatrix = alterMatrix(x, y, s, customMatrix);
				}
				if (x < customMatrix.length && y < customMatrix[0].length && x > 0 && y > 0) {
				
					if (s == 'A') {
						if (findCharGUI(customMatrix, 'A').X() == -1 && findCharGUI(customMatrix, 'A').Y() == -1) {
							customMatrix = alterMatrix(x, y, s, customMatrix); 
						}
					}
					if (s == 'k') {
						if (findCharGUI(customMatrix, 'k').X() == -1 && findCharGUI(customMatrix, 'k').Y() == -1) {
							customMatrix = alterMatrix(x, y, s, customMatrix); 
						}
					}
				
					if (s == 'O') {
						if (customOGs.size() < 5) {
							Coords co = new Coords(x, y);
							int modx = 0;
							if(x + 1 < customMatrix.length) modx = 1;
							else modx = -1;
							Coords cc = new Coords(x + modx, y);
							Ogre og = new Ogre(s, co, cc, '*');
							customOGs.add(og);
							customMatrix = alterMatrix(x, y, s, customMatrix);
						}
					}
					
					if (s == 'X') {
						customMatrix = alterMatrix(x, y, s, customMatrix);
					}
				}
				
				panel.setBoard(customMatrix);
				panel.repaint();	
			}
		

		});
		btnAdd.setBounds(620, 472, 117, 29);
		frame.getContentPane().add(btnAdd);
		btnAdd.setVisible(false);
		
		btnRemove = new JButton("Clear board");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < customMatrix.length; i++) {
					for (int j = 0; j < customMatrix[0].length; j++) {
						customMatrix[i][j] = ' ';
						if (i == 0 || j == 0 || i == customMatrix.length - 1 || j == customMatrix[0].length - 1) {
							customMatrix[i][j] = 'X';
						}
					}
				}
				g = null;
				panel.setBoard(customMatrix);
				panel.repaint();
			}
		});
		btnRemove.setBounds(620, 498, 117, 29);
		frame.getContentPane().add(btnRemove);
		btnRemove.setVisible(false);

	}
	
	public char [][] customMatrix(){
		char a[][] = {
				{'X','X','X','X','X','X','X','X','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X','X','X','X','X','X','X','X','X'}
		};
		
		return a;
	}
	
	public char[][] alterMatrix(int x, int y, char s, char[][]b){
		b[x][y] = s;
		return b;
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
		panel.setFocusable(false);
		
	}
	
	public void win() {
		g = null;
		message.setText("You win (gud game, son)");
		//textArea.setText("");
		btnUp.setEnabled(false);
		btnDown.setEnabled(false);
		btnLeft.setEnabled(false);
		btnRight.setEnabled(false);
		panel.setFocusable(false);
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
	
	public char comboboxToChar(JComboBox<String> c) {
		
		String item = String.valueOf(c.getSelectedItem());
		
		switch (item) {
		case "Hero":
			return 'A';
		case "Ogre":
			return 'O';
		case "Wall":
			return 'X';
		case "Exit":
			return 'I';
		case "Key":
			return 'k';
		default:
			break;
		}
		
		return '0';
	}
	
	public Coords findCharGUI(char board[][], char c) {

		Coords b = new Coords(-1, -1);
		for(int i = 0; i < board.length; i++)
			for(int j = 0; j < board[i].length; j++)
				if (board[i][j] == c) {
					b.setCoords(i, j);
				}
		return b;
	}
	
}
