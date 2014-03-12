package com.iunet.itext.helloworld;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: xiaolei
 * Date: 13-8-8
 * Time: 下午10:37
 * To change this template use File | Settings | File Templates.
 */
public class HelloWorld {

    /**
     * 创建简单pdf文档
     * @param dir
     * @param fileName
     */
    public void createSimplePdf(String dir, String fileName) {
        Document document = null;
        try {
            String pdfFile = createPdfFilePath(dir, fileName);

            document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            document.open();
            document.add(new Paragraph("@@@@@@@@@@@\n@@@@@@@@@"));

            System.out.println("创建pdf文档成功！");
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (document != null) document.close();
        }
    }

    /**
     * 创建一定大小的pdf文档
     * @param dir
     * @param fileName
     */
    public void createRectanglePdf(String dir, String fileName) {
        Document document = null;
        try {
            String pdfFile = createPdfFilePath(dir, fileName);

            Rectangle pageSize = new Rectangle(300f, 500f);
            document = new Document(pageSize, 36f, 72f, 108f, 180f);
            PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            document.open();
            document.add(new Paragraph("hahahahahhahaha\nhahahaha"));

            System.out.println("创建pdf文档成功！");
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (document != null) document.close();
        }
    }

    /**
     * 组装pdf文件路径
     * @param dir
     * @param fileName
     * @return pdf文件路径
     */
    private String createPdfFilePath(String dir, String fileName) {
        File p = new File(dir);
        if (!p.exists()) p.mkdirs();
        return (dir + File.separator + fileName);
    }
}
