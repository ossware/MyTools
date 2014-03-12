package com.xl.tools.catchPicUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UtilSeriz {
	/**
	 * ���������л��������ļ���
	 * 
	 * @param
	 * @throwsException
	 */
	public static void writeObject(Object o, String strPath) throws Exception {
		File f = new File(strPath);
		if (f.exists()) {
			f.delete();
		}
		FileOutputStream os = new FileOutputStream(f);
		// ObjectOutputStream ������
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(o);
		oos.close();
		os.close();
	}

	/**
	 * �����л�,�������ļ�ת��Ϊ����
	 * 
	 * @paramf
	 * @return
	 * @throwsException
	 */
	public static Object readObject(String strPath) throws Exception {
		File f = new File(strPath);
		if (!f.exists()) {
			return null;
		}
		InputStream is = new FileInputStream(f);
		// ObjectOutputStream ������
		ObjectInputStream ois = new ObjectInputStream(is);
		return ois.readObject();
	}
}
