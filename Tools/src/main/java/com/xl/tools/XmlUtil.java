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
 * Xml������
 * @author haoxiaolei
 *
 */
@SuppressWarnings("unchecked")
public class XmlUtil {

	/**
	 * ����xml�ĵ�
	 * @param dstFile Ҫ�����ɵ�xml�ĵ�
	 */
	public static boolean createXmlFile(String fileName) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		boolean returnValue = false;
		Document xmlDoc = DocumentHelper.createDocument();		//����document����
		Element rootElement = xmlDoc.addElement("root");		//����xml�ĵ��ĸ�
//		rootElement.addComment("�����ĵ��ĸ�...");				//����һ��ע��
		rootElement.addAttribute("tm", sdf.format(new Date()));	//���һ������
		Element type = rootElement.addElement("type");			//����һ���ӽڵ�
//		type.addAttribute("show", "true");
		type.addText("���ڷ���");								//���ӽڵ����һ���ı�����
		Element title = rootElement.addElement("title");
		title.addText("����");
		Element filenumber = rootElement.addElement("filenumber");
		filenumber.setText("�ĺ�");
		Element dispatchdate = rootElement.addElement("dispatchdate");
		dispatchdate.setText("����ʱ��");
		Element keyword = rootElement.addElement("keyword");
		keyword.setText("�����");
		Element mainsender = rootElement.addElement("mainsender");
		mainsender.setText("����");
		//��xmlDoc�е�����д���ļ���
		XMLWriter writer = null;
		try {
//			writer = new XMLWriter(new FileWriter(new File(fileName)));	//һ���ʽ(�޸�ʽ)			
			OutputFormat format = OutputFormat.createPrettyPrint();//������ʽ
//			format = OutputFormat.createCompactFormat();		   //������ʽ	
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
	 * �޸Ľڵ�
	 * @param fileName	���޸ĵĶ����ļ�
	 * @return
	 */	
	public static boolean modifyXmlFile(String srcFileName,String dstFileName) {
		boolean returnValue = false;
		SAXReader saxReader = new SAXReader();
		try {
			Document xmlDoc = saxReader.read(new File(srcFileName));
			//����xpath���Ҷ���(�޸Ľڵ�����ֵ)
			List list = xmlDoc.selectNodes("/root/type/@show");
			if(list != null) {
				for(Iterator iter=list.iterator();iter.hasNext();) {
					Attribute typeAttrShow = (Attribute) iter.next();
					if("true".equals(typeAttrShow.getValue())) {
						typeAttrShow.setValue("false");
					}
				}
			}
			//�޸Ľڵ��ı�����
			list = xmlDoc.selectNodes("/root/type");
			if(list != null) {
				for(Iterator iter=list.iterator();iter.hasNext();) {
					Element typeElement = (Element) iter.next();
					typeElement.setText("���ͣ����ڷ���/�����籨��");
				}
			}
			//�������xml�ĵ�
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
	
	//����
	public static void main(String[] args) {
		if(XmlUtil.createXmlFile("f:/desk/example.xml")) {
			System.out.println("����xml�ɹ�~");
		}
		
//		if(XmlUtil.modifyXmlFile("f:/desk/example.xml","f:/desk/modifyed.xml")) {
//			System.out.println("�޸�xml�ɹ�~");
//		}
	}
}
