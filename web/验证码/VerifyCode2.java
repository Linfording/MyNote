package verifycode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

public class VerifyCode2 {
	private int base=30;//指定一个字的宽度
	private int width=30*4;
	private int height=30;
	private static String codes = "23456789abcdefghjkmnopqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";
	private static String[] fontNames = { "宋体", "华文楷体", "黑体", "微软雅黑",  "楷体_GB2312" };
	
	Graphics2D g2d;
//	画一张验证码图片
	public void drawImage(){
//	1.创建图片缓冲区对象
		BufferedImage bi=new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		try {
//	2.得到绘制环境（拿到画笔）
		g2d=(Graphics2D) bi.getGraphics();
//		改变画笔颜色
		g2d.setColor(Color.white);
		
//	3.开始绘图
//		画一条线
//		g2d.drawLine(0, 0, width, height);
//		画一个矩形
//		g2d.drawRect(0, 0, width-1, height-1);
//		画一个填充的矩形
		g2d.fillRect(0, 0, width-1, height-1);
//		画一个椭圆(圆)
//		g2d.drawOval(0, 0, width, height);
		
//		画一个字符串
//		这里的xy是基线的位置
//		g2d.setFont(new Font("微软雅黑", Font.BOLD, 20));
//		g2d.drawString("中", 0, height-8);
		
		for (int i = 0; i < 4; i++) {
			g2d.setColor(new Color(this.getRandom(0, 150), this.getRandom(0, 150), this.getRandom(0, 150)));
			String fontName=fontNames[this.getRandom(0, fontNames.length)];
			g2d.setFont(new Font("微软雅黑", Font.BOLD, 22));
			String code=codes.charAt(this.getRandom(0, codes.length()))+"";
			
//			字无法旋转，把画旋转了再画，最后再把画旋转回来
			double theta=this.getRandom(-45, 45);
//			1：旋转的弧度,23:x,y:基于哪个店作为旋转点
			g2d.rotate(theta*Math.PI/180,base*i+4, height-8);
//			划字
			g2d.drawString(code, base*i+4, height-8);
//			旋转回来
			g2d.rotate(-theta*Math.PI/180,base*i+4, height-8);
		}
//		画干扰线
		for (int i = 0; i < 6; i++) {
			g2d.setColor(new Color(this.getRandom(0, 150), this.getRandom(0, 150), this.getRandom(0, 150)));
			g2d.drawLine(this.getRandom(0, width)
					, this.getRandom(0, width)
					, this.getRandom(0, width)
					, this.getRandom(0, width));
		}
		
//		给个边框
		g2d.setColor(Color.gray);
		g2d.drawRect(0, 0, width-1, height-1);
//	4.保存图片到指定位置(保存到硬盘上或者直接发送给浏览器)
			ImageIO.write(bi, "JPEG", 
					new FileOutputStream(new File("e:/vc2.jpg")));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
//	5.释放资源
			if (g2d!=null) {
				g2d.dispose();
			}
		}
	}
//	返回随机数start-end之间的随机数
	public static int getRandom(int start,int end){
		return new Random().nextInt((end-start)+start);
	}
		
	
	
	public static void main(String[] args) {
		VerifyCode2 vc2=new VerifyCode2();
		vc2.drawImage();
	}
}
