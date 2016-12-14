package com.github.xzzpig.mine;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

import com.github.xzzpig.mine.view.MineView;
import com.github.xzzpig.mine.view.ScoreView;

import net.miginfocom.swing.MigLayout;

public class Main {

	public static Main instance;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					instance = window;
					window.frmMine.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public JFrame frmMine;
	private JMenu menu_Game;
	private JMenuBar menuBar;
	private JMenuItem menuItem;
	private JMenuItem menuItem_Exit;
	public MineView mineView;
	private JRadioButtonMenuItem radioButtonMenuItem_0;
	private JRadioButtonMenuItem radioButtonMenuItem_1;
	private JRadioButtonMenuItem radioButtonMenuItem_2;
	private JRadioButtonMenuItem radioButtonMenuItem_Custom;

	public ScoreView scoreView;

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMine = new JFrame();
		frmMine.setTitle("\u626B\u96F7");
		frmMine.setBounds(100, 100, 252, 366);
		frmMine.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMine.getContentPane()
				.setLayout(new MigLayout("", "[222px,grow,fill]", "[56.00:56.00:56.00][219px,grow,fill]"));

		JPanel panel = new JPanel();
		frmMine.getContentPane().add(panel, "cell 0 0,grow");
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		scoreView = new ScoreView();
		panel.add(scoreView);
		mineView = new MineView();
		frmMine.getContentPane().add(mineView, "cell 0 1,grow");

		menuBar = new JMenuBar();
		frmMine.setJMenuBar(menuBar);

		menu_Game = new JMenu("\u6E38\u620F");
		menuBar.add(menu_Game);

		menuItem = new JMenuItem("\u5F00\u5C40");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mineView.paintItem.reset();
			}
		});
		menu_Game.add(menuItem);

		radioButtonMenuItem_0 = new JRadioButtonMenuItem("\u521D\u7EA7");
		radioButtonMenuItem_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				radioButtonMenuItem_0.setSelected(true);
				radioButtonMenuItem_1.setSelected(false);
				radioButtonMenuItem_2.setSelected(false);
				radioButtonMenuItem_Custom.setSelected(false);
				MineModel.instance.reset(9, 9, 10);
				mineView.paintItem.reset();
				frmMine.setBounds(frmMine.getX(), frmMine.getY(), 252, 366);
			}
		});
		radioButtonMenuItem_0.setSelected(true);
		menu_Game.add(radioButtonMenuItem_0);

		radioButtonMenuItem_1 = new JRadioButtonMenuItem("\u4E2D\u7EA7");
		radioButtonMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				radioButtonMenuItem_0.setSelected(false);
				radioButtonMenuItem_1.setSelected(true);
				radioButtonMenuItem_2.setSelected(false);
				radioButtonMenuItem_Custom.setSelected(false);
				MineModel.instance.reset(16, 16, 40);
				mineView.paintItem.reset();
				frmMine.setBounds(frmMine.getX(), frmMine.getY(), 24 * 16 + 36, 24 * 16 + 150);
			}
		});
		menu_Game.add(radioButtonMenuItem_1);

		radioButtonMenuItem_2 = new JRadioButtonMenuItem("\u9AD8\u7EA7");
		radioButtonMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				radioButtonMenuItem_0.setSelected(false);
				radioButtonMenuItem_1.setSelected(false);
				radioButtonMenuItem_2.setSelected(true);
				radioButtonMenuItem_Custom.setSelected(false);
				MineModel.instance.reset(30, 16, 99);
				mineView.paintItem.reset();
				frmMine.setBounds(frmMine.getX(), frmMine.getY(), 24 * 30 + 36, 24 * 16 + 150);
			}
		});
		menu_Game.add(radioButtonMenuItem_2);

		radioButtonMenuItem_Custom = new JRadioButtonMenuItem("\u81EA\u5B9A\u4E49");
		radioButtonMenuItem_Custom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String x = JOptionPane.showInputDialog(frmMine, "¿í¶È", "É¨À×", MessageType.INFO.ordinal()),
						y = JOptionPane.showInputDialog(frmMine, "¸ß¶È", "É¨À×", MessageType.INFO.ordinal()),
						num = JOptionPane.showInputDialog(frmMine, "À×Êý", "É¨À×", MessageType.INFO.ordinal());
				;
				try {
					int x_ = Integer.parseInt(x), y_ = Integer.parseInt(y), num_ = Integer.parseInt(num);
					if (x_ == 0 || y_ == 0 || num_ == 0) {
						return;
					}
					MineModel.instance.reset(Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(num));
					mineView.paintItem.reset();
					frmMine.setBounds(frmMine.getX(), frmMine.getY(), 24 * x_ + 36, 24 * y_ + 150);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		menu_Game.add(radioButtonMenuItem_Custom);

		menuItem_Exit = new JMenuItem("\u9000\u51FA");
		menu_Game.add(menuItem_Exit);
	}

}
