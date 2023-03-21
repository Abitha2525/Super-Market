package filtersOfAdmin;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;

import fullTimeUse.ConstantVariables;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
//@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/admin/*"})
@SuppressWarnings("serial")
public class AuthenticationFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public AuthenticationFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
         HttpServletRequest req = (HttpServletRequest)request;
		Cookie[] cookies = req.getCookies();
		String sessionId = null;
		JSONObject jsonObject = new JSONObject();
		boolean check = false;
		for(int i = 0; i < cookies.length; i++) {
			if(cookies[i].getName().equals("SESSIONID")) {
				sessionId = cookies[i].getValue();
				break;
			}
		}
		try {
		PreparedStatement ppst = ConstantVariables.dbConnection.prepareStatement("select * from sessions");
		ResultSet rs = ppst.executeQuery();
		while(rs.next()) {
			if(rs.getString(1).equals(sessionId)) {
				check = true;
				chain.doFilter(request, response);
				break;
			}
		}
		if(!check) {
			jsonObject.put("statusCode", 400);
			jsonObject.put("message", "You didn't have any access");
			response.getWriter().append(jsonObject.toString());
		}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
			jsonObject.put("statusCode", 400);
			jsonObject.put("message", "Some error occurred");
			response.getWriter().append(jsonObject.toString());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
