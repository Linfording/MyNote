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

public class FileDownServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		下载哪个文件
		String fileNameStr=request.getParameter("filename");
		String fileName=new String(fileNameStr.getBytes("ISO8859-1"), "utf-8");
//		设置响应头,以文件下载方式打开
//		response.setHeader("Content-Disposition", "attachment;filename="+fileName);
		response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(fileName,"utf-8"));
//		io操作
		OutputStream ops=response.getOutputStream();
		InputStream ips=new FileInputStream(new File(getServletContext().getRealPath(fileName)));

		byte[] date=new byte[1024];
		int len=-1;
		while ((len=ips.read(date))!=-1) {
			ops.write(date,0,len);
		}
		ips.close();
		ops.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
