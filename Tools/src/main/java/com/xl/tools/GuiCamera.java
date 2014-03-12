package com.xl.tools;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

/**
 * ��Ļ�����
 * 	--ֻ��ץȡ������ǰ����Ļ
 * @author haoxiaolei
 *
 */
public class GuiCamera {
	private String picFileDir; // �ļ���ǰ׺
	private String defaultName = "GuiCamera";
	private String imageFormat; // ͼ���ļ��ĸ�ʽ
	private String defaultImageFormat = "png";
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

	/****************************************************************
	 * Ĭ�ϵ��ļ�ǰ׺ΪGuiCamera���ļ���ʽΪPNG��ʽ 
	 ****************************************************************/
	public GuiCamera() {
		picFileDir = defaultName;
		imageFormat = defaultImageFormat;
	}

	/****************************************************************
	 * @param s
	 *            �ļ��Ĵ��·��(Ŀ¼)
	 * @param format
	 *            the format of the image file, it can be "jpg" or "png"
	 *            ������֧��JPG��PNG�ļ��Ĵ洢
	 ****************************************************************/
	public GuiCamera(String s, String format) {		
		picFileDir = s;
		imageFormat = format;
	}

	/****************************************************************
	 * ����Ļ�������� snapShot the Gui once
	 ****************************************************************/
	public boolean snapShot() {
		boolean createPicResult = false;		//�ж��Ƿ��Ѿ��ɹ�����ͼƬ
		try {
			// ������Ļ��һ��BufferedImage����screenshot
			BufferedImage screenShot = (new Robot()).createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));			
			// �����ļ�ǰ׺�������ļ���ʽ�������Զ������ļ���
			File picDir = new File(picFileDir);		//���ͼƬ��Ŀ¼
			String finalFileName = "screenPic(" + new SimpleDateFormat("yyyy-MM-dd hh.mm.ss").format(new Date()) + ")." + imageFormat;//���ɵ�ͼƬ����
			File picFile = new File(picDir.getPath() + File.separator + finalFileName);
			if(!picDir.exists()) {
				boolean b = picDir.mkdirs();
				if(b) {
					picFile.createNewFile();					
				}
			}			
			System.out.print("Save File " + picFile);
			// ��screenshot����д��ͼ���ļ�			
			createPicResult = ImageIO.write(screenShot, imageFormat, picFile);
			System.out.print("..Finished!\n");
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return createPicResult;
	}

	public static void main(String[] args) {
		GuiCamera cam = new GuiCamera("d:" + File.separator + "uploadFile" + File.separator + "temp", "png");
		if(cam.snapShot())
			System.out.println("ץȡ��Ļ�ɹ�~");
	}

}
