package filtersOfAdmin;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
 * Servlet Filter implementation class AddAProductFilter
 */
@WebFilter(filterName = "AddAProductFilter", urlPatterns = {"/admin/AddAProduct"})
public class AddAProductFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public AddAProductFilter() {
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
	    	
	    	 String category = request.getParameter("category");
	 		String productName = request.getParameter("product");
	 	    int count = Integer.valueOf(request.getParameter("count"));
	 	    double price = Double.valueOf(request.getParameter("price"));
	 	    String url = request.getParameter("url");
	 	    boolean check = false;
	 	    	 	    
	    	PreparedStatement ppst = ConstantVariables.dbConnection.prepareStatement("select productName, status from Products where productName = ?");
	    	ppst.setString(1, productName);
	    	ResultSet rs = ppst.executeQuery();
	    	while(rs.next()) {
	    		if(rs.getString(2).equals("Deleted")) {
	    			check = false;
	    		}
	    		else {
	    		check = true;
	    		jsonObject.put("statusCode", 103);
				jsonObject.put("message", "This product is already in our shop");
				response.getWriter().append(jsonObject.toString());
				break;
	    		}
	    	}
	    	if(!check) {
	    		ppst = ConstantVariables.dbConnection.prepareStatement("select urlOfProducts from Products where urlOfProducts = ?");
	    		ppst.setString(1, url);
	    		ResultSet rs2 = ppst.executeQuery();
	    		if(!rs2.next()) {
	    		if(!productName.equals("")) {
	    			if(count > 0 && count < 201) {
		    			if(price > 0) {
		    				chain.doFilter(request, response);
		    			}
		    			else {
			    			jsonObject.put("statusCode", 100);
							jsonObject.put("message", "Price is always greater than 0 rupees");
							response.getWriter().append(jsonObject.toString());
			    			}
	    			}
	    			else {
		    			jsonObject.put("statusCode", 102);
						jsonObject.put("message", "You can add, minimum 1 product and maximum 200 products in our shop");
						response.getWriter().append(jsonObject.toString());
		    		}
	    		}
	    		else {
		    		jsonObject.put("statusCode", 400);
					jsonObject.put("message", "Please fill the product name");
					response.getWriter().append(jsonObject.toString());
		    	}
	    		}
	    		else {
	    			jsonObject.put("statusCode", 400);
					jsonObject.put("message", "Please enter an unique url");
					response.getWriter().append(jsonObject.toString());
	    		}
	    		
	    	}
	    }
	    catch(NumberFormatException ex) {
	    	jsonObject.put("statusCode", 400);
			jsonObject.put("message", "Please fill the required fields with valid values");
			response.getWriter().append(jsonObject.toString());
	    }
	    catch(SQLException ex) {
	    	ex.printStackTrace();
	    	jsonObject.put("statusCode", 400);
			jsonObject.put("message", "URL is too long");
			response.getWriter().append(jsonObject.toString());
	    }
	    catch(Exception ex) {
	    	ex.printStackTrace();
	    	jsonObject.put("statusCode", 400);
			jsonObject.put("message", "Some error occured");
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
