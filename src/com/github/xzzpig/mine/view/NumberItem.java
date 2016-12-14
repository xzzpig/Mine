package com.github.xzzpig.mine.view;

import java.awt.Color;
import java.awt.Font;

import com.github.xzzpig.pigapi.paint.Rect;
import com.github.xzzpig.pigapi.paint.ZoomTextItem;

public class NumberItem extends ZoomTextItem {

	int num;

	public NumberItem(int num, Font f, Rect size) {
		super("Num", num + "", f, size, ZoomType.Suit);
		this.num = num;
	}

	@Override
	public Color getColor() {
		Color color = Color.BLACK;
		if (getNum() == 1) {
			color = Color.BLUE;
		} else if (getNum() == 2) {
			color = Color.GREEN;
		} else {
			color = Color.RED;
		}
		return color;
	}

	public int getNum() {
		return num;
	}

	@Override
	public String getStr() {
		return getNum() + "";
	}

	public void setNum(int num) {
		this.num = num;
	}
}