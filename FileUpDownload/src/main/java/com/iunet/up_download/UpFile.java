package com.iunet.up_download;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public class UpFile {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024 * 1024 * 20);
		// factory.setRepository(new File(request.getRealPath("/")));
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 允许上传的文件类型
		// upload.setAllowedFilesList("doc,xls,");
		// 拒绝上传的文件类型
		// upload.setDeniedFilesList("exe,bat,jsp");
		// 文件大小20M
		upload.setSizeMax(1024 * 1024 * 20);

		List items;
		String affix = null;
		try {

			items = upload.parseRequest(request);

			for (Iterator it = items.iterator(); it.hasNext();) {
				FileItem item = (FileItem) it.next();
				if (item.isFormField()) {
					String name = item.getFieldName();

					if (name.equals("boy")) {
						affix = item.getString("utf-8");
					}
				} else {
					if (item.getSize() > 0) {
						String fieldName = item.getFieldName();

						String fileName = item.getName();
						String contentType = item.getContentType();
						/*
						 * affix = System.currentTimeMillis() +
						 * fileName.substring(fileName.lastIndexOf("."),
						 * fileName.length());
						 */
						// affix =
						// fileName.substring(fileName.lastIndexOf("."),fileName.length());

						fileName = new String(fileName.getBytes("utf-8"), "utf-8");

						affix = fileName;
						// 设置上传路径
						FileOutputStream fos = new FileOutputStream(request.getRealPath("Upload") + "/" + affix);
						if (item.isInMemory()) {
							fos.write(item.get());
						} else {
							InputStream is = item.getInputStream();
							byte[] buffer = new byte[1024];
							int len;
							while ((len = is.read(buffer)) > 0) {
								fos.write(buffer, 0, len);
							}
							is.close();
						}
						fos.flush();
						fos.close();
					}
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

	}
}
