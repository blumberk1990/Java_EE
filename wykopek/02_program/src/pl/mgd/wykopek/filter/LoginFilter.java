package pl.mgd.wykopek.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import pl.mgd.wykopek.model.User;
import pl.mgd.wykopek.service.UserService;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		if(httpReq.getUserPrincipal() != null && httpReq.getSession().getAttribute("user") == null) {
			saveUserInSession(httpReq);
		}
		chain.doFilter(request, response);
	}

	private void saveUserInSession(HttpServletRequest request) {
		UserService userService = new UserService();
		String username = request.getUserPrincipal().getName();
		User userByUsername = userService.getUserByUsername(username);
		request.getSession().setAttribute("user", userByUsername);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}
	
	public void destroy() {

	}

}
