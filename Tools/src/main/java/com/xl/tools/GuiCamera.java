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
 * 屏幕照相机
 * 	--只能抓取整个当前的屏幕
 * @author haoxiaolei
 *
 */
public class GuiCamera {
	private String picFileDir; // 文件的前缀
	private String defaultName = "GuiCamera";
	private String imageFormat; // 图像文件的格式
	private String defaultImageFormat = "png";
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

	/****************************************************************
	 * 默认的文件前缀为GuiCamera，文件格式为PNG格式 
	 ****************************************************************/
	public GuiCamera() {
		picFileDir = defaultName;
		imageFormat = defaultImageFormat;
	}

	/****************************************************************
	 * @param s
	 *            文件的存放路径(目录)
	 * @param format
	 *            the format of the image file, it can be "jpg" or "png"
	 *            本构造支持JPG和PNG文件的存储
	 ****************************************************************/
	public GuiCamera(String s, String format) {		
		picFileDir = s;
		imageFormat = format;
	}

	/****************************************************************
	 * 对屏幕进行拍照 snapShot the Gui once
	 ****************************************************************/
	public boolean snapShot() {
		boolean createPicResult = false;		//判断是否已经成功生成图片
		try {
			// 拷贝屏幕到一个BufferedImage对象screenshot
			BufferedImage screenShot = (new Robot()).createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));			
			// 根据文件前缀变量和文件格式变量，自动生成文件名
			File picDir = new File(picFileDir);		//存放图片的目录
			String finalFileName = "screenPic(" + new SimpleDateFormat("yyyy-MM-dd hh.mm.ss").format(new Date()) + ")." + imageFormat;//生成的图片名称
			File picFile = new File(picDir.getPath() + File.separator + finalFileName);
			if(!picDir.exists()) {
				boolean b = picDir.mkdirs();
				if(b) {
					picFile.createNewFile();					
				}
			}			
			System.out.print("Save File " + picFile);
			// 将screenshot对象写入图像文件			
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
			System.out.println("抓取屏幕成功~");
	}

}
