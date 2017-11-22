package com.shsxt.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shsxt.model.UserModel;


public class LoginFilter implements Filter
{

	@Override
	public void destroy()
	{

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		System.out.println("uri:" + uri);
		if (uri.contains("/login") || uri.contains("/js") || uri.contains("/register") || uri.contains("/logout")
				|| uri.contains("/js"))
		{
			chain.doFilter(req, resp);
		}
		else
		{
			HttpSession session = req.getSession();
			UserModel user = (UserModel) session.getAttribute("userModel");
			if (null == user || "".equals(user))
			{
				resp.sendRedirect("login");
			}
			else
			{
				chain.doFilter(req, resp);
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException
	{

	}

}
