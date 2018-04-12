package src.dkeep.gui;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class Pannel extends JPanel{

	private BufferedImage background;
	private char [][] board;
	private int lvl;
	private HashMap<String, BufferedImage> assets = new HashMap<String, BufferedImage>();
	
	
	
	public Pannel() {
		String pathG = getPath('G'), pathg = getPath('g'), pathH = getPath('H'),
		pathA = getPath('A'), pathX  = getPath('X'), path8 = getPath('8'), 
		pathO = getPath('O'), pathI = getPath('I'), pathCloud = getPath('$'), 
		pathClub = getPath('*'), pathLever = getPath('k'), pathK = getPath('K');
		try {
			background = ImageIO.read(getClass().getResource("/resources/background.png"));
			BufferedImage gW = ImageIO.read(getClass().getResource(pathG));
			BufferedImage gS = ImageIO.read(getClass().getResource(pathg));
			BufferedImage h = ImageIO.read(getClass().getResource(pathH));
			BufferedImage a = ImageIO.read(getClass().getResource(pathA));
			BufferedImage x = ImageIO.read(getClass().getResource(pathX));
			BufferedImage _8 = ImageIO.read(getClass().getResource(path8));
			BufferedImage o = ImageIO.read(getClass().getResource(pathO));
			BufferedImage i = ImageIO.read(getClass().getResource(pathI));
			BufferedImage c = ImageIO.read(getClass().getResource(pathCloud));
			BufferedImage cl = ImageIO.read(getClass().getResource(pathClub));
			BufferedImage l = ImageIO.read(getClass().getResource(pathLever));
			BufferedImage mkay = ImageIO.read(getClass().getResource(pathK));
			
			assets.put(pathG, gW);
			assets.put(pathg, gS);
			assets.put(pathH, h);
			assets.put(pathA, a);
			assets.put(pathX, x);
			assets.put(path8, _8);
			assets.put(pathO, o);
			assets.put(pathI, i);
			assets.put(pathCloud, c);
			assets.put(pathClub, cl);
			assets.put(pathLever, l);
			assets.put(pathK, mkay);
			
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setBoard(char [][] board) {
		this.board = board;
	}
	
	public String getPath(char a) {
		String s = "/resources/";
		if (a == 'G') {
			s += "KoopaAW.png";
		}else if (a == 'g') {
			s += "KoopaS.png";
		}else if (a == 'H') {
			s += "marioDB.png";
		}else if (a == 'A') {
			s += "marioVA.png";
		}else if (a == 'X') {
			s += "brick.png";
		}else if (a == 'O') {
			s += "Bowser.png";
		}else if (a == '8'){
			s += "BowserS.png";
		}else if (a == '*') {
			s += "club.png";
		}else if (a == '$') {
			s += "cloud.png";
		}else if (a == 'I') {
			s += "door.png";
		}else if (a == 'k') {
			s += "lever.png";
		}else if (a == 'K'){
			s += "marioK.png";
		}
		return s;
	}
	
	@Override
	protected void paintComponent(Graphics ko) {
		super.paintComponent(ko);
		ko.drawImage(background, 0, 0, 36000	, 36000 , null);
		int cell = 36;
		String path = "";
		if (board != null) {
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[0].length; j++) {
					path = "";
					char toDraw = board[i][j];
					if (toDraw != 'S' && toDraw != ' ') {
						path = getPath(toDraw);
						ko.drawImage(assets.get(path), j * cell, i * cell, cell, cell, null);
					}
				}
			}
		}
	}
	
}
