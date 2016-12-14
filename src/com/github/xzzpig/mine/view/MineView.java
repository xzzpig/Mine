package com.github.xzzpig.mine.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

public class MineView extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -863733082917703600L;

	private boolean left, right;

	public MineItem mineItem;

	public BorderItem paintItem;

	public MineView() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				paintItem.clickOnMineItem(mineItem, e.getButton() == 3);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == 1) {
					left = true;
				} else if (e.getButton() == 3) {
					right = true;
				}
				mineItem = paintItem.getMineItem(e.getX(), e.getY());
				if (mineItem == null) {
					return;
				}
				paintItem.pressOnMineItem(mineItem, left, right);
				repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == 1) {
					left = false;
				} else if (e.getButton() == 3) {
					right = false;
				}
				if (mineItem == null) {
					return;
				}
				paintItem.releaseOnMineItem(mineItem, left, right);
				repaint();
			}
		});
		paintItem = new BorderItem(null, "main", this, false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image image = paintItem.getFinalImage();
		g.drawImage(image, 0, 0, null);
	}

}
