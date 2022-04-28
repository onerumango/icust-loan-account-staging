package com.rumango.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Component
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class IcustCustomFilter extends CorsFilter {

	public IcustCustomFilter(CorsConfigurationSource configSource) {
		super(configSource);
	}

	private static final Logger LOGGER = Logger.getLogger(IcustCustomFilter.class);

	@Autowired
	private Encryption encryption;

	private static final String AUTH = "Auth";

	@Override
	protected synchronized void doFilterInternal(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}

	protected synchronized void doFilterInternal2(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
		String url = null;
		String method = null;
//			Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
//			if (headerNames != null) {
//				while (headerNames.hasMoreElements()) {
//					String name = headerNames.nextElement();
//					LOGGER.info("Header: " + name + " value:" + httpServletRequest.getHeader(name));
//				}
//			}

		String s = httpServletRequest.getHeader(AUTH);
		LOGGER.info("Inside custom filer" + s);

		if (httpServletRequest instanceof HttpServletRequest) {
			url = httpServletRequest.getRequestURL().toString();
			method = httpServletRequest.getMethod();
			LOGGER.info(method + "::" + url);
		}

		if (method != null && method.equalsIgnoreCase("OPTIONS"))
			filterChain.doFilter(httpServletRequest, httpServletResponse);

		else if (s != null) {
			String secretKey = "@";
			String decpsw = encryption.decryptText(s, secretKey);
			LOGGER.info("value of decpsw is" + decpsw);
			if (!"sr".equals(decpsw)) {
				httpServletResponse.sendError(HttpStatus.BAD_REQUEST.value(), "Request not allowed.");
				return;
			}
			filterChain.doFilter(httpServletRequest, httpServletResponse);
		} else {
			httpServletResponse.sendError(HttpStatus.BAD_REQUEST.value(), "Request not allowed.");
			return;
		}
	}
}
