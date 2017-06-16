package cn.tedu.web.back;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.sun.mail.imap.protocol.Item;

import cn.tedu.bean.Product;

public class BackProdAddServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
//		创建DiskFileItemFactory对象，设置缓冲区大小和临时文件目录。
			
		DiskFileItemFactory diskFileItemFactory=new DiskFileItemFactory(1024*100, new File(request.getServletContext().getRealPath("/WEB-INF/temp")));
//		用DiskFileItemFactory创建ServletFileUpload对象，并设置上传文件的大小限制。
//		ServletFileUpload.parseRequest方法解析request
		ServletFileUpload servletFileUpload=new ServletFileUpload(diskFileItemFactory);
		//判断上传表单是否为multipart/form-data类型。
		if (!servletFileUpload.isMultipartContent(request)) {
			throw new RuntimeException("请使用正确的表单上传数据");
		}
//		设置单个文件大小限制
		servletFileUpload.setFileSizeMax(1024*1024*10);
//		设置总大小限制
		servletFileUpload.setSizeMax(1024*1024*20);
//		设置编码解决上传文件名的乱码问题
		servletFileUpload.setHeaderEncoding("utf-8");
		
//解析request对象，并把表单中的每一个输入项包装成一个fileItem 对象，并返回一个保存了所有FileItem的list集合。
		List<FileItem> list=servletFileUpload.parseRequest(request);
		for(FileItem fi:list){
			if (fi.isFormField()) {
//				True 为普通表单字段，则调用getFieldName、getString方法得到字段名和字段值
//				False 为上传文件项，则调用getInputStream方法得到数据输入流，从而读取上传数据。
				System.out.println(fi.getFieldName()+":"+fi.getString("utf-8"));
			}else {
//				文件名处理
				String fname=fi.getName();
				fname=UUID.randomUUID().toString()+"_"+fname;
//				文件路径处理
				String hash=Integer.toHexString(fname.hashCode());
				String path="WEB-INF/upload";
				for(char c:hash.toCharArray()){
					path=path+"/"+c;
				}
//				创建文件
				File file=new File(this.getServletContext().getRealPath(path));
				file.mkdirs();
				System.out.println(file.getPath());
//				获取上传文件(临时文件)的输入流
				InputStream in=fi.getInputStream();
//				获取服务存储文件的输出流
				OutputStream out=new FileOutputStream(file.getPath()+"/"+fname);
				byte[] bs=new byte[1024];
				int i=0;
//				从临时文件读出入数据写入存储文件
				while((i=in.read(bs))!=-1){
					out.write(bs,0,i);
				}
//				传输完毕
				in.close();
				out.close();
//				删除临时文件
				fi.delete();
				request.getRequestDispatcher("/back/manageAddProd.jsp").forward(request, response);
				}		
			}
		} catch (SizeLimitExceededException e) {
			response.getWriter().write("单个文件不能超过10M，总大小不能超过20M！");
			return;
		}catch (FileUploadException e) {
			e.printStackTrace();
			
		}
	}
	
}
