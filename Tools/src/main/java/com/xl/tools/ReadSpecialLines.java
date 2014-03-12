package com.xl.tools;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * ��ȡ�ض������ı��ļ�
 * @author haoxiaolei
 *
 */
public class ReadSpecialLines {	
	
	/**
	 * ��д��־�ļ�(��ݿ�ʼʱ��ͽ���ʱ�䡢��ȡ������)
	 * @param srcFile		Դ�ļ�
	 * @param dstFile		Ҫ��ɵ��µ��ļ�
	 * @param logStartTime	��ʼʱ��
	 * @param logEndTime	����ʱ��
	 * @param cutLines		Ҫ��ȡ������
	 * 
	 * ע��������ʱ��Ϊnull���ȡ�������֮��ȡȡ�����ռ�
	 */
	public static void readAndWriteFile(String srcFile, String dstFile, String logStartTime, String logEndTime, Integer cutLines) {
		String _dstFile = dstFile.replaceAll(":", ".");		
		FileReader fr = null;
		FileWriter fw = null;
		BufferedReader br = null;
		BufferedWriter bw = null;
		File _dFile = new File("d:" + File.separator + "uploadFile" + File.separator + "temp");	//�����ɵ��µ���־�ļ�Ŀ¼	
		LineNumberReader lnr = null;
		try {
			File dFile = new File(_dFile.getPath() + File.separator + _dstFile);
			if(!dFile.exists()) {
				boolean tempBoo = _dFile.mkdirs();
				if(tempBoo) {
					dFile.createNewFile();
				}
			}
			fr = new FileReader(new File(srcFile));
			fw = new FileWriter(dFile);
			br = new BufferedReader(fr);
			bw = new BufferedWriter(fw);
			lnr = new LineNumberReader(br);
			String aline;
			int currentLine = 0;			
			long start = System.currentTimeMillis();
			int totalLines = getTotalLines(new File(srcFile));
			while ((aline = lnr.readLine()) != null) {
				if(currentLine == 0) {
					if (aline.startsWith("[" + logStartTime)) {
						bw.write(aline);
						bw.write("\n");
						currentLine++;					
					}				
				} else {
					if(logEndTime != null) {
						bw.write(aline);
						bw.write("\n");
						currentLine++;
						if(buildTime(aline.substring(1,17)).after(buildTime(logEndTime))) {
							break;
						}
					} else {
						bw.write(aline);
						bw.write("\n");
						currentLine++;
						if((currentLine < totalLines ? currentLine : totalLines) > cutLines) break;
					}
				}
				System.out.println(aline);
				bw.flush();
			}
			
			long end = System.currentTimeMillis();
			System.out.println("һ�����ˣ�" + (end-start) + " ����");
			System.out.println("�ܹ��� " + currentLine + " ��");			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (lnr != null) {
				try {
					lnr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	/**
	 * ��װʱ��
	 * @param timeStr	�����ʱ���ַ�
	 * @return
	 */
	private static Date buildTime(String timeStr) {
		Date returnDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
		try {
			returnDate = sdf.parse(timeStr);
		} catch (ParseException e) {
			System.out.println("�㴫���ʱ���ʽ����ȷ��");
			e.printStackTrace();
		}
		return returnDate;
	}
	
	/**
	 * ��ʽ��ʱ���ַ���ݼ�д
	 * @param time
	 * @return
	 */
	private static String yearShortening(String time) {
		String timeStr = time.substring(2,time.length());
		return timeStr;
	}
	
	/**
	 * �ļ����ݵ������� 
	 * @param file
	 * @return
	 * @throws java.io.IOException
	 */
    private static int getTotalLines(File file) throws IOException { 
        FileReader in = new FileReader(file); 
        LineNumberReader reader = new LineNumberReader(in); 
        String s = reader.readLine(); 
        int lines = 0; 
        while (s != null) { 
            lines++; 
            s = reader.readLine(); 
        } 
        reader.close(); 
        in.close(); 
        return lines; 
    } 

    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * ��ʱѡ��ʱ��ʱ����Ҫ��ȡʱ�䴮Ϊ����-��-�� ʱ:��,����Ҫ���һ����
		 */
		
		ReadSpecialLines.readAndWriteFile("f:/desk/logs/SystemOut.log",
								"topomsLog(" + new SimpleDateFormat("yy-MM-dd hh:mm:ss").format(new Date()) + ").log",
//								"test.log", 
								yearShortening("2010-11-1 10:06"), null, 100);
		
//		ReadSelectedLine.buildTime("10-10-22 11:35:57");
//		System.out.println(yearShortening("2010-11-1 10:07:44"));
	}
}
