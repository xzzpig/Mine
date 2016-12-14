package com.github.xzzpig.mine.view;

import java.awt.Graphics;
import java.awt.Image;

import com.github.xzzpig.mine.resource.R;
import com.github.xzzpig.pigapi.paint.PaintItem;
import com.github.xzzpig.pigapi.paint.Rect;

public class SmilePicItem extends PaintItem {

	public enum SmileState {
		Dead, Down, Happy, Win, WTF
	}

	public SmileState state;

	public SmilePicItem(PaintItem parent, SmileState state) {
		super(parent, "SmilePic", new Rect(0, 0, 10, 10));
		this.state = state;
	}

	@Override
	public PaintItem clone() {
		return new SmilePicItem(getParent(), state);
	}

	@Override
	public Image getImage() {
		Image image = null;
		if (state == SmileState.Dead) {
			image = R.Mine_Smile_Dead;
		} else if (state == SmileState.Happy) {
			image = R.Mine_Smile_Happy;

		} else if (state == SmileState.Down) {
			image = R.Mine_Smile_Down;
		} else if (state == SmileState.Win) {
			image = R.Mine_Smile_Win;
		} else {
			image = R.Mine_Smile_WTF;
		}
		return image;
	}

	@Override
	public Rect getSize() {
		Rect rect = new Rect(0, 0, 39, 39);
		rect.left = (getParent().getSize().width - 39) / 2;
		rect.top = (getParent().getSize().height - 39) / 2;
		return rect;
	}

	@Override
	public void paint(Graphics g) {
	}

	public void setState(SmileState state) {
		this.state = state;
	}
}
