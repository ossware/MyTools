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
 * Xml操作类
 * @author haoxiaolei
 *
 */
@SuppressWarnings("unchecked")
public class XmlUtil2 {

	/**
	 * 创建xml文档
	 * @param dstFile 要创建成的xml文档
	 */	
	public static boolean createXmlFile(DroolXmlBean dxb) {
        boolean returnValue = false;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Document xmlDoc = DocumentHelper.createDocument();           //创建document对象
            Element rootElement = xmlDoc.addElement("r");                //创建xml文档的根
            rootElement.addAttribute("tm", sdf.format(new Date()));     //添加一个属性
            List fieldList = GetBeanFields.getBeanFields(dxb);
            if (fieldList != null && fieldList.size() > 0) {
                for (Iterator iter = fieldList.iterator(); iter.hasNext();) {
                    String elementStr = (String) iter.next();
                    Element element = rootElement.addElement(elementStr);//添加xml标签元素
                    StringBuffer sb = new StringBuffer();
                    try {
                        //组装对象中的方法                    	
                        sb.append("get");
                        sb.append(elementStr.substring(0, 1).toUpperCase());
                        sb.append(elementStr.substring(1, elementStr.length()));
                        Method m = dxb.getClass().getDeclaredMethod(sb.toString());
                        //添加标签的值
                        element.addText(m.invoke(dxb) != null ? m.invoke(dxb).toString() : "");     //m.invoke(dxb)是调用组装好的方法
                    } catch (InvocationTargetException e) {
                        System.out.println("执行【" + sb.toString() + "】方法失败");
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        System.out.println("没有【" + sb.toString() + "】方法");
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        System.out.println("非法访问【" + sb.toString() + "】方法");
                        e.printStackTrace();
                    }
                }
            }
            //将xmlDoc中的内容写到文件中
            XMLWriter writer = null;
            try {
                String rulerEngineXmlPath = "d:" + File.separator + "rulerEnginePath" + File.separator + "xml";
                System.out.println(rulerEngineXmlPath);
                File dir = new File(rulerEngineXmlPath);
                if (!dir.exists()) dir.mkdir();
                OutputFormat format = OutputFormat.createPrettyPrint();     //美化格式
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
    	dxb.setTitle("这里是填写标题的标签");
        createXmlFile(dxb);
    }
}
