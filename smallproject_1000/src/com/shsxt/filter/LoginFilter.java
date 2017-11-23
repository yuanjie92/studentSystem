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

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.shsxt.model.UserModel;
import com.shsxt.service.UserService;


public class LoginFilter implements Filter
{
	private Logger logger = Logger.getLogger(getClass());

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String uri = req.getRequestURI();

		boolean hasLogin = true;
		if (!(uri.contains("/login") || uri.contains("/js") || uri.contains("/register") || uri.contains("/logout")
				|| uri.contains("/js"))) {
			HttpSession session = req.getSession();
			UserModel user = (UserModel) session.getAttribute("userModel");
			if (user == null) {
				Cookie[] cookie = req.getCookies();
				Cookie rememberMe = null;
				for (Cookie c : cookie) {
					if ("rememberMe".equals(c.getName())) {
						rememberMe = c;
						break;
					}
				}

				UserModel userModel = null;
				if (rememberMe != null) {
					String token = rememberMe.getValue();
					String tokenString = new String(Base64.decode(token.getBytes()));

					logger.info(String.format("cookie token is :%s", tokenString));

					String[] tokenArray = tokenString.split(":");
					if (tokenArray != null && tokenArray.length == 3)
					{
						String name = tokenArray[0];
						long expireTime = Long.parseLong(tokenArray[1]);
						String passwordToken = tokenArray[2];

						//1. 校验是否过期 没过期再看数据正确性。过期了，把cookie删除
						long currentTime = System.currentTimeMillis();
						if (expireTime - currentTime < 0)
						{
							//删除没用的cookie
							rememberMe.setMaxAge(0);
							resp.addCookie(rememberMe);
							resp.sendRedirect("login");
						}

						WebApplicationContext ctx = WebApplicationContextUtils
								.getRequiredWebApplicationContext(req.getServletContext());

						UserService userService = ctx.getBean(UserService.class);
						Md5PasswordEncoder md5Encoder = ctx.getBean(Md5PasswordEncoder.class);

						//2 获取当前用户的用户名
						userModel = userService.queryUserByName(name);
						if (userModel == null)
						{//当前用户不存在
							resp.sendRedirect("login");
						}

						//3. 校验密码是否过期
						String currentPassword = md5Encoder.encodePassword(userModel.getPassword(), name);
						if (StringUtils.equals(currentPassword, passwordToken))
						{
							session.setAttribute("userModel", userModel);
						}
						else
						{
							hasLogin = false;
						}
					}
					String[] str = token.split("\\(\\$\\)");
					String name = str[0];
					String password = str[1];
					WebApplicationContext ctx = WebApplicationContextUtils
							.getRequiredWebApplicationContext(req.getServletContext());
					UserService userService = ctx.getBean(UserService.class);
					userModel = userService.queryUserByNameAndPassword(name, password);
				}

				if (userModel == null) {
					hasLogin = false;
				} else {
					session.setAttribute("userModel", userModel);
				}
			}
		}

		if (hasLogin) {
			chain.doFilter(req, resp);
		} else {
			resp.sendRedirect("login");
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
