package com.xl.tools;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xl.beans.User;

/**
 * 得到bean里的字段
 * @author haoxiaolei
 *
 */
@SuppressWarnings("unchecked")
public class GetBeanFields {

	/**
	 * 根据类的名字获取类中所有字段
	 * @param obj
	 * @return
	 */	
	public static List<String> getBeanFields(Object obj) {
		List<String> fieldsList = new ArrayList<String>();
		try {
			Class cls = Class.forName(obj.getClass().getName());
			for(;!cls.equals(Object.class);cls=cls.getSuperclass()) {
				Field[] field = cls.getDeclaredFields();
				for(int i=0;i<field.length;i++) {
					System.out.println(field[i].getName());
					fieldsList.add(field[i].getName());
				}				
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return fieldsList;
	}
	
	public static void main(String[] args) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		User u = new User();
		u.setName("xiaolei");
		u.setAge(25);
		u.setBirthday(new Date());
		StringBuffer sb = new StringBuffer();
		//组装对象中的方法
        sb.append("get");
        sb.append("name".substring(0, 1).toUpperCase());
        sb.append("name".substring(1, "name".length()));
		Method m = u.getClass().getDeclaredMethod(sb.toString());
		//调用对象中的方法
		m.invoke(u);
		System.out.println(sb);
//		GetBeanFields.getBeanFields(u);
//		System.out.println(User.class.getName());
	}
}
