package com.github.xzzpig.mine.view;

import java.awt.Graphics;
import java.awt.Image;

import com.github.xzzpig.mine.resource.R;
import com.github.xzzpig.pigapi.paint.PaintItem;
import com.github.xzzpig.pigapi.paint.Rect;

public class MinePicItem extends PaintItem {

	public enum MineState {
		Default, Flag, On, Q
	}

	public MineState state;

	public MinePicItem(PaintItem parent, Rect size, MineState state) {
		super(parent, "MinePic", size);
		this.state = state;
	}

	@Override
	public PaintItem clone() {
		return new MinePicItem(getParent(), new Rect(getSize()), state);
	}

	@Override
	public Image getImage() {
		Image image = null;
		if (state == MineState.Default) {
			image = R.Mine_Mine_Default;
		} else if (state == MineState.On) {
			image = R.Mine_Mine_On;

		} else if (state == MineState.Flag) {
			image = R.Mine_Flag;
		} else {
			image = R.Mine_Q;
		}
		return image;
	}

	@Override
	public void paint(Graphics g) {
	}

}
