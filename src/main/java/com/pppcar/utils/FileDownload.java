package com.pppcar.utils;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class FileDownload {


	public static void DownloadFile(String name,HttpServletRequest request, HttpServletResponse response, HSSFWorkbook book)
			throws IOException {
		String filename = name+new Date(System.currentTimeMillis())+".xls";
		//获取响应头
		response.reset();
		response.setContentType(request.getServletContext().getMimeType(filename));
		response.addHeader("Content-Disposition", "attachment;filename=" + FileUtils.encodeDownloadFilename(filename, request.getHeader("user-agent")));
		ServletOutputStream outputStream = response.getOutputStream();
		book.write(outputStream);
		response.flushBuffer();
		outputStream.close();
	}
}
