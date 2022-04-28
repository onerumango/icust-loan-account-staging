package com.rumango.config;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomFilter implements Filter {

	/**
	 * Default constructor
	 */
	public CustomFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// destroy method
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;

		Enumeration<String> headers = request.getHeaderNames();
		String origin = "*";
		while (headers.hasMoreElements()) {
			String tempName = headers.nextElement();
			if ("origin".equalsIgnoreCase(tempName)) {
				origin = request.getHeader(tempName);
				break;
			}
		}
		((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Origin", origin);
		((HttpServletResponse) servletResponse).setHeader("Timing-Allow-Origin", origin);

		((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Methods",
				"GET, OPTIONS, HEAD, PUT, POST,DELETE");
		((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Headers",
				"Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
		((HttpServletResponse) servletResponse).setHeader("Access-Control-Request-Headers",
				"Content-Type, application/json, Authorization, X-Requested-With");

		HttpServletResponse resp = (HttpServletResponse) servletResponse;

		if ("OPTIONS".equals(request.getMethod())) {
			resp.setStatus(HttpServletResponse.SC_ACCEPTED);
			return;
		}

		chain.doFilter(request, servletResponse);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// init method
	}
}
