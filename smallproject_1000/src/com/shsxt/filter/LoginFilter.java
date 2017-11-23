package com.shsxt.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.shsxt.model.UserModel;
import com.shsxt.service.UserService;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		System.out.println("uri:" + uri);
		if (uri.contains("/login") || uri.contains("/js") || uri.contains("/register") || uri.contains("/logout")
				|| uri.contains("/js")) {
			chain.doFilter(req, resp);
		} else {
			HttpSession session = req.getSession();
			UserModel user = (UserModel) session.getAttribute("userModel");
			if (null == user) {
				Cookie[] cookie = req.getCookies();
				for (Cookie c : cookie) {
					if ("rememberMe".equals(c.getName())) {
						String token = c.getValue();
						String[] str = token.split("\\(\\$\\)");
						String name = str[0];
						String password = str[1];
						WebApplicationContext ctx = WebApplicationContextUtils
								.getRequiredWebApplicationContext(req.getServletContext());
						UserService userService = ctx.getBean(UserService.class);
						userService.queryUserByNameAndPassword(name, password);
					}
				}
				resp.sendRedirect("login");
			} else {

				chain.doFilter(req, resp);
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
