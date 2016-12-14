package com.github.xzzpig.mine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MineModel {
	public static final MineModel instance = new MineModel();

	private static Random random = new Random();

	private int[][] data;

	private int size_x, size_y, boom_num;

	public MineModel() {
		reset(9, 9, 10);
	}

	public int getBoomNum() {
		return boom_num;
	}

	public int getData(int x, int y) {
		return data[x][y];
	}

	private int getNum(int x, int y) {
		int n = 0;
		if (data[x][y] == -1) {
			return -1;
		}
		try {
			if (data[x - 1][y - 1] == -1)
				n++;
		} catch (Exception e) {
		}
		try {
			if (data[x - 1][y] == -1)
				n++;
		} catch (Exception e) {
		}
		try {
			if (data[x - 1][y + 1] == -1)
				n++;
		} catch (Exception e) {
		}
		try {
			if (data[x][y - 1] == -1)
				n++;
		} catch (Exception e) {
		}
		try {
			if (data[x][y + 1] == -1)
				n++;
		} catch (Exception e) {
		}
		try {
			if (data[x + 1][y - 1] == -1)
				n++;
		} catch (Exception e) {
		}
		try {
			if (data[x + 1][y] == -1)
				n++;
		} catch (Exception e) {
		}
		try {
			if (data[x + 1][y + 1] == -1)
				n++;
		} catch (Exception e) {
		}
		return n;
	}

	public int[] getSize() {
		return new int[] { size_x, size_y };
	}

	public void print() {
		for (int j = 0; j < size_y; j++) {
			for (int i = 0; i < size_x; i++) {
				if (data[i][j] == -1) {
					System.out.print("* ");
				} else {
					System.out.print(data[i][j] + " ");
				}
			}
			System.out.println();
		}
	}

	public void rebuild() {
		random = new Random();
		data = new int[size_x][size_y];
		List<Integer> l = new ArrayList<>();
		for (int i = 0; i < boom_num; i++) {
			l.add(-1);
		}
		for (int i = 0; i < size_x * size_y - boom_num; i++) {
			l.add(random.nextInt(l.size()), 0);
		}
		for (int j = 0; j < size_y; j++) {
			for (int i = 0; i < size_x; i++) {
				data[i][j] = l.get(i + j * size_y);
			}
		}
		for (int j = 0; j < size_y; j++) {
			for (int i = 0; i < size_x; i++) {
				data[i][j] = getNum(i, j);
			}
		}
	}

	public void reset(int x, int y, int num) {
		size_x = x;
		size_y = y;
		boom_num = num;
		rebuild();
	}
}
