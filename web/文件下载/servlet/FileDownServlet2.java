package down;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileDownServlet2 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		下载哪个文件
		String fileNameStr=request.getParameter("filename");
		String fileName=new String(fileNameStr.getBytes("ISO8859-1"), "utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(fileName,"utf-8"));
		request.getRequestDispatcher(fileName).forward(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	doGet(request, response);
	}

}
