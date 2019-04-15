package ar.edu.uces.progweb2.springmvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object arg2) throws Exception {
		
		if (!req.getRequestURI().contains("home") && !req.getRequestURI().contains("login.htm")&& !req.getRequestURI().contains("search.htm") && !req.getRequestURI().contains("/ver/")&& !req.getRequestURI().contains("userHome.htm") && req.getSession().getAttribute("user") == null) {
			req.getRequestDispatcher("login.htm").forward(req, res);
			return false;
		}

		return true;
	}

}

