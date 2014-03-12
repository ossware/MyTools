package com.xl.tools;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;

public class RemoteFileUtil {
    public static final String REMOTE_HOST_IP = "192.168.*.*";
    public static final String LOGIN_ACCOUNT = "administrator";
    public static final String LOGIN_PASSWORD = "password";
    public static final String SHARE_DOC_NAME = "shareDoc";

    private String remoteHostIp;  //远程主机IP   
    private String account;       //登陆账户   
    private String password;      //登陆密码   
    private String shareDocName;  //共享文件夹名称   


    /**
     * 默认构造函数
     */
    public RemoteFileUtil() {
        this.remoteHostIp = REMOTE_HOST_IP;
        this.account = LOGIN_ACCOUNT;
        this.password = LOGIN_PASSWORD;
        this.shareDocName = SHARE_DOC_NAME;
    }

    /**
     * 构造函数
     *
     * @param remoteHostIp 远程主机Ip
     * @param account      登陆账户
     * @param password     登陆密码
     * @param shareDocName 共享文件夹路径
     */
    public RemoteFileUtil(String remoteHostIp, String account, String password, String shareDocName) {
        this.remoteHostIp = remoteHostIp;
        this.account = account;
        this.password = password;
        this.shareDocName = shareDocName;
    }

    /**
     * 对远程共享文件进行读取所有行
     *
     * @param remoteFileName 文件名  说明：参数为共享目录下的相对路径 若远程文件的路径为：shareDoc\test.txt,则参数为test.txt(其中shareDoc为共享目录名称);
     *                       若远程文件的路径为：shareDoc\doc\text.txt,则参数为doc\text.txt;
     *
     * @return 文件的所有行
     */
    public List<String> readFile(String remoteFileName) {
        SmbFile smbFile = null;
        BufferedReader reader = null;
        List<String> resultLines = null;
        //构建连接字符串,并取得文件连接   
        String conStr = null;
        conStr = "smb://" + account + ":" + password + "@" + remoteHostIp + "/" + shareDocName + "/" + remoteFileName;
        try {
            smbFile = new SmbFile(conStr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //创建reader   
        try {
            reader = new BufferedReader(new InputStreamReader(new SmbFileInputStream(smbFile)));
        } catch (SmbException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        //循环对文件进行读取   
        String line;
        try {
            line = reader.readLine();
            if (line != null && line.length() > 0) {
                resultLines = new ArrayList<String>();
            }
            while (line != null) {
                resultLines.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //返回   
        return resultLines;
    }

    /**
     * 对远程共享文件进行写入
     *
     * @param is             本地文件的输入流
     * @param remoteFileName 远程文件名    说明：参数为共享目录下的相对路径 若远程文件的路径为：shareDoc\test.txt,则参数为test.txt(其中shareDoc为共享目录名称);
     *                       若远程文件的路径为：shareDoc\doc\text.txt,则参数为doc\text.txt;
     *
     * @return
     */
    public boolean writeFile(InputStream is, String remoteFileName) {
        SmbFile smbFile = null;
        OutputStream os = null;
        byte[] buffer = new byte[1024 * 8];
        //构建连接字符串,并取得文件连接   
        String conStr = null;
        conStr = "smb://" + account + ":" + password + "@" + remoteHostIp + "/" + shareDocName + "/" + remoteFileName;
        try {
            smbFile = new SmbFile(conStr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        }

        //获取远程文件输出流并写文件到远程共享文件夹   
        try {
            os = new BufferedOutputStream(new SmbFileOutputStream(smbFile));
            while ((is.read(buffer)) != -1) {
                os.write(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


    /**
     * 对远程共享文件进行写入重载
     *
     * @param localFileFullName 要写入的本地文件全名
     * @param remoteFileName    远程文件名    说明：参数为共享目录下的相对路径 若远程文件的路径为：shareDoc\test.txt,则参数为test.txt(其中shareDoc为共享目录名称);
     *                          若远程文件的路径为：shareDoc\doc\text.txt,则参数为doc\text.txt;
     *
     * @return
     */
    public boolean writeFile(String localFileFullName, String remoteFileName) {
        try {
            return writeFile(new FileInputStream(new File(localFileFullName)), remoteFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 对远程共享文件进行写入重载
     *
     * @param localFile      要写入的本地文件
     * @param remoteFileName 远程文件名    说明：参数为共享目录下的相对路径 若远程文件的路径为：shareDoc\test.txt,则参数为test.txt(其中shareDoc为共享目录名称);
     *                       若远程文件的路径为：shareDoc\doc\text.txt,则参数为doc\text.txt;
     *
     * @return
     */
    public boolean writeFile(File localFile, String remoteFileName) {
        try {
            return writeFile(new FileInputStream(localFile), remoteFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static void main(String[] args) {
        RemoteFileUtil util = new RemoteFileUtil("192.168.3.12", "administrator", "topicisdb2", "share");
//      List<String> lines = util.readFile("test.txt");   
//      for (String string : lines) {   
//          System.out.println(string);   
//      }  

        boolean returnValue = util.writeFile(new File("f:/desk/jcifs-1.3.14.zip"), "TopOA_DroolXmlPath/new.zip");
        if (returnValue)
            System.out.println("远程文件写入成功~");
    }
}
