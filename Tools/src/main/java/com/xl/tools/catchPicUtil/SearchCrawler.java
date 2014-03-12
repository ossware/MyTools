package com.xl.tools.catchPicUtil;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

/**
 * ˵��:ץͼ����
 * 
 * @author haoxiaolei
 * 
 */
// ����Web������
public class SearchCrawler implements Runnable {
	/*
	 * disallowListCache����robot������������URL�� RobotЭ����Webվ��ĸ�Ŀ¼������һ��robots.txt�ļ�,
	 * �涨վ���ϵ���Щҳ�������������ġ� ��������Ӧ��������������������Щ����,������robots.txt��һ������: # robots.txt for
	 * http://somehost.com/ User-agent: * Disallow: /cgi-bin/ Disallow:
	 * /registration # /Disallow robots on registration page Disallow: /login
	 */
	public static SimpleBloomFilter filterUrl;
	public static SimpleBloomFilter filterImg;

	private HashMap<String, ArrayList<String>> disallowListCache = new HashMap<String, ArrayList<String>>();
	ArrayList<String> errorList = new ArrayList<String>();// ������Ϣ
	ArrayList<String> result = new ArrayList<String>(); // �������Ľ��
	String startUrl;// ��ʼ���������
	int maxUrl;// ������url��
	String searchString;// Ҫ�������ַ���(Ӣ��)
	boolean caseSensitive = false;// �Ƿ����ִ�Сд
	boolean limitHost = false;// �Ƿ������Ƶ�����������
	private static String outdir;

	private static String seroutdir;
	private static String seroutdirimg;
	private boolean blnFlag = false;

	/***
	 * ���캯��
	 * 
	 * @param startUrl
	 * @param maxUrl
	 * @param searchString
	 */
	public SearchCrawler(String startUrl, int maxUrl, String searchString) {
		this.startUrl = startUrl;
		this.maxUrl = maxUrl;
		this.searchString = searchString;
	}

	public ArrayList<String> getResult() {
		return result;
	}

	public void run() {// ���������߳�
		new Thread(new TimeWrite2File()).start();
		blnFlag = true;
		crawl(startUrl, maxUrl, searchString, limitHost, caseSensitive);

	}

	// ���URL��ʽ
	private URL verifyUrl(String url) {
		// ֻ����HTTP URLs.
		if (!url.toLowerCase().startsWith("http://"))
			return null;

		URL verifiedUrl = null;
		try {
			verifiedUrl = new URL(url);
		} catch (Exception e) {
			return null;
		}

		return verifiedUrl;
	}

	// ���robot�Ƿ�������ʸ�����URL.
	private boolean isRobotAllowed(URL urlToCheck) {
		String host = urlToCheck.getHost().toLowerCase();// ��ȡ����RUL������
		// System.out.println("����="+host);

		// ��ȡ����������������URL����
		ArrayList<String> disallowList = disallowListCache.get(host);

		// �����û�л���,���ز����档
		if (disallowList == null) {
			disallowList = new ArrayList<String>();
			try {
				URL robotsFileUrl = new URL("http://" + host + "/robots.txt");
				BufferedReader reader = new BufferedReader(new InputStreamReader(robotsFileUrl.openStream()));

				// ��robot�ļ���������������ʵ�·���б�
				String line;
				while ((line = reader.readLine()) != null) {
					if (line.indexOf("Disallow:") == 0) {// �Ƿ����"Disallow:"
						String disallowPath = line.substring("Disallow:".length());// ��ȡ���������·��

						// ����Ƿ���ע�͡�
						int commentIndex = disallowPath.indexOf("#");
						if (commentIndex != -1) {
							disallowPath = disallowPath.substring(0, commentIndex);// ȥ��ע��
						}

						disallowPath = disallowPath.trim();
						disallowList.add(disallowPath);
					}
				}

				// �����������������ʵ�·����
				disallowListCache.put(host, disallowList);
			} catch (Exception e) {
				return true; // webվ���Ŀ¼��û��robots.txt�ļ�,������
			}
		}

		String file = urlToCheck.getFile();
		// System.out.println("�ļ�getFile()="+file);
		for (int i = 0; i < disallowList.size(); i++) {
			String disallow = disallowList.get(i);
			if (file.startsWith(disallow)) {
				return false;
			}
		}

		return true;
	}

