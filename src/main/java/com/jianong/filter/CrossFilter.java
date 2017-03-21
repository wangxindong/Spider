package com.jianong.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class CrossFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain fChain)
			throws ServletException, IOException {
//		 if (request.getHeader("Access-Control-Request-Method") != null && "POST".equals(request.getMethod())) {
		if ( "POST".equals(request.getMethod())) {	 
			 HttpServletResponse rps = (HttpServletResponse)response;
//			 rps.addHeader("Access-Control-Allow-Origin", "*");
			 rps.setHeader("Access-Control-Allow-Origin", "*");
//			 rps.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
//			 rps.addHeader("Access-Control-Allow-Headers", "Content-Type");
//			 rps.addHeader("Access-Control-Max-Age", "1800");//30 min
			 }
			 fChain.doFilter(request, response);
		}
}
