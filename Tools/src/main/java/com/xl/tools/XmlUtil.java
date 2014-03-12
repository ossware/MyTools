package com.xl.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * Xml操作类
 * @author haoxiaolei
 *
 */
@SuppressWarnings("unchecked")
public class XmlUtil {

	/**
	 * 创建xml文档
	 * @param dstFile 要创建成的xml文档
	 */
	public static boolean createXmlFile(String fileName) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		boolean returnValue = false;
		Document xmlDoc = DocumentHelper.createDocument();		//创建document对象
		Element rootElement = xmlDoc.addElement("root");		//创建xml文档的根
//		rootElement.addComment("这是文档的根...");				//加入一行注释
		rootElement.addAttribute("tm", sdf.format(new Date()));	//添加一个属性
		Element type = rootElement.addElement("type");			//创建一个子节点
//		type.addAttribute("show", "true");
		type.addText("近期发文");								//给子节点添加一个文本内容
		Element title = rootElement.addElement("title");
		title.addText("标题");
		Element filenumber = rootElement.addElement("filenumber");
		filenumber.setText("文号");
		Element dispatchdate = rootElement.addElement("dispatchdate");
		dispatchdate.setText("发文时间");
		Element keyword = rootElement.addElement("keyword");
		keyword.setText("主题词");
		Element mainsender = rootElement.addElement("mainsender");
		mainsender.setText("文送");
		//将xmlDoc中的内容写到文件中
		XMLWriter writer = null;
		try {
//			writer = new XMLWriter(new FileWriter(new File(fileName)));	//一般格式(无格式)			
			OutputFormat format = OutputFormat.createPrettyPrint();//美化格式
//			format = OutputFormat.createCompactFormat();		   //缩减格式	
			writer = new XMLWriter(new FileWriter(new File(fileName)),format);
			writer.write(xmlDoc);			
			
			returnValue = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(writer != null) writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return returnValue;
	}
	
	/**
	 * 修改节点
	 * @param fileName	被修改的对象文件
	 * @return
	 */	
	public static boolean modifyXmlFile(String srcFileName,String dstFileName) {
		boolean returnValue = false;
		SAXReader saxReader = new SAXReader();
		try {
			Document xmlDoc = saxReader.read(new File(srcFileName));
			//先用xpath查找对象(修改节点属性值)
			List list = xmlDoc.selectNodes("/root/type/@show");
			if(list != null) {
				for(Iterator iter=list.iterator();iter.hasNext();) {
					Attribute typeAttrShow = (Attribute) iter.next();
					if("true".equals(typeAttrShow.getValue())) {
						typeAttrShow.setValue("false");
					}
				}
			}
			//修改节点文本内容
			list = xmlDoc.selectNodes("/root/type");
			if(list != null) {
				for(Iterator iter=list.iterator();iter.hasNext();) {
					Element typeElement = (Element) iter.next();
					typeElement.setText("类型（近期发文/明传电报）");
				}
			}
			//重新输出xml文档
			try {
				XMLWriter writer = new XMLWriter(new FileWriter(new File(dstFileName)));
				writer.write(xmlDoc);
				writer.close();
				returnValue = true;
			} catch (IOException e) {
				e.printStackTrace();
			}			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return returnValue;
	}
	
	//测试
	public static void main(String[] args) {
		if(XmlUtil.createXmlFile("f:/desk/example.xml")) {
			System.out.println("生成xml成功~");
		}
		
//		if(XmlUtil.modifyXmlFile("f:/desk/example.xml","f:/desk/modifyed.xml")) {
//			System.out.println("修改xml成功~");
//		}
	}
}
