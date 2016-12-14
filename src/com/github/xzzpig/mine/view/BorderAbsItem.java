package com.github.xzzpig.mine.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import com.github.xzzpig.pigapi.paint.PaintItem;
import com.github.xzzpig.pigapi.paint.Rect;

public abstract class BorderAbsItem extends PaintItem {

	private JComponent bind;
	private boolean up;

	protected BorderAbsItem(PaintItem parent, String name, JComponent bind, boolean up) {
		super(parent, name, new Rect(0, 0, 10, 10));
		this.bind = bind;
		this.up = up;
	}

	public BorderAbsItem(PaintItem parent, String name, Rect size, boolean up) {
		super(parent, name, new Rect(0, 0, 10, 10));
		this.setSize(size);
		this.up = up;
	}

	@Override
	public PaintItem clone() {
		return this;
	}

	public void fillTriangle(Graphics g, Color color, int x1, int y1, int x2, int y2, int x3, int y3) {
		Polygon filledPolygon = new Polygon();
		filledPolygon.addPoint(x1, y1);
		filledPolygon.addPoint(x2, y2);
		filledPolygon.addPoint(x3, y3);
		g.setColor(color);
		g.fillPolygon(filledPolygon);
	}

	@Override
	public Image getImage() {
		BufferedImage image = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_ARGB);
		paint(image.createGraphics());
		return image;
	}

	@Override
	public Rect getSize() {
		if (bind != null) {
			return new Rect(0, 0, bind.getHeight(), bind.getWidth());
		} else {
			return super.getSize();
		}
	}

	public boolean isUp() {
		return up;
	}

	@Override
	public void paint(Graphics g) {
		Color white = new Color(0xee, 0xee, 0xee), gray = new Color(0x80, 0x80, 0x80),
				lightgray = new Color(0xc0, 0xc0, 0xc0);
		if (isUp()) {
			fillTriangle(g, white, 0, 0, getSize().getWidth(), 0, 0, getSize().getHeight());
		} else {
			fillTriangle(g, gray, 0, 0, getSize().getWidth(), 0, 0, getSize().getHeight());
		}

		if (isUp()) {
			fillTriangle(g, gray, getSize().getWidth(), getSize().getHeight(), getSize().getWidth(), 0, 0,
					getSize().getHeight());
			g.setColor(gray);
		} else {
			fillTriangle(g, white, getSize().getWidth(), getSize().getHeight(), getSize().getWidth(), 0, 0,
					getSize().getHeight());
			g.setColor(white);
		}
		g.setColor(lightgray);
		g.fillRect(3, 3, getSize().width - 6, getSize().height - 6);
	}

	public void setUp(boolean up) {
		this.up = up;
	}
}
