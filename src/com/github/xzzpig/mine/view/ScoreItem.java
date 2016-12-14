package com.github.xzzpig.mine.view;

import javax.swing.JComponent;

import com.github.xzzpig.mine.view.SmilePicItem.SmileState;
import com.github.xzzpig.pigapi.paint.PaintItem;

public class ScoreItem extends BorderAbsItem {

	public SmilePicItem picItem;

	protected ScoreItem(PaintItem parent, String name, JComponent bind) {
		super(parent, name, bind, false);
		picItem = new SmilePicItem(this, SmileState.Happy);
		addSub(picItem);
	}
}
