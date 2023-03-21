package filtersOfAdmin;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

import org.json.JSONObject;

import fullTimeUse.ConstantVariables;

/**
 * Servlet Filter implementation class IncreaseProductCountFilter
 */
@WebFilter(filterName = "IncreaseProductCountFilter", urlPatterns = {"/admin/IncreaseProductCount"})
public class IncreaseProductCountFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public IncreaseProductCountFilter() {
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
			String product = request.getParameter("productName");
			String category = request.getParameter("category");
			int quantity = Integer.valueOf(request.getParameter("quantity"));
			int id = 0;
			
			if(!product.equals("")) {
			PreparedStatement ppst = ConstantVariables.dbConnection.prepareStatement("select categoryId from Categories where category = ?");
			ppst.setString(1, category);
			ResultSet rs = ppst.executeQuery();
			if(rs.next()) {
				id = rs.getInt(1);
			}
			
				ppst = ConstantVariables.dbConnection.prepareStatement("select productName from Products where productName = ? and categoryId = ?");
				ppst.setString(1, product);
				ppst.setInt(2, id);
				ResultSet rs1 = ppst.executeQuery();
	    	
	    	
	    	if(rs1.next()) {
	    		if(quantity > 0 && quantity < 201) {
	    			chain.doFilter(request, response);
	    		}
	    		else {
	    			jsonObject.put("statusCode", 101);
	    			jsonObject.put("message", "You can increase, minimum 1 product and maximum 200 products in our shop");
	    			response.getWriter().append(jsonObject.toString());
	    		}
	    	}
	    	else {
	    		jsonObject.put("statusCode", 102);
    			jsonObject.put("message", "We don't have that product");
    			response.getWriter().append(jsonObject.toString());
	    	}
			}
			else {
				jsonObject.put("statusCode", 400);
    			jsonObject.put("message", "Please fill the product name");
    			response.getWriter().append(jsonObject.toString());
			}
			}
		catch(NumberFormatException ex) {
			jsonObject.put("statusCode", 400);
			jsonObject.put("message", "Please fill the required fields with valid values");
			response.getWriter().append(jsonObject.toString());
		}
		catch(Exception ex){
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