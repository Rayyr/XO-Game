package spring_project;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class intro extends JFrame {

	private JLabel label;
	private JButton cont;
	private handler obj = new handler();
	private Image appIcon;
	private music sound;

	public intro() {

		super("JAVA SPRING PROJECT");
		super.setSize(680, 200);
		super.setLayout(new FlowLayout());
		super.setLocation(460, 300);
		super.setResizable(false);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.getContentPane().setBackground(new Color(197, 203, 202));

		appIcon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("xo.png"));
		super.setIconImage(appIcon);

		sound=new music();
		
		label = new JLabel("         Wellcome To My JAVA Project   -Tic Tac Toe-");
		label.setForeground(new Color(10, 126, 140));
		label.setFont(new Font("arial", Font.BOLD, 22));

		cont = new JButton("CONTINUE...");
		cont.setPreferredSize(new Dimension(160, 50));
		cont.setForeground(new Color(255, 211, 215));
		cont.setBackground(new Color(250, 249, 246));
		cont.setFont(new Font("arial", Font.BOLD, 19));
		cont.setBorder(BorderFactory.createDashedBorder(new Color(255, 211, 215)));
		cont.setFocusable(false);

		super.add(label);
		super.add(cont);

		cont.addActionListener(obj);

		super.setVisible(true);
	}

	private class handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			intro.super.setVisible(false);
			getGameMode();

		}
	}

	public void getGameMode() {
		JLabel l = new JLabel("Your Opponent Player : ");

		JRadioButton human = new JRadioButton("Human");
		JRadioButton ai = new JRadioButton("AI", true);// if the user not choose
		human.setFocusable(false);
		ai.setFocusable(false);

		ButtonGroup groub = new ButtonGroup();
		groub.add(human);
		groub.add(ai);

		JPanel p = new JPanel();

		p.add(l);
		p.add(human);
		p.add(ai);

		int choice = JOptionPane.showConfirmDialog(null, p, "Player Mode", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		/*
		 * we will take the selected option if it is the ( human) we will pass true ,
		 * else we pass false
		 */

		if (choice == JOptionPane.OK_OPTION) {
			// the user choose one of the assigned options ( human or ai )

			if (human.isSelected()) {
				// human Vs human
				getNames(true);

			} else {
				// human VS ai
				// the seconed player name still to be null
				getNames(false);

			}

			super.dispose();// when all the instructions are taken properlly
		}

		else {
			// if he choose ( CANCEL ) option , i will terminate the application
			JOptionPane.showMessageDialog(null, "Sorry , You Don't Follow The Instructions !", "Warning",
					JOptionPane.WARNING_MESSAGE);
			System.exit(0);

		}

	}

	public void getNames(boolean value) {

		JLabel l1 = new JLabel("First Player Name : ");
		JLabel l2 = new JLabel("Seconed Player Name : ");

		JTextField name1 = new JTextField(10);
		JTextField name2 = new JTextField(10);

		JPanel p = new JPanel();

		if (value == true) {
			/*
			 * i will promot the user to enter the player names ( both ) since it is human
			 * VS human game
			 */

			p.add(l1);
			p.add(name1);
			p.add(l2);
			p.add(name2);

			int flag = 0;
			// this loop will gurntee that the entered names are valid
			spring_project.testclass.player1 = name1.getText();// neither null nor space
			spring_project.testclass.player2 = name2.getText();// neither null nor space

			while ((spring_project.testclass.player1.equals(spring_project.testclass.player2))
					|| !valid(spring_project.testclass.player1, name1)
					|| !valid(spring_project.testclass.player2, name2)) {

				if (flag != 0) {
					sound.setFile("error.wav");
					JOptionPane.showMessageDialog(p, "Please Enter Valid Name !", "Invalid Name",
							JOptionPane.ERROR_MESSAGE);
				}

				if (spring_project.testclass.player1.equals(spring_project.testclass.player2) == true) {
					name1.setText("");
					name2.setText("");
				}
				int option = JOptionPane.showConfirmDialog(null, p, "Players Names", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE);

				if (option == JOptionPane.OK_OPTION) {
					spring_project.testclass.player1 = name1.getText();
					spring_project.testclass.player2 = name2.getText();

				} else {
					JOptionPane.showMessageDialog(null, "Sorry , You Don't Follow The Instructions ! ",
							"Warning", JOptionPane.WARNING_MESSAGE);
					System.exit(0);
				}
				flag++;
			}

		}

		else {
			/*
			 * here the user will enter only 1 player name since its human VS ai game
			 */

			p.add(l1);
			p.add(name1);

			int flag = 0;
			spring_project.testclass.player1 = name1.getText();// neither null nor space but it's blank

			while (!valid(spring_project.testclass.player1, name1)) {

				if (flag != 0) {
					sound.setFile("error.wav");
					JOptionPane.showMessageDialog(p, "Please Enter Valid Name !", "Invalid Name",
							JOptionPane.ERROR_MESSAGE);
				}

				int option = JOptionPane.showConfirmDialog(null, p, "Player Name", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE);

				if (option == JOptionPane.OK_OPTION) {

					spring_project.testclass.player1 = name1.getText();

				} else {
					JOptionPane.showMessageDialog(null, "Sorry , You Don't Follow The Instructions ! ",
							"Warning", JOptionPane.WARNING_MESSAGE);
					System.exit(0);
				}
				flag++;
			}
			setDifficultyLevel();

		}

	}

	public void setDifficultyLevel() {

		JLabel l = new JLabel("Choose Difficulty Level : ");

		JRadioButton b1 = new JRadioButton("Easy", true);
		JRadioButton b2 = new JRadioButton("Medium");
		JRadioButton b3 = new JRadioButton("Hard");

		b1.setFocusable(false);
		b2.setFocusable(false);
		b3.setFocusable(false);

		ButtonGroup groub = new ButtonGroup();
		groub.add(b1);
		groub.add(b2);
		groub.add(b3);

		JPanel p = new JPanel();
		p.add(l);
		p.add(b1);
		p.add(b2);
		p.add(b3);

		int option = JOptionPane.showConfirmDialog(null, p, "Difficulty Level", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (option == JOptionPane.OK_OPTION) {
			if (b1.isSelected())
				spring_project.testclass.level = b1.getText();

			if (b2.isSelected())
				spring_project.testclass.level = b2.getText();

			if (b3.isSelected())
				spring_project.testclass.level = b3.getText();

		} else {
			JOptionPane.showMessageDialog(null, "Sorry , You Don't Follow The Instructions ! ", "Warning",
					JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}

	}

	public boolean valid(String playerName, JTextField x) {

		if (playerName.equalsIgnoreCase("AI") == true) {
			x.setText("");
			return false;
		}

	 
		if (playerName.equals("") == true) {
			x.setText("");
			return false;
		}

		for (Character y : playerName.toCharArray()) {
			if (!Character.isAlphabetic(y)) {
				x.setText("");
				return false;
			}

		}

		x.setEditable(false);
		return true;

	}
}
 
