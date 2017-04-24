package cn.tedu.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
//!!!!!!!!!!!!!
public class EncodeFilter implements Filter{
	private String encode;
	@Override
	public void init(FilterConfig config) throws ServletException {
		encode=config.getInitParameter("encode");
	}
	 class MyServletRequeset extends HttpServletRequestWrapper{
			HttpServletRequest request;
			private boolean hasEncode=false;
			public MyServletRequeset(HttpServletRequest request) {
				super(request);
				this.request=request;
			}

			@Override
			public String getParameter(String name) {
				return getParameterMap().get(name)==null?"":getParameterMap().get(name)[0];
			}
			@Override
			public Map<String, String[]> getParameterMap() {
//				判断提交方式post/get
				if ("POST".equals(request.getMethod())) {
					try {
						request.setCharacterEncoding(encode);
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					return request.getParameterMap();
				}else if ("GET".equals(request.getMethod())) {
					Map<String, String[]> map=request.getParameterMap();
					if (!hasEncode) {
						for(Map.Entry<String, String[]> entry:map.entrySet()){
							String[] vals=entry.getValue();
							for (int i = 0; i < vals.length; i++) {
								try {
									vals[i]=new String(vals[i].getBytes("ISO8859-1"), encode);
								} catch (UnsupportedEncodingException e) {
									e.printStackTrace();
								}
							}
						}
					}
					return map;
				}else{
					return super.getParameterMap();
				}
			}
			@Override
			public String[] getParameterValues(String name) {
				return getParameterMap().get(name);
			}
	 }

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(encode);
		response.setContentType("text/html;charset="+encode);
		chain.doFilter(new MyServletRequeset((HttpServletRequest) request), response);
		
	}

	@Override
	public void destroy() {
		
	}

}
