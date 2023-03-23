package filtersOfUser;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import fullTimeUse.ConstantVariables;

/**
 * Servlet Filter implementation class SignInFilter
 */
@WebFilter(filterName = "SignInFilter", urlPatterns = {"/User"})
public class SignInFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public SignInFilter() {
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
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		Cookie[] cookies = req.getCookies();
		JSONObject jsonObject = new JSONObject();
		
		try {
			PreparedStatement ppst = ConstantVariables.dbConnection.prepareStatement("select sessionId, mobileNumber from sessions");
			ResultSet rs = ppst.executeQuery();
			boolean check = false;
			
			while(rs.next()) {
				
				ppst = ConstantVariables.dbConnection.prepareStatement("select role from LoginUsers where mobileNumber = ?");
				ppst.setString(1, rs.getString(2));
				ResultSet rs2 = ppst.executeQuery();
			
				if(rs2.next()) {
				for(int i = 0; i < cookies.length; i++) {
					if(cookies[i].getName().equals("SESSIONID")) {
						if(rs.getString(1).equals(cookies[i].getValue()) && rs2.getString(1).equals("User")) {
							check = true;
							jsonObject.put("statusCode", 1000);
							break;
						}
						else if(rs.getString(1).equals(cookies[i].getValue()) && rs2.getString(1).equals("Admin")) {
							check = true;
							jsonObject.put("statusCode", 1001);
							break;
						}
					}
				}
			}
				if(check) {
				   response.getWriter().append(jsonObject.toString());
				}
			}
			if(!check) {
				chain.doFilter(request, response);			}
			
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
