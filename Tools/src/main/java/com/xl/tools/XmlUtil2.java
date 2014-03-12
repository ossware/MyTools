package com.xl.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.xl.beans.DroolXmlBean;

/**
 * Xml������
 * @author haoxiaolei
 *
 */
@SuppressWarnings("unchecked")
public class XmlUtil2 {

	/**
	 * ����xml�ĵ�
	 * @param dstFile Ҫ�����ɵ�xml�ĵ�
	 */	
	public static boolean createXmlFile(DroolXmlBean dxb) {
        boolean returnValue = false;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Document xmlDoc = DocumentHelper.createDocument();           //����document����
            Element rootElement = xmlDoc.addElement("r");                //����xml�ĵ��ĸ�
            rootElement.addAttribute("tm", sdf.format(new Date()));     //���һ������
            List fieldList = GetBeanFields.getBeanFields(dxb);
            if (fieldList != null && fieldList.size() > 0) {
                for (Iterator iter = fieldList.iterator(); iter.hasNext();) {
                    String elementStr = (String) iter.next();
                    Element element = rootElement.addElement(elementStr);//���xml��ǩԪ��
                    StringBuffer sb = new StringBuffer();
                    try {
                        //��װ�����еķ���                    	
                        sb.append("get");
                        sb.append(elementStr.substring(0, 1).toUpperCase());
                        sb.append(elementStr.substring(1, elementStr.length()));
                        Method m = dxb.getClass().getDeclaredMethod(sb.toString());
                        //��ӱ�ǩ��ֵ
                        element.addText(m.invoke(dxb) != null ? m.invoke(dxb).toString() : "");     //m.invoke(dxb)�ǵ�����װ�õķ���
                    } catch (InvocationTargetException e) {
                        System.out.println("ִ�С�" + sb.toString() + "������ʧ��");
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        System.out.println("û�С�" + sb.toString() + "������");
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        System.out.println("�Ƿ����ʡ�" + sb.toString() + "������");
                        e.printStackTrace();
                    }
                }
            }
            //��xmlDoc�е�����д���ļ���
            XMLWriter writer = null;
            try {
                String rulerEngineXmlPath = "d:" + File.separator + "rulerEnginePath" + File.separator + "xml";
                System.out.println(rulerEngineXmlPath);
                File dir = new File(rulerEngineXmlPath);
                if (!dir.exists()) dir.mkdir();
                OutputFormat format = OutputFormat.createPrettyPrint();     //������ʽ
                writer = new XMLWriter(new FileWriter(new File(rulerEngineXmlPath + "/example.xml")), format);
                writer.write(xmlDoc);
                returnValue = true;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (writer != null) writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        
        return returnValue;
    }

    public static void main(String[] args) {
    	DroolXmlBean dxb = new DroolXmlBean();
    	dxb.setTitle("��������д����ı�ǩ");
        createXmlFile(dxb);
    }
}
