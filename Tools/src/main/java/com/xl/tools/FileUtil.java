package com.xl.tools;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * Created by IntelliJ IDEA. User: haoxiaolei Date: 2010-10-14 Time: 15:21:26 To change this template use File |
 * Settings | File Templates.
 */
public class FileUtil {
    /**
     * 功能描述：列出某文件夹及其子文件夹下面的文件，并可根据扩展名过滤
     *
     * @param path 文件夹
     */
    public static void list(File path) {
        if (!path.exists()) {
            System.out.println("文件名称不存在!");
        } else {
            if (path.isFile()) {
                if (path.getName().toLowerCase().endsWith(".pdf") || path.getName().toLowerCase().endsWith(".doc") || path.getName().toLowerCase().endsWith(".chm") || path.getName().toLowerCase().endsWith(".html") || path.getName().toLowerCase().endsWith(".htm")) {// 文件格式
                    System.out.println(path);
                    System.out.println(path.getName());
                }
            } else {
                File[] files = path.listFiles();
                for (int i = 0; i < files.length; i++) {
                    list(files[i]);
                }
            }
        }
    }

    /**
     * 功能描述：拷贝一个目录或者文件到指定路径下，即把源文件拷贝到目标文件路径下
     *
     * @param source 源文件
     * @param target 目标文件路径
     *
     * @return void
     */
    public static void copy(File source, File target) {
        File tarpath = new File(target, source.getName());
        if (source.isDirectory()) {
            tarpath.mkdir();
            File[] dir = source.listFiles();
            for (int i = 0; i < dir.length; i++) {
                copy(dir[i], tarpath);
            }
        } else {
            try {
                InputStream is = new FileInputStream(source); // 用于读取文件的原始字节流
                OutputStream os = new FileOutputStream(tarpath); // 用于写入文件的原始字节的流
                byte[] buf = new byte[1024];// 存储读取数据的缓冲区大小
                int len = 0;
                while ((len = is.read(buf)) != -1) {
                    os.write(buf, 0, len);
                }
                is.close();
                os.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 压缩文件
     *
     * @param inputFileName  要压缩的文件，例如：c:\\a.txt
     * @param outputFileName 输出zip文件的路径，例如：c:\\a.gz
     */
    public static void gzip(String inputFileName, String outputFileName) throws Exception {
        if (inputFileName == null || inputFileName.equals("")) {
            System.err.println("请指定源文件！");
            System.exit(1);
        }
        if (outputFileName == null || outputFileName.equals("")) {
            System.err.println("请指定源文件！");
            System.exit(1);
        }
        FileInputStream fin = null;
        FileOutputStream fout = null;
        GZIPOutputStream gzout = null;
        try {
            //打开需压缩文件作为文件输入流
            fin = new FileInputStream(inputFileName);  //建立压缩文件输出流
            fout = new FileOutputStream(outputFileName);  //建立gzip压缩输出流
            gzout = new GZIPOutputStream(fout);
            byte[] buf = new byte[8888];//设定读入缓冲区尺寸
            int num;
            while ((num = fin.read(buf)) != -1) {
                gzout.write(buf, 0, num);
            }

            gzout.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fin != null) fin.close();
                if (fout != null) fout.close();
//                if (gzout != null) gzout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 压缩文件
     *
     * @param out  org.apache.tools.zip.ZipOutputStream
     * @param file 待压缩的文件
     * @param base 压缩的根目录
     */
    private static void zip(ZipOutputStream out, File file, String base) throws Exception {
        if (file.isDirectory()) {
            File[] fl = file.listFiles();
            base = base.length() == 0 ? "" : base + File.separator;
            for (int i = 0; i < fl.length; i++) {
                zip(out, fl[i], base + fl[i].getName());
            }
        } else {
            out.putNextEntry(new ZipEntry(base));
            // log.debug("添加压缩文件：" + base);
            System.out.println("添加压缩文件：" + base);
            FileInputStream in = new FileInputStream(file);
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            in.close();
        }
    }

    /**
     * 压缩文件
     *
     * @param filename 文件数组
     * @param outname  输出zip文件的名称
     *
     * @return
     */
    public static boolean zip(String[] filename, String outname) {
        final int BUFFER = 2048; // 缓冲大小
        boolean result = true;
        try {
            BufferedInputStream origin = null;
            FileOutputStream dest = new FileOutputStream(outname);
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
            byte data[] = new byte[BUFFER];
            // File f= new File("d:\\111\\");
            // File files[] = f.listFiles();

            for (int i = 0; i < filename.length; i++) {
                File file = new File(filename[i]);
                FileInputStream fi = new FileInputStream(file);
                origin = new BufferedInputStream(fi, BUFFER);
                ZipEntry entry = new ZipEntry(file.getName());
                out.putNextEntry(entry);
                int count;
                while ((count = origin.read(data, 0, BUFFER)) != -1) {
                    out.write(data, 0, count);
                }
                origin.close();
            }
            out.close();
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 解压zip文件
     *
     * @param zipFileName     待解压的zip文件路径，例如：c:\\a.zip
     * @param outputDirectory 解压目标文件夹,例如：c:\\a\
     */
    public static void unZip(String zipFileName, String outputDirectory) throws Exception {
        ZipFile zipFile = new ZipFile(zipFileName);
        try {
            Enumeration<?> e = (Enumeration<?>) zipFile.getEntry(outputDirectory);
            ZipEntry zipEntry = null;
            createDirectory(outputDirectory, "");
            while (e.hasMoreElements()) {
                zipEntry = (ZipEntry) e.nextElement();
                // log.debug("解压：" + zipEntry.getName());
                System.out.println("解压：" + zipEntry.getName());
                if (zipEntry.isDirectory()) {
                    String name = zipEntry.getName();
                    name = name.substring(0, name.length() - 1);
                    File f = new File(outputDirectory + File.separator + name);
                    f.mkdir();
                    // log.debug("创建目录：" + outputDirectory + File.separator +
                    // name);
                    System.out.println("创建目录：" + outputDirectory + File.separator + name);
                } else {
                    String fileName = zipEntry.getName();
                    fileName = fileName.replace('\\', '/');
                    if (fileName.indexOf("/") != -1) {
                        createDirectory(outputDirectory, fileName.substring(0, fileName.lastIndexOf("/")));
                        fileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
                    }

                    File f = new File(outputDirectory + File.separator + zipEntry.getName());
                    f.createNewFile();
                    InputStream in = zipFile.getInputStream(zipEntry);
                    FileOutputStream out = new FileOutputStream(f);
                    byte[] by = new byte[1024];
                    int c;
                    while ((c = in.read(by)) != -1) {
                        out.write(by, 0, c);
                    }
                    in.close();
                    out.close();
                }
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            zipFile.close();
            // log.debug("解压完成！");
            System.out.println("解压完成！");
        }
    }

    private static void createDirectory(String directory, String subDirectory) {
        String dir[];
        File fl = new File(directory);
        try {
            if (subDirectory == "" && fl.exists() != true) {
                fl.mkdir();
            } else if (subDirectory != "") {
                dir = subDirectory.replace('\\', '/').split("/");
                for (int i = 0; i < dir.length; i++) {
                    File subFile = new File(directory + File.separator + dir[i]);
                    if (subFile.exists() == false)
                        subFile.mkdir();
                    directory += File.separator + dir[i];
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * 拷贝文件夹中的所有文件到另外一个文件夹
     *
     * @param srcDirector 源文件夹
     * @param desDirector 目标文件夹
     */
    public static void copyFileWithDirector(String srcDirector, String desDirector) throws IOException {
        (new File(desDirector)).mkdirs();
        File[] file = (new File(srcDirector)).listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()) {
                // log.debug("拷贝：" + file[i].getAbsolutePath() + "-->" +
                // desDirector + "/" + file[i].getName());
                System.out.println("拷贝：" + file[i].getAbsolutePath() + "-->" + desDirector + "/" + file[i].getName());
                FileInputStream input = new FileInputStream(file[i]);
                FileOutputStream output = new FileOutputStream(desDirector + "/" + file[i].getName());
                byte[] b = new byte[1024 * 5];
                int len;
                while ((len = input.read(b)) != -1) {
                    output.write(b, 0, len);
                }
                output.flush();
                output.close();
                input.close();
            }
            if (file[i].isDirectory()) {
                // log.debug("拷贝：" + file[i].getAbsolutePath() + "-->" +
                // desDirector + "/" + file[i].getName());
                System.out.println("拷贝：" + file[i].getAbsolutePath() + "-->" + desDirector + "/" + file[i].getName());
                copyFileWithDirector(srcDirector + "/" + file[i].getName(), desDirector + "/" + file[i].getName());
            }
        }
    }

    /**
     * 删除文件夹
     *
     * @param folderPath folderPath 文件夹完整绝对路径
     */
    public static void delFolder(String folderPath) throws Exception {
        // 删除完里面所有内容
        delAllFile(folderPath);
        String filePath = folderPath;
        filePath = filePath.toString();
        File myFilePath = new File(filePath);
        // 删除空文件夹
        myFilePath.delete();
    }

    /**
     * 删除指定文件夹下所有文件
     *
     * @param path 文件夹完整绝对路径
     */

    public static boolean delAllFile(String path) throws Exception {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                // 先删除文件夹里面的文件
                delAllFile(path + "/" + tempList[i]);
                // 再删除空文件夹
                delFolder(path + "/" + tempList[i]);
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 读取文件
     *
     * @param srcFile
     *
     * @return
     */
    public static OutputStream readFile(String srcFile) {
        FileOutputStream fos = null;
        FileInputStream fis = null;
        int copyLen;
        byte[] copyBuf = new byte[1024 * 5];
        try {
            fos = new FileOutputStream(new File("f:/desk/log.log"));
            fis = new FileInputStream(new File(srcFile));
            while ((copyLen = fis.read(copyBuf, 0, 1024 * 5)) != -1) {
                String copyStr = new String(copyBuf);
                if (copyStr.startsWith("[")) {
                    System.out.println(copyStr);
                    fos.write(copyBuf, 0, copyLen);
                }
            }
            // System.out.println("总共  " + byteTotal + " 个字节");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return fos;

    }

    public static void main(String[] args) {

    }

}
