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
	private int base=30;//ָ��һ���ֵĿ��
	private int width=30*4;
	private int height=30;
	private static String codes = "23456789abcdefghjkmnopqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";
	private static String[] fontNames = { "����", "���Ŀ���", "����", "΢���ź�",  "����_GB2312" };
	
	Graphics2D g2d;
//	��һ����֤��ͼƬ
	public void drawImage(){
//	1.����ͼƬ����������
		BufferedImage bi=new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		try {
//	2.�õ����ƻ������õ����ʣ�
		g2d=(Graphics2D) bi.getGraphics();
//		�ı仭����ɫ
		g2d.setColor(Color.white);
		
//	3.��ʼ��ͼ
//		��һ����
//		g2d.drawLine(0, 0, width, height);
//		��һ������
//		g2d.drawRect(0, 0, width-1, height-1);
//		��һ�����ľ���
		g2d.fillRect(0, 0, width-1, height-1);
//		��һ����Բ(Բ)
//		g2d.drawOval(0, 0, width, height);
		
//		��һ���ַ���
//		�����xy�ǻ��ߵ�λ��
//		g2d.setFont(new Font("΢���ź�", Font.BOLD, 20));
//		g2d.drawString("��", 0, height-8);
		
		for (int i = 0; i < 4; i++) {
			g2d.setColor(new Color(this.getRandom(0, 150), this.getRandom(0, 150), this.getRandom(0, 150)));
			String fontName=fontNames[this.getRandom(0, fontNames.length)];
			g2d.setFont(new Font("΢���ź�", Font.BOLD, 22));
			String code=codes.charAt(this.getRandom(0, codes.length()))+"";
			
//			���޷���ת���ѻ���ת���ٻ�������ٰѻ���ת����
			double theta=this.getRandom(-45, 45);
//			1����ת�Ļ���,23:x,y:�����ĸ�����Ϊ��ת��
			g2d.rotate(theta*Math.PI/180,base*i+4, height-8);
//			����
			g2d.drawString(code, base*i+4, height-8);
//			��ת����
			g2d.rotate(-theta*Math.PI/180,base*i+4, height-8);
		}
//		��������
		for (int i = 0; i < 6; i++) {
			g2d.setColor(new Color(this.getRandom(0, 150), this.getRandom(0, 150), this.getRandom(0, 150)));
			g2d.drawLine(this.getRandom(0, width)
					, this.getRandom(0, width)
					, this.getRandom(0, width)
					, this.getRandom(0, width));
		}
		
//		�����߿�
		g2d.setColor(Color.gray);
		g2d.drawRect(0, 0, width-1, height-1);
//	4.����ͼƬ��ָ��λ��(���浽Ӳ���ϻ���ֱ�ӷ��͸������)
			ImageIO.write(bi, "JPEG", 
					new FileOutputStream(new File("e:/vc2.jpg")));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
//	5.�ͷ���Դ
			if (g2d!=null) {
				g2d.dispose();
			}
		}
	}
//	���������start-end֮��������
	public static int getRandom(int start,int end){
		return new Random().nextInt((end-start)+start);
	}
		
	
	
	public static void main(String[] args) {
		VerifyCode2 vc2=new VerifyCode2();
		vc2.drawImage();
	}
}
