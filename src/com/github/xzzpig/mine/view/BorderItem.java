package com.github.xzzpig.mine.view;

import javax.swing.JComponent;

import com.github.xzzpig.mine.Main;
import com.github.xzzpig.mine.MineModel;
import com.github.xzzpig.mine.view.MineItem.MineState;
import com.github.xzzpig.mine.view.SmilePicItem.SmileState;
import com.github.xzzpig.pigapi.paint.PaintItem;

public class BorderItem extends BorderAbsItem {

	private boolean doub;

	private boolean finish;

	private MineItem[][] mineItems;

	public BorderItem(PaintItem parent, String name, JComponent bind, boolean up) {
		super(parent, name, bind, up);
		int size_x = MineModel.instance.getSize()[0];
		int size_y = MineModel.instance.getSize()[1];
		mineItems = new MineItem[size_x][size_y];
		for (int j = 0; j < size_y; j++) {
			for (int i = 0; i < size_x; i++) {
				mineItems[i][j] = new MineItem(this, i, j);
				addSub(mineItems[i][j]);
			}
		}
	}

	public void clickOnMineItem(MineItem item, boolean right) {
		if (item == null || finish) {
			return;
		}
		if (!right) {
			if (item.getState() != MineState.Default) {
				return;
			}
			item.setState(MineState.Down);
			MineItem.last = item;
			int n = item.getNum();
			if (n == -1) {
				{
					Main.instance.scoreView.paintItem.picItem.state = SmileState.Dead;
					Main.instance.scoreView.repaint();
					finish = true;
				}
				return;
			}
			if (n == 0) {
				for (int i = -1; i <= 1; i++) {
					for (int j = -1; j <= 1; j++) {
						try {
							MineItem m = mineItems[item.getX() + i][item.getY() + j];
							if (m.getState() == MineState.Down) {
								continue;
							}
							int n2 = m.getNum();
							if (n2 >= 0) {
								clickOnMineItem(m, false);
							}
						} catch (Exception e) {
						}
					}
				}
			}
		} else {
			if (item.getState() == MineState.Default) {
				item.setState(MineState.Flag);
			} else if (item.getState() == MineState.Flag) {
				item.setState(MineState.Q);
			} else if (item.getState() == MineState.Q) {
				item.setState(MineState.Default);
			}
		}
		for (MineItem[] mines : mineItems) {
			for (MineItem m : mines) {
				int num = m.getNum();
				if (m.getState() != MineState.Down && num >= 0) {
					return;
				}
			}
		}
		{
			Main.instance.scoreView.paintItem.picItem.state = SmileState.Win;
			Main.instance.scoreView.repaint();
			finish = true;
		}
	}

	public MineItem getMineItem(int loc_x, int loc_y) {
		int size_x = MineModel.instance.getSize()[0];
		int size_y = MineModel.instance.getSize()[1];
		for (int j = 0; j < size_y; j++) {
			for (int i = 0; i < size_x; i++) {
				if (mineItems[i][j].getSize().contains(loc_x, loc_y)) {
					return mineItems[i][j];
				}
			}
		}
		return null;
	}

	public void pressOnMineItem(MineItem item, boolean left, boolean right) {
		if (finish) {
			return;
		}
		int size_x = MineModel.instance.getSize()[0];
		int size_y = MineModel.instance.getSize()[1];
		if (left) {
			item.setUp(false);
			{
				Main.instance.scoreView.paintItem.picItem.state = SmileState.WTF;
				Main.instance.scoreView.repaint();
			}
			if (right) {
				for (int i = item.getX() - 1; i <= item.getX() + 1; i++) {
					if (i < 0 || i >= size_x) {
						continue;
					}
					for (int j = item.getY() - 1; j <= item.getY() + 1; j++) {
						if (j < 0 || j > size_y) {
							continue;
						}
						try {
							mineItems[i][j].setUp(false);
						} catch (Exception e) {
						}
					}
				}
				doub = true;
			}
		}
	}

	public void releaseOnMineItem(MineItem item, boolean left, boolean right) {
		if (finish) {
			return;
		}
		{
			Main.instance.scoreView.paintItem.picItem.state = SmileState.Happy;
			Main.instance.scoreView.repaint();
		}
		for (PaintItem paintItem : getSubs()) {
			if (paintItem instanceof MineItem) {
				((MineItem) paintItem).setUp(true);
			}
		}
		if (doub && item.getState() == MineState.Down) {
			doub = false;
			int count_b = 0, cout_f = 0;
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					try {
						MineItem m = mineItems[item.getX() + i][item.getY() + j];
						if (m == item) {
							continue;
						}
						if (m.getState() == MineState.Flag && m.getNum() != -1) {
							cout_f++;
						}
						if (m.getState() == MineState.Default && m.getNum() == -1) {
							count_b++;
						}
					} catch (Exception e) {
					}
				}
			}
			if (count_b == 0 || (count_b != 0 && cout_f != 0)) {
				for (int i = -1; i <= 1; i++) {
					for (int j = -1; j <= 1; j++) {
						try {
							MineItem m = mineItems[item.getX() + i][item.getY() + j];
							clickOnMineItem(m, false);
						} catch (Exception e) {
						}
					}
				}
			}
		}
	}

	public void reset() {
		MineModel.instance.reset(MineModel.instance.getSize()[0], MineModel.instance.getSize()[1],
				MineModel.instance.getBoomNum());
		for (MineItem[] mines : mineItems) {
			for (MineItem m : mines) {
				m.clarAllSubs();
				m.setState(MineState.Default);
			}
		}
		int size_x = MineModel.instance.getSize()[0];
		int size_y = MineModel.instance.getSize()[1];
		mineItems = new MineItem[size_x][size_y];
		for (int j = 0; j < size_y; j++) {
			for (int i = 0; i < size_x; i++) {
				mineItems[i][j] = new MineItem(this, i, j);
				addSub(mineItems[i][j]);
			}
		}
		finish = false;
		Main.instance.mineView.repaint();
	}
}
