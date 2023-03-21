package filtersOfUser;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

import org.json.JSONObject;

import fullTimeUse.ConstantVariables;

/**
 * Servlet Filter implementation class CheckAreYouNotLogOut
 */
//@WebFilter( filterName = "CheckAreYouNotLogOut", urlPatterns = {"/User"})
public class CheckAreYouNotLogOut extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public CheckAreYouNotLogOut() {
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
		
		JSONObject jsonObject = new JSONObject();
		try {
		HttpServletRequest req = (HttpServletRequest) request;
		String sessionId = "";
		String mobile = "";
		String role = "";
		
		Cookie[] cookies = req.getCookies();
		for(int i = 0; i < cookies.length; i++) {
			if(cookies[i].getName().equals("SESSIONID")) {
				sessionId = cookies[i].getValue();
				break;
			}
		}
		
		if(!sessionId.equals("")) {
			PreparedStatement ppst = ConstantVariables.dbConnection.prepareStatement("select mobileNumber from sessions where sessionId = ?");
			ppst.setString(1, sessionId);
			ResultSet rs = ppst.executeQuery();
			if(rs.next()) {
				mobile = rs.getString(1);
			}
			
			if(!mobile.equals("")) {
              ppst = ConstantVariables.dbConnection.prepareStatement("select role from LoginUsers where mobileNumber = ?");
              ppst.setString(1, mobile);
              ResultSet rs2 = ppst.executeQuery();
              if(rs2.next()){
            	  role = rs2.getString(1);
            	  }
              }
			else {
				jsonObject.put("statusCode", 200);
				response.getWriter().append(jsonObject.toString());
			}
		}
		else {
			jsonObject.put("statusCode", 200);
			chain.doFilter(request, response);
		}
		
		if(role.equals("Admin")) {
			jsonObject.put("statusCode", 300);
			response.getWriter().append(jsonObject.toString());
		}
		else if(role.equals("User")) {
			jsonObject.put("statusCode", 400);
			response.getWriter().append(jsonObject.toString());
		}
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
