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

import org.json.JSONObject;

import fullTimeUse.ConstantVariables;

/**
 * Servlet Filter implementation class AuthorizationFilter
 */
//@WebFilter(filterName = "AutorizationFilter", urlPatterns = {"/admin/AddAProduct", "/admin/EditTheProfileOfAdmin"})
public class AuthorizationFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public AuthorizationFilter() {
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
	@SuppressWarnings("unlikely-arg-type")
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		JSONObject jsonObject = new JSONObject();
		boolean check = false;
		try {
			PreparedStatement ppst = ConstantVariables.dbConnection.prepareStatement("select * from sessions");
			ResultSet rs = ppst.executeQuery();
			HttpServletRequest req = (HttpServletRequest) request;
			Cookie[] cookies = req.getCookies();
			while(rs.next()) {
			for(int i = 0; i < cookies.length; i++) {
				if(cookies[i].getName().equals("ROLE")) {
					ppst = ConstantVariables.dbConnection.prepareStatement("select role from LoginUsers where mobileNumber = ?");
					ppst.setString(1, rs.getString(2));
					ResultSet rs2 = ppst.executeQuery();
					if(rs2.next()) {
					if(rs2.getString(1).equals(cookies[i].getValue())) {
						check = true;
						chain.doFilter(request, response);
						break;
				}
			}
				}
			}
			if(!check) {
				jsonObject.put("statusCode", 400);
				jsonObject.put("message", "You are not an logged in user");
				response.getWriter().append(jsonObject.toString());
			}
		}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
			jsonObject.put("statusCode", 400);
			jsonObject.put("message", "Some error occurred");
			response.getWriter().append(jsonObject.toString());
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
