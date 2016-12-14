package com.github.xzzpig.mine.view;

import java.awt.Font;
import java.awt.Graphics;

import com.github.xzzpig.mine.MineModel;
import com.github.xzzpig.pigapi.paint.Rect;

public class MineItem extends BorderAbsItem {

	enum MineState {
		Default, Down, Flag, Q
	}

	public static MineItem last;

	private NumberItem numberItem;

	private MineState state = MineState.Default;

	private int x, y;

	public MineItem(BorderItem parent, int x, int y) {
		super(parent, "Mine(" + x + "," + y + ")", new Rect(0, 0, 24, 24), true);
		this.x = x;
		this.y = y;
		numberItem = new NumberItem(MineModel.instance.getData(x, y), new Font("ËÎÌו", Font.CENTER_BASELINE, 100),
				getSize());
	}

	private int getHeight() {
		return (getParent().getSize().height - 3) / MineModel.instance.getSize()[1];
	}

	public int getNum() {
		return MineModel.instance.getData(x, y);
	}

	@Override
	public Rect getSize() {
		Rect rect = new Rect();
		rect.width = getWidth();
		rect.height = getHeight();
		rect.top = y * rect.height + 3;
		rect.left = x * rect.width + 3;
		return rect;
	}

	public MineState getState() {
		return state;
	}

	private int getWidth() {
		return (getParent().getSize().width - 3) / MineModel.instance.getSize()[0];
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public boolean isUp() {
		if (state == MineState.Down) {
			return false;
		} else {
			return super.isUp();
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		clarAllSubs();
		if (state == MineState.Down) {
			int num = MineModel.instance.getData(x, y);
			if (num > 0) {
				numberItem = new NumberItem(num, new Font("ËÎÌו", Font.BOLD, 100), getSize());
				numberItem.setLeft((int) (getWidth() * 0.3));
				numberItem.setTop((int) (getHeight() * 0.3));
				addSub(numberItem);
			} else if (num == -1) {
				if (last == this) {
					addSub(new MinePicItem(this, new Rect(0, 0, getHeight(), getWidth()), MinePicItem.MineState.On));
				} else {
					addSub(new MinePicItem(this, new Rect(0, 0, getHeight(), getWidth()),
							MinePicItem.MineState.Default));
				}
			}
		} else if (state == MineState.Flag) {
			addSub(new MinePicItem(this, new Rect(0, 0, getHeight(), getWidth()), MinePicItem.MineState.Flag));
		} else if (state == MineState.Q) {
			addSub(new MinePicItem(this, new Rect(0, 0, getHeight(), getWidth()), MinePicItem.MineState.Q));
		}
	}

	public void setState(MineState state) {
		this.state = state;
	}

	@Override
	public void setUp(boolean up) {
		super.setUp(up);
	}
}
