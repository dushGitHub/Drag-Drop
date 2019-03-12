/**
 * Polytechnic West Project example
 */
 
package core;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import base.Piece;
import java.awt.BorderLayout;


	public Tetris()	{
		
		setSize(400, 530);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
		setTitle("Java Tetris");
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		setMenu();

		setNorthPanel();
		setCentrePanel();
		setEastPanel();
		setWestPanel();
		setSouthPanel();
		
		add(pnlNorth, BorderLayout.NORTH);
		add(pnlCentre, BorderLayout.CENTER);
		add(pnlEast, BorderLayout.EAST);
		add(pnlWest, BorderLayout.WEST);
		add(pnlSouth, BorderLayout.SOUTH);
	}

	/**
	 * Create application menu
	 *
	 */
	private void setMenu() {
		
		//About
		about = new AboutBox();

		//Menu
		menubar = new JMenuBar();
		menFile = new JMenu("File");
		menGame = new JMenu("Game");
		menHelp = new JMenu("Help");
		itmExit = new JMenuItem("Exit");
		itmStart = new JMenuItem("Start");
		itmAbout = new JMenuItem("About");
		
		//Shortcuts
		menFile.setMnemonic('F');
		menGame.setMnemonic('G');
		menHelp.setMnemonic('H');
		itmExit.setMnemonic('x');
		itmStart.setMnemonic('S');
		itmAbout.setMnemonic('A');
		
		//Key Accelerator
		itmExit.setAccelerator(KeyStroke.getKeyStroke("alt F4"));
		itmStart.setAccelerator(KeyStroke.getKeyStroke("alt F2"));
		itmExit.addActionListener(this);
		itmStart.addActionListener(this);
		itmAbout.addActionListener(this);
		
		//Adding Items to Menu
		menFile.add(itmExit);
		menGame.add(itmStart);
		menHelp.add(itmAbout);
		
		menubar.add(menFile);
		menubar.add(menGame);
		menubar.add(menHelp);
		
		setJMenuBar(menubar);
	}

	/**
	 * Set BorderLayout
	 *
	 */
	
	/**
	 * Create a new piece
	 *
	 */
	public void createNewPiece() {
		
		int centerOfColumn = playField.length / 2;

		int pieceColor = (int) Math.floor(Math.random() * 7);
		switch (pieceColor) {
		case 0:
			//[][][][] - Red
			piecePoints = new Point[4];
			piecePoints[0] = new Point(centerOfColumn - 0, 0);
			piecePoints[1] = new Point(centerOfColumn - 1, 0);
			piecePoints[2] = new Point(centerOfColumn - 2, 0);
			piecePoints[3] = new Point(centerOfColumn + 1, 0);
			currentPiece = new Piece(pieceColorList[pieceColor], piecePoints);
			break;

		case 1:
			//[][][] - Blue
			//  []
			piecePoints = new Point[4];
			piecePoints[0] = new Point(centerOfColumn - 0, 0);
			piecePoints[1] = new Point(centerOfColumn - 1, 0);
			piecePoints[2] = new Point(centerOfColumn + 1, 0);
			piecePoints[3] = new Point(centerOfColumn - 0, 1);
			currentPiece = new Piece(pieceColorList[pieceColor], piecePoints);
			break;

		case 2:
			// [][] - Cyan
			// [][]
			piecePoints = new Point[4];
			piecePoints[0] = new Point(centerOfColumn - 0, 0);
			piecePoints[1] = new Point(centerOfColumn - 1, 0);
			piecePoints[2] = new Point(centerOfColumn - 0, 1);
			piecePoints[3] = new Point(centerOfColumn - 1, 1);
			currentPiece = new Piece(pieceColorList[pieceColor], piecePoints);
			break;
		
		case 3:
			//  [][]   - Green
			//[][]
			piecePoints = new Point[4];
			piecePoints[0] = new Point(centerOfColumn - 0, 0);
			piecePoints[1] = new Point(centerOfColumn + 1, 0);
			piecePoints[2] = new Point(centerOfColumn - 0, 1);
			piecePoints[3] = new Point(centerOfColumn - 1, 1);
			currentPiece = new Piece(pieceColorList[pieceColor], piecePoints);
			break;

		case 4:
			//[][]   -Magenta
			//  [][]
			piecePoints = new Point[4];
			piecePoints[0] = new Point(centerOfColumn - 0, 0);
			piecePoints[1] = new Point(centerOfColumn - 1, 0);
			piecePoints[2] = new Point(centerOfColumn + 0, 1);
			piecePoints[3] = new Point(centerOfColumn + 1, 1);
			currentPiece = new Piece(pieceColorList[pieceColor], piecePoints);
			break;
			
		case 5:
			//[][][] - Orange
			//    []
			piecePoints = new Point[4];
			piecePoints[0] = new Point(centerOfColumn - 0, 0);
			piecePoints[1] = new Point(centerOfColumn - 1, 0);
			piecePoints[2] = new Point(centerOfColumn - 0, 1);
			piecePoints[3] = new Point(centerOfColumn - 2, 0);
			currentPiece = new Piece(pieceColorList[pieceColor], piecePoints);
			break;
			
		case 6:
			//[][][] - Pink
			//[]
			piecePoints = new Point[4];
			piecePoints[0] = new Point(centerOfColumn - 0, 0);
			piecePoints[1] = new Point(centerOfColumn - 1, 0);
			piecePoints[2] = new Point(centerOfColumn + 1, 0);
			piecePoints[3] = new Point(centerOfColumn - 1, 1);
			currentPiece = new Piece(pieceColorList[pieceColor], piecePoints);
			break;
		}
	
		//It is not possible, game over! lol
		if(isCurrentMovePossible() != true)
		{
			JOptionPane.showMessageDialog(null, "Game over");
			System.exit(0);	
		}
	}

	/**
	 * Clear current piece
	 *
	 */
	public void clearCurrentPiece() {
		for (int i = 0; i < currentPiece.getCells().length; i++) {
			playField[currentPiece.getCells()[i].getColumn()][currentPiece.getCells()[i].getRow()] = gameBoard.DEFAULT_PLAY_FIELD_VALUE;
		}
	}
	
	/**
	 * Draw current piece
	 *
	 */
	public void drawCurrentPiece() {
		for(int i = 0; i < currentPiece.getCells().length; i++) {
			playField[currentPiece.getCells()[i].getColumn()][currentPiece.getCells()[i].getRow()] = currentPiece.getValue();
		}
	}

	/**
	 * Verify possible piece moviment. 
	 * @return True if the moviment is possible or False it is not
	 */
	public boolean isCurrentMovePossible() {
		for (int i = 0; i < currentPiece.getCells().length; i++) {
			int x, y;
			
			x = currentPiece.getCells()[i].getColumn();
			y = currentPiece.getCells()[i].getRow();

			if ((x < 0) || (x >= playField.length)
					|| (y < 0) || y >= playField[0].length
					|| (playField[currentPiece.getCells()[i].getColumn()][currentPiece.getCells()[i].getRow()] != gameBoard.DEFAULT_PLAY_FIELD_VALUE)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Execute piece moviment 
	 * This moviment can be DOWN or ROTATION
	 * @param movePieceOption UP, DOWN, LEFT, RIGHT
	 */
	public void moveCurrentPiece(int movePieceOption) {
		
		//While moving piece stop creating next one
		if (isNewPieceRequired)
			return;
		
		switch (movePieceOption) {
		case MOVE_UP_CURRENT_PIECE:
			this.clearCurrentPiece();
			currentPiece.moveUp();
			
			if(!this.isCurrentMovePossible()) {
				currentPiece.moveDown();
			}
			this.drawCurrentPiece();
			break;

		case MOVE_DOWN_CURRENT_PIECE:
			this.clearCurrentPiece();
			currentPiece.moveDown();
			
			if(!this.isCurrentMovePossible()) {
				currentPiece.moveUp();
				isNewPieceRequired = true; // new piece required here, but piece isnt created
													// therefore user can still move the previous piece
			}
			this.drawCurrentPiece();
			break;

		case MOVE_LEFT_CURRENT_PIECE:
			this.clearCurrentPiece();
			currentPiece.moveLeft();
			
			if(!this.isCurrentMovePossible()) {
				currentPiece.moveRight();
			}
			this.drawCurrentPiece();
			break;

		case MOVE_RIGHT_CURRENT_PIECE:
			this.clearCurrentPiece();
			currentPiece.moveRight();
			
			if(!this.isCurrentMovePossible()) {
				currentPiece.moveLeft();
			}
			this.drawCurrentPiece();
			break;

		case ROTATE_RIGHT_CURRENT_PIECE:
			this.clearCurrentPiece();
			currentPiece.rotateRight();
			
			if(!this.isCurrentMovePossible()) {
				currentPiece.rotateLeft();
			}
			this.drawCurrentPiece();
			break;

		case ROTATE_LEFT_CURRENT_PIECE:
			this.clearCurrentPiece();
			currentPiece.rotateLeft();
			
			if(!this.isCurrentMovePossible()) {
				currentPiece.rotateRight();
			}
			this.drawCurrentPiece();
			break;
		}
		
		//refreshing screen
		gameBoard.paintBuffer(); 
		gameBoard.repaint();
	}

	/**
	 * Looks for full lines and clear them moving up pieces to down position
	 *
	 */
	public void clearRows()	{
		
		int count = 0;
		
		//Look for 4 possibles full rows
		for (int r = 0; r < MAX_FULL_ROWS; r++) {
			for (int i = (gameBoard.DEFAULT_PLAY_FIELD_HEIGHT - 1); i > 0; i--) {
				boolean isFullRow = true;
				
				for (int j = 0; j < gameBoard.DEFAULT_PLAY_FIELD_WIDTH; j++) {
					if(playField[j][i] == gameBoard.DEFAULT_PLAY_FIELD_VALUE) {
						isFullRow = false;
						break;
					}
				}
	
				//Row is full, clear it
				if (isFullRow) {
					count++;

					if (count > MAX_FULL_ROWS)
						count = 0;

					for (int k = (i - 1); k > 1; k--) {	
						for (int l = 0; l < gameBoard.DEFAULT_PLAY_FIELD_WIDTH; l++) {
							playField[l][k + 1] = playField[l][k]; 
					   }
					}
				}		
			}
		}
		
		//Compute Score
		deletedLines += count;
		totalScore  += count * GAME_SCORE[currentLevel];
		scorePanel.setScore(totalScore);
		
		if (deletedLines >= NEXT_LEVEL) {
			currentLevel++;
			
			if (currentLevel > MAX_GAME_LEVEL)
				currentLevel = 0;
			
			scorePanel.setLevel(currentLevel + 1);
			deletedLines = 0;
		}

	}

	/**
	 * Stop thread
	 *
	 */
	public void stop() {
		daemon = null;
	}

