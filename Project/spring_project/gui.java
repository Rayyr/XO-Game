package spring_project;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.Random;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
 

public class gui extends JFrame implements ItemListener, ActionListener {

	private JLabel title, one, two,oneScore,twoScore;
	private JPanel centerPanel, board, screenbench;
	private JButton cards[][];
	private JButton play, reset, quit;
	private String oneSympol, twoSympol, player1, player2, difficultyLevel;
	private JRadioButton b1, b2;
	private Integer score1, score2, counter , rowIndx, colIndx;
	private boolean turn = true;
	private Icon X,O,oneIcon,twoIcon;
	private Image appIcon;
	private music sound;

	/*
	 * I make the defalut-overriden constructor private accessability to prevent the
	 * user to create an (gui obj) by using it and to setup the shanew Color(197, 203, 202  ) components
	 * design between the 2 game modes instead of rewrite them in each overloaded
	 * constructor
	 */
	private gui() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {

		
		// frame properties
		super("XO Game");
		super.setSize(620, 380);
	 	super.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		super.setLocation(370, 210); 
		super.setLayout(new BorderLayout(30, 30));
		super.getContentPane().setBackground(new Color(197, 203, 202  ));
		super.setResizable(false);
		  
        appIcon=Toolkit.getDefaultToolkit().getImage(getClass().getResource("xo.png"));
        super.setIconImage(appIcon);
      
        sound=new music();
        
        
		counter=0;
		score1 = 0;
		score2 = 0;
		player1 = spring_project.testclass.player1;
		player2 = spring_project.testclass.player2;
		difficultyLevel = spring_project.testclass.level;
		X=new ImageIcon(getClass().getResource("x.png"));
		O=new ImageIcon(getClass().getResource("o.png"));

		
		// title label
		title = new JLabel("X-O Game", SwingConstants.CENTER);
		title.setFont(new Font("arial", Font.BOLD, 40));
		title.setForeground(new Color(10, 126, 140 ));
		super.add(title, BorderLayout.NORTH);

		// cenetral Panel it will composite of 2 sub panels
		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));// flow layout the default layout
		centerPanel.setBackground( new Color(197, 203, 202));
		
		// game left panel ( the grid )
		initializeBoardPanel();
		centerPanel.add(board);

		// the current setting and results of the game right panel
		initializeScreenBenchPanel();
		centerPanel.add(screenbench);

		super.add(centerPanel, BorderLayout.CENTER);

		super.setVisible(true);
		
		
		
	}

	/*
	 * overloaded constructor with 2 parameters ( dummy parameters ) it is used when
	 * the game mode is human VS human here we can access the players name since
	 * they are in the same package but i have pass them as parameters to
	 * differentiate between the game mode
	 */
	public gui(String player1Name, String player2Name) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException   {

		this();// invoking the default constructor

		JPanel playersInfo = new JPanel();
		playersInfo.setLayout(new BoxLayout(playersInfo, BoxLayout.Y_AXIS));
		playersInfo.setBackground( new Color(197, 203, 202  ));

		// first player info
		one = new JLabel("1st Player Name : " + player1Name);
		one.setAlignmentX(Component.CENTER_ALIGNMENT);
		one.setFont(new Font("arial", Font.BOLD, 20));
		playersInfo.add(one);

		
		// xo chooser buttons
		b1 = new JRadioButton("X");
		b2 = new JRadioButton("O");
		b1.setFocusable(false);
		b2.setFocusable(false);
		b1.setFont(new Font("arial", Font.BOLD, 18));
		b2.setFont(new Font("arial", Font.BOLD, 18));
		b1.setBackground( new Color(197, 203, 202  ));
		b2.setBackground( new Color(197, 203, 202  ));
		b1.setForeground(new Color(255, 211, 215));
		b2.setForeground(new Color(255, 211, 215));

		Box box = Box.createHorizontalBox();
		box.add(b1);
		box.add(b2);
		playersInfo.add(box);

		b1.addItemListener(this);
		b2.addItemListener(this);

		ButtonGroup choose = new ButtonGroup();
		choose.add(b1);
		choose.add(b2);

		// seconed player info
		two = new JLabel("2nd Player Name : " + player2Name);
		two.setAlignmentX(CENTER_ALIGNMENT);
		two.setFont(new Font("arial", Font.BOLD, 20));
		playersInfo.add(two);

		screenbench.add(playersInfo, BorderLayout.NORTH);

		// south region of the screen bench is empty ( center is spanned it )

	}

	// overloaded constructor with 1 parameter
	// it is used when the game mode is human VS ai
	public gui(String player1Name) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException  {
		this();

		JPanel playersInfo = new JPanel();
		playersInfo.setLayout(new BoxLayout(playersInfo, BoxLayout.Y_AXIS));
		playersInfo.setBackground( new Color(197, 203, 202  ));
		
		// first player info
		one = new JLabel("Player Name : " + player1Name);
		one.setAlignmentX(Component.CENTER_ALIGNMENT);
		one.setFont(new Font("arial", Font.BOLD, 20));
		playersInfo.add(one);

		two = new JLabel();// dummy

		// xo chooser buttons
		b1 = new JRadioButton("X");
		b2 = new JRadioButton("O");
		b1.setFocusable(false);
		b2.setFocusable(false);
		b1.setFont(new Font("arial", Font.BOLD, 18));
		b2.setFont(new Font("arial", Font.BOLD, 18));
		b1.setBackground( new Color(197, 203, 202  ));
		b2.setBackground( new Color(197, 203, 202  )); 
		b1.setForeground(new Color(255, 211, 215));
		b2.setForeground(new Color(255, 211, 215));

		Box box = Box.createHorizontalBox();
		box.add(b1);
		box.add(b2);
		playersInfo.add(box);
		 

		b1.addItemListener(this);
		b2.addItemListener(this);

		ButtonGroup choose = new ButtonGroup();
		choose.add(b1);
		choose.add(b2);

		screenbench.add(playersInfo, BorderLayout.NORTH);

	}

	public void initializeBoardPanel() {

	 
		board = new JPanel();
		board.setLayout(new GridLayout(3, 3, 3, 3)); // 3 raws * 3 cols
		board.setBackground( new Color(197, 203, 202  ));
	    
		cards = new JButton[3][3];
		for (int c = 0; c < 3; c++) {
			for (int j = 0; j < 3; j++) {
				cards[c][j] = new JButton();
				cards[c][j].setBorder(BorderFactory.createLineBorder(Color.black,1));
				cards[c][j].setBackground(new Color(250, 249, 246));
				cards[c][j].setPreferredSize(new Dimension(120, 120));
				cards[c][j].setFocusable(false);
				cards[c][j].setEnabled(false);
				cards[c][j].setIcon(null);
				cards[c][j].addActionListener(this);
				cards[c][j].setBorder(BorderFactory.createDashedBorder(new Color(255, 211, 215)));
				
				board.add(cards[c][j]);

			}
		}

		return;
	}

	public void initializeScreenBenchPanel() {

		screenbench = new JPanel(new BorderLayout(70, 70));
		screenbench.setBackground( new Color(197, 203, 202  ));
		Box total = Box.createVerticalBox(); 	// contains the btns and scores

		
		Box score = Box.createVerticalBox();
		JLabel s = new JLabel("Score");
		s.setFont(new Font("arial", Font.BOLD, 20));
		s.setForeground(new Color(10, 126, 140 ));
		s.setAlignmentX(CENTER_ALIGNMENT);

		
		oneScore = new JLabel(spring_project.testclass.player1 + " : " + score1);
		oneScore.setFont(new Font("arial", Font.BOLD, 20));
		oneScore.setAlignmentX(CENTER_ALIGNMENT);

		 
		if (spring_project.testclass.player2 == null) { // human vs ai
			twoScore = new JLabel("AI : " + score2);
			twoScore.setFont(new Font("arial", Font.BOLD, 20));
			twoScore.setAlignmentX(CENTER_ALIGNMENT);
		} else {

			twoScore = new JLabel(spring_project.testclass.player2 + " : " + score2);// human vs human
			twoScore.setFont(new Font("arial", Font.BOLD, 20));
			twoScore.setAlignmentX(CENTER_ALIGNMENT);
		}
		
		score.add(s);
		score.add(oneScore);
		score.add(twoScore);

		Box btns = Box.createHorizontalBox();

		// set up the buttons
		play = new JButton("Play");
		play.addActionListener(this);
		play.setEnabled(false);
		

		reset = new JButton("Reset");
		reset.addActionListener(this);
		reset.setEnabled(false);

		quit = new JButton("Quit");
		quit.addActionListener(this);

		
		btns.setAlignmentX(CENTER_ALIGNMENT);

		btns.add(play);
		btns.add(reset);
		btns.add(quit);
		
		
		// buttons properties
		play.setFocusable(false);
		reset.setFocusable(false);
		quit.setFocusable(false);
		play.setFont(new Font("arial", Font.BOLD, 17));
		reset.setFont(new Font("arial", Font.BOLD, 17));
		quit.setFont(new Font("arial", Font.BOLD, 17));
        play.setForeground(new Color(255, 211, 215));
        reset.setForeground(new Color(255, 211, 215));
        quit.setForeground(new Color(255, 211, 215));
        play.setBackground(new Color(250, 249, 246));
        reset.setBackground(new Color(250, 249, 246));
        quit.setBackground(new Color(250, 249, 246));
        play.setToolTipText("First You Need To Choose Your Sympols , Then You Can Start !");
        quit.setToolTipText("Quit The Game");
        
		
		total.add(btns);
		total.add(score);

		// add the buttons panel to the screenbench panel ( central one )
		screenbench.add(total, BorderLayout.CENTER);

		return;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {

		// this function will be called one time because then it will be disabeld
		sound.setFile("select.wav");
		if (b1.isSelected()) {
			oneSympol = b1.getText(); // 1st player : X
			twoSympol = b2.getText();// 2nd player : O
		 
			one.setForeground(new Color(10, 126, 140));//blue
			two.setForeground(new Color(251,253,136));//yello
			oneScore.setForeground(new Color(10, 126, 140));
			twoScore.setForeground(new Color(251,253,136));
			 
		}

		else {
			oneSympol = b2.getText();// 1st player : O
			twoSympol = b1.getText();// 2nd player : X
			
			two.setForeground(new Color(10, 126, 140));//blue
			one.setForeground(new Color(251,253,136));//yello
			twoScore.setForeground(new Color(10, 126, 140));
			oneScore.setForeground(new Color(251,253,136));
			 
			 
		}

		 if(oneSympol.equals("X")==true)
		 {
			 oneIcon=X;
			 twoIcon=O;
		 }
		 else
		 {
			 oneIcon=O;
			 twoIcon=X;
		 }
		 
		 
		one.setText(one.getText() + " ( " + oneSympol + " )");
		two.setText(two.getText() + " ( " + twoSympol + " )");

	    
		b1.setEnabled(false);
		b2.setEnabled(false);
		play.setEnabled(true);
		play.setToolTipText(null);

		return;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == play) {
			// enable for the grid cards
			sound.setFile("play.wav");
			for (int c = 0; c < 3; c++) {
				for (JButton x : cards[c])
					x.setEnabled(true);
			}
			play.setEnabled(false);
			reset.setEnabled(true);
			reset.setToolTipText("Reset The Game , If You The Current Result Will Be Ignored ");

		}

		else if (e.getSource() == reset) {
		 sound.setFile("reset.wav");
           clear();
		}

		else if (e.getSource() == quit) {
			
			sound.setFile("quit.wav");
			showResult();
			
		}

		else {
			for (int c = 0; c < 3; c++) {
				for (int j = 0; j < 3; j++) {
					if (e.getSource() == cards[c][j]) {
						sound.setFile("move.wav");
						if (turn == true) {
							// 1st player turn
							cards[c][j].setIcon(oneIcon);
							cards[c][j].setEnabled(false);
							cards[c][j].setDisabledIcon(oneIcon);
							if (win() == true) {
								sound.setFile("win.wav");
								score1++;
								oneScore.setText(player1 + " : " + score1);
								nextRound("Congrats " +oneSympol + " " + player1);
								 
								return ;
							}
							turn = false;

							counter++;
							if (counter == 9) {
								sound.setFile("quit.wav");
								nextRound("It's A Draw !");
								return;// draw , no moves still
							}
							
							// check if ai mode turn or not
							if (player2 == null) {
								// so the 2nd player is AI
								 
								switch (difficultyLevel) {

								// they are algorithms to choose the best indx. based to chosen difficulty level
								// in the
								// Beginning of the app.
								case "Easy":
									blockIndxEasyLevel();
									break;

								case "Medium":
									blockIndxMediumLevel();
									break;

								case "Hard":
									blockIndxHardLevel();
									break;

								}
								 
								sound.setFile("move.wav");
								cards[rowIndx][colIndx].setIcon(twoIcon);
								cards[rowIndx][colIndx].setEnabled(false);
								cards[rowIndx][colIndx].setDisabledIcon(twoIcon);
								if (win() == true) {
									
									sound.setFile("win.wav");
									score2++;
									twoScore.setText("AI : " + score2);
									nextRound("Congrats " +twoSympol + " AI ");
								 
									return;
								}
								turn = true;
								counter++;
								if (counter == 9) {
									sound.setFile("quit.wav");
									nextRound("It's A Draw !");
									return;// draw , no moves still
								}
							}

						}

						else {
							// 2nd player turn
							cards[c][j].setIcon(twoIcon);
							cards[c][j].setEnabled(false);
							cards[c][j].setDisabledIcon(twoIcon);
							if (win() == true) {
                                
								sound.setFile("win.wav");
								score2++;
								twoScore.setText(player2 + " : " + score2);
								nextRound("Congrats " +twoSympol + " " + player2);
								
								//to break and start from brgining again , else it will 
								// continue so wrong logic
								return;
							}
							turn = true;

							counter++;
							if (counter == 9) {
								sound.setFile("quit.wav");
								nextRound("It's A Draw !");
								return;// draw , no moves still
							}
						}

					}
				}

			}
		}

		return;
	}

	public boolean win() {

		if (winHorizantal() == true)
			return true;

		if (winVertical() == true)
			return true;

		if (winDiagonal() == true)
			return true;

		return false;
	}

	public boolean winHorizantal() {

		for (int c = 0; c < 3; c++) {

			if ((cards[c][0].getIcon() != null) && (cards[c][1].getIcon() != null) && (cards[c][2].getIcon() != null)) {
				if ((cards[c][0].getIcon().equals(cards[c][1].getIcon()))
						&& (cards[c][0].getIcon().equals(cards[c][2].getIcon()))) {
					// System.out.print("h");
					
					
					if(player2==null && difficultyLevel.equals("Hard") && turn==false) {
						
						 
						if(cards[c][0].getIcon().equals(oneIcon))
							return true;
					}
					
					
					
					
					
					if(cards[c][0].getIcon().equals(X)) {
						//xplayer
						cards[c][0].setBackground(new Color(115,236,223));
						cards[c][1].setBackground(new Color(115,236,223));
						cards[c][2].setBackground(new Color(115,236,223));
					}
					else {
						//oplayer
						cards[c][0].setBackground(new Color(251,253,136));
						cards[c][1].setBackground(new Color(251,253,136));
						cards[c][2].setBackground(new Color(251,253,136));
					}
					return true;
				}

			}
		}
		return false;

	}

	public boolean winVertical() {
		for (int c = 0; c < 3; c++) {

			if ((cards[0][c].getIcon() != null) && (cards[1][c].getIcon() != null) && (cards[2][c].getIcon() != null)) {
				if ((cards[0][c].getIcon().equals(cards[1][c].getIcon()))
						&& (cards[0][c].getIcon().equals(cards[2][c].getIcon()))) {
					// System.out.print("v");
					
					
					if(player2==null && difficultyLevel.equals("Hard") && turn==false) {
						
						 
						if(cards[0][c].getIcon().equals(oneIcon))
							return true;
					}
					
					
					if(cards[0][c].getIcon().equals(X)) {
						//xplayer
						cards[0][c].setBackground(new Color(115,236,223));
						cards[1][c].setBackground(new Color(115,236,223));
						cards[2][c].setBackground(new Color(115,236,223));
					}
					else {
						//oplayer
						cards[0][c].setBackground(new Color(251,253,136));
						cards[1][c].setBackground(new Color(251,253,136));
						cards[2][c].setBackground(new Color(251,253,136));
					}
					return true;
				}

			}
		}
		return false;
	}

	public boolean winDiagonal() {

		if ((cards[0][0].getIcon() != null) && (cards[1][1].getIcon() != null) && (cards[2][2].getIcon() != null))
			if ((cards[0][0].getIcon().equals(cards[1][1].getIcon()))
					&& (cards[0][0].getIcon().equals(cards[2][2].getIcon()))) {

				// System.out.print("dmain");
				
				if(player2==null && difficultyLevel.equals("Hard") && turn==false) {
					
					 
					if(cards[0][0].getIcon().equals(oneIcon))
						return true;
				}
				
				if(cards[0][0].getIcon().equals(X)) {
					//xplayer
					cards[0][0].setBackground(new Color(115,236,223));
					cards[1][1].setBackground(new Color(115,236,223));
					cards[2][2].setBackground(new Color(115,236,223));
				}
				else {
					//oplayer
					cards[0][0].setBackground(new Color(251,253,136));
					cards[1][1].setBackground(new Color(251,253,136));
					cards[2][2].setBackground(new Color(251,253,136));
				}
				return true;

			}

		if ((cards[0][2].getIcon() != null) && (cards[1][1].getIcon() != null) && (cards[2][0].getIcon() != null))
			if ((cards[0][2].getIcon().equals(cards[1][1].getIcon()))
					&& (cards[0][2].getIcon().equals(cards[2][0].getIcon()))) {

				// System.out.print("dsec");
				if(player2==null && difficultyLevel.equals("Hard") && turn==false) {
					
					 
					if(cards[0][2].getIcon().equals(oneIcon))
						return true;
				}
				
				if(cards[0][2].getIcon().equals(X)) {
					//xplayer
					cards[0][2].setBackground(new Color(115,236,223));
					cards[1][1].setBackground(new Color(115,236,223));
					cards[2][0].setBackground(new Color(115,236,223));
				}
				else {
					//oplayer
					cards[0][2].setBackground(new Color(251,253,136));
					cards[1][1].setBackground(new Color(251,253,136));
					cards[2][0].setBackground(new Color(251,253,136));
				}
				return true;

			}
		return false;
	}

	public void blockIndxEasyLevel() {

		// this algo. based to choose random indx for the row & the column
		Random Rand = new Random();

		rowIndx = Rand.nextInt(3);
		colIndx = Rand.nextInt(3);

		// check if we still have enabled cards to make event on them

		while (cards[rowIndx][colIndx].isEnabled() == false) {

			// the loop will continue till we found card which is
			// enabeld ( we can perform an event on it
			rowIndx = Rand.nextInt(3);
			colIndx = Rand.nextInt(3);

		}

		return ; 
	}

	public void blockIndxMediumLevel() {

		for (int c = 0; c < 3; c++) {
			for (int j = 0; j < 3; j++) {

				// i need enabeled card ( can make on it an event )
				if (cards[c][j].isEnabled() == true) {
					cards[c][j].setIcon(twoIcon);
					if (win() == false) {
						// this future card not make him win
						// so we need to choose else card
						cards[c][j].setIcon(null);
						// restore the original text of the card

					} else {
						rowIndx = c;
						colIndx = j;
						return;
					}
				}
			}
		}

		// if the compiler reaches here that means no such card (not found ) makes ai
		// won ,
		// so we will move to the next state ( less priorty ) which is choose
		// random card by invoking the easy version method

		blockIndxEasyLevel();
		return;
	}

	public void blockIndxHardLevel() {

		for (int c = 0; c < 3; c++) {
			for (int j = 0; j < 3; j++) {

				// i need enabeled card ( can make on it an event )
				if (cards[c][j].isEnabled() == true) {
					cards[c][j].setIcon(twoIcon);
					if (win() == false) {
						// this future card not make him win
						// so we need to choose else card
						cards[c][j].setIcon(null);
						// restore the original text of the card

					} else {
						rowIndx = c;
						colIndx = j;
						return;
					}
				}
			}
		}

		// if the compiler reaches here that means no such card (not found )makes ai won
		// ,
		// so we will move to the next state ( less priorty ) which is choose
		// card which may prevent the 1st player to won ( if found )

		for (int c = 0; c < 3; c++) {
			for (int j = 0; j < 3; j++) {

				// i need enabeled card ( can make on it an event )
				if (cards[c][j].isEnabled() == true) {
					cards[c][j].setIcon(oneIcon);
					if (win() == true) {

						rowIndx = c;
						colIndx = j;
						return;
					} else {
						cards[c][j].setIcon(null);
					}
				}
			}
		}

		// if the compiler reaches here that means no such card (not found )makes ai won
		// ,
		// and no such card found to prevent the 1st player of wining so we will move
		// to the last option ( least priority ) which is choose random card
		blockIndxEasyLevel();
		return;

	}
	
	
	public void nextRound(String winOrDraw) {
		//it will be called when the game is ended ( one player in won ) 
		String content[]= {winOrDraw,"Do You Need To Play Another Round ? "};
		int option=JOptionPane.showConfirmDialog(this,content,"Next Round",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
		
		if(option==JOptionPane.OK_OPTION) {
			 clear();
		}
	     
		else 
			showResult();
		
		return ;
		 
	}
	
	
	public void clear() {
		for (int c = 0; c < 3; c++) {
			for (JButton x : cards[c]) {
				x.setEnabled(false);
				x.setIcon(null);
				x.setBackground(new Color(250, 249, 246));
			}
		}
		play.setEnabled(true);
		
		//by default the grid is reseted so we can't make it again reset 
		reset.setEnabled(false);
		reset.setToolTipText(null);
		counter=0;
		turn=true;
		return ;
	}
	
	
	public void showResult() {
		 
		 String x=player2==null?"AI":player2;
		 String arr[]=new String[] {"The Final Result : ",player1+" Gains "+score1+" Rounds ",x+" Gains "+score2+" Rounds "};
		 JOptionPane.showMessageDialog(this,arr,"Final Result",JOptionPane.PLAIN_MESSAGE);
		 System.exit(0);
	 }
 

}
