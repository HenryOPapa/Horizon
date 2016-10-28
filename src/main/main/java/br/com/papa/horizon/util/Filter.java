
package br.com.papa.horizon.util;

/**
 * Author: Henry Papa
 */

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter("/Filter")
public class Filter implements javax.servlet.Filter {
	private static final String JSP = ".jsp";

    /**
     * Default constructor. 
     */
    public Filter() {
    }


	public void destroy() {
		
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		
		HttpServletRequest rq = (HttpServletRequest) request;
		HttpServletResponse rs = (HttpServletResponse) response;
		HttpSession session = rq.getSession();
		
		if (session.getAttribute("usuario") != null){
			chain.doFilter(request, response);
		}else{
			rs.sendRedirect("login.jsp");
		}
		
		
	}
	
	

	/**
	 * @see FilterAdmin#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
	