	private String downloadPage(URL pageUrl) {
		try {
			// Open connection to URL for reading.
			BufferedReader reader = new BufferedReader(new InputStreamReader(pageUrl.openStream()));

			// Read page into buffer.
			String line;
			StringBuffer pageBuffer = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				pageBuffer.append(line);
			}

			return pageBuffer.toString();
		} catch (Exception e) {
		}

		return null;
	}

	// ��URL��ȥ��"www"
	private String removeWwwFromUrl(String url) {
		int index = url.indexOf("://www.");
		if (index != -1) {
			return url.substring(0, index + 3) + url.substring(index + 7);
		}

		return (url);
	}

	// ����ҳ�沢�ҳ�����
	private ArrayList<String> retrieveLinks(URL pageUrl, String pageContents, boolean limitHost) {
		// ��������ʽ�������ӵ�ƥ��ģʽ��
		Pattern p = Pattern.compile("<a\\s+href\\s*=\\s*\"?(.*?)[\"|>]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(pageContents);

		ArrayList<String> linkList = new ArrayList<String>();
		while (m.find()) {
			String link = m.group(1).trim();

			if (link.length() < 1) {
				continue;
			}

			// ����������ҳ�������ӡ�
			if (link.charAt(0) == '#') {
				continue;
			}

			if (link.indexOf("mailto:") != -1) {
				continue;
			}

			if (link.toLowerCase().indexOf("javascript") != -1) {
				continue;
			}

			if (link.indexOf("://") == -1) {
				if (link.charAt(0) == '/') {// ������Ե�
					link = "http://" + pageUrl.getHost() + ":" + pageUrl.getPort() + link;
				} else {
					String file = pageUrl.getFile();
					if (file.indexOf('/') == -1) {// ������Ե�ַ
						link = "http://" + pageUrl.getHost() + ":" + pageUrl.getPort() + "/" + link;
					} else {
						String path = file.substring(0, file.lastIndexOf('/') + 1);
						link = "http://" + pageUrl.getHost() + ":" + pageUrl.getPort() + path + link;
					}
				}
			}

			int index = link.indexOf('#');
			if (index != -1) {
				link = link.substring(0, index);
			}

			link = removeWwwFromUrl(link);

			URL verifiedLink = verifyUrl(link);
			if (verifiedLink == null) {
				continue;
			}

			/* ����޶��������ų���Щ����������URL */
			if (limitHost && !pageUrl.getHost().toLowerCase().equals(verifiedLink.getHost().toLowerCase())) {
				continue;
			}

			// ������Щ�Ѿ����������.
			// if (crawledList.contains(link)) {
			// continue;
			// }

			if (filterUrl.contains(link)) {

				continue;
			} else {
				filterUrl.add(link);
			}

			linkList.add(link);
		}

		return (linkList);
	}

	// ����ҳ�沢�ҳ�����
	private ArrayList<String> retrieveImgLinks(URL pageUrl, String pageContents, boolean limitHost) {
		// ��������ʽ�������ӵ�ƥ��ģʽ��
		Pattern p = Pattern.compile("<img\\s+src\\s*=\\s*\"?(.*?)[\"|>]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(pageContents);

		ArrayList<String> linkList = new ArrayList<String>();
		while (m.find()) {
			String link = m.group(1).trim();

			if (link.length() < 1) {
				continue;
			}

			// ����������ҳ�������ӡ�
			if (link.charAt(0) == '#') {
				continue;
			}

			if (link.indexOf("mailto:") != -1) {
				continue;
			}

			if (link.toLowerCase().indexOf("javascript") != -1) {
				continue;
			}

			if (link.indexOf("://") == -1) {
				if (link.charAt(0) == '/') {// ������Ե�
					link = "http://" + pageUrl.getHost() + ":" + pageUrl.getPort() + link;
				} else {
					String file = pageUrl.getFile();
					if (file.indexOf('/') == -1) {// ������Ե�ַ
						link = "http://" + pageUrl.getHost() + ":" + pageUrl.getPort() + "/" + link;
					} else {
						String path = file.substring(0, file.lastIndexOf('/') + 1);
						link = "http://" + pageUrl.getHost() + ":" + pageUrl.getPort() + path + link;
					}
				}
			}

			int index = link.indexOf('#');
			if (index != -1) {
				link = link.substring(0, index);
			}

			link = removeWwwFromUrl(link);

			URL verifiedLink = verifyUrl(link);
			if (verifiedLink == null) {
				continue;
			}

			/* ����޶��������ų���Щ����������URL */
			if (limitHost && !pageUrl.getHost().toLowerCase().equals(verifiedLink.getHost().toLowerCase())) {
				continue;
			}

			// ������Щ�Ѿ����������.
			// if (crawledList.contains(link)) {
			// continue;
			// }
			if (filterImg.contains(link)) {

				continue;
			} else {
				filterImg.add(link);
			}

			if (link.lastIndexOf(".gif") == -1) {
				linkList.add(link);
			}

		}

		return (linkList);
	}

	// ��������Webҳ������ݣ��ж��ڸ�ҳ������û��ָ���������ַ���

	@SuppressWarnings("unused")
	private boolean searchStringMatches(String pageContents, String searchString, boolean caseSensitive) {
		String searchContents = pageContents;
		if (!caseSensitive) {// ��������ִ�Сд
			searchContents = pageContents.toLowerCase();
		}

		Pattern p = Pattern.compile("[\\s]+");
		String[] terms = p.split(searchString);
		for (int i = 0; i < terms.length; i++) {
			if (caseSensitive) {
				if (searchContents.indexOf(terms[i]) == -1) {
					return false;
				}
			} else {
				if (searchContents.indexOf(terms[i].toLowerCase()) == -1) {
					return false;
				}
			}
		}

		return true;
	}

	// ִ��ʵ�ʵ���������
	public ArrayList<String> crawl(String startUrl, int maxUrls, String searchString, boolean limithost, boolean caseSensitive) {

		// System.out.println("searchString="+searchString);
		// HashSet< String> crawledList = new HashSet< String>();
		LinkedHashSet<String> toCrawlList = new LinkedHashSet<String>();
		// LinkedHashSet< String> toImgList = new LinkedHashSet< String>();

		if (maxUrls < 1) {
			errorList.add("Invalid Max URLs value.");
			System.out.println("Invalid Max URLs value.");
		}

		if (searchString.length() < 1) {
			errorList.add("Missing Search String.");
			System.out.println("Missing search String");
		}

		if (errorList.size() > 0) {
			System.out.println("err!!!");
			return errorList;
		}

		// �ӿ�ʼURL���Ƴ�www
		startUrl = removeWwwFromUrl(startUrl);

		toCrawlList.add(startUrl);
		while (toCrawlList.size() > 0) {

			// Get URL at bottom of the list.
			String url = toCrawlList.iterator().next();

			// Remove URL from the to crawl list.
			toCrawlList.remove(url);

			// if(filterUrl.contains(url))
			// {
			//
			// continue;
			// }
			// else
			// {
			// filterUrl.add(url);
			// }
			// Convert string url to URL object.
			URL verifiedUrl = verifyUrl(url);

			// Skip URL if robots are not allowed to access it.
			if (!isRobotAllowed(verifiedUrl)) {
				continue;
			}

			// �����Ѵ����URL��crawledList
			// crawledList.add(url);
			String pageContents = downloadPage(verifiedUrl);

			if (pageContents != null && pageContents.length() > 0) {
				// ��ҳ���л�ȡ��Ч������
				ArrayList<String> links = retrieveLinks(verifiedUrl, pageContents, limitHost);

				// ��ҳ���л�ȡ��Ч������
				ArrayList<String> imglinks = retrieveImgLinks(verifiedUrl, pageContents, limitHost);

				// ��ӵ�ͼƬ���ض���
				toCrawlList.addAll(links);

				int iCount = 0;
				for (int i = 0; i < imglinks.size(); i++) {
					if (imglinks.get(i).indexOf("http:") != -1) {
						// if(!filterImg.contains(imglinks.get(i)))
						// {
						iCount++;
						filterImg.add(imglinks.get(i));
						new Thread(new ImgDownThread(imglinks.get(i))).start();
						// }

						if ((iCount != 1) && (iCount % 10 == 1)) {
							try {
								logEvent("ͼ����Ϣ1��......");
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					}

				}
				System.out.println("iCount:" + iCount);
				if (iCount > 10) {
					System.out.println(new SimpleDateFormat("yyyy��MM��dd��HHʱmm��ss��").format(new Date(Calendar.getInstance().getTimeInMillis())) + "=====>" + "����10��...");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				// if (searchStringMatches(pageContents,
				// searchString,caseSensitive))
				// {
				// result.add(url);
				// System.out.println(url);
				// }
			}

		}
		blnFlag = false;

		logEvent("ץͼ���......");

		return result;
	}

	public static void logEvent(String strLog) {
		System.out.println(new SimpleDateFormat("yyyy��MM��dd��HHʱmm��ss��").format(new Date(Calendar.getInstance().getTimeInMillis())) + "=====>" + strLog);

	}

	// ������
	public static void main(String[] args) {
		if (args.length != 6) {
			System.out.println("Usage:java SearchCrawler startUrl maxUrl searchString");
			return;
		}
		int max = Integer.parseInt(args[1]);
		SearchCrawler crawler = new SearchCrawler(args[0], max, args[2]);
		outdir = args[3];
		seroutdir = args[4];
		seroutdirimg = args[5];

		try {
			if (UtilSeriz.readObject(seroutdir) != null) {
				System.out.println(new SimpleDateFormat("yyyy��MM��dd��HHʱmm��ss��").format(new Date(Calendar.getInstance().getTimeInMillis())) + "=====>" + "�����л�URL...");
				filterUrl = (SimpleBloomFilter) UtilSeriz.readObject(seroutdir);
			} else {
				filterUrl = new SimpleBloomFilter();
			}
			if (UtilSeriz.readObject(seroutdir) != null) {
				System.out.println(new SimpleDateFormat("yyyy��MM��dd��HHʱmm��ss��").format(new Date(Calendar.getInstance().getTimeInMillis())) + "=====>" + "�����л�ͼƬ...");

				filterImg = (SimpleBloomFilter) UtilSeriz.readObject(seroutdirimg);
			} else {
				filterImg = new SimpleBloomFilter();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		Thread search = new Thread(crawler);
		System.out.println(new SimpleDateFormat("yyyy��MM��dd��HHʱmm��ss��").format(new Date(Calendar.getInstance().getTimeInMillis())) + "=====>" + "��ʼ��ͼ...");
		System.out.println("������ͼ:");
		search.start();

	}

	/**
	 * ˵��:����ͼƬ���߳�
	 * 
	 * @author binbin0915
	 * 
	 */
	public class ImgDownThread implements Runnable {
		private String stru;

		private boolean isStart = true;

		public ImgDownThread(String strurl) {
			super();
			this.stru = strurl;
		}

		public void run() {
			try {
				// String stru="";
				// while(isStart)
				// {
				// stru = toImgList.iterator().next();
				URL url = new URL(stru);
				BufferedInputStream in = new BufferedInputStream(url.openStream());

				BufferedImage bi = ImageIO.read(url.openStream());

				if (bi.getWidth() < 30 || bi.getHeight() < 30) {

					in.close();
					return;
				}
				String ss = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(Calendar.getInstance().getTimeInMillis())) + "_" + Math.round(Math.random() * 89999999999999L + 1000) + stru.substring(stru.lastIndexOf("."));
				String s = outdir + ss;
				FileOutputStream file = new FileOutputStream(new File(s));
				int t;
				while ((t = in.read()) != -1) {
					file.write(t);
				}
				file.close();
				if (new File(s).length() <= 1 * 1024) {

					in.close();
					new File(s).delete();
					return;
				}
				in.close();
				System.out.println(new SimpleDateFormat("yyyy��MM��dd��HHʱmm��ss��").format(new Date(Calendar.getInstance().getTimeInMillis())) + "=====>" + "������ͼƬ" + stru);

				// }

			} catch (Exception e) {
				// e.printStackTrace();
			}

		}

		public boolean isStart() {
			return isStart;
		}

		public void setStart(boolean isStart) {
			this.isStart = isStart;
		}

	}

	public class TimeWrite2File implements Runnable {
		public void run() {
			while (blnFlag) {
				try {
					System.out.println(new SimpleDateFormat("yyyy��MM��dd��HHʱmm��ss��").format(new Date(Calendar.getInstance().getTimeInMillis())) + "=====>" + "���л�URL...");

					logEvent("��ʼ���л�URL");
					UtilSeriz.writeObject(filterUrl, seroutdir);
					logEvent("��ʼ���л�ͼƬ");
					UtilSeriz.writeObject(filterImg, seroutdirimg);
					Thread.sleep(120000);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}

	}
}
