package com.github.xzzpig.mine.resource;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class R {
	public static Image Mine_Flag;
	public static Image Mine_Mine_Default;
	public static Image Mine_Mine_On;
	public static Image Mine_Q;
	public static Image Mine_Smile_Dead;
	public static Image Mine_Smile_Down;
	public static Image Mine_Smile_Happy;
	public static Image Mine_Smile_Win;
	public static Image Mine_Smile_WTF;
	static {
		try {
			Mine_Mine_Default = ImageIO.read(R.class.getClassLoader()
					.getResourceAsStream("com/github/xzzpig/mine/resource/Mine_Mine_Default.png"));
			Mine_Mine_On = ImageIO.read(
					R.class.getClassLoader().getResourceAsStream("com/github/xzzpig/mine/resource/Mine_Mine_On.png"));
			Mine_Flag = ImageIO.read(
					R.class.getClassLoader().getResourceAsStream("com/github/xzzpig/mine/resource/Mine_Flag.png"));
			Mine_Q = ImageIO
					.read(R.class.getClassLoader().getResourceAsStream("com/github/xzzpig/mine/resource/Mine_Q.png"));
			Mine_Smile_Dead = ImageIO.read(R.class.getClassLoader()
					.getResourceAsStream("com/github/xzzpig/mine/resource/Mine_Smile_Dead.png"));
			Mine_Smile_Down = ImageIO.read(R.class.getClassLoader()
					.getResourceAsStream("com/github/xzzpig/mine/resource/Mine_Smile_Down.png"));
			Mine_Smile_Happy = ImageIO.read(R.class.getClassLoader()
					.getResourceAsStream("com/github/xzzpig/mine/resource/Mine_Smile_Happy.png"));
			Mine_Smile_WTF = ImageIO.read(
					R.class.getClassLoader().getResourceAsStream("com/github/xzzpig/mine/resource/Mine_Smile_WTF.png"));
			Mine_Smile_Win = ImageIO.read(
					R.class.getClassLoader().getResourceAsStream("com/github/xzzpig/mine/resource/Mine_Smile_Win.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
