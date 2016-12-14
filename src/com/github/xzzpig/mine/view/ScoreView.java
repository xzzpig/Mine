package com.github.xzzpig.mine.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

import com.github.xzzpig.mine.Main;
import com.github.xzzpig.mine.view.SmilePicItem.SmileState;

public class ScoreView extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -863733082917703600L;

	public ScoreItem paintItem;

	public ScoreView() {
		paintItem = new ScoreItem(null, "Score", this);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 1 && paintItem.picItem.getSize().contains(e.getX(), e.getY())) {
					Main.instance.mineView.paintItem.reset();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == 1 && paintItem.picItem.getSize().contains(e.getX(), e.getY()))
					paintItem.picItem.state = SmileState.Down;
				repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == 1 && paintItem.picItem.getSize().contains(e.getX(), e.getY()))
					paintItem.picItem.state = SmileState.Happy;
				repaint();
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image image = paintItem.getFinalImage();
		g.drawImage(image, 0, 0, null);
	}

}
